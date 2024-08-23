SELECT
    p.id AS id,
    p.first_name AS first_name,
    p.last_name AS last_name,
    p.email AS email,
    p.role AS role
FROM
    person AS p
WHERE
    p.id = ?;