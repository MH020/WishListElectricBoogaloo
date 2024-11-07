package org.example.wishlistelectricboogaloo.Repository;

import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.Model.Product;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private final Connection conn;

    public ProductRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //show market that user is connected to by marketId
    public List<Product> getAllProducts(int marketId) {
        String sql = "select product_id, product_name, product_description, product_price, market_id from Product where market_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, marketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt("product_id");
                String name = resultSet.getString("product_name");
                String description = resultSet.getString("product_description");
                Double price = resultSet.getDouble("product_price");
                products.add(new Product(id, name, description, price));
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMarketByProfileID(int profileId) {
        String sql = "select m.city from Market m " + //making use of aliases
                "join Joined_Profile_Market jpm on m.market_id = jpm.market_id " +
                "where jpm.profile_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, profileId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("city");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}