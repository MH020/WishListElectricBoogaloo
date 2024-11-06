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
public class MarketRepository {
    private final Connection conn;

    public MarketRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //show market that user is connected to by marketId
    public List<Product> getAllProducts(int marketId) {
        String sql = "SELECT * FROM Product WHERE marketId = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("name"));
                product.setProductPrice(resultSet.getDouble("price"));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}