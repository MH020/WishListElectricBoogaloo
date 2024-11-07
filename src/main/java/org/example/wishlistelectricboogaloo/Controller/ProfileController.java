package org.example.wishlistelectricboogaloo.Controller;
import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.example.wishlistelectricboogaloo.Service.ProfileService;
import org.example.wishlistelectricboogaloo.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/homepage/{profileID}")
public class ProfileController {
    private final WishlistService wishlistService;
    private final ProfileService profileService;
    private final HttpSession session;

    public ProfileController(WishlistService wishlistService,HttpSession session, ProfileService profileService) {
        this.wishlistService = wishlistService;
        this.profileService = profileService;
        this.session = session;

    }

    @GetMapping("")
    public String getMyHomepage(Model model){
        model.addAttribute("wishlist", new Wishlist());
        return "myHomepage";
    }

    @GetMapping("/market")
    public String getMarket(@PathVariable int profileID, Model model) {
        //List<Product> products = profileService.getAllProducts(marketId);
        model.addAttribute("profileID", profileID);
        return "market";
    }

    @PostMapping("/logout")
    public String postLogout(HttpSession session){
        session.invalidate();
        return "redirect: loginPage";
    }

    @PostMapping("/deleteAccount")
    public String postDeleteAccount(){
        return "redirect: loginPage";
    }

    @PostMapping("/addWishList")
    public String addWishList(Wishlist wishlist) {
        Integer id = (Integer) session.getAttribute("id");
        wishlistService.createWishlist(wishlist,id);
        return "redirect:/myWishlist";
    }
}