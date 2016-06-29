create database rst character set=utf8;

use rst;

create table tbl_user(
	id int not null auto_increment, 
	user_name varchar(32) not null comment '姓名',
	login_name varchar(32) not null comment '登录名',
	password varchar(32) not null comment '密码',
	description varchar(512) default null comment '描述',

	primary key(id)
) comment='用户';

create table tbl_menu(
	id int auto_increment not null,
	name varchar(512) not null comment '菜名',
	description varchar(512) not null comment '描述',
	price double(5,2) not null comment '价格',

	primary key(id)
) comment='菜单';

create table tbl_order(
	id int auto_increment not null,
	g_time datetime not null comment '订单生成时间',
	state int not null comment '状态：0.未知；1.未完成；2.已完成；3.取消',
	menu_id int not null comment '菜单ID',
	copies int not null comment '份数',
	num int not null comment '顾客号码',
	comment varcahr(512) default null comment '备注',

	primary key(id),
	foreign key(menu_id) references tbl_menu(id)
) comment='订单';

create table tbl_oper_log(
	id int auto_increment not null,
	category int not null comment '类别',
	g_time datetime not null comment '日志生成时间',
	user_id int not null comment '当前操作用户',
	state int not null comment '日志状态：0.未知；1.成功；2.失败',
	content varchar(512) not null comment '操作内容',
	reason varchar(512) default null comment '操作原因',

	primary key(id),
	foreign key(user_id) references tbl_user(id)
) comment='操作日志';

create table tbl_customer(
	id int auto_increment not null,
	first_name varchar(32),
	last_name varchar(32),

	primary key(id)
);
