package com.jobease.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobease.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public @ResponseBody String addNewUser (@RequestParam String email, @RequestParam String pwd) {
        userService.registerUser(email, pwd);
        return "Saved User";
    }

    @PostMapping("/login")
    public @ResponseBody String loginUser (@RequestParam String email, @RequestParam String pwd) {
        userService.loginUser(email, pwd);
        return "Logged In";
    }

    @PostMapping("/delete")
    public @ResponseBody String deleteUser (@RequestParam String email) {
        userService.deleteUser(email);
        return "Deleted User";
    }
}
