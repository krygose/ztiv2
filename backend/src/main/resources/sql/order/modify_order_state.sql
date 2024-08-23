UPDATE orders
SET
    state = 0

WHERE
    id = :id;