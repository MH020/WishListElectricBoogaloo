package org.example.wishlistelectricboogaloo.Repository;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class WishlistRepository {
    private final Connection conn;

    public WishlistRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //create a wishlist
    public int createWishlist(Wishlist wishlist,int id) {
        String SQLInsertWishlist = "insert into wishlist (name,profileid) values(?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLInsertWishlist, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wishlist.getName());
            preparedStatement.setInt(2, id);
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

    public Wishlist getWishlist(int profileID, int wishlistID){
        String SQLGetwishlist = "SELECT * FROM wishlist WHERE profile_id = ? AND wishlist_id = ?";
        Wishlist wishlist = new Wishlist();

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(SQLGetwishlist);
            preparedStatement.setInt(1, profileID);
            preparedStatement.setInt(2, wishlistID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                wishlist.setId(resultSet.getInt("wishlist_id"));
                wishlist.setName(resultSet.getString("name"));
                wishlist.setProfileId(resultSet.getInt("profile_id"));

            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return wishlist;
    }
}