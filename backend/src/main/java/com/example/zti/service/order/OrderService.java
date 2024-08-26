package com.example.zti.service.order;

import com.example.zti.common.ShortUuidGenerator;
import com.example.zti.service.order.dto.NewOrderDto;
import com.example.zti.service.order.dto.OrderDto;
import com.example.zti.service.order.sql.OrderSqlService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderSqlService orderSqlService;

    public OrderService(OrderSqlService orderSqlService) {
        this.orderSqlService = orderSqlService;
    }

    public OrderDto getOrderById(String id) {
        return orderSqlService.getOrderById(id).map(OrderMapper::toOrderDto).orElse(null);
    }


    public void createOrder(NewOrderDto newOrderDto){
        String id = String.format("%s.%s.%s", newOrderDto.personId(), ShortUuidGenerator.generateRandomString(4), "id");
        orderSqlService.createOrder(
                id,
                newOrderDto.personId(),
                newOrderDto.state()
        );

    }

    public List<OrderDto> getOrderAll() {
        return orderSqlService.getOrderAll()
                .stream()
                .map(OrderMapper::toOrderDto)
                .collect(Collectors.toList());
    }



}
