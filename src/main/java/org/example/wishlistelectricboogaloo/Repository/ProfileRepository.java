package org.example.wishlistelectricboogaloo.Repository;
import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.Model.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@org.springframework.stereotype.Repository
public class ProfileRepository {

    private final Connection conn;

    public ProfileRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }
    //create method
    public int saveUser(Profile profile) {
        String Sql = "INSERT INTO Profile (username, password,profile_email,profile_phone) VALUES (?,?,?,?)";
    try (PreparedStatement preparedStatement = conn.prepareStatement(Sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setString(1, profile.getUsername());
        preparedStatement.setString(2, profile.getPassword());
        preparedStatement.setString(3, profile.getEmail());
        preparedStatement.setString(4, profile.getPhoneNumber());
        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return 0;
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
    public Profile authenticateProfile(String Username, String Password) {
        String SQLcheck = "SELECT * FROM profile WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQLcheck, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Password);

            //sees if there is a resultSet in the database that matches the username and password entered by the user and returns true if there is
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("profile_id"));
                profile.setUsername(resultSet.getString("username"));
                profile.setPassword(resultSet.getString("password"));
                profile.setEmail(resultSet.getString("profile_email"));
                profile.setPhoneNumber(resultSet.getString("profile_phone"));
                return profile;
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return null;
    }



}