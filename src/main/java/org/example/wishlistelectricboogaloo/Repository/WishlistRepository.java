package org.example.wishlistelectricboogaloo.Repository;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.Model.Wishlist;
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
    public int addWishlist(Wishlist wishlist,int wishlist_id) {
        String SQLInsertWishlist = "insert into wishlist (name,profile_id) values(?,?)";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLInsertWishlist, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wishlist.getName());
            preparedStatement.setInt(2, wishlist_id);
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
    // delete a wishlist
    public void deleteWishlist(int wishlist_id){
        int updatedRows = 0;
        String SQlDeleteFromWishlist = "Delete from Wishlist where wishlist_id = ?";
        String SQlDeleteFromJoinedTable ="DELETE FROM Joined wishlist and products where id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQlDeleteFromWishlist)) {
            preparedStatement.setInt(1, wishlist_id);
            updatedRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(SQlDeleteFromJoinedTable)) {
            preparedStatement.setInt(1, wishlist_id);
            updatedRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean updateWishlistAddProduct(int product_id, int wishlist_id) {


        String sql = "INSERT INTO Joined_Wishlist_And_Products (wishlist_id, product_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, wishlist_id);
            preparedStatement.setInt(2, product_id);
            int rowsEffected = preparedStatement.executeUpdate();
            return rowsEffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<String> searchforWishlist(String search){
        List<String> results = new ArrayList<>();
        String sql = "SELECT FROM wishlist WHERE name LIKE = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            // the % is a wildcard that allows us to search for a string that contains the search string
            preparedStatement.setString(1,"%" + search+ "%");
            //resultset is the result of the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(resultSet.getString("name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

}