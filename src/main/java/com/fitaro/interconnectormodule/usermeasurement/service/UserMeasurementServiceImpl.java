package com.fitaro.interconnectormodule.usermeasurement.service;

import com.fitaro.interconnectormodule.client.UserMeasurementClient;
import com.fitaro.interconnectormodule.user.repository.UserRepository;
import com.fitaro.interconnectormodule.usermeasurement.model.UserMeasurement;
import com.fitaro.interconnectormodule.usermeasurement.repository.UserMeasurementRepository;
import com.fitaro.interconnectormodule.util.Error;
import com.fitaro.interconnectormodule.util.ResHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UserMeasurementServiceImpl implements UserMeasurementService {
    UserMeasurementRepository umRepository;
    UserRepository userRepository;
    UserMeasurementClient umClient;

    @Autowired
    public UserMeasurementServiceImpl(
            @Qualifier("umPostgresql") UserMeasurementRepository umRepository,
            @Qualifier("uPostgresql") UserRepository userRepository,
            UserMeasurementClient umClient
    ) {
        this.umRepository = umRepository;
        this.userRepository = userRepository;
        this.umClient = umClient;
    }

    @Override
    public ResponseEntity<Object> getUserMeasurements(String username) {
        try {
            int userId = userRepository.valUsername(username);
            if (userId == Error.USERNAME_NOT_FOUND.getValue())
                return ResHandler.error(Error.USERNAME_NOT_FOUND, HttpStatus.NOT_FOUND);

            UserMeasurement um = umRepository.getUserMeasurements(userId);
            if (um == null)
                return ResHandler.error(Error.USER_MEASUREMENT_NOT_FOUND, HttpStatus.NOT_FOUND);

            return ResHandler.success("Retrieve user measurement success", um, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Object> addNewUserMeasurements(String username, MultipartFile usrImage, float usrHeight) {
        try {
            if (usrImage.isEmpty())
                return ResHandler.error(Error.USER_IMAGE_MISSING, HttpStatus.BAD_REQUEST);

            int userId = userRepository.valUsername(username);
            if (userId == Error.USERNAME_NOT_FOUND.getValue())
                return ResHandler.error(Error.USERNAME_NOT_FOUND, HttpStatus.NOT_FOUND);

            UserMeasurement userMeasures = umClient.getUserMeasurement(usrImage, usrHeight);

            int resCode = umRepository.addNewUserMeasurements(userId, userMeasures);
            if (resCode < 1)
                return ResHandler.error(Error.ADD_NEW_USER_MEASUREMENT_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);

            return ResHandler.success("Add user measurements success", userMeasures, HttpStatus.OK);

        } catch (IOException e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.UM_CLIENT_CONNECTION_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            System.out.println("exception : " + e.getMessage());
            return ResHandler.error(Error.DATABASE_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
