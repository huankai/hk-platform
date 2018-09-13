/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.76
Source Server Version : 50715
Source Host           : 192.168.1.76:3306
Source Database       : hk_emi

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-09-13 17:17:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_base_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_base_code`;
CREATE TABLE `sys_base_code` (
  `id` char(32) NOT NULL,
  `base_code` varchar(20) NOT NULL COMMENT '编号',
  `code_name` varchar(50) NOT NULL COMMENT '名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL COMMENT '创建人id',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `last_modified_by` char(32) NOT NULL COMMENT '最后更新人id',
  `last_modified_date` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `base_code` (`base_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_base_code
-- ----------------------------
INSERT INTO `sys_base_code` VALUES ('4028c081655a3a5a01655a3acd160000', 'CSLB', ' 城市类型', ' 城市类型', '4028c08162bda8ce0162bda8df6a0000', '2018-08-21 10:05:28', '4028c08162bda8ce0162bda8df6a0000', '2018-08-21 10:06:31');
INSERT INTO `sys_base_code` VALUES ('4028c081655a3a5a01655a3acd160001', 'SFYX', '是否有效', '是否有效', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:57:34', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:57:37');
INSERT INTO `sys_base_code` VALUES ('4028c081658f05b301658f0740970000', 'RDXB', '人的性别', '人的性别', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:09:02', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:09:02');
INSERT INTO `sys_base_code` VALUES ('4028c081658f05b301658f0bf9b70005', 'YHZT', '用户状态', '用户状态', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:14:11', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:14:11');

-- ----------------------------
-- Table structure for sys_child_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_child_code`;
CREATE TABLE `sys_child_code` (
  `id` char(32) NOT NULL,
  `base_code_id` char(32) DEFAULT NULL,
  `child_code` varchar(20) NOT NULL,
  `code_name` varchar(50) NOT NULL,
  `code_value` tinyint(1) NOT NULL,
  `state` tinyint(1) NOT NULL COMMENT '状态(0,不可用，1：可用)',
  `description` varchar(100) DEFAULT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `base_code_id` (`base_code_id`,`child_code`) USING BTREE,
  CONSTRAINT `base_code_id` FOREIGN KEY (`base_code_id`) REFERENCES `sys_base_code` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_child_code
-- ----------------------------
INSERT INTO `sys_child_code` VALUES ('4028c081655a7a5a01615a3acd160000', '4028c081655a3a5a01655a3acd160000', 'CONTURY', ' 国家', '1', '1', ' 国家', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:50:06', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:50:10');
INSERT INTO `sys_child_code` VALUES ('4028c081655a7a5a01615a3acd160001', '4028c081655a3a5a01655a3acd160000', 'PROVINCE', '省', '2', '1', '省', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:50:29', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:50:31');
INSERT INTO `sys_child_code` VALUES ('4028c081655a7a5a01615a3acd160002', '4028c081655a3a5a01655a3acd160000', 'CITY', '市', '3', '1', '市', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:53:57', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:53:59');
INSERT INTO `sys_child_code` VALUES ('4028c081655a7a5a01615a3acd160003', '4028c081655a3a5a01655a3acd160000', 'AREA', '区或县', '4', '1', '区或县', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:54:41', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:54:44');
INSERT INTO `sys_child_code` VALUES ('4028c081655a7a5a01615a3acd160004', '4028c081655a3a5a01655a3acd160000', 'TOWN', '镇', '5', '1', '镇', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:55:07', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:55:10');
INSERT INTO `sys_child_code` VALUES ('4028c081655a7a5a01615a3acd160005', '4028c081655a3a5a01655a3acd160000', 'VILLAGE', '村', '6', '1', '村', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:55:40', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 14:55:43');
INSERT INTO `sys_child_code` VALUES ('4028c081655a7a5a01615a3acd160006', '4028c081655a3a5a01655a3acd160001', 'YES', '是', '1', '1', '是', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 15:30:46', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 15:30:43');
INSERT INTO `sys_child_code` VALUES ('4028c081655a7a5a01615a3acd160007', '4028c081655a3a5a01655a3acd160001', 'NO', '否', '1', '1', '否', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 15:31:16', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 15:31:13');
INSERT INTO `sys_child_code` VALUES ('4028c081658f05b301658f090ad50001', '4028c081658f05b301658f0740970000', 'MAN', '男', '1', '1', '男', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:10:59', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:10:59');
INSERT INTO `sys_child_code` VALUES ('4028c081658f05b301658f0966c20002', '4028c081658f05b301658f0740970000', 'WOMAN', '女', '2', '1', '女', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:11:23', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:11:23');
INSERT INTO `sys_child_code` VALUES ('4028c081658f05b301658f09bcb40003', '4028c081658f05b301658f0740970000', 'UNKNOWN', '未知', '9', '1', '未知', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:11:45', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:11:45');
INSERT INTO `sys_child_code` VALUES ('4028c081658f05b301658f0c62e10006', '4028c081658f05b301658f0bf9b70005', 'ENABLE', '启用', '1', '1', '启用', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:14:38', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:14:38');
INSERT INTO `sys_child_code` VALUES ('4028c081658f05b301658f0cc3b50007', '4028c081658f05b301658f0bf9b70005', 'DISABLE', '锁定', '2', '1', '锁定', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:15:03', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:15:03');
INSERT INTO `sys_child_code` VALUES ('4028c081658f05b301658f0d44ed0008', '4028c081658f05b301658f0bf9b70005', 'DELETED', '已删除', '9', '1', '已删除', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:15:36', '4028c08162bda8ce0162bda8df6a0000', '2018-08-31 16:15:36');

-- ----------------------------
-- Table structure for sys_city
-- ----------------------------
DROP TABLE IF EXISTS `sys_city`;
CREATE TABLE `sys_city` (
  `id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL COMMENT '上级id',
  `code` varchar(20) NOT NULL,
  `city_type` tinyint(1) NOT NULL COMMENT '城市类型，1:国家,2:省或直辖市,3:市,4:区或县,5:镇,6:村',
  `full_name` varchar(50) NOT NULL COMMENT '全名',
  `short_name` varchar(50) NOT NULL COMMENT '简称',
  `post_office` varchar(10) DEFAULT NULL COMMENT '邮政编码',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_city
