package com.example.zti.service.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemDto(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("price") Double price,
        @JsonProperty("quantity") Integer quantity) {
}
