package org.example.wishlistelectricboogaloo.Repository;

import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

@Repository
public class ProductRepository {
    private Connection conn;

    public ProductRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }
}
