SELECT p.id,
       p.first_name,
       p.last_name,
       p.email,
       p.password,
       p.role
FROM person AS p
WHERE p.email = :email;