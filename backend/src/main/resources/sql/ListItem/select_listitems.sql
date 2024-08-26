SELECT
    i.name as name,
    l.item_id as item_id,
    l.order_id as order_id,
    i.price as  price,
    l.quantity as quantity
FROM
    list_items AS l
LEFT JOIN item as i on l.item_id = i.id;