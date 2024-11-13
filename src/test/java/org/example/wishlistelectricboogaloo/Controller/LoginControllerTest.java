package org.example.wishlistelectricboogaloo.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.Service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(LoginController.class) //hvilken controller vi gerne vil teste.
class LoginControllerTest {

    //i stedet for en rigtig web-server.
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProfileService profileService;



    //login metoder:
    @Test
    void getLoginPage() throws Exception{
        mockMvc.perform(get("/login")
                .sessionAttr("profile_id", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage")) //tjekker at den rigtige htmlside forwardes.
                .andExpect((content().string(containsString("${profile.getUsername()}")))); //tjekker at html-filen indeholder et bestemt udtryk.
    }

    @Test
    void postLogin() {
    }

    //metoder til at oprette ny profil.
    @Test
    void getNewProfilePage() {
    }

    @Test
    void postNewUser() {
    }
}