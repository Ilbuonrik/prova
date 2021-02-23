package repository;

import config.ConnectionDatabase;
import model.Ruolo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RuoloRepositoryImpl implements RuoloRepository {

    @Override
    public List<Ruolo> getRoles() {
        Connection connection = ConnectionDatabase.getConnection();
        List<Ruolo> ruoli = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ruoli");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Ruolo ruolo = new Ruolo();

                ruolo.setDescrizione(resultSet.getString(1));
                ruoli.add(ruolo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ruoli;
    }

    @Override
    public void saveRoles(Ruolo ruolo) {
        Connection connection = ConnectionDatabase.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO ruoli VALUES (null, ?)");
            statement.setString(1, ruolo.getDescrizione());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRoles(Ruolo ruolo) {
        Connection connection = ConnectionDatabase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE  SET descrizione =? WHERE id = ?");
            statement.setString(1, ruolo.getDescrizione());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoles(int id) {
        Connection connection = ConnectionDatabase.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM ruoli WHERE id = ? ");
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Ruolo getDefaultRuolo(String desc) {//prende il ruolo e gli dà la descrizione assegnata
        Connection connection = ConnectionDatabase.getConnection();
        Ruolo ruolo = null;

       try{
           PreparedStatement statement = connection.prepareStatement("SELECT * FROM ruoli WHERE descrizione =?");
           statement.setString(1,desc);

           ResultSet rs = statement.executeQuery();

           while(rs.next()) {
               ruolo = new Ruolo();
               ruolo.setIdruolo(rs.getInt(1));
               ruolo.setDescrizione(rs.getString(2));

           }
       } catch (SQLException e) {
           e.printStackTrace();
       }

        return ruolo;
    } // implementa
}
