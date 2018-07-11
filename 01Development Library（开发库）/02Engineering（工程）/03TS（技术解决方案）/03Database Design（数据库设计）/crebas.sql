/*
Navicat MySQL Data Transfer

Source Server         : MySQL56 at localhost
Source Server Version : 50634
Source Host           : localhost:3306
Source Database       : hdproject

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2018-07-11 20:30:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `acc_id` decimal(10,0) NOT NULL,
  `bus_id` decimal(10,0) NOT NULL,
  `account_type` varchar(50) NOT NULL COMMENT '���ݷ�Χ��1~4\r\n            1������Ա\r\n            2��ǰ̨\r\n            3���ͻ�����\r\n            4������',
  `acc_name` varchar(50) NOT NULL,
  `acc_psd` varchar(50) NOT NULL,
  `isStart` tinyint(1) NOT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`acc_id`),
  KEY `FK_Relationship_10` (`bus_id`),
  KEY `FK_Relationship_8` (`account_type`),
  CONSTRAINT `FK_Relationship_10` FOREIGN KEY (`bus_id`) REFERENCES `business` (`bus_id`),
  CONSTRAINT `FK_Relationship_8` FOREIGN KEY (`account_type`) REFERENCES `power` (`account_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `bus_id` decimal(10,0) NOT NULL,
  `con_id` decimal(10,0) NOT NULL,
  `bus_type` tinyint(50) NOT NULL,
  `bus_name` varchar(50) NOT NULL,
  `bus_add` varchar(50) NOT NULL,
  `bus_phone` varchar(50) NOT NULL,
  `bus_postcode` decimal(10,0) DEFAULT NULL,
  `bus_star` int(11) DEFAULT NULL COMMENT '���ݷ�Χ��0~5  ��ʾ0��5�Ǽ�',
  `con_intergral` decimal(10,0) NOT NULL,
  `discount` decimal(10,0) NOT NULL,
  PRIMARY KEY (`bus_id`),
  KEY `FK_Relationship_1` (`con_id`),
  KEY `FK_business_businesstype` (`bus_type`),
  CONSTRAINT `FK_Relationship_1` FOREIGN KEY (`con_id`) REFERENCES `contacts` (`con_id`),
  CONSTRAINT `FK_business_businesstype` FOREIGN KEY (`bus_type`) REFERENCES `businesstype` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for businesstype
-- ----------------------------
DROP TABLE IF EXISTS `businesstype`;
CREATE TABLE `businesstype` (
  `type_id` tinyint(255) NOT NULL COMMENT '企业类别编号',
  `type_name` varchar(255) NOT NULL COMMENT '类别名称',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contacts
-- ----------------------------
DROP TABLE IF EXISTS `contacts`;
CREATE TABLE `contacts` (
  `con_id` decimal(10,0) NOT NULL,
  `con_title` enum('0','1') NOT NULL DEFAULT '0' COMMENT '称谓：0.先生  1.女士',
  `con_name` varchar(50) NOT NULL,
  `con_position` varchar(50) NOT NULL,
  `con_tel` varchar(50) NOT NULL,
  `con_mobile` decimal(20,0) DEFAULT NULL,
  `con_fax` decimal(20,0) DEFAULT NULL,
  `con_email` varchar(30) NOT NULL,
  PRIMARY KEY (`con_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `log_id` decimal(10,0) NOT NULL,
  `acc_id` decimal(10,0) DEFAULT NULL,
  `log_type` tinyint(1) DEFAULT NULL COMMENT 'trueΪ��½��falseΪ�˳�',
  `log_time` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  KEY `FK_Relationship_12` (`acc_id`),
  CONSTRAINT `FK_Relationship_12` FOREIGN KEY (`acc_id`) REFERENCES `account` (`acc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `opt_id` decimal(10,0) NOT NULL,
  `acc_id` decimal(10,0) DEFAULT NULL,
  `opt_date` datetime NOT NULL,
  `opt_url` varchar(50) DEFAULT NULL,
  `opt_fun` varchar(50) DEFAULT NULL,
  `opt_exp` varchar(50) DEFAULT NULL,
  `remarks` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`opt_id`),
  KEY `FK_Relationship_5` (`acc_id`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`acc_id`) REFERENCES `account` (`acc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `account_type` varchar(50) NOT NULL COMMENT '���ݷ�Χ��1~4\r\n            1������Ա\r\n            2��ǰ̨\r\n            3���ͻ�����\r\n            4������',
  `record` tinyint(1) DEFAULT NULL COMMENT 'true:ֻ���޸�30�����ڼ�¼����ֻ���޸��Լ��˻���ӵĽӴ���¼\r\n            false:�����޸ı���48Сʱ�ڵĽӴ���¼',
  `information` tinyint(1) DEFAULT NULL COMMENT 'true:ֻ�����޸�30�����ڼ�¼����ֻ���޸��Լ��˻���ӵĻ�Ա����\r\n            false:�κ�ʱ������޸ı���Ļ�Ա��Ϣ',
  `business` tinyint(1) DEFAULT NULL COMMENT 'true:����\r\n            false:������',
  `report` tinyint(1) DEFAULT NULL COMMENT 'true:����\r\n            false:������',
  `account` tinyint(1) DEFAULT NULL COMMENT 'true:����\r\n            false:������',
  `cardtype` tinyint(1) DEFAULT NULL COMMENT 'true:����\r\n            false:������',
  `recharge` tinyint(1) DEFAULT NULL COMMENT 'true:����\r\n            false:������',
  `manager` tinyint(1) DEFAULT NULL COMMENT 'true:����\r\n            false:������',
  PRIMARY KEY (`account_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
