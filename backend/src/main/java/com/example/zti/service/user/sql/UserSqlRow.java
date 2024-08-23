package com.example.zti.service.user.sql;


public record UserSqlRow(
        String id,
        String firstName,
        String lastName,
        String email,
        String password,
        String role
) {
}
