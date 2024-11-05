package org.example.wishlistelectricboogaloo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage/{profileID}/wishlist")
public class WishlistController {

    @GetMapping("/view/{wishlistID}")
    public String getWishlist(){
        return "myWishlist";
    }

    @PostMapping("/delete/{wishlistID}")
    public String deleteWishlist(){
        return "redirect: myHomepage";
    }

    @PostMapping("/update/{wishlistID}/removeWish/{productID}")
    public String updateWishlistRemoveProduct(){
        return "redirect: myWishlist";
    }

    @PostMapping("/update/{wishlistID}/addWish")
    public String updateWishlistAddProduct(){
        return "redirect: market";
    }

    //get and post: create new wishlist
}
