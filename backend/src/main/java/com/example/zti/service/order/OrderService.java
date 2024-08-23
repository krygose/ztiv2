package com.example.zti.service.order;

import com.example.zti.common.ShortUuidGenerator;
import com.example.zti.service.order.dto.NewOrderDto;
import com.example.zti.service.order.dto.OrderDto;
import com.example.zti.service.order.sql.OrderSqlService;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    OrderSqlService orderSqlService;

    public OrderService(OrderSqlService orderSqlService) {
        this.orderSqlService = orderSqlService;
    }

    public OrderDto getOrderById(String id) {
        return orderSqlService.getOrderById(id).map(OrderMapper::toOrderDto).orElse(null);
    }



//     public ContentDto<OrderDto> getOrders(){
//         return new ContentDto<>(orderSqlService.getOrders().stream().map(OrderMapper::toOrderDto).toList());
//     }

    public void createOrder(NewOrderDto newOrderDto){
        String id = String.format("%s.%s.%s", newOrderDto.personId(), ShortUuidGenerator.generateRandomString(4), "id");
        orderSqlService.createOrder(
                id,
                newOrderDto.personId(),
                newOrderDto.state()
        );
//         return OrderMapper.toOrderDto(
//                 id,
//                 newOrderDto.personId(),
//                 newOrderDto.state()
//         );
    }

    public OrderDto getOrderAll() {
        return orderSqlService.getOrderAll().map(OrderMapper::toOrderDto).orElse(null);
    }


}
