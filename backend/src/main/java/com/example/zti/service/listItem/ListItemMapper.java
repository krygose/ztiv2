package com.example.zti.service.listItem;


import com.example.zti.service.listItem.dto.ListItemDto;
import com.example.zti.service.listItem.sql.ListItemSqlRow;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public record ListItemMapper(ObjectMapper objectMapper) implements RowMapper<ListItemSqlRow> {

    @Override
    public ListItemSqlRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ListItemSqlRow (
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getString("orderId"));
    }
    public static ListItemDto toListItemDto(ListItemSqlRow listItemSqlRow){
        return new ListItemDto(
                listItemSqlRow.name(),
                listItemSqlRow.orderId(),
                listItemSqlRow.quantity()
        );
    }

    public  static ListItemDto toListItemDto(
            String item_id,
            String person_order_id,
            Integer quantity
    ){
        return new ListItemDto(item_id,person_order_id,quantity);
    }
}
