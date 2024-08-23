package com.example.zti.service.order;

import com.example.zti.service.item.dto.ItemDto;
import com.example.zti.service.order.dto.OrderDto;
import com.example.zti.service.order.sql.OrderSqlRow;
import com.example.zti.service.order.sql.OrderSqlRow.ListItemSqlRow;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public record OrderMapper(ObjectMapper objectMapper) implements RowMapper<OrderSqlRow> {

    @Override
    public OrderSqlRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            return new OrderSqlRow (
                rs.getBoolean("state"),
                rs.getString("personId"),
                extractListItemSqlRowList(rs.getString("items")));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ListItemSqlRow> extractListItemSqlRowList(String listItemSqlRowJsonString) throws JsonProcessingException {
        return listItemSqlRowJsonString == null
            ? Collections.emptyList()
            : Arrays.asList(
                objectMapper.readValue(listItemSqlRowJsonString, ListItemSqlRow[].class)
            );
    }

    public static OrderDto toOrderDto(OrderSqlRow orderSqlRow) {
        return new OrderDto(
            orderSqlRow.personId(),
            orderSqlRow.state(),
            orderSqlRow.items().stream().map(OrderMapper::toItemDto).toList(),
            orderSqlRow.items().stream()
                        .mapToDouble(item -> item.price() * item.quantity())
                        .sum());
    }




    private static ItemDto toItemDto(ListItemSqlRow listItemSqlRow) {
        return new ItemDto(
            listItemSqlRow.name(),
            listItemSqlRow.price(),
            listItemSqlRow.quantity()
        );
    }
}
