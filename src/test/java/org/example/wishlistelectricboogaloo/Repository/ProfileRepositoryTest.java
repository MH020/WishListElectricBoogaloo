package org.example.wishlistelectricboogaloo.Repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.example.wishlistelectricboogaloo.Model.Profile;

class ProfileRepositoryTest {

    Profile profile;

    @Test
    void saveUser() {
        profile = new Profile();
        profile.setPassword("test");
        profile.setUsername("test");
        profile.setEmail("test@mail.com");
        profile.setPhoneNumber(12341234);




    }

    @Test
    void readUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void authenticateProfile() {
    }
}