use shop_demo;
# 注册用户

insert into s_user_info(`username`, `password`, `fullname`, `if_serve`) values('buyer001', '111111', '买家一号',0);
#注册商户
set autocommit = 0;
start transaction;
insert into s_user_info(`username`, `password`, `fullname`, `if_serve`) values('serve003', '111111', '卖家三号',1);
insert into s_shop(`shop_name`, `shop_desc`, `shop_owner_id`)
select '神仙炒货店', '这是一家神仙炒货店, 神仙来了都说好', id from s_user_info where username = 'serve001';
commit;

# 添加商品
use shop_demo;
insert into s_goods(`goods_name`, `goods_desc`, `goods_price`, `goods_price_unit`, `goods_quantity`, `goods_quantity_unit`, `shop_id`)
select '炒栗子', '各种口味炒栗子, 神仙来了都说好', 20 ,'RMB',1000,'kg', id from s_shop where shop_name = '神仙炒货店';


# 添加到购物车

insert into s_cart(`user_id`)
select id from s_user_info where username = 'buyer001';

insert into s_r_cart_goods(`cart_id`, `goods_id`)
select a.id, b.id from s_cart a, s_goods b where 1=1
 and a.user_id= (select id from s_user_info where username = 'buyer001')
and b.goods_name = '炒栗子'
