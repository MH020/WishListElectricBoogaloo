package org.example.wishlistelectricboogaloo.Repository;

import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Model.Market;
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
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM Product WHERE city = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Market.getMarketId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();
            while (resultSet.next()) {
                Product product = new Product();
                Product.setProductId(resultSet.getInt("id"));
                Product.setProductName(resultSet.getString("name"));
                Product.setProductPrice(resultSet.getDouble("price"));
                Market.setCity(resultSet.getString("city"));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}