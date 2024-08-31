package com.example.zti.service.order;

import com.example.zti.common.ShortUuidGenerator;
import com.example.zti.common.problem.InternalServerErrorProblem;
import com.example.zti.service.item.sql.ItemSqlService;
import com.example.zti.service.listItem.sql.ListItemSqlService;
import com.example.zti.service.order.dto.NewOrderDto;
import com.example.zti.service.order.dto.OrderDto;
import com.example.zti.service.order.sql.OrderSqlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderSqlService orderSqlService;
    private final ListItemSqlService listItemSqlService;
    private final ItemSqlService itemSqlService;

    public OrderService(OrderSqlService orderSqlService, ListItemSqlService listItemSqlService, ItemSqlService itemSqlService) {
        this.orderSqlService = orderSqlService;
        this.listItemSqlService = listItemSqlService;
        this.itemSqlService = itemSqlService;
    }

    public OrderDto getOrderById(String id) {
        return orderSqlService.getOrderById(id).map(OrderMapper::toOrderDto).orElse(null);
    }


    @Transactional
    public void createOrder(NewOrderDto newOrderDto){
        try {
            String orderId = String.format("%s.%s.%s", newOrderDto.personId(), ShortUuidGenerator.generateRandomString(4), "id");
            orderSqlService.createOrder(
                    orderId,
                    newOrderDto.personId(),
                    newOrderDto.state()
            );

            for (NewOrderDto.NewOrderItem item : newOrderDto.itemList()) {
                listItemSqlService.addItem(item.id(), orderId, item.quantity());
                itemSqlService.modifyItem(item.id(), item.quantity());
            }
        } catch (Exception e) {
            throw new InternalServerErrorProblem();
        }
    }

    public List<OrderDto> getOrderAll() {
        return orderSqlService.getOrderAll()
                .stream()
                .map(OrderMapper::toOrderDto)
                .collect(Collectors.toList());
    }



}
