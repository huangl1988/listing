CREATE TABLE `tb_order_total` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `site_no` varchar(50) NOT NULL COMMENT '座位号' ,
  `total_price` decimal(10,2) NOT NULL COMMENT '总价' ,
  `order_num` int DEFAULT NOT  NULL COMMENT 'order total number' ,
  `order_time` datetime DEFAULT NULL COMMENT 'order create time',
  `inserttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_no` varchar(100) NOT NULL,
  `on_line_flag` tinyint(1) DEFAULT 0 COMMENT '线上标记',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `point` varchar(20) DEFAULT NULL COMMENT '坐标',
  `confirm_flag` tinyint(1) DEFAULT 0 COMMENT '确认标记',
  `confirm_date` datetime DEFAULT NULL COMMENT '确认时间',
  `shop_id` int NOT NULL  COMMENT '商店主键',
  PRIMARY KEY (`id`)
  
)AUTO_INCREMENT = 1,ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table tb_order_details(
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   commody_id bigint(20) NOT NULL,
   `commody_price` decimal(10,2) not null,
    tags_name text default null,
    site_no varchar(100) not null,
    total_id bigint(20) not null,
    order_no varchar(100) not null,
    cancel_flag tinyint(1) default 0,
    `inserttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    cancel_time datetime,
    is_cooking tinyint(1) not null default 0, 
    PRIMARY KEY('id')
)AUTO_INCREMENT = 1,ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table tb_pay_order(
`id` bigint(20) NOT NULL AUTO_INCREMENT,
order_no varchar(100) not null,
price_total decimal(10,2) not null,
site_no varchar(100) not null,
status int not null default 10,
inserttime datetime not null default current_timestamp,
updatetime datetime not null default current_timestamp,
primary key('id'),
UNIQUE KEY `uqx_orderno` (`order_no`) USING BTREE
)auto_increment=1,engine=innodb default charset=utf8mb4;

