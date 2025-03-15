package com.fitaro.interconnectormodule.user.repository;

import com.fitaro.interconnectormodule.user.model.User;
import com.fitaro.interconnectormodule.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository(value = "uPostgresql")
public class UserRepositoryImpl implements UserRepository {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createUser(User user) {
        String insertQuery = "INSERT INTO users (user_name, password, user_type) VALUES (?, ?, ?)";
        return jdbcTemplate.update(insertQuery, user.getUsername(), user.getPassword(), user.getUserType());
    }

    @Override
    public int valUsername(String username) {
        String getUsrQuery = "SELECT user_id FROM users WHERE user_name = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(getUsrQuery, username);

        if (!rowSet.next()) return Error.USERNAME_NOT_FOUND.getValue();
        return rowSet.getInt("user_id");
    }

    @Override
    public int valPassword(String username, String password) {
        String getUsrQuery = "SELECT user_id FROM users WHERE user_name = ? and password = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(getUsrQuery, username, password);

        if (!rowSet.next()) return Error.WRONG_PASSWORD.getValue();
        return HttpStatus.OK.value();
    }

    @Override
    public User getUserDetails(String username) {
        String sqlQuery = "SELECT user_id, user_name, password, user_type FROM users WHERE user_name = ?";

        return jdbcTemplate.queryForObject(sqlQuery, (rs, rowNum) -> {
            String userId = rs.getString("user_id");
            String userName = rs.getString("user_name");
            String password = rs.getString("password");
            String userType = rs.getString("user_type");
            return new User(userId, userName, password, userType);
        }, username);
    }

    @Override
    public int updateUsername(String username, String newUsername) {
        String updateQuery = "UPDATE users SET user_name = ?, last_updated_date = now() WHERE user_name = ?";
        int dbRes = jdbcTemplate.update(updateQuery, newUsername, username);
        if (dbRes == 0) return Error.USERNAME_UPDATE_FAILED.getValue();
        return dbRes;
    }

    @Override
    public int updatePassword(String username, String newPassword) {
        String updateQuery = "UPDATE users SET password = ?, last_updated_date = now() WHERE user_name = ?";
        int dbRes = jdbcTemplate.update(updateQuery, newPassword, username);
        if (dbRes == 0) return Error.PASSWORD_UPDATE_FAILED.getValue();
        return dbRes;
    }
}
