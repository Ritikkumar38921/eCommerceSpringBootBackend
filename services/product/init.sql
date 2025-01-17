begin;

-- product db

create database if not exists productdb;

\connect productdb

create table if not exists category
(
    id int8 not null
        primary key,
    description varchar(255),
    name        varchar(255)
);

create table if not exists product
(
    id                 integer          not null
        primary key,
    available_quantity double precision not null,
    description        varchar(255),
    name               varchar(255),
    price              numeric(38, 2),
    category_id        integer
        constraint fk1mtsbur82frn64de7balymq9s
            references category
);

<!-- insert value -->

-- INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Keyboards', 'Keyboards');
-- INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Monitors', 'Monitors');
-- INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Display Screens', 'Screens');
-- INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Mice', 'Mice');
-- INSERT INTO category (id, description, name) VALUES (nextval('category_seq'), 'Computer Accessories', 'Accessories');


-- -- Assuming you already have a sequence named 'product_seq'

-- -- Insert products for the 'Keyboards' category
-- INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
-- VALUES
--     (nextval('product_seq'), 10, 'Mechanical keyboard with RGB lighting', 'Mechanical Keyboard 1', 99.99, (SELECT id FROM category WHERE name = 'Keyboards')),
--     (nextval('product_seq'), 15, 'Wireless compact keyboard', 'Wireless Compact Keyboard 1', 79.99, (SELECT id FROM category WHERE name = 'Keyboards')),
--     (nextval('product_seq'), 20, 'Backlit gaming keyboard with customizable keys', 'Gaming Keyboard 1', 129.99, (SELECT id FROM category WHERE name = 'Keyboards')),
--     (nextval('product_seq'), 25, 'Mechanical keyboard with wrist rest', 'Ergonomic Keyboard 1', 109.99, (SELECT id FROM category WHERE name = 'Keyboards')),
--     (nextval('product_seq'), 18, 'Wireless keyboard and mouse combo', 'Wireless Combo 1', 69.99, (SELECT id FROM category WHERE name = 'Keyboards'));

-- -- Insert products for the 'Monitors' category
-- INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
-- VALUES
--     (nextval('product_seq'), 30, '27-inch IPS monitor with 4K resolution', '4K Monitor 1', 399.99, (SELECT id FROM category WHERE name = 'Monitors')),
--     (nextval('product_seq'), 25, 'Ultra-wide gaming monitor with HDR support', 'Ultra-wide Gaming Monitor 1', 499.99, (SELECT id FROM category WHERE name = 'Monitors')),
--     (nextval('product_seq'), 22, '24-inch LED monitor for office use', 'Office Monitor 1', 179.99, (SELECT id FROM category WHERE name = 'Monitors')),
--     (nextval('product_seq'), 28, '32-inch curved monitor with AMD FreeSync', 'Curved Monitor 1', 329.99, (SELECT id FROM category WHERE name = 'Monitors')),
--     (nextval('product_seq'), 35, 'Portable USB-C monitor for laptops', 'Portable Monitor 1', 249.99, (SELECT id FROM category WHERE name = 'Monitors'));

-- -- Insert products for the 'Screens' category
-- INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
-- VALUES
--     (nextval('product_seq'), 15, 'Curved OLED gaming screen with 240Hz refresh rate', 'Curved OLED Gaming Screen 1', 799.99, (SELECT id FROM category WHERE name = 'Screens')),
--     (nextval('product_seq'), 18, 'Flat QLED monitor with 1440p resolution', 'QLED Monitor 1', 599.99, (SELECT id FROM category WHERE name = 'Screens')),
--     (nextval('product_seq'), 22, '27-inch touch screen display for creative work', 'Touch Screen Display 1', 699.99, (SELECT id FROM category WHERE name = 'Screens')),
--     (nextval('product_seq'), 20, 'Ultra-slim 4K HDR display for multimedia', 'Ultra-slim 4K HDR Display 1', 449.99, (SELECT id FROM category WHERE name = 'Screens')),
--     (nextval('product_seq'), 25, 'Gaming projector with low input lag', 'Gaming Projector 1', 899.99, (SELECT id FROM category WHERE name = 'Screens'));

