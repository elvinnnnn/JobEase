package com.jobease.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobease.model.User;
import com.jobease.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registerUser(String email, String pwd) {
        User user = new User(email, passwordEncoder.encode(pwd));
        return userRepository.save(user);
    }

    public User loginUser(String email, String pwd) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(pwd, user.getPwdHash())) {
            return user;
        }
        return null;
    }

    public User deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }
}
