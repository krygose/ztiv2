package com.example.zti.controller;

import com.example.zti.service.listItem.ListItemService;
import com.example.zti.service.listItem.dto.ListItemDto;
import com.example.zti.service.listItem.dto.NewListItemDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listItem")
public class ListItemController {

    private final ListItemService listItemService;

    public ListItemController(ListItemService listItemService) {
        this.listItemService = listItemService;
    }

    @PostMapping
    public void createListItem(@RequestBody NewListItemDto newListItemDto) {listItemService.addListItem(newListItemDto); }

    @GetMapping("/list")
    public List<ListItemDto> getListItem(){
        return listItemService.getListItems().content();
    }
}
