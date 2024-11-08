package org.example.wishlistelectricboogaloo.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.Model.Profile;
import org.example.wishlistelectricboogaloo.Service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final ProfileService ProfileService;
    private final HttpSession session;

    public LoginController(ProfileService ProfileService, HttpSession session) {
        this.ProfileService = ProfileService;
        this.session = session;
    }

    @GetMapping("")
    public String getLoginPage(Model model){
        model.addAttribute("user", new Profile());
        return "loginPage";
    }

    @PostMapping("")
    public String postLogin(@ModelAttribute Profile profile, HttpSession session) {
        //if the profile is authenticated, the profile is redirected to their homepage

        try {
            Profile realUser = ProfileService.authenticateProfile(profile.getUsername(), profile.getPassword());


        if (realUser != null) {
            session.setAttribute("id",realUser.getId());
            System.out.println("id: " + session.getAttribute("id"));
            return "redirect:/homepage/" + realUser.getId();
        }
        }catch(Exception e){
            System.out.println("boooo: " + e);
        }
        return "/loginPage";
    }


    @GetMapping("/newProfile")
    public String getNewProfilePage(Model model){
        model.addAttribute("newProfile", new Profile());
        return "newProfile";
    }

    @PostMapping("/newProfile")
    public String postNewUser (@ModelAttribute Profile profile){
        ProfileService.saveUser(profile);
        return "redirect:/login";
    }
}