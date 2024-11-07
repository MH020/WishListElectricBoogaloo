package org.example.wishlistelectricboogaloo.Repository;

import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final Connection conn;

    public ProductRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //show market that user is connected to by marketId
    public List<Product> getAllProducts(int marketId) {
        String sql = "SELECT product_id, product_name, product_description, product_price, market_id FROM Product WHERE market_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, marketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                //Product product = new Product(id, name, description, price);
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
}