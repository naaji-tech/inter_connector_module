package com.fitaro.interconnectormodule.user.controller;

import com.fitaro.interconnectormodule.user.model.User;
import com.fitaro.interconnectormodule.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("userServices/v1/")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("users/signup")
    public ResponseEntity<Object> signup(@RequestBody User user) {
        return userService.signupUser(user);
    }

    @PostMapping("users/login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
        return userService.loginUser(username, password);
    }

    @GetMapping("users/{username}")
    public ResponseEntity<Object> getUserProfile(@PathVariable String username) {
        return userService.getUserDetails(username);
    }

    @PutMapping("users/username/{username}")
    public ResponseEntity<Object> uptUsername(@PathVariable String username, @RequestParam String newUsername) {
        return userService.updateUsername(username, newUsername);
    }

    @PutMapping("users/password/{username}")
    public ResponseEntity<Object> uptPassword(@PathVariable String username, @RequestParam String newPassword) {
        return userService.updatePassword(username, newPassword);
    }
}
