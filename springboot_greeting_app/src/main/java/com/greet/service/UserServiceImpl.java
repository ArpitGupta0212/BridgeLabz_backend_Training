package com.greet.service;

import com.greet.model.User;
import com.greet.repository.UserRepository;
import com.greet.util.HashUtil;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(User user) {
        log.debug("Attempting to register user: {}", user.getUsername());
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.warn("Registration failed. Username '{}' is already taken", user.getUsername());
            return false;
        }

        user.setPassword(HashUtil.hashPassword(user.getPassword()));
        userRepository.save(user);
        log.info("User '{}' registered successfully with ID {}", user.getUsername(), user.getId());
        return true;
    }

    @Override
    public Optional<User> login(String username, String password) {
        log.debug("Attempting authentication login for user: {}", username);
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(HashUtil.hashPassword(password)))
                .map(user -> {
                    log.info("Authentication successful for user: {}", username);
                    return user;
                });
    }
}
