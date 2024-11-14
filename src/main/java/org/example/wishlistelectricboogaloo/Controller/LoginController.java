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
    private final ProfileService profileService;
    private final HttpSession session;

    public LoginController(ProfileService profileService, HttpSession session) {
        this.profileService = profileService;
        this.session = session;
    }

    @GetMapping("")
    public String getLoginPage(Model model){
        model.addAttribute("profile", new Profile());
        return "loginPage";
    }

    @PostMapping("")
    public String postLogin(@ModelAttribute Profile profile, HttpSession session) {
        //if the profile is authenticated, the profile is redirected to their homepage
        try {
            Profile realUser = profileService.authenticateProfile(profile.getUsername(), profile.getPassword());

            if (realUser != null) {
                session.setAttribute("profile_id",realUser.getId());
                System.out.println("profile_id: " + session.getAttribute("profile_id"));
                return "redirect:/homepage/" + realUser.getId();
            }

        }catch(Exception e){
            System.out.println("fejl: " + e);
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
        profileService.saveUser(profile);
        return "redirect:/login";
    }
}