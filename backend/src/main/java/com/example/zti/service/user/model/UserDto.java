package com.example.zti.service.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDto(
        @JsonProperty("id") String id,
        @JsonProperty("firstname") @NotNull  String firstName,
        @JsonProperty("lastname")  String lastName,
        @JsonProperty("email") @NotNull  String email,
        @JsonProperty("password") @NotNull String password,
        @JsonProperty("role") String role) {
}


