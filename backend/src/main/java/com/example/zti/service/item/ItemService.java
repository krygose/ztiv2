package com.example.zti.service.item;

import com.example.zti.common.dto.ContentDto;
import com.example.zti.service.item.dto.ItemDto;
import com.example.zti.service.item.dto.NewItemDto;
import com.example.zti.service.item.sql.ItemSqlService;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    ItemSqlService itemSqlService;

    public ItemService(ItemSqlService itemService) {
        this.itemSqlService = itemService;
    }

    public ContentDto<ItemDto> getItems() {
        return new ContentDto<>(itemSqlService.getItems().stream().map(ItemMapper::toItemDto).toList());
    }

    public void addItem(NewItemDto newItemDto) {
        String id = newItemDto.name().toLowerCase();
        itemSqlService.addItem(
                id,
                newItemDto.name(),
                newItemDto.price(),
                newItemDto.quantity()

        );
//        return ItemMapper.toItemDto(
//                id,
//                newItemDto.name(),
//                newItemDto.price(),
//                newItemDto.quantity());
    }

    public void modifyItem(String id, Integer quantity) {
        itemSqlService.modifyItem(id, quantity);
    }

    public ContentDto<ItemDto> getItemsExist() {
        return new ContentDto<>(itemSqlService.getItemsExist().stream().map(ItemMapper::toItemDto).toList());
    }
}
