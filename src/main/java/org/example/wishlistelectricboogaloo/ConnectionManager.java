package org.example.wishlistelectricboogaloo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionManager {

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private Connection conn;

    public Connection getConnection() {
        // If a connection is already established, return it
        if (conn != null) {
            return conn;
        }
        // If no connection is established, establish a new connection
        try {
                conn = DriverManager.getConnection(databaseUrl, username, password);
                System.out.println("Connected to a database");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database");
        }

        return conn;
    }
}
