package org.example.wishlistelectricboogaloo.Controller;

import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.example.wishlistelectricboogaloo.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/addWishList")
    public String addWishList(Model model) {
        model.addAttribute("wishlist", new Wishlist());
        return "myHomepage";

    }

    @PostMapping("/addWishList")
    public String addWishList(Wishlist wishlist) {
        wishlistService.createWishlist(wishlist);
        return "redirect:/myWishlist";
    }

}
