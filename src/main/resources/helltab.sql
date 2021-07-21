create database  if not exists shop_demo character set utf8mb4 collate utf8mb4_0900_ai_ci;
use shop_demo;
create table if not exists s_cart(
    id bigint primary key auto_increment,
    user_id bigint not null
)engine=innodb default charset=utf8mb4;

create table if not exists  s_user_info(
    id bigint primary key auto_increment,
    username varchar(50) not null unique,
    password varchar(200) not null,
    fullname varchar(50) not null,
    if_serve varchar(1) not null default 0
)engine=innodb default charset=utf8mb4;

create table if not exists  s_shop(
    id bigint primary key auto_increment,
    shop_name varchar(50) not null unique,
    shop_desc text not null,
    shop_owner_id bigint not null
)engine=innodb default charset=utf8mb4;

create table if not exists  s_goods(
    id bigint primary key auto_increment,
    shop_id bigint not null,
    goods_name varchar(50) not null unique,
    goods_desc text not null,
    goods_price int(15) not null default 0,
    goods_price_unit varchar(10) not null default 'RMB',
    goods_quantity int(15) not null default 0,
    goods_quantity_unit varchar(15) not null default 'kg',
    goods_sold varchar(10) not null default 0,
    goods_images text
)engine=innodb default charset=utf8mb4;

create table if not exists  s_order(
    id bigint primary key auto_increment,
    user_id bigint not null,
    order_number varchar(50) not null unique,
    order_state varchar(1) not null default 0,
    order_price int(15) not null default 0,
    order_price_unit varchar(10) not null default 'RMB'
)engine=innodb default charset=utf8mb4;


create table if not exists  s_r_order_goods(
    id bigint primary key auto_increment,
    order_id bigint not null,
    goods_id bigint not null,
    goods_quantity varchar(10) not null default 1
)engine=innodb default charset=utf8mb4;

create table if not exists  s_r_cart_goods(
    id bigint primary key auto_increment,
    cart_id bigint not null,
    goods_id bigint not null,
    goods_quantity varchar(10) not null default 1
)engine=innodb default charset=utf8mb4;





# INSERT INTO s_user_info VALUES (1,'helltab','111111', '何太波');
