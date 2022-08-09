package com.company.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtility {

    public static Connection getConnection() {
        String url, username, password;
        url = "jdbc:postgresql://127.0.0.1:5432/practical-workshop";
        username = "postgres";
        password = "ResidentEvil6";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}
