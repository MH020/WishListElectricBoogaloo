package org.example.wishlistelectricboogaloo.Repository;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishlistRepository {
    private final Connection conn;

    public WishlistRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //create a wishlist
    public int addWishlist(Wishlist wishlist,int profile_id) {
        String SQLInsertWishlist = "insert into wishlist (wishlist_name,profile_id) values(?,?)";


        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLInsertWishlist, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, wishlist.getName());
            preparedStatement.setInt(2, profile_id);
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
        String SQlDeleteFromJoinedTable ="DELETE FROM ProductWishlist where wishlist_id = ?";

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


    // Get all wishlists
    public List<Wishlist> getAllWishLists(int profile_id) {
        String SQLReadFromWishlist = "SELECT * From Wishlist where profile_id = ?";
        List<Wishlist> allWishList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLReadFromWishlist);
            preparedStatement.setInt(1, profile_id);
            ResultSet resultset = preparedStatement.executeQuery();
            while(resultset.next()) {
               Wishlist wishlist = new Wishlist(profile_id);
               //add the get name method to the wishlist
               wishlist.setName(resultset.getString("wishlist_name"));
                wishlist.setWishlist_id(resultset.getInt("wishlist_id"));
               allWishList.add(wishlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allWishList ;
        //Der skal laves en HTML til AllWishlists metode
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

                wishlist.setWishlist_id(resultSet.getInt("wishlist_id"));
                wishlist.setName(resultSet.getString("wishlist_name"));

                wishlist.setProfileId(resultSet.getInt("profile_id"));

                wishlist.setProducts(getAllProductsBelongingToWishlist(wishlistID));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return wishlist;
    }

    public List<Product> getAllProductsBelongingToWishlist(int wishlistID){
        List <Integer> productIDs = getProductIDfromJoinTable(wishlistID);
        List<Product> productList = new ArrayList<>();

        String SQLgetproductsfromProductTable = "SELECT * FROM product WHERE product_id = ?";

        try{
            PreparedStatement preparedStatement = conn.prepareStatement(SQLgetproductsfromProductTable);

            for (Integer productID : productIDs){
                preparedStatement.setInt(1, productID);
                ResultSet resultSet = preparedStatement.executeQuery();

                //adding product to productlist:
                while(resultSet.next()){
                    String productName = resultSet.getString("product_name");
                    String productDesc = resultSet.getString("product_description");
                    double productPrice = resultSet.getDouble("product_price");
                    productList.add(new Product(productID,productName,productDesc,productPrice));
                }
            }//end of for each loop
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productList;
    }

    public List<Integer> getProductIDfromJoinTable(int wishlistID){
        String SQLproductjoinwishlist = "SELECT * FROM ProductWishlist WHERE wishlist_id = ?";

        List <Integer> productIDs = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SQLproductjoinwishlist);
            preparedStatement.setInt(1, wishlistID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                productIDs.add(resultSet.getInt("product_id"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productIDs;
    }
    public boolean updateWishlistAddProduct(int product_id, int wishlist_id) {

        String sql = "INSERT INTO wishlistProduct (wishlist_id, product_id) VALUES (?, ?)";
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
        String sql = "SELECT FROM wishlist WHERE wishlist_name LIKE = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            // the % is a wildcard that allows us to search for a string that contains the search string
            preparedStatement.setString(1,"%" + search+ "%");
            //resultset is the result of the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    results.add(resultSet.getString("wishlist_name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}