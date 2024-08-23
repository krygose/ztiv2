package com.example.zti.service.order.sql;

import java.util.List;

public record OrderSqlRow(
        Boolean state,
        String personId,
        List<ListItemSqlRow> items) {
    public record ListItemSqlRow(String name, Double price, Integer quantity) {
    }
}
