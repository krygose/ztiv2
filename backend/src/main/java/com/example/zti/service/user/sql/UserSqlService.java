package com.example.zti.service.user.sql;

import com.example.zti.service.user.model.UserDto;
import com.fasterxml.jackson.databind.util.Named;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jdbc.core.DataClassRowMapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import java.sql.*;

import static com.example.zti.common.resource.ResourceManager.readSqlQuery;

@Service
public class UserSqlService {
    private static final Logger log = LoggerFactory.getLogger(UserSqlService.class);

    private static final String INSERT_INTO_USER = readSqlQuery("sql/User/insert_into_user.sql");
    private static final String SELECT_COUNT_BY_EMAIL =
            readSqlQuery("sql/User/select_user_count_by_email.sql");
    private static final String SELECT_USER_BY_ID = readSqlQuery("sql/User/select_user_by_id.sql");
    private static final String SELECT_USER_BY_EMAIL =
            readSqlQuery("sql/User/select_user_by_email.sql");

    private final NamedParameterJdbcOperations jdbcOperations;
    private final PasswordEncoder passwordEncoder;

    public UserSqlService(
            NamedParameterJdbcOperations jdbcOperations, PasswordEncoder passwordEncoder) {
        this.jdbcOperations = jdbcOperations;
        this.passwordEncoder = passwordEncoder;
    }

    public Integer createUser(
            String id,
            String firstName,
            String lastName,
            String email,
            String password,
            String role) {

        MapSqlParameterSource parameters =
                insertUserParameterSource(
                        new MapSqlParameterSource(),
                        id,
                        firstName,
                        lastName,
                        email,
                        passwordEncoder.encode(password),
                        role);
        try {
            return jdbcOperations.update(INSERT_INTO_USER, parameters);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public MapSqlParameterSource insertUserParameterSource(
            MapSqlParameterSource parameterSource,
            String id,
            String firstName,
            String lastName,

            String email,
            String password,
            String role) {
        parameterSource.addValue("id", id);
        parameterSource.addValue("first_name", firstName);
        parameterSource.addValue("last_name", lastName);
        parameterSource.addValue("email", email);
        parameterSource.addValue("password", password);
        parameterSource.addValue("role", role);
        return parameterSource;
    }

    public Integer getCountByEmail(String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("email", email);
        return jdbcOperations.queryForObject(SELECT_COUNT_BY_EMAIL, parameters, Integer.class);
    }

    public UserDto getUserByEmail(String email) {
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("email", email);
        return jdbcOperations.queryForObject(SELECT_USER_BY_EMAIL, parameters,
                DataClassRowMapper.newInstance(UserDto.class));
    }

}
