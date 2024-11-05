package org.example.wishlistelectricboogaloo.Repository;
import org.example.wishlistelectricboogaloo.ConnectionManager;
import java.sql.Connection;

@org.springframework.stereotype.Repository
public class UserRepository {

    private final Connection conn;

    public UserRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }
    //create method
    public void saveUser(){
    }
    // Read Method
    public void readUser() {
    }
    // Update Method
    public void updateUser(){
    }
    //delete Method
    public void deleteUser() {
    }

}
