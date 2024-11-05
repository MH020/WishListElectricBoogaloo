package org.example.wishlistelectricboogaloo.Repository;

import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class WishlistRepository {
    private final Connection conn;

    public WishlistRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //create a wishlist
    public int createWishlist(String name, int profileId) {
        String SQLInsertWishlist = "insert into wishlist (name) values(?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLInsertWishlist, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, profileId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    }

