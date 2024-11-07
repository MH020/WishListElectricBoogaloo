package org.example.wishlistelectricboogaloo.Service;

import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Repository.MarketRepository;
import org.example.wishlistelectricboogaloo.Repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class ProfileService {
        private final ProfileRepository profileRepository;
        private final MarketRepository marketRepository;

        public ProfileService(ProfileRepository profileRepository, MarketRepository marketRepository) {
            this.profileRepository = profileRepository;
            this.marketRepository = marketRepository;
        }

        //show local market
        public List<Product> getAllProducts(int marketId) {
            return marketRepository.getAllProducts(marketId);
        }
    }