package com.example.mangastoreservidor.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static java.sql.Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantedb" , "root", "fera7eric");
        } catch (SQLException var1) {
            throw new RuntimeException(var1);
        }
    }
}
