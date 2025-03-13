package com.fitaro.interconnectormodule.user.repository;

import com.fitaro.interconnectormodule.user.model.User;

public interface UserRepository {
    int createUser(User user) throws Exception;

    int valUsername(String username) throws Exception;

    int valPassword(String username, String password) throws Exception;

    User getUserDetails(String username) throws Exception;

    int updateUsername(String username, String newUsername) throws Exception;

    int updatePassword(String username, String newPassword) throws Exception;
}
