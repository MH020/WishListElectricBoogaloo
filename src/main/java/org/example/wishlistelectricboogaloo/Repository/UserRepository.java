package org.example.wishlistelectricboogaloo.Repository;
import org.example.wishlistelectricboogaloo.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    //authenticate method
    public boolean authenticateUser(String Username, String Password) {
        String SQLcheck = "SELECT * FROM User WHERE Username = ? AND Password = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQLcheck)) {
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);

            //sees if there is a resultSet in the database that matches the username and password entered by the user and returns true if there is
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


}
