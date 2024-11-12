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
    public int addWishlist(Wishlist wishlist,int wishlist_id ) {
        return wishlistRepository.addWishlist(wishlist,wishlist_id);
    }
    //delete a wishlist
    public void deleteWishlist(int wishlist_id) {
        wishlistRepository.deleteWishlist(wishlist_id);
    }
    public boolean updateWishlistAddProduct(int product_id, int wishlist_id) {
        return wishlistRepository.updateWishlistAddProduct(product_id,wishlist_id);
    }
    public List<String> searchforWishlist(String search){
        return wishlistRepository.searchforWishlist(search);

    }
}
