package com.company.data.dao;

import org.postgresql.Driver;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Logger;

public class ConnectionUtility {
    Logger logger = Logger.getLogger(ConnectionUtility.class.getName());

    public static Connection getConnection() {
        try {
            InputStream inputStream = ConnectionUtility.class.getClassLoader().getResourceAsStream("META-INF/db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            Class.forName(properties.getProperty("db.driver"));
            Connection connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"));
            connection.setAutoCommit(false);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}
//    public static Connection getConnection() {
//        String url, username, password;
//        url = "jdbc:postgresql://127.0.0.1:5432/practical-workshop";
//        username = "postgres";
//        password = "ResidentEvil6";
//        try {
//            Class.forName("org.postgresql.Driver");
//            Connection connection = DriverManager.getConnection(url, username, password);
//            connection.setAutoCommit(false);
//            return connection;
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(0);
//            return null;
//        }
//    }
