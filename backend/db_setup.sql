create table person
(
    id         varchar(100) NOT NULL primary key,
    first_name varchar(30)  NOT NULL,
    last_name  varchar(30),
    email      varchar(50)  NOT NULL,
    password   text         NOT NULL,
    role       varchar(20)  NOT NULL
);

create table item
(
    id       varchar(30)      NOT NULL primary key,
    name     varchar(30)      NOT NULL,
    price    double precision NOT NULL,
    quantity integer          NOT NULL
);

create table orders
(
    id        varchar(30) NOT NULL primary key,
    person_id varchar(30) NOT NULL,
    state     integer     NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person (id)

);

CREATE TABLE list_items
(
    item_id  varchar(30) NOT NULL,
    order_id varchar(30) NOT NULL,
    quantity integer     NOT NULL,
    FOREIGN KEY (item_id) REFERENCES item (id),
    FOREIGN KEY (order_id) REFERENCES orders (id)
);


CREATE VIEW item_list AS
SELECT
    i.name AS name,
    i.price AS price,
    il.quantity AS quantity,
    il.order_id AS order_id
FROM
    list_items AS il
        LEFT JOIN item AS i ON i.id = il.item_id;
