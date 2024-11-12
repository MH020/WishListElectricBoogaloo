package org.example.wishlistelectricboogaloo.Repository;

import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.example.wishlistelectricboogaloo.Model.Market;
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
    public List<Product> getAllProducts(int market_id) {
        String sql = "select product_id, product_name, product_description,product_price, market_id from Product where market_id = ?";

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, market_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String productDescription = resultSet.getString("product_description");
                Double productPrice = resultSet.getDouble("product_price");
                products.add(new Product(productId, productName, productDescription, productPrice));
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //show market that user is connected to by profileId
    public int getMarketByProfileID(int profile_id) {
        String sql = "select market_id from ProfileMarket where profile_id = ?";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, profile_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("market_id: " + resultSet.getInt("market_id"));
                return resultSet.getInt("market_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //read Markets hjemmelavet metode
    public List<Market> getAllMarkets() {
        List <Market> markets = new ArrayList<>();
        String Sql = "SELECT * FROM Market";
        try (PreparedStatement preparedStatement = conn.prepareStatement(Sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int market_id = resultSet.getInt("market_id");
                String city = resultSet.getString("market_city");
                markets.add(new Market(market_id, city));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return markets;
    }
    //add market to user hjemmelavet metode
    public void addMarketToUser(int profile_id, int market_id) {
        String sql = "INSERT INTO ProfileMarket (profile_id, market_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, profile_id);
            preparedStatement.setInt(2, market_id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}