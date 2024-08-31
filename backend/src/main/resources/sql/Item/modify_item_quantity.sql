UPDATE item
SET
    quantity = quantity - :quantity
WHERE
    id = :id;