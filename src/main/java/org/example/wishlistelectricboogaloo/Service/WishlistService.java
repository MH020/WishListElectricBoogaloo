package org.example.wishlistelectricboogaloo.Service;

import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.example.wishlistelectricboogaloo.Repository.WishlistRepository;
import org.springframework.stereotype.Service;

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
}
