package repository;

import config.ConnectionDatabase;
import model.Contatto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactsRepositoryImpl implements ContactsRepository {

    @Override
    public List<Contatto> getRubrica() {
        Connection connection = ConnectionDatabase.getConnection();
        List<Contatto> contacts = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * from contatti");
            ResultSet resultset = statement.executeQuery();

            while (resultset.next()) {
                Contatto contact = new Contatto();
                contact.setIdutente(resultset.getInt(1));
                contact.setNome(resultset.getString(2));
                contact.setCognome(resultset.getString(3));
                contact.setNumero(resultset.getString(4));

                contacts.add(contact);
            }
            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }

    @Override
    public void save(Contatto contatto) {
        Connection connection = ConnectionDatabase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contatti VALUES(null, ?, ?, ?, ?)");
            statement.setString(1, contatto.getNome());
            statement.setString(2, contatto.getCognome());
            statement.setString(3, contatto.getNumero());
            statement.setInt(4,contatto.getIdutente());

            statement.executeUpdate();
            statement.close();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void modify(Contatto contatto) {
        Connection connection = ConnectionDatabase.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE rubrica.contatti SET nome = ?, cognome = ?, numero = ?, idutente = ? WHERE (idcontatto = ?)");
            statement.setString(1,contatto.getNome());
            statement.setString(2, contatto.getCognome());
            statement.setString(3, contatto.getNumero());
            statement.setInt(4, contatto.getIdutente());
            statement.setInt(5, contatto.getIdcontatto());

            statement.executeUpdate();
            statement.close();

            connection.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = ConnectionDatabase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM contatti WHERE idcontatto = ? ");
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
