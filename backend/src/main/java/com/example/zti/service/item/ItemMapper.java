package com.example.zti.service.item;

import com.example.zti.service.item.dto.ItemDto;
import com.example.zti.service.item.sql.ItemSqlRow;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public record ItemMapper(ObjectMapper objectMapper) implements RowMapper<ItemSqlRow> {
    @Override
    public ItemSqlRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ItemSqlRow (
                rs.getString("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("quantity"));
    }

    public static ItemDto toItemDto(ItemSqlRow itemSqlRow){
        return new ItemDto(
                itemSqlRow.id(),
                itemSqlRow.name(),
                itemSqlRow.price(),
                itemSqlRow.quantity());
    }

    public static ItemDto toItemDto(
            String id,
            String name,
            Double price,
            Integer quantity
    ){
        return new ItemDto(
                id,
                name,price,quantity);
    }
}
