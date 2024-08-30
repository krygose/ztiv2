UPDATE orders
SET
    state = 1

WHERE
    id = :id;