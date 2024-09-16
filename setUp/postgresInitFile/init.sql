-- begin;

-- product db

-- drop database if exists productdb;
-- drop database if exists orderdb;
-- drop database if exists paymentdb;


-- create database productdb;
create database  orderdb;
-- create database  paymentdb;

-- \connect productdb

create table category
(
    id int8 not null
        primary key,
    description varchar(255),
    name        varchar(255)
);

create table product
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

-- order db



\connect orderdb

create table  customer_order(
    id int8 primary key,
    reference varchar(255),
    total_amount decimal(12,4),
    payment_method varchar(255) null,
    customer_id varchar(255) null,
    created_date timestamp null,
    last_modified_date timestamp null
);

create table  customer_line(
    id int8 primary key,
    product_id int8 null,
    quantity decimal(12,4),
    order_id int8 constraint order_id_foreign_key references customer_order
);

create sequence if not exists  category_seq increment by 1;
create sequence if not exists  product_seq increment by 1;
create sequence if not exists   customer_order_seq increment by 1;
create sequence if not exists   customer_line_seq increment by 1;
create sequence if not exists   payment_seq increment by 1;

-- payment db

-- \connect paymentdb

create table  payment (
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
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO postgres;

-- commit;