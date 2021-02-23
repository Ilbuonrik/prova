package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDatabase {
    public static Connection getConnection(){
        // registro un driver jdbc
        Connection connection = null;
        Properties properties =null;

        try {
            properties = new Config().getConfig();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        try {
            Class.forName(properties.getProperty("jdbc_Driver"));
            //apro la connessione
            System.out.println("Connection to database...");

            connection = DriverManager.getConnection(properties.getProperty("db_url"), properties.getProperty("user"),
            properties.getProperty("password"));

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
