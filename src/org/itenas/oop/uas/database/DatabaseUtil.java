package org.itenas.oop.uas.database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    String url = "jdbc:mysql://localhost:3306/perpustakaan_oop";
    String username = "root";
    String password = "P@ssw0rd.01";
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public void connect() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
            statement.close();
            resultSet.close();
        } catch (Exception e) {
            System.out.println("Terjadi error: " + e.getMessage());
        }
    }
    
    public void disconnect(int x) {
        try {
            connection.close();
            statement.close();
        } catch (Exception e) {
            System.out.println("Terjadi error: " + e.getMessage());
        }
    }

    public ResultSet readData(String query) {
        try {

            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);

            return resultSet;

        } catch (SQLException ex) {
            System.out.println("Terjadi error: " + ex.getMessage());
        }

        return resultSet;
    }

    public void executeQuery(String query) {
        try {
            statement = connection.createStatement();

            statement.executeUpdate(query);

        } catch (SQLException ex) {
            System.out.println("Terjadi error: " + ex.getMessage());
        }
    }
}
