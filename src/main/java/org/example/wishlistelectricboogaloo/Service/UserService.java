package org.example.wishlistelectricboogaloo.Service;

import org.example.wishlistelectricboogaloo.Model.User;
import org.example.wishlistelectricboogaloo.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticateUser(String username, String password) {
        return userRepository.authenticateUser(username, password);
    }
}
