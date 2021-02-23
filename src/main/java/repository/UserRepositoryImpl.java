package repository;

import config.ConnectionDatabase;
import model.Contatto;
import model.Ruolo;
import model.User;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    static RuoloRepository ruoloRepository = new RuoloRepositoryImpl();

    public UserRepositoryImpl() throws IOException {
    }

    @Override
    public User login(String email, String password) throws IOException { // restituire user con i suoi ruoli settare i ruoli
        Connection connection = ConnectionDatabase.getConnection();
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? and password = ?");
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {//.next è un cursore della tabella dei dati che si sposta sempre alla riga dopo
                user = new User();
                user.setId(resultSet.getInt(1));
                user.setNome(resultSet.getString(2));
                user.setCognome(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.setRuoli(getRuoloByUser(resultSet.getInt(1)));
                user.setRubrica(getContattoByUser(resultSet.getInt(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Ruolo> getRuoloByUser(int id) throws SQLException {
        Connection connection = ConnectionDatabase.getConnection();
        List<Ruolo> ruoli = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(
                "SELECT u.id,r.descrizione,r.idruolo from users u, user_as_role ur, ruoli r where u.id = ? && u.id = ur.idutente && r.idruolo = ur.idruolo;");

        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Ruolo ruolo = new Ruolo();
            ruolo.setIdruolo(resultSet.getInt(3));
            ruolo.setDescrizione(resultSet.getString(2));

            ruoli.add(ruolo);
            System.out.println(ruolo.getDescrizione());
        }
        return ruoli;
    }

    @Override
    public List<Contatto> getContattoByUser(int id) {
        Connection connection = ConnectionDatabase.getConnection();
        List<Contatto> contatti = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT c.idutente, c.idcontatto,c.nome, c.cognome, c.numero from contatti c where c.idutente=?;");

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Contatto contatto = new Contatto();
                contatto.setIdutente(resultSet.getInt(1));
                contatto.setIdcontatto(resultSet.getInt(2));
                contatto.setNome(resultSet.getString(3));
                contatto.setCognome(resultSet.getString(4));
                contatto.setNumero(resultSet.getString(5));

                contatti.add(contatto);
            }
            connection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return contatti;
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        Connection conn = ConnectionDatabase.getConnection();

        try {
            PreparedStatement statement = conn.prepareStatement("DELETE FROM user_as_role WHERE ID_UTENTE = ?");
            statement.setInt(1, id);

            PreparedStatement statement1 = conn.prepareCall("DELETE FROM contatto WHERE ID_UTENTE = ?");
            statement1.setInt(1, id);

            PreparedStatement statement2 = conn.prepareStatement("DELETE FROM utenti WHERE ID_UTENTE = ?");
            statement2.setInt(1, id);

            statement.executeUpdate();
            statement1.executeUpdate();
            statement2.executeUpdate();

            statement.close();
            statement1.close();
            statement2.close();

            conn.close();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        Connection connection = ConnectionDatabase.getConnection();
        List<User> users = new ArrayList<>();
        //if ruolo è questo allora fammi vedere bla bla
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getInt(1));
                user.setNome(resultSet.getString(2));
                user.setCognome(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));

                List<Ruolo> ruoli = this.getRuoloByUser(user.getId());
                user.setRuoli(ruoli);
                List<Contatto> contatti = this.getContattoByUser(user.getId());
                user.setRubrica(contatti);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User user) throws IOException { //come ruolo di default user
        Connection connection = ConnectionDatabase.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users VALUES (null, ?,?,?,?)");
            statement.setString(1,user.getNome());
            statement.setString(2, user.getCognome());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();

            User us = this.login(user.getEmail(),user.getPassword());

            Ruolo ruolo = ruoloRepository.getDefaultRuolo("user");

            PreparedStatement statement2 = connection.prepareStatement("INSERT INTO user_as_role VALUES (?,?)");
            statement2.setInt(1, us.getId());
            statement2.setInt(2,ruolo.getIdruolo());

            statement2.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
