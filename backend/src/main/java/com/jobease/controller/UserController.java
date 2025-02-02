package com.jobease.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobease.dtos.UserDto;
import com.jobease.model.User;
import com.jobease.responses.LoginResponse;
import com.jobease.service.JwtService;
import com.jobease.service.UserService;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    public UserController(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> addNewUser (@RequestBody UserDto user) {
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser (@RequestBody UserDto user) {
        User authenticatedUser = userService.loginUser(user);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/delete")
    public String deleteUser (@RequestParam String email) {
        userService.deleteUser(email);
        return "Deleted User";
    }
}
