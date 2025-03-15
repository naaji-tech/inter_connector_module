package com.fitaro.interconnectormodule.usermeasurement.repository;

import com.fitaro.interconnectormodule.usermeasurement.model.UserMeasurement;

public interface UMRepository {
    UserMeasurement getUserMeasurements(int userId) throws Exception;

    int addNewUserMeasurements(int userId, UserMeasurement userMeasures) throws Exception;
}
