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
    o.state = 0
GROUP BY
    o.person_id, o.state;