SELECT
    o.id as id,
    o.person_id as person_id,
    o.state as state

FROM
    orders AS o
where o.state = 0;