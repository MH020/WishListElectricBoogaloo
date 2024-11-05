package org.example.wishlistelectricboogaloo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/homepage/{profileID}")
public class ProfileController {

    @GetMapping("")
    public String getMyHomepage(){
        return "myHomepage";
    }

    @GetMapping("/market")
    public String getMarketPage(){
        return "market";
    }

    @PostMapping("/logout")
    public String postLogout(){
        return "redirect: loginPage";
    }

    @PostMapping("/deleteAccount")
    public String postDeleteAccount(){
        return "redirect: loginPage";
    }

}
