package com.jobease.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.jobease.dtos.PreferencesDto;
import com.jobease.dtos.UserDto;
import com.jobease.model.Preferences;
import com.jobease.model.User;
import com.jobease.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.authenticationManager = authenticationManager;
    }

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

    public User loginUser(UserDto input) {
        User user = userRepository.findByEmail(input.getEmail());
        System.out.println(input.getPassword() + " " + user.getPassword() + " " + passwordEncoder.matches(input.getPassword(), user.getPassword()));
        if (!passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return user;
    }

    public User deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    public void updatePreferences(String email, PreferencesDto input) {
        User user = userRepository.findByEmail(email);
        user.getPreferences().updatePreferences(input);
        userRepository.save(user);
    }

    public Preferences getPreferences(String email) {
        User user = userRepository.findByEmail(email);
        return user.getPreferences();
    }
}
