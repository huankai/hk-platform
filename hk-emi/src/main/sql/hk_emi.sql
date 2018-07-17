/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.76
 Source Server Type    : MySQL
 Source Server Version : 50715
 Source Host           : 192.168.1.76:3306
 Source Schema         : hk_emi

 Target Server Type    : MySQL
 Target Server Version : 50715
 File Encoding         : 65001

 Date: 13/07/2018 18:01:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_base_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_base_code`;
CREATE TABLE `sys_base_code`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `base_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `code_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `created_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人id',
  `created_date` datetime(0) NOT NULL COMMENT '创建时间',
  `last_modified_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最后更新人id',
  `last_modified_date` datetime(0) NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_code`(`base_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_child_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_child_code`;
CREATE TABLE `sys_child_code`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `base_code_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `child_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `code_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `state` tinyint(1) NOT NULL COMMENT '状态(0,不可用，1：可用)',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_date` datetime(0) NULL DEFAULT NULL,
  `last_modified_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_modified_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `base_code_id`(`base_code_id`, `child_code`) USING BTREE,
  CONSTRAINT `base_code_id` FOREIGN KEY (`base_code_id`) REFERENCES `sys_base_code` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_city
-- ----------------------------
DROP TABLE IF EXISTS `sys_city`;
CREATE TABLE `sys_city`  (
  `id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '上级id',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `city_type` tinyint(1) NOT NULL COMMENT '城市类型，1:国家,2:省或直辖市,3:市,4:区或县,5:镇,6:村',
  `full_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '全名',
  `short_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简称',
  `post_office` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `created_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_date` datetime(0) NOT NULL,
  `last_modified_by` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_modified_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code`(`code`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_city
-- ----------------------------
INSERT INTO `sys_city` VALUES ('4028c08162be57660162be5779cb0000', '4028c08162be57660162be5779cb0000', '1', 1, '中国', ' 中国', '1', '中国setDescriptionaaa', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 17:16:44', '4028c08162bda8ce0162bda8df6a0000', '2018-07-11 16:38:25');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08370000', '4028c08162be57660162be5779cb0000', '110000', 2, '北京市', '北京', '110000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420001', '4028c08162be57660162be5779cb0000', '120000', 2, '天津市', '天津', '120000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420002', '4028c08162be57660162be5779cb0000', '130000', 2, '河北省', '河北', '130000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420003', '4028c08162be57660162be5779cb0000', '140000', 2, '山西省', '山西', '140000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420004', '4028c08162be57660162be5779cb0000', '150000', 2, '内蒙古自治区', '内蒙古', '150000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420005', '4028c08162be57660162be5779cb0000', '210000', 2, '辽宁省', '辽宁', '210000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420006', '4028c08162be57660162be5779cb0000', '220000', 2, '吉林省', '吉林', '220000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420007', '4028c08162be57660162be5779cb0000', '230000', 2, '黑龙江省', '黑龙江', '230000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08420008', '4028c08162be57660162be5779cb0000', '310000', 2, '上海市', '上海', '310000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08430009', '4028c08162be57660162be5779cb0000', '320000', 2, '江苏省', '江苏', '320000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000a', '4028c08162be57660162be5779cb0000', '330000', 2, '浙江省', '浙江省', '330000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000b', '4028c08162be57660162be5779cb0000', '340000', 2, '安徽省', '安徽', '340000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000c', '4028c08162be57660162be5779cb0000', '350000', 2, '福建省', '福建', '350000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000d', '4028c08162be57660162be5779cb0000', '360000', 2, '江西省', '江西', '360000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000e', '4028c08162be57660162be5779cb0000', '370000', 2, '山东省', '山东', '370000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d0843000f', '4028c08162be57660162be5779cb0000', '410000', 2, '河南省', '河南', '410000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440010', '4028c08162be57660162be5779cb0000', '420000', 2, '湖北省', '湖北', '420000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440011', '4028c08162be57660162be5779cb0000', '430000', 2, '湖南省', '湖南', '430000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440012', '4028c08162be57660162be5779cb0000', '440000', 2, '广东省', '广东', '440000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440013', '4028c08162be57660162be5779cb0000', '450000', 2, '广西省', '广西', '450000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');
INSERT INTO `sys_city` VALUES ('4028c081638a9ceb01638a9d08440014', '4028c08162be57660162be5779cb0000', '460000', 2, '海南省', '海南', '460000', NULL, '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57', '4028c08163872c4e0163872c65e30000', '2018-05-23 09:28:57');

SET FOREIGN_KEY_CHECKS = 1;
