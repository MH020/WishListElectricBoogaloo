package org.example.wishlistelectricboogaloo.Repository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.wishlistelectricboogaloo.Model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest
// Uses active profile to make sure we are hooked to database
@ActiveProfiles("test")
// @SQL ensures that h2 is reset for usage
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:schema.sql")
class ProfileRepositoryTest {

    Profile profile;

    @Autowired
    ProfileRepository profileRepository;



    @Test
    void saveUser() {
        profile = new Profile();
        profile.setPassword("test");
        profile.setUsername("test");
        profile.setEmail("test@mail.com");
        profile.setPhoneNumber("12341234");
        profile.setId(3); //two instances of profiles already exist.

        int actualProfile_id = profileRepository.saveUser(profile);
        int expectedProfile_id = profile.getId();

        assertEquals(expectedProfile_id, actualProfile_id);
    }

    @Test
    void deleteUser() {
        profile = new Profile();
        profile.setId(3);
        profileRepository.deleteUser(profile.getId());

        //assertNull(profile);
    }

    @Test
    void authenticateProfile() {
    }
}