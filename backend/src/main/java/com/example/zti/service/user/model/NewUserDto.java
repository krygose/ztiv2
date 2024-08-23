package com.example.zti.service.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUserDto(
        @JsonProperty("firstname") @NotNull @Size(max=30) String firstName,
        @JsonProperty("lastname") @Size(max=30) String lastName,
        @JsonProperty("email") @NotNull @Size(max=30) String email,
        @JsonProperty("password") @NotNull String password,
        @JsonProperty("role") String role
) {
}
