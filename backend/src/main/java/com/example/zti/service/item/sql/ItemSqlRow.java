package com.example.zti.service.item.sql;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ItemSqlRow(
        String id,
        String name,
        Double price,
        Integer quantity) {
}
