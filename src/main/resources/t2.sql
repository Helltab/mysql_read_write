select @@autocommit;

######################👇读未提交👇#########################
set autocommit = 0;
use shop_demo;
set session transaction isolation level read uncommitted ;
start transaction;
# 原始
select *
from s_user_info;
# 其他事务没有提交, 这里就可以读到更改
select *
from s_user_info;
# 其他事务提交了, 这里可以读到
select *
from s_user_info;
commit;
######################👆读未提交👆#########################

######################👇读已提交👇#########################
set autocommit = 0;
use shop_demo;
set session transaction isolation level read uncommitted ;
start transaction;
# 原始
select *
from s_user_info;
# 执行其他事物的更改操作
# 其他事务没有提交, 这里不可以读到更改
select *
from s_user_info;
# 其他事务提交了, 这里可以读到
select *
from s_user_info;
commit;
######################👆读已提交👆#########################
delete from s_user_info where username='testTransaction_v';
insert into s_user_info(`username`, `password`, `fullname`, `if_serve`) values('testTransaction_v', '111111', '测试事务_幻读',0);

# insert into s_user_info(`username`, `password`, `fullname`, `if_serve`) values('testTransaction', '111111', '测试事务',0);
