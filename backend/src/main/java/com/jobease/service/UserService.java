package com.jobease.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobease.dtos.PreferencesDto;
import com.jobease.dtos.UserDto;
import com.jobease.model.Preferences;
import com.jobease.model.User;
import com.jobease.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registers a user with UserDto input {email, password}
     * @param input
     * @return User object of the registered user
     */
    public User registerUser(UserDto input) {
        User user = new User(input.getEmail(), passwordEncoder.encode(input.getPassword()));
        try {
            Preferences preferences = new Preferences();
            user.setPreferences(preferences);
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email already exists");
        }
    }

    /**
     * Logs user in with UserDto input {email, password}
     * @param input
     * @return User object of the logged in user
     */
    public User loginUser(UserDto input) {
        User user = userRepository.findByEmail(input.getEmail());
        System.out.println(input.getPassword() + " " + user.getPassword() + " " + passwordEncoder.matches(input.getPassword(), user.getPassword()));
        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return user;
    }

    /**
     * Deletes user with specified email
     * @param email
     * @return User object of the deleted user
     */
    public User deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    /**
     * Updates a user's (located with email) preferences with a PreferencesDto input
     * @param email
     * @param input
     */
    public void updatePreferences(String email, PreferencesDto input) {
        User user = userRepository.findByEmail(email);
        user.getPreferences().updatePreferences(input);
        userRepository.save(user);
    }

    /**
     * Gets a user's preferences with specified email
     * @param email
     * @return Preferences object of the user
     */
    public Preferences getPreferences(String email) {
        User user = userRepository.findByEmail(email);
        return user.getPreferences();
    }
}
