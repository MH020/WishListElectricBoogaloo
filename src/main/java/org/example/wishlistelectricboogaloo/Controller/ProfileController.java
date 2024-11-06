package org.example.wishlistelectricboogaloo.Controller;

import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.example.wishlistelectricboogaloo.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/homepage/{profileID}")
public class ProfileController {
    WishlistService wishlistService;
    public ProfileController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    @GetMapping("")
    public String getMyHomepage(Model model){
        model.addAttribute("wishlist", new Wishlist());
        return "myHomepage";
    }

    @GetMapping("/market")
    public String getMarketPage(){
        return "market";
    }

    @PostMapping("/logout")
    public String postLogout(){
        return "redirect: /login";
    }

    @PostMapping("/deleteAccount")
    public String postDeleteAccount(){
        return "redirect: /login";
    }

    @PostMapping("/addWishList")
    public String addWishList(@ModelAttribute Wishlist wishlist, @PathVariable int profileID) {
        wishlistService.createWishlist(wishlist, profileID);
        return "redirect:/homepage/{profileID}";
    }
}
