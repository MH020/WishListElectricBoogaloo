package org.example.wishlistelectricboogaloo.Repository;
import org.example.wishlistelectricboogaloo.ConnectionManager;
import java.sql.Connection;

@org.springframework.stereotype.Repository
public class Repository {

    private final Connection conn;

    public Repository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }
}
