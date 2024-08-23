package com.example.zti.controller;

import com.example.zti.service.item.ItemService;
import com.example.zti.service.item.dto.ItemDto;
import com.example.zti.service.item.dto.NewItemDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/items")
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public void createItem(@RequestBody NewItemDto newItemDto) {
        itemService.addItem(newItemDto);
    }

    @PostMapping("/modifyItem")
    public void modifyItem(@RequestBody String id, @RequestBody Integer quantity) {
        itemService.modifyItem(id,quantity);
    }


    @GetMapping("/item")
    public List<ItemDto> getItems() {
        return itemService.getItems().content();
    }

    @GetMapping("/itemExist")
    public List<ItemDto> getItemsExist() {
        return itemService.getItemsExist().content();
    }

}
