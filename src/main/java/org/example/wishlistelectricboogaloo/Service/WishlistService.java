package org.example.wishlistelectricboogaloo.Service;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.example.wishlistelectricboogaloo.Repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    // Create a new wishlist
    public int createWishlist(Wishlist wishlist,int profile_id ) {
        return wishlistRepository.createWishlist(wishlist,profile_id);
    }
    //Get all wishlists

    public List<Wishlist> getAllWishLists(int profile_id) {
        return wishlistRepository.getAllWishLists(profile_id);
    }
    //delete a wishlist
    public void deleteWishlist(int wishlist_id) {
        wishlistRepository.deleteWishlist(id);
    }
    public boolean updateWishlistAddProduct(int product_id, int wishlist_id) {
        return wishlistRepository.updateWishlistAddProduct(product_id,wishlist_id);
    }
}
