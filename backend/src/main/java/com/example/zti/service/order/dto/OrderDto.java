package com.example.zti.service.order.dto;

import com.example.zti.service.item.dto.ItemDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrderDto(
//         @JsonProperty("id") String id,
        @JsonProperty("personId") String personId,
        @JsonProperty("state") Boolean state,
        @JsonProperty("itemsList") List<ItemDto> itemList,
        @JsonProperty("value") Double value) {
}