-- -- Insert products for the 'Mice' category
-- INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
-- VALUES
--     (nextval('product_seq'), 30, 'Wireless gaming mouse with customizable RGB lighting', 'RGB Gaming Mouse 1', 59.99, (SELECT id FROM category WHERE name = 'Mice')),
--     (nextval('product_seq'), 28, 'Ergonomic wired mouse for productivity', 'Ergonomic Wired Mouse 1', 29.99, (SELECT id FROM category WHERE name = 'Mice')),
--     (nextval('product_seq'), 32, 'Ambidextrous gaming mouse with high DPI', 'Ambidextrous Gaming Mouse 1', 69.99, (SELECT id FROM category WHERE name = 'Mice')),
--     (nextval('product_seq'), 26, 'Travel-sized compact mouse for laptops', 'Travel Mouse 1', 19.99, (SELECT id FROM category WHERE name = 'Mice')),
--     (nextval('product_seq'), 35, 'Vertical ergonomic mouse for reduced strain', 'Vertical Ergonomic Mouse 1', 39.99, (SELECT id FROM category WHERE name = 'Mice'));

-- -- Insert products for the 'Accessories' category
-- INSERT INTO public.product (id, available_quantity, description, name, price, category_id)
-- VALUES
--     (nextval('product_seq'), 25, 'Adjustable laptop stand with cooling fan', 'Adjustable Laptop Stand 1', 34.99, (SELECT id FROM category WHERE name = 'Accessories')),
--     (nextval('product_seq'), 20, 'Wireless charging pad for smartphones', 'Wireless Charging Pad 1', 24.99, (SELECT id FROM category WHERE name = 'Accessories')),
--     (nextval('product_seq'), 28, 'Gaming headset stand with RGB lighting', 'RGB Headset Stand 1', 49.99, (SELECT id FROM category WHERE name = 'Accessories')),
--     (nextval('product_seq'), 22, 'Bluetooth mechanical keypad for tablets', 'Bluetooth Keypad 1', 39.99, (SELECT id FROM category WHERE name = 'Accessories')),
--     (nextval('product_seq'), 30, 'External hard drive enclosure with USB-C', 'External Hard Drive Enclosure 1', 29.99, (SELECT id FROM category WHERE name = 'Accessories'));


-- order db


create database if not exists orderdb;
\connect orderdb

create table if not exists customer_order(
    id int8 primary key,
    reference varchar(255),
    total_amount decimal(12,4),
    payment_method varchar(255) null,
    customer_id varchar(255) null,
    created_date timestamp null,
    last_modified_date timestamp null
);

create table if not exists customer_line(
    id int8 primary key,
    product_id int8 null,
    quantity decimal(12,4),
    order_id int8 add constraint order_id_foreign_key references customer_order
);

create sequence if not exists category_seq increment by 50;
create sequence if not exists product_seq increment by 50;
create sequence if not exists customer_order_seq increment by 1;
create sequence if not exists customer_line_seq increment by 1;
create sequence if not exists payment_seq increment by 1;

-- payment db

create database if not exists paymentdb;
\connect paymentdb

create table if not exists payment (
    id int8 primary key,
    amount decimal(12,4) null,
    payment_method varchar(255) null,
    order_id int8 null,
    created_date timestamp null,
    last_modified_date timestamp null
);



-- grant permission to user

GRANT ALL PRIVILEGES ON DATABASE productdb TO postgres;
GRANT ALL PRIVILEGES ON DATABASE orderdb TO postgres;
GRANT ALL PRIVILEGES ON DATABASE paymentdb TO postgres;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO postgres;
GRANT USAGE, SELECT ON ALL SEQUENCE IN SCHEMA public TO postgres;

commit;