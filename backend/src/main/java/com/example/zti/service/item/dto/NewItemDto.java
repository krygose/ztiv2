package com.example.zti.service.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record NewItemDto (

        @JsonProperty("name") @NotNull String name,
        @JsonProperty("price") @NotNull Double price,
        @JsonProperty("quantity") @NotNull Integer quantity
)
{}
