package com.fitaro.interconnectormodule.user.service;

import com.fitaro.interconnectormodule.user.model.User;
import com.fitaro.interconnectormodule.user.repository.UserRepository;
import com.fitaro.interconnectormodule.util.Error;
import com.fitaro.interconnectormodule.util.ResHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(@Qualifier("uPostgresql") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Object> signupUser(User user) {
        try {
            int dbRes = userRepository.createUser(user);

            if (dbRes > 0)
                System.out.println("User created successfully");

            return ResHandler.success("User Signup success", HttpStatus.CREATED);

        } catch (DuplicateKeyException e) {
            System.out.println("duplicate key exception : " + e.getMessage());
            return ResHandler.error(Error.DUPLICATE_KEY_EXCEPTION, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> loginUser(String username, String password) {
        try {
            int dbRes = userRepository.valUsername(username);

            if (dbRes == Error.USERNAME_NOT_FOUND.getValue())
                return ResHandler.error(Error.USERNAME_NOT_FOUND, HttpStatus.NOT_FOUND);

            dbRes = userRepository.valPassword(username, password);

            if (dbRes == Error.WRONG_PASSWORD.getValue())
                return ResHandler.error(Error.WRONG_PASSWORD, HttpStatus.NOT_FOUND);

            User user = userRepository.getUserDetails(username);

            if (user == null)
                return ResHandler.error(Error.USER_NOT_FOUND, HttpStatus.NOT_FOUND);

            return ResHandler.success("User login success", user, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> getUserDetails(String username) {
        try {
            User user = userRepository.getUserDetails(username);

            if (user == null)
                return ResHandler.error(Error.USER_NOT_FOUND, HttpStatus.NOT_FOUND);

            return ResHandler.success("User details success", user, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updateUsername(String username, String newUsername) {
        try {
            int dbRes = userRepository.updateUsername(username, newUsername);

            if (dbRes == Error.USERNAME_UPDATE_FAILED.getValue())
                return ResHandler.error(Error.USERNAME_UPDATE_FAILED, HttpStatus.BAD_REQUEST);

            return ResHandler.success("Username update success", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> updatePassword(String username, String newPassword) {
        try {
            int dbRes = userRepository.updatePassword(username, newPassword);

            if (dbRes == Error.PASSWORD_UPDATE_FAILED.getValue())
                return ResHandler.error(Error.PASSWORD_UPDATE_FAILED, HttpStatus.BAD_REQUEST);

            return ResHandler.success("Password update success", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
