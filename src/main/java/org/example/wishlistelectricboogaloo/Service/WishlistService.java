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
    public int createWishlist(Wishlist wishlist,int id ) {
        return wishlistRepository.createWishlist(wishlist,id);
    }
    //Get all wishlists
    public List<Wishlist> getAllWishLists(int profileId) {
        return wishlistRepository.getAllWishLists(profileId);
    }

    //delete a wishlist
    public void deleteWishlist(int id) {
        wishlistRepository.deleteWishlist(id);
    }
    public boolean updateWishlistAddProduct(int productID, int wishlistId) {
        return wishlistRepository.updateWishlistAddProduct(productID,wishlistId);
    }
}
