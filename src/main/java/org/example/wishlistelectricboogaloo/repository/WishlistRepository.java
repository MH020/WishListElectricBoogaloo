package org.example.wishlistelectricboogaloo.repository;

import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.model.Wishlist;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {
    private final Connection conn;

    public WishlistRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //create a wishlist
    public int createWishlist(Wishlist wishlist) {
        String SQLInsertWishlist = "insert into wishlist (name,profileid) values(?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLInsertWishlist, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wishlist.getName());
            preparedStatement.setInt(2, wishlist.getProfileId());
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
    public void deleteWishlist(int id){
        int updatedRows = 0;
        String SQlDeleteFromWishlist = "Delete from Wishlist where id = ?";
        String SQlDeleteFromJoinedTable ="DELETE FROM Joined wishlist and products where id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQlDeleteFromWishlist)) {
            preparedStatement.setInt(1, id);
            updatedRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQlDeleteFromJoinedTable)) {
            preparedStatement.setInt(1, id);
            updatedRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Wishlist> getAllWishLists(int profileId) {
        String SQLReadFromWishlist = "SELECT * From Wishlist where profile_ID = ?";
        List<Wishlist> allWishList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLReadFromWishlist);
            preparedStatement.setInt(1, profileId);
            ResultSet resultset = preparedStatement.executeQuery();
            while(resultset.next()){
                int profile_id = resultset.getInt("profile_id");
                String name = resultset.getString("name");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allWishList ;

        }




    }


