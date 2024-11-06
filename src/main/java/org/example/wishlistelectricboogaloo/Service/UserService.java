package org.example.wishlistelectricboogaloo.Service;

import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Repository.MarketRepository;
import org.example.wishlistelectricboogaloo.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MarketRepository marketRepository;

    public UserService(UserRepository userRepository, MarketRepository marketRepository) {
        this.userRepository = userRepository;
        this.marketRepository = marketRepository;
    }

    //show local market
    public List<Product> getAllProducts(int marketId) {
        return marketRepository.getAllProducts(marketId);
    }
}
