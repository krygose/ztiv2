package com.example.zti.controller;

import com.example.zti.service.order.OrdersService;
import com.example.zti.service.order.dto.OrdersDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/orders")
public class OrdersController {
    private final OrdersService ordersService;


    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    // nie wiem czy tak ?
    @GetMapping
    public List<OrdersDto> getOrders() {

        return  ordersService.getOrders();
    }

    // nie wiem czy tak ?
    @GetMapping("/{ordersId}")
    public Optional<OrdersDto> getOrdersByID(@PathVariable String ordersId) {
        return ordersService.getOrdersById(ordersId);
    }

    @PutMapping(path = "/{id}/modifyState")
    public void modifyState(@PathVariable String id){

        ordersService.modifyState(id);
    }
}
