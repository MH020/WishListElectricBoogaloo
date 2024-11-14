package org.example.wishlistelectricboogaloo.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.wishlistelectricboogaloo.Model.Profile;
import org.example.wishlistelectricboogaloo.Repository.ProfileRepository;
import org.example.wishlistelectricboogaloo.Service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
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

    @MockBean
    ProfileRepository profileRepository;


    //login metoder:
    @Test
    void getLoginPage() throws Exception{
        mockMvc.perform(get("/login")
                .sessionAttr("profile_id", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("loginPage")) //tjekker at den rigtige htmlside forwardes.
                .andExpect((content().string(containsString("Create a new profile")))); //tjekker at html-filen indeholder et bestemt udtryk.
    }

    @Test
    void postLogin() throws Exception{
        when(profileService.authenticateProfile("test", "test")).thenReturn(new Profile());//return
        mockMvc.perform(post("/login")
                .sessionAttr("profile_id", 1))
                .andExpect(status().isOk());
    }

    //metoder til at oprette ny profil.
    @Test
    void getNewProfilePage() throws Exception{
        mockMvc.perform(get("/login/newProfile")
                .sessionAttr("profile_id", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("newProfile")); //tjekker at den rigtige htmlside forwardes.
    }


    /*
    @Test
    void postNewUser() throws Exception{
        mockMvc.perform(post("/login/newProfile")
                .sessionAttr("profile_id", 1))
                .andExpect(model().attributeExists("profile"))
                //.andExpect(status().is3xxRedirection())
                .andExpect(status().isOk());
        /*.contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                   {
                         "participantId": 1,
                         "disciplineId": 1,
                         "resultDate": "2023-05-05",
                         "hours": 1,
                         "minutes": 30,
                         "seconds": 15,
                         "hundredths": 50,
                         "meters": 100,
                         "centimeters": 50,
                         "points": 10
                     }
                """)
        //.andExpect(view().is3xxRedirection())
    }*/
}