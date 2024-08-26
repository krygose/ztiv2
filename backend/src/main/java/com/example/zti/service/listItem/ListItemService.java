package com.example.zti.service.listItem;

import com.example.zti.common.dto.ContentDto;
import com.example.zti.service.listItem.dto.ListItemDto;
import com.example.zti.service.listItem.dto.NewListItemDto;
import com.example.zti.service.listItem.sql.ListItemSqlService;
import org.springframework.stereotype.Service;

@Service
public class ListItemService {
    ListItemSqlService listItemSqlService;

    public ListItemService(ListItemSqlService listItemSqlService) {
        this.listItemSqlService = listItemSqlService;
    }

    public ContentDto<ListItemDto> getListItems() {
        return new ContentDto<>(listItemSqlService.getListItems().stream().map(ListItemMapper::toListItemDto).toList());

    }

    public void addListItem(NewListItemDto newListItemDto) {
        listItemSqlService.addItem(
                newListItemDto.item_id(),
                newListItemDto.person_order_id(),
                newListItemDto.quantity()
        );

    }
}
