package com.fitaro.interconnectormodule.user.service;

import com.fitaro.interconnectormodule.user.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Object> signupUser(User user);

    ResponseEntity<Object> loginUser(String username, String password);

    ResponseEntity<Object> getUserDetails(String username);

    ResponseEntity<Object> updateUsername(String username, String newUsername);

    ResponseEntity<Object> updatePassword(String username, String newPassword);
}
