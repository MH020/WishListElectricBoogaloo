package org.example.wishlistelectricboogaloo.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.Model.User;
import org.example.wishlistelectricboogaloo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final UserService UserService;
    private final HttpSession session;

    public LoginController(UserService UserService, HttpSession session) {
        this.UserService = UserService;
        this.session = session;
    }

    @GetMapping("")
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "loginPage";
    }

    @PostMapping("")
    public String postLogin(@ModelAttribute User user, HttpSession session){
        //if the user is authenticated, the user is redirected to their homepage

        try {
            User realUser = UserService.authenticateUser(user.getUsername(), user.getPassword());


        if (realUser != null) {
            session.setAttribute("id",realUser.getId());
            System.out.println("id: " + session.getAttribute("id"));
            return "redirect:/homepage/" + realUser.getId();
        }
        }catch(Exception e){
            System.out.println("boooo: " + e);
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
