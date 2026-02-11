package org.example.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    private static Properties properties = new Properties();
    static {
        try( InputStream inputStream = DatabaseConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
           {
               properties.load(inputStream);
           };
        } catch (Exception e) {
            throw new RuntimeException("Cannot load db properties" , e);
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password")
        );
    }

}

