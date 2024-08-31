package com.example.zti.service.order.sql;

import java.util.List;

public record OrderSqlRow(
        String personId,
        Boolean state,
        List<ListItemSqlRow> items) {
    public record ListItemSqlRow(String id,String name, Double price, Integer quantity) {
    }
}
