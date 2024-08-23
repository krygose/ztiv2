package com.example.zti.service.order;

import com.example.zti.service.order.dto.OrdersDto;
import com.example.zti.service.order.sql.OrdersSqlRow;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public record OrdersMapper(ObjectMapper objectMapper) implements RowMapper<OrdersSqlRow> {

    @Override
    public OrdersSqlRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrdersSqlRow (
                rs.getString("id"),
                rs.getString("personId"),
                rs.getBoolean("state")
                );
    }

    public static OrdersDto toOrdersDto(OrdersSqlRow ordersSqlRow){
        return new OrdersDto(
                ordersSqlRow.id(),
                ordersSqlRow.personId(),
                ordersSqlRow.state()
        );
    }
}
