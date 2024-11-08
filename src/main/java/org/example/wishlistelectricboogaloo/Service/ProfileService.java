package org.example.wishlistelectricboogaloo.Service;

import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Model.Profile;
import org.example.wishlistelectricboogaloo.Repository.ProductRepository;
import org.example.wishlistelectricboogaloo.Repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class ProfileService {
        private final ProfileRepository profileRepository;
        private final ProductRepository productRepository;

        public ProfileService(ProfileRepository profileRepository, ProductRepository productRepository) {
            this.profileRepository = profileRepository;
            this.productRepository = productRepository;
        }

        //show local market
        public List<Product> getAllProducts(int marketId) {
            return productRepository.getAllProducts(marketId);
        }

        public int getMarketByProfileID(int profileId) {
            return productRepository.getMarketByProfileID(profileId);
        }

        public Profile authenticateProfile (String username, String password) {
            return profileRepository.authenticateProfile(username, password);
        }
    }