create table customers (
    id                      bigserial,
    name                    varchar(255) not null,
    primary key (id)
);

create table products (
    id                      bigserial primary key,
    title                   varchar(255),
    price                   int
);

create table orders (
    id                      bigserial primary key,
    customer_id             bigint references customers(id),
    price                   int
);

create table order_items (
    id                      bigserial primary key,
    product_id              bigint references products(id),
    order_id                bigint references orders(id),
    price                   int,
    price_per_product       int,
    quantity                int
);

insert into products (title, price)
values
('Apple', 1),
('Meat', 2),
('Milk', 3),
('Bread', 4),
('Salt', 5),
('Sugar', 6),
('Tea', 7),
('Coffee', 8),
('Candy', 9),
('Beer', 10),
('Product11', 11),
('Product12', 12),
('Product13', 13),
('Product14', 14),
('Product15', 15),
('Product16', 16),
('Product17', 17),
('Product18', 18),
('Product19', 19),
('Product20', 20);

insert into customers (name)
values
('Customer1'),
('Customer2'),
('Customer3');