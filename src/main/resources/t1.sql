select @@autocommit;

# read uncommitted; --读未提交
# read commited;--读已提交
# repeatable read;--可重复读
# (serializable)--序列化执行，串行执行

insert into s_user_info(`username`, `password`, `fullname`, `if_serve`) values('testTransaction', '111111', '测试事务',0);
######################👇读未提交👇#########################
set autocommit = 0;
use shop_demo;
set session transaction isolation level read uncommitted ;
start transaction;
# 原始
select *
from s_user_info;
# 开启其他事务并查询原始数据
# 事务没有提交, 但是其他事务已经可以读到了
update s_user_info set fullname = '测试事务_读未提交' where username = 'testTransaction';
# 自己事务能读到, 其他事务也能读到
select *
from s_user_info;
commit;
######################👆读未提交👆#########################


######################👇读已提交👇#########################
set autocommit = 0;
use shop_demo;
set session transaction isolation level  read committed ;
start transaction;
# 查询原始
select *
from s_user_info;
# switch: 其他事务开始查询
# 事务没有提交,  其他事务读不到
update s_user_info set fullname = '测试事务_读未提交' where username = 'testTransaction';
# switch: 其他事务开始查询
# 自己事务能读到, 其他事务读不到
select *
from s_user_info;
commit;
# switch: 其他事务开始查询
######################👆读已提交👆#########################
set session transaction isolation level serializable ;

start transaction;
use shop_demo;
select * from s_user_info;

delete from s_user_info where username='testTransaction_v';

select * from s_user_info;

# update s_user_info set fullname = '测试事务04' where username = 'testTransaction';

 insert into s_user_info(`username`, `password`, `fullname`, `if_serve`) values('testTransaction_v', '111111', '测试事务_幻读',0);

select * from s_user_info;
commit;
