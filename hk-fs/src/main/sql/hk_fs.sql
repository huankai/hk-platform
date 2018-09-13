/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.76
Source Server Version : 50715
Source Host           : 192.168.1.76:3306
Source Database       : hk_fs

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-09-13 17:17:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `file_name` varchar(50) NOT NULL,
  `group_name` varchar(10) NOT NULL,
  `file_path` varchar(100) NOT NULL,
  `file_size` double(10,2) NOT NULL,
  `unit` char(2) NOT NULL,
  `digest` varchar(100) NOT NULL,
  `extension` char(5) NOT NULL,
  `upload_date` datetime NOT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `file_digest` (`digest`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件表';

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('4028c08165d121d90165d12894bb0001', '4028c08162bda8ce0162bda8df6a0000', 'apache-maven-3.5.3-bin.zip', 'group1', 'M00/00/00/wKhAgFuZ5TmELOETAAAAAKxs49o359.zip', '8.54', 'MB', '044665b799396cf7ee9442a9dd7d9ac6255ba91f7377248a52a6ec031ca7f8de', 'zip', '2018-09-13 13:40:19', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 12:20:22', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 12:20:22');
INSERT INTO `file_info` VALUES ('4028c08165d12b500165d12bcb360000', '4028c08162bda8ce0162bda8df6a0000', 'apache-maven-3.5.3-bin.zip', 'group1', 'M00/00/00/wKhAgFuZ5TmELOETAAAAAKxs49o359.zip', '8.54', 'MB', '044665b799396cf7ee9442a9dd7d9ac6255ba91f7377248a52a6ec031ca7f8de', 'zip', '2018-09-13 13:40:21', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 12:23:53', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 12:23:53');
INSERT INTO `file_info` VALUES ('4028c08165d136820165d13747cf0000', '4028c08162bda8ce0162bda8df6a0000', 'Capture001.png', 'group1', 'M00/00/00/wKhAgFuZ6PmENweHAAAAAGBI4cw472.png', '225.13', 'KB', 'f78be177414e617f739ed292a20b1ab9fbb7735587d385e6e3ed04ae7318788e', 'png', '2018-09-13 13:40:23', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 12:36:26', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 12:36:26');
INSERT INTO `file_info` VALUES ('4028c08165d160330165d16116a40000', '4028c08162bda8ce0162bda8df6a0000', 'head.png', 'group1', 'M00/00/00/wKhAgFuZ86aEYCW2AAAAAM_3meM156.png', '4.18', 'KB', '42241147949d21fb9290db76078802e10db4f5a91d886f98b716efdff46eb492', 'png', '2018-09-13 13:40:25', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 13:22:06', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 13:22:06');
INSERT INTO `file_info` VALUES ('4028c08165d166470165d166a4bd0000', '4028c08162bda8ce0162bda8df6a0000', 'head.png', 'group1', 'M00/00/00/wKhAgFuZ86aEYCW2AAAAAM_3meM156.png', '4.18', 'KB', '42241147949d21fb9290db76078802e10db4f5a91d886f98b716efdff46eb492', 'png', '2018-09-13 13:40:27', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 13:28:10', '4028c08162bda8ce0162bda8df6a0000', '2018-09-13 13:28:10');

-- ----------------------------
-- Table structure for file_share
-- ----------------------------
DROP TABLE IF EXISTS `file_share`;
CREATE TABLE `file_share` (
  `id` char(32) NOT NULL,
  `file_id` char(32) NOT NULL,
  `share_uri` varchar(50) NOT NULL,
  `password` varchar(10) DEFAULT NULL,
  `share_date` datetime DEFAULT NULL,
  `expire_date` datetime DEFAULT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `file_id` (`file_id`),
  CONSTRAINT `file_id` FOREIGN KEY (`file_id`) REFERENCES `file_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件分享表';

-- ----------------------------
-- Records of file_share
-- ----------------------------
