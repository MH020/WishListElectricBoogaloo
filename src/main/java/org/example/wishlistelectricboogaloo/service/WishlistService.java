package org.example.wishlistelectricboogaloo.service;

import org.example.wishlistelectricboogaloo.model.Wishlist;
import org.example.wishlistelectricboogaloo.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // Create a new wishlist
    public int createWishlist(Wishlist wishlist) {
        return wishlistRepository.createWishlist(wishlist);
    }
    //delete a wishlist
    public void deleteWishlist(int id) {
        wishlistRepository.deleteWishlist(id);
    }

    public List<Wishlist> getAllWishLists(int profileId) {
        return wishlistRepository.getAllWishLists(profileId);
    }
}
