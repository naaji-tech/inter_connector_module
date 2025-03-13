package com.fitaro.interconnectormodule.usermeasurement.repository;

import com.fitaro.interconnectormodule.usermeasurement.model.UserMeasurement;

public interface UserMeasurementRepository {
    UserMeasurement getUserMeasurements(int userId) throws Exception;

    int addNewUserMeasurements(int userId, UserMeasurement userMeasures) throws Exception;
}
