/*
Navicat MySQL Data Transfer

Source Server         : 192.168.212.137
Source Server Version : 50714
Source Host           : 192.168.212.137:3331
Source Database       : pfq_order

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-11-07 19:17:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_charge_snap_shot
-- ----------------------------
DROP TABLE IF EXISTS `tb_charge_snap_shot`;
CREATE TABLE `tb_charge_snap_shot` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` varchar(11) NOT NULL COMMENT '用户Id',
  `isactive` tinyint(1) NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `date` char(8) NOT NULL COMMENT '时间（yyyymmdd）',
  `default_int` tinyint(1) NOT NULL DEFAULT '2' COMMENT '默认次数',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0 初始状态 10 进行中',
  `result` tinyint(4) NOT NULL DEFAULT '0' COMMENT '充值结果',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `amount` bigint(20) NOT NULL COMMENT '金额',
  `optUserName` varchar(50) NOT NULL COMMENT '操作人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_uid_date` (`user_id`,`date`) USING BTREE,
  KEY `idx_inserttime` (`inserttime`),
  KEY `idx_updatetime` (`updatetime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='后台代扣快照 /*# Owner: huangliang ;Manager: yangyang #*/';

-- ----------------------------
-- Table structure for tb_commodity
-- ----------------------------
DROP TABLE IF EXISTS `tb_commodity`;
CREATE TABLE `tb_commodity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `commodity_code` varchar(50) NOT NULL COMMENT '商品编号',
  `commoditiy_name` varchar(255) NOT NULL COMMENT '商品名字',
  `inserttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uqx_cmdcode` (`commodity_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_commody_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_commody_shop`;
CREATE TABLE `tb_commody_shop` (
  `id` bigint(20) NOT NULL,
  `commody_code` varchar(50) NOT NULL,
  `shop_code` varchar(50) NOT NULL,
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `show_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否展示给前台',
  `inserttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `picture_url` varchar(255) NOT NULL,
  `shop_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_spc_cmdc` (`commody_code`,`shop_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_commody_shop_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_commody_shop_tag`;
CREATE TABLE `tb_commody_shop_tag` (
  `id` int(11) NOT NULL,
  `commody_shop_id` bigint(20) NOT NULL,
  `tag_name` varchar(50) NOT NULL,
  `inserttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isTop` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_order_details
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_details`;
CREATE TABLE `tb_order_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_no` varchar(50) NOT NULL,
  `commody_code` varchar(50) NOT NULL,
  `commody_price` decimal(10,2) NOT NULL,
  `activity_id` bigint(20) DEFAULT NULL,
  `inserttime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_order_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_info`;
CREATE TABLE `tb_order_info` (
  `id` bigint(20) NOT NULL,
  `bill_no` varchar(50) NOT NULL,
  `shop_code` varchar(50) NOT NULL,
  `site_no` varchar(3) NOT NULL,
  `inserttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `activity_id` bigint(20) DEFAULT NULL,
  `total_price` decimal(10,2) NOT NULL,
  `is_over` char(1) DEFAULT NULL,
  `rest_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_pay
-- ----------------------------
DROP TABLE IF EXISTS `tb_pay`;
CREATE TABLE `tb_pay` (
  `id` int(11) NOT NULL,
  `bill_no` int(11) NOT NULL,
  `pay_no` int(11) NOT NULL,
  `bill_price` decimal(10,2) NOT NULL,
  `real_price` decimal(10,2) NOT NULL,
  `pay_channel` char(3) NOT NULL,
  `pay_sender_data` text,
  `response_data` text,
  `result` tinyint(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_shop_activity
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_activity`;
CREATE TABLE `tb_shop_activity` (
  `id` int(11) DEFAULT NULL,
  `shop_code` varchar(255) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `inserttime` datetime DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_shop_commody_activty
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_commody_activty`;
CREATE TABLE `tb_shop_commody_activty` (
  `id` int(11) NOT NULL,
  `shop_code` varchar(255) NOT NULL,
  `commody_code` varchar(255) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `inserttime` datetime NOT NULL,
  `updatetime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_shop_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_info`;
CREATE TABLE `tb_shop_info` (
  `id` int(11) NOT NULL,
  `shop_addres` varchar(255) NOT NULL,
  `shop_code` varchar(50) NOT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `close_flag` varchar(255) NOT NULL COMMENT '0',
  `inserttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for tb_shop_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop_tag`;
CREATE TABLE `tb_shop_tag` (
  `id` int(11) NOT NULL,
  `shop_code` varchar(50) NOT NULL,
  `tag_name` varchar(50) NOT NULL,
  `inserttime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatetime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isTop` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
