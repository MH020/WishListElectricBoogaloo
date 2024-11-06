package org.example.wishlistelectricboogaloo.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.Model.User;
import org.example.wishlistelectricboogaloo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService UserService;

    public LoginController(UserService UserService) {
        this.UserService = UserService;
    }

    @GetMapping("")
    public String getLoginPage(){
        return "loginPage";
    }

    @PostMapping("")
    public String postLogin(@RequestParam String username, @RequestParam String password, HttpSession session){
        //if the user is authenticated, the user is redirected to their homepage
        User realUser = UserService.authenticateUser(username, password);
        if (realUser != null) {
            session.setAttribute("id",realUser.getId());
            return "redirect: myHomepage";
        }
        return "loginPage";
    }

    @GetMapping("/newProfile")
    public String getNewProfilePage(){
        return "newProfile";
    }

    @PostMapping("/newProfile")
    public String postNewUser (){
        return "redirect: myHomePage";
    }


}
