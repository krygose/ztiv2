package com.example.zti.service.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrdersDto(
        @JsonProperty("id") String id,
        @JsonProperty("personId") String personId,
        @JsonProperty("state") Boolean state) {
}
