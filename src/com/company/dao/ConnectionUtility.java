package com.company.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtility {

    public static Connection getConnection() {
        String url, username, password;
        url = "jdbc:postgresql://127.0.0.1:5432/teacher";
        username = "postgres";
        password = "ResidentEvil6";
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}
