package org.example;

import org.example.config.DatabaseConfig;

import java.sql.Connection;

public class Main {
    static void main() {
        try (Connection con = DatabaseConfig.getConnection()){
            System.out.println("Khchiiiiii");
        } catch (Exception e) {
            System.out.println("TFooooo");
            e.printStackTrace();
        }
    }
}
