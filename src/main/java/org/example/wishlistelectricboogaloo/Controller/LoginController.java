package org.example.wishlistelectricboogaloo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("")
    public String getLoginPage(){
        return "loginPage";
    }

    @PostMapping("")
    public String postLogin(){
        return "redirect: myHomepage"
    }

    @GetMapping("/newProfile")
    public String getNewProfilePage(){
        return "newProfile"
    }

    @PostMapping("/newProfile")
    public String postNewUser (){
        return "redirect: myHomePage";
    }

}
