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

    @PutMapping("/{id}/modifyItem/{quantity}")
    public void modifyItem(@PathVariable String id, @PathVariable Integer quantity) {
        itemService.modifyItem(id,quantity);
    }


    @GetMapping
    public List<ItemDto> getItems() {
        return itemService.getItems().content();
    }

    @GetMapping("/itemExist")
    public List<ItemDto> getItemsExist() {
        return itemService.getItemsExist().content();
    }

}
