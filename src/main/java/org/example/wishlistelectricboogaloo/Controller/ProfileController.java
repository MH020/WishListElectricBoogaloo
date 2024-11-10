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
        model.addAttribute("profileID", session.getAttribute("id"));
        return "myHomepage";
    }

    @GetMapping("/market")
    public String getMarket(Model model) {
        Integer profileID = (Integer) session.getAttribute("id");
        int market = profileService.getMarketByProfileID(profileID); //get market info based on profileID
        //if market is 0, redirect to allMarkets so you can choose a market
        if (market == 0) {
            return "redirect:/homepage/{profileID}/allMarkets";
        }
        List<Product> products = profileService.getAllProducts(market); //get all products from market
        //List(Wishlist) wishlists = wishlistService.getWishListByProfileID(profileID);
        model.addAttribute("products", products); //add products to model?
        model.addAttribute("profileID", profileID);
        //model.addAttribute("wishlists", wishlists); //add wishlists to model?
        return "market";
    }

    @GetMapping("/allMarkets")
    public String getAllMarkets(Model model){
        Integer profileID = (Integer) session.getAttribute("id");
        List<Market> markets = profileService.getAllMarkets();
        model.addAttribute("allMarkets", markets);
        model.addAttribute("profileID", profileID);
        return "allMarkets";
    }

    @PostMapping("/addMarket")
    public String addMarket(@RequestParam int marketId,@PathVariable int profileID){
        Integer ID = (Integer) session.getAttribute("id");
        profileService.addMarketToUser(ID, marketId);
        return "redirect:/homepage/" + profileID;
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
        return "redirect:/homepage/" + id;
    }
}