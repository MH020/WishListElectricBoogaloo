package org.example.wishlistelectricboogaloo.Controller;

import org.springframework.ui.Model;
import org.example.wishlistelectricboogaloo.Model.Product;
import org.example.wishlistelectricboogaloo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/homepage/{profileID}")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/market")
    public String getMarket(int marketId, Model model) {

        // Retrieve products by marketId
        List<Product> products = userService.getAllProducts(marketId);
        // Add the list of products to the model
        model.addAttribute("products");
        // Return the view name for the "market" page
        return "market";
    }
}