package com.example.zti.service.order;

import com.example.zti.service.order.dto.OrdersDto;
import com.example.zti.service.order.sql.OrdersSqlService;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class OrdersService {

    OrdersSqlService ordersSqlService;

    public Stream<OrdersDto> getOrders() {
        return ordersSqlService.getOrders().stream().map(OrdersMapper::toOrdersDto);
    }

    public Stream<OrdersDto> getOrdersById(String ordersId) {
        return ordersSqlService.getOrdersById(ordersId).stream().map(OrdersMapper::toOrdersDto);
    }

    public void modifyState(String id) {
        ordersSqlService.modifyState(id);
    }
}
