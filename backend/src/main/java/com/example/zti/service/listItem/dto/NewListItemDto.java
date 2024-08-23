package com.example.zti.service.listItem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record NewListItemDto(
        @JsonProperty("item_id") @NotNull String item_id,
        @JsonProperty("person_order_id") @NotNull String person_order_id,
        @JsonProperty("quantity") @NotNull Integer quantity
) {
}
