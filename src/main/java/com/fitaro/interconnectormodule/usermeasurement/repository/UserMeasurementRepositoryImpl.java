package com.fitaro.interconnectormodule.usermeasurement.repository;

import com.fitaro.interconnectormodule.usermeasurement.model.UserMeasurement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository(value = "umPostgresql")
public class UserMeasurementRepositoryImpl implements UserMeasurementRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserMeasurementRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserMeasurement getUserMeasurements(int userId) {
        String getQuery = """
                SELECT head_circumference,
                       neck_circumference,
                       shoulder_to_crotch_height,
                       chest_circumference,
                       waist_circumference,
                       hip_circumference,
                       wrist_circumference,
                       bicep_circumference,
                       forearm_circumference,
                       arm_length,
                       inside_leg_height,
                       thigh_circumference,
                       calf_circumference,
                       ankle_circumference,
                       shoulder_breadth,
                       height
                FROM user_measurements
                WHERE user_id = ?
                ORDER BY last_updated_date desc""";

        SqlRowSet rs = jdbcTemplate.queryForRowSet(getQuery, userId);

        if (rs.next()) {
            float headCircumference = rs.getFloat("head_circumference");
            float neckCircumference = rs.getFloat("neck_circumference");
            float shoulderToCrotchHeight = rs.getFloat("shoulder_to_crotch_height");
            float chestCircumference = rs.getFloat("chest_circumference");
            float waistCircumference = rs.getFloat("waist_circumference");
            float hipCircumference = rs.getFloat("hip_circumference");
            float wristCircumference = rs.getFloat("wrist_circumference");
            float bicepCircumference = rs.getFloat("bicep_circumference");
            float forearmCircumference = rs.getFloat("forearm_circumference");
            float armLength = rs.getFloat("arm_length");
            float insideLegHeight = rs.getFloat("inside_leg_height");
            float thighCircumference = rs.getFloat("thigh_circumference");
            float calfCircumference = rs.getFloat("calf_circumference");
            float ankleCircumference = rs.getFloat("ankle_circumference");
            float shoulderBreadth = rs.getFloat("shoulder_breadth");
            float height = rs.getFloat("height");

            return new UserMeasurement(headCircumference, neckCircumference, shoulderToCrotchHeight, chestCircumference, waistCircumference, hipCircumference, wristCircumference, bicepCircumference, forearmCircumference, armLength, insideLegHeight, thighCircumference, calfCircumference, ankleCircumference, shoulderBreadth, height);
        }

        return null;
    }

    @Override
    public int addNewUserMeasurements(int userId, UserMeasurement userMeasures) {
        String sql = """
                INSERT INTO user_measurements (
                    user_id,
                    head_circumference,
                    neck_circumference,
                    shoulder_to_crotch_height,
                    chest_circumference,
                    waist_circumference,
                    hip_circumference,
                    wrist_circumference,
                    bicep_circumference,
                    forearm_circumference,
                    arm_length,
                    inside_leg_height,
                    thigh_circumference,
                    calf_circumference,
                    ankle_circumference,
                    shoulder_breadth,
                    height)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";

        return jdbcTemplate.update(sql,
                userId,
                userMeasures.getHeadCircumference(),
                userMeasures.getNeckCircumference(),
                userMeasures.getShoulderToCrotchHeight(),
                userMeasures.getChestCircumference(),
                userMeasures.getWaistCircumference(),
                userMeasures.getHipCircumference(),
                userMeasures.getWristCircumference(),
                userMeasures.getBicepCircumference(),
                userMeasures.getForearmCircumference(),
                userMeasures.getArmLength(),
                userMeasures.getInsideLegHeight(),
                userMeasures.getThighCircumference(),
                userMeasures.getCalfCircumference(),
                userMeasures.getAnkleCircumference(),
                userMeasures.getShoulderBreadth(),
                userMeasures.getHeight()
        );
    }
}
