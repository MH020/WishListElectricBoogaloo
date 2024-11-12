package org.example.wishlistelectricboogaloo.Service;

import org.example.wishlistelectricboogaloo.Encrypter;
import org.example.wishlistelectricboogaloo.Model.Market;
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
        public void saveUser(Profile profile) {
            Encrypter encrypter = new Encrypter();
            String encryptedPassword = encrypter.encrypt(profile.getPassword());
            String encryptedUsername = encrypter.encrypt(profile.getUsername());
            profile.setPassword(encryptedPassword);
            profile.setUsername(encryptedUsername);
            profileRepository.saveUser(profile);
        }

        //show local market
        public List<Product> getAllProducts(int marketId) {
            return productRepository.getAllProducts(marketId);
        }

        public int getMarketByProfileID(int profileId) {
            return productRepository.getMarketByProfileID(profileId);
        }
        //hjemmelavet metode
        public List <Market> getAllMarkets() {
            return productRepository.getAllMarkets();
        }
        //also this one er hjemmelavet
        public void addMarketToUser(int profile_id, int market_id) {
            productRepository.addMarketToUser(profile_id, market_id);
        }

        public Profile authenticateProfile (String username, String password) {
            Encrypter encrypter = new Encrypter();
            String encryptedPassword = encrypter.encrypt(password);
            String encryptedUsername = encrypter.encrypt(username);
            return profileRepository.authenticateProfile(encryptedUsername, encryptedPassword);
        }
    }