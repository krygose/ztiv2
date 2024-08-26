package com.example.zti.service.order;

import com.example.zti.service.order.dto.OrdersDto;
import com.example.zti.service.order.sql.OrdersSqlService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {



    OrdersSqlService ordersSqlService;

    public OrdersService(OrdersSqlService ordersSqlService) {
        this.ordersSqlService = ordersSqlService;
    }
    public List<OrdersDto> getOrders() {
        return ordersSqlService.getOrders().stream().map(OrdersMapper::toOrdersDto).toList();
    }

    public Optional<OrdersDto> getOrdersById(String ordersId) {
        return ordersSqlService.getOrdersById(ordersId).stream().map(OrdersMapper::toOrdersDto).findFirst();
    }

    public void modifyState(String id) {
        ordersSqlService.modifyState(id);
    }
}
