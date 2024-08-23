package com.example.zti.controller;

import com.example.zti.service.order.OrderService;
import com.example.zti.service.order.OrdersService;
import com.example.zti.service.order.dto.NewOrderDto;
import com.example.zti.service.order.dto.OrderDto;
import com.example.zti.service.order.dto.OrdersDto;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(path = "/api/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrdersService ordersService;


    OrderController(OrderService orderService, OrdersService ordersService) {
        this.orderService = orderService;
        this.ordersService = ordersService;
    }

     @PostMapping
     public void createOrder(@RequestBody NewOrderDto newOrderDto) {
         orderService.createOrder(newOrderDto);
     }

    @GetMapping("/{orderId}")
    public OrderDto getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }



    @GetMapping("/{orderAll}")
    public OrderDto getOrderAll() {
        return orderService.getOrderAll();
    }

    // nie wiem czy tak ?
    @GetMapping("/{orders}")
    public Stream<OrdersDto> getOrders() {

        return ordersService.getOrders();
    }

    // nie wiem czy tak ?
    @GetMapping("/{ordersId}")
    public Stream<OrdersDto> getOrders(@PathVariable String ordersId) {
        return ordersService.getOrdersById(ordersId);
    }

    @PostMapping("/modifyState")
    public void modifyState(@PathVariable String id){
        ordersService.modifyState(id);
    }

}






