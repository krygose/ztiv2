SELECT
    o.id,
    o.person_id,
    o.state
FROM
    orders AS o
where
    o.id = :id;