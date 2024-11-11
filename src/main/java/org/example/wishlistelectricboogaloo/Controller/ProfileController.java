package org.example.wishlistelectricboogaloo.Controller;
import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.Model.Market;
import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Model.Wishlist;
import org.example.wishlistelectricboogaloo.Service.ProfileService;
import org.example.wishlistelectricboogaloo.Service.WishlistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/homepage/{profile_id}")
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
        model.addAttribute("profile_id", session.getAttribute("profile_id"));
        return "myHomepage";
    }

    @GetMapping("/market")
    public String getMarket(Model model) {
        Integer profile_id = (Integer) session.getAttribute("profile_id");
        int market = profileService.getMarketByProfileID(profile_id); //get market info based on profileID
        //if market is 0, redirect to allMarkets so you can choose a market
        if (market == 0) {
            return "redirect:/homepage/" + profile_id + "/allMarkets";
        }
        List<Product> products = profileService.getAllProducts(market); //get all products from market
        //List(Wishlist) wishlists = wishlistService.getWishListByProfileID(profileID);
        model.addAttribute("products", products); //add products to model?
        model.addAttribute("profile_id", profile_id);
        //model.addAttribute("wishlists", wishlists); //add wishlists to model?
        return "market";
    }

    @GetMapping("/allMarkets")
    public String getAllMarkets(Model model){
        Integer profile_id = (Integer) session.getAttribute("profile_id");
        List<Market> markets = profileService.getAllMarkets();
        model.addAttribute("allMarkets", markets);
        model.addAttribute("profile_id", profile_id);
        return "allMarkets";
    }

    @PostMapping("/addMarket")
    public String addMarket(@RequestParam int market_id){
        int profile_id = (int) session.getAttribute("profile_id");
        profileService.addMarketToUser(profile_id, market_id);
        return "redirect:/homepage/" + profile_id;
    }

    @PostMapping("/logout")
    public String postLogout(HttpSession session){
        session.invalidate();
        return "redirect: /login";
    }

    @PostMapping("/deleteAccount")
    public String postDeleteAccount(){
        return "redirect: /login";
    }

    @PostMapping("/addWishList")
    public String addWishList(Wishlist wishlist) {
        int profile_id = (int) session.getAttribute("profile_id");
        wishlistService.createWishlist(wishlist,profile_id);
        return "redirect:/homepage/" + profile_id;
    }
}