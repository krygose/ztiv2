WITH item_list AS (
    SELECT
        i.name AS name,
        i.price AS price,
        il.quantity AS quantity,
        il.order_id AS order_id
    FROM
        list_items AS il
            LEFT JOIN item AS i ON i.id = il.item_id
-- Uncomment the next lines if you want to filter by a specific order
--     WHERE
--         il.order_id = 'o001'
)

SELECT
    o.person_id AS person_id,
    o.state AS state,
    json_agg(
            json_build_object(
                    'name', il.name,
                    'price', il.price,
                    'quantity', il.quantity
            )
    ) AS items
FROM
    orders AS o
        LEFT JOIN item_list AS il ON o.id = il.order_id
WHERE
    o.state = 1
GROUP BY
    o.person_id, o.state