-- ----------------------------
INSERT INTO `sys_city` VALUES ('4028c08162be57660162be5779cb0000', '4028c08162be57660162be5779cb0000', '1', '1', '中国', ' 中国', '1', '中国setDescriptionaaa', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 17:16:44', '4028c08162bda8ce0162bda8df6a0000', '2018-07-11 16:38:25');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08370000', '4028c08162be57660162be5779cb0000', '110000', '2', '北京市', '北京', '110000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420001', '4028c08162be57660162be5779cb0000', '120000', '2', '天津市', '天津', '120000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420002', '4028c08162be57660162be5779cb0000', '130000', '2', '河北省', '河北', '130000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420003', '4028c08162be57660162be5779cb0000', '140000', '2', '山西省', '山西', '140000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420004', '4028c08162be57660162be5779cb0000', '150000', '2', '内蒙古自治区', '内蒙古', '150000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420005', '4028c08162be57660162be5779cb0000', '210000', '2', '辽宁省', '辽宁', '210000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420006', '4028c08162be57660162be5779cb0000', '220000', '2', '吉林省', '吉林', '220000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420007', '4028c08162be57660162be5779cb0000', '230000', '2', '黑龙江省', '黑龙江', '230000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420008', '4028c08162be57660162be5779cb0000', '310000', '2', '上海市', '上海', '310000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08430009', '4028c08162be57660162be5779cb0000', '320000', '2', '江苏省', '江苏', '320000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000a', '4028c08162be57660162be5779cb0000', '330000', '2', '浙江省', '浙江省', '330000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000b', '4028c08162be57660162be5779cb0000', '340000', '2', '安徽省', '安徽', '340000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000c', '4028c08162be57660162be5779cb0000', '350000', '2', '福建省', '福建', '350000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000d', '4028c08162be57660162be5779cb0000', '360000', '2', '江西省', '江西', '360000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000e', '4028c08162be57660162be5779cb0000', '370000', '2', '山东省', '山东', '370000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000f', '4028c08162be57660162be5779cb0000', '410000', '2', '河南省', '河南', '410000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440010', '4028c08162be57660162be5779cb0000', '420000', '2', '湖北省', '湖北', '420000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440011', '4028c08162be57660162be5779cb0000', '430000', '2', '湖南省', '湖南', '430000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440012', '4028c08162be57660162be5779cb0000', '440000', '2', '广东省', '广东', '440000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440013', '4028c08162be57660162be5779cb0000', '450000', '2', '广西省', '广西', '450000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440014', '4028c08162be57660162be5779cb0000', '460000', '2', '海南省', '海南', '460000', null, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
