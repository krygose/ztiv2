package com.example.zti.controller;

import com.example.zti.service.order.OrderService;
import com.example.zti.service.order.dto.NewOrderDto;
import com.example.zti.service.order.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/order")
public class OrderController {

    private final OrderService orderService;


    OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

     @PostMapping
     public void createOrder(@RequestBody NewOrderDto newOrderDto) {
         orderService.createOrder(newOrderDto);
     }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }



    @GetMapping
    public List<OrderDto> getOrderAll() {
        return orderService.getOrderAll();
    }



}






