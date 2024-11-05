package org.example.wishlistelectricboogaloo.Controller;

import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.example.wishlistelectricboogaloo.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }
    @GetMapping("/addWishList")
    public String addWishList(Model model) {
        model.addAttribute("wishlist", new Wishlist());
        return "addWishList";

    }

    @PostMapping("/addWishList")
    public String addWishList(Wishlist wishlist) {
        wishlistService.createWishlist(wishlist);
        return "redirect:/addWishList";
    }




}
