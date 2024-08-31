package com.example.zti.service.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record NewOrderDto(
        @JsonProperty("personId") String personId,
        @JsonProperty("state") Integer state,
        @JsonProperty("itemList") List<NewOrderItem> itemList) {
    public record NewOrderItem(@JsonProperty("id") String id, @JsonProperty("quantity") Integer quantity) {};
}
