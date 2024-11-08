package org.example.wishlistelectricboogaloo.Controller;

import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.example.wishlistelectricboogaloo.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/homepage/{profileID}/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/view/{wishlistID}")
    public String getWishlist(){
        return "myWishlist";
    }

    @PostMapping("/delete/{wishlistID}")
    public String deleteWishlist(@PathVariable int wishlistID){
        wishlistService.deleteWishlist(wishlistID);
        return "redirect: myHomepage";
    }

    @PostMapping("/update/{wishlistID}/removeWish/{productID}")
    public String updateWishlistRemoveProduct(){
        return "redirect: myWishlist";
    }

    @PostMapping("/update/{wishlistID}/addWish")
    public String updateWishlistAddProduct(@PathVariable int wishlistID,@RequestParam int productID){
        wishlistService.updateWishlistAddProduct(productID, wishlistID);
        return "redirect: market";
    }
}