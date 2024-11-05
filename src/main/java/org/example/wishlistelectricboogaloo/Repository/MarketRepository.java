package org.example.wishlistelectricboogaloo.Repository;

import org.example.wishlistelectricboogaloo.Model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MarketRepository {

    //show market that user is connected to by marketId
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM Product WHERE city = ?";

    }
}