
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.76
Source Server Version : 50715
Source Host           : 192.168.1.76:3306
Source Database       : hk_pms

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-08-27 08:55:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` char(32) NOT NULL,
  `client_secret` varchar(50) NOT NULL,
  `resource_ids` varchar(100) DEFAULT NULL,
  `scope` varchar(50) NOT NULL,
  `authorized_grant_types` varchar(100) NOT NULL,
  `web_server_redirect_uri` varchar(50) DEFAULT NULL,
  `authorities` varchar(100) DEFAULT NULL,
  `access_token_validity` int(10) NOT NULL,
  `refresh_token_validity` int(10) NOT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(50) NOT NULL,
  PRIMARY KEY (`client_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('4028c08162b9340f0162b93427c40000', '{noop}4028c08162b9340f0162b93427c40000', null, 'all', 'authorization_code,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO `oauth_client_details` VALUES ('4028c0816371a097016371a38d5a0000', '{noop}4028c0816371a097016371a38d5a0000', null, 'all', 'authorization_code,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO `oauth_client_details` VALUES ('4028c0816371a097016371a38d650001', '(noop)4028c0816371a097016371a38d650001', null, 'all', 'authorization_code,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO `oauth_client_details` VALUES ('4028c0816371a097016371a38d650002', '{noop}4028c0816371a097016371a38d650001', null, 'all', 'password,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO `oauth_client_details` VALUES ('4028c0816371a097016371a38d660003', '{noop}4028c0816371a097016371a38d660003', null, 'all', 'refresh_token', null, null, '7200', '604800', null, 'true');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` char(32) NOT NULL,
  `app_code` varchar(50) NOT NULL COMMENT '应用编号',
  `app_name` varchar(100) NOT NULL COMMENT '应用名称',
  `domain_name` varchar(50) DEFAULT NULL COMMENT '域名',
  `app_ip` varchar(50) NOT NULL COMMENT '应用id',
  `app_icon` varchar(100) NOT NULL COMMENT 'icon图标',
  `app_port` smallint(5) NOT NULL COMMENT '端口号',
  `app_status` tinyint(1) NOT NULL COMMENT '状态(1:启用,2:禁用)',
  `local_app` tinyint(1) NOT NULL COMMENT '是否本地app',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_code` (`app_code`) USING BTREE,
  KEY `create_by` (`created_by`),
  CONSTRAINT `create_by` FOREIGN KEY (`created_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用系统表';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('4028c08162b9340f0162b93427c40000', 'HK-PMS', '权限管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 17:33:46', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 17:33:46');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d5a0000', 'HK_EMI', '字典管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d650001', 'HK-FS', '文件管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d650002', 'HK-WEICHAT-TEST', '微信公账号测试', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d660003', 'GATEWAY-ZUUL', 'zuul', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d660004', 'Code4', 'Name4', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d670005', 'Code5', 'Name5', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d670006', 'Code6', 'Name6', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d680007', 'Code7', 'Name7', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d690008', 'Code8', 'Name8', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d6a0009', 'Code9', 'Name9', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` char(32) NOT NULL,
  `dept_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`dept_id`) USING BTREE,
  KEY `FK_Reference_6` (`role_id`) USING BTREE,
  CONSTRAINT `sys_dept_role_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_org_dept` (`id`),
  CONSTRAINT `sys_dept_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门角色表';

-- ----------------------------
-- Records of sys_dept_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL,
  `org_code` varchar(20) NOT NULL,
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `org_icon` varchar(100) DEFAULT NULL COMMENT '机构图标',
  `responsible_id` char(32) DEFAULT NULL COMMENT '责任人id',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_code` (`org_code`),
  KEY `FK_Reference_13` (`responsible_id`) USING BTREE,
  KEY `sys_org_parent_id` (`parent_id`) USING BTREE,
  CONSTRAINT `sys_org_ibfk_1` FOREIGN KEY (`responsible_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_org_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('402881e662ba5fff0162ba602bff0000', '402881e662ba5fff0162ba602bff0000', '', '根节点', null, 'a.png', '4028c08162bda8ce0162bda8df6a0000', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 23:01:27', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 23:01:28');

-- ----------------------------
-- Table structure for sys_org_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_dept`;
CREATE TABLE `sys_org_dept` (
  `id` char(32) NOT NULL,
  `org_id` char(32) NOT NULL,
  `dept_name` varchar(20) NOT NULL COMMENT '部门名称',
  `parent_id` char(32) NOT NULL COMMENT '上级部门id',
  `responsible_id` char(32) DEFAULT NULL COMMENT '责任人id',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`org_id`) USING BTREE,
  KEY `FK_Reference_14` (`responsible_id`) USING BTREE,
  KEY `sys_org_dept_ibfk_3` (`parent_id`) USING BTREE,
  CONSTRAINT `sys_org_dept_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`),
  CONSTRAINT `sys_org_dept_ibfk_2` FOREIGN KEY (`responsible_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_org_dept_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `sys_org_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构部门表';

-- ----------------------------
-- Records of sys_org_dept
-- ----------------------------
INSERT INTO `sys_org_dept` VALUES ('4028c08162bda84d0162bda85d6b0000', '402881e662ba5fff0162ba602bff0000', '财务部', '4028c08162bda84d0162bda85d6b0000', '4028c08162bda8ce0162bda8df6a0000', null, '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:10', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:10');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL COMMENT '上级权限id',
  `app_id` char(32) NOT NULL COMMENT '应用名称',
  `permission_code` varchar(20) NOT NULL COMMENT '权限编号',
  `permission_name` varchar(30) NOT NULL COMMENT '权限名称',
  `url` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `permission_app_id` (`app_id`) USING BTREE,
  CONSTRAINT `sys_permission_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('4028c081634dc57001634dc84de80001', '4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_list', '权限管理', null, null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 13:59:23', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 13:59:23');
INSERT INTO `sys_permission` VALUES ('4028c081634dc9b001634dd5545a0000', '4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_create', '添加权限', null, null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:13:36', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:13:36');
INSERT INTO `sys_permission` VALUES ('4028c081634dc9b001634dd5fb060001', '4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_edit', '修改权限', null, null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:14:19', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:14:19');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0119890001', '4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_delete', '删除权限', null, null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:25', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:25');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0122c00005', '4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_list', '角色管理', null, null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:27', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:27');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0124df0006', '4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_create', '添加角色', null, null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0126b90007', '4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_edit', '修改角色', null, null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0128cf0008', '4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_delete', '删除角色', null, null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:29', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:29');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` char(32) NOT NULL,
  `app_id` char(32) NOT NULL COMMENT '应用id',
  `role_name` varchar(20) NOT NULL COMMENT '角色名称',
  `role_code` varchar(30) NOT NULL COMMENT '角色编号',
  `role_status` tinyint(1) NOT NULL COMMENT '角色状态(0:禁用,1:启用)',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_app_id` (`app_id`) USING BTREE,
  CONSTRAINT `sys_role_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('4028c08162d2866a0162d28687770000', '4028c08162b9340f0162b93427c40000', '系统管理员', 'ADMIN', '1', null, '1', '2018-04-17 15:34:15', '1', '2018-04-17 15:34:15');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  `permission_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`permission_id`) USING BTREE,
  KEY `FK_Reference_9` (`role_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` char(32) NOT NULL COMMENT '主键',
  `org_id` char(32) NOT NULL,
  `account` varchar(20) NOT NULL COMMENT '登陆账号',
  `dept_id` char(32) NOT NULL COMMENT '部门id',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `password` varchar(100) NOT NULL COMMENT '登陆密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱虚',
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `user_type` tinyint(1) NOT NULL COMMENT '用户类型',
  `is_protect` tinyint(1) NOT NULL COMMENT '是否受保护的账号(0,否,1:是)，保护的账号有全部权限',
  `sex` tinyint(1) NOT NULL COMMENT '用户性别(1,男，2：女)',
  `icon_path` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `birth` date DEFAULT NULL COMMENT '生日',
  `privince_id` char(32) DEFAULT NULL COMMENT '省份id',
  `city_id` char(32) DEFAULT NULL COMMENT '市id',
  `user_status` tinyint(1) NOT NULL COMMENT '用户状态(0,禁用,1:启用)',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL COMMENT '创建日期',
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`) USING BTREE,
  UNIQUE KEY `user_phone` (`phone`) USING BTREE,
  UNIQUE KEY `user_email` (`email`) USING BTREE,
  KEY `user_org_id` (`org_id`) USING BTREE,
  KEY `user_org_dept_id` (`dept_id`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_org_dept` (`id`),
  CONSTRAINT `sys_user_ibfk_2` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('4028c08162bda8ce0162bda8df6a0000', '402881e662ba5fff0162ba602bff0000', '18820136090', '4028c08162bda84d0162bda85d6b0000', '18820136090', '{bcrypt}$2a$10$Cl73dNsDXWV55uvHcLRNsu/LmvKdnCYAL50uOgcSn49AFopS3ZIaq', 'huankai@139.com', '系统管理员', '0', '1', '1', null, '2000-01-01', null, null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:44', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:44');
INSERT INTO `sys_user` VALUES ('4028c08162bda8ce0162bda8df6a0001', '402881e662ba5fff0162ba602bff0000', '18820132014', '4028c08162bda84d0162bda85d6b0000', '18820132014', '{bcrypt}$2a$10$Cl73dNsDXWV55uvHcLRNsu/LmvKdnCYAL50uOgcSn49AFopS3ZIaq', '18820132014@139.com', 'text', '0', '0', '1', null, null, null, null, '1', 'a', '2018-08-02 15:37:18', 'a', '2018-08-02 15:37:22');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`role_id`) USING BTREE,
  KEY `sys_user_role_ibfk_1` (`user_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '4028c08162bda8ce0162bda8df6a0001', '4028c08162d2866a0162d28687770000');

-- ----------------------------
-- Table structure for sys_user_third
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_third`;
CREATE TABLE `sys_user_third` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL COMMENT '用户id',
  `user_third_name` varchar(50) NOT NULL COMMENT '用户名',
  `open_id` varchar(50) NOT NULL,
  `icon_url` varchar(100) DEFAULT NULL COMMENT '头像url',
  `account_type` tinyint(1) NOT NULL COMMENT '账号类型(见数据字典account_type)',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `FK_Reference_12` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_third_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户';

-- ----------------------------
-- Records of sys_user_third
-- ----------------------------
INSERT INTO `sys_user_third` VALUES ('4028c08162bdb2aa0162bdb2b9ea0000', '4028c08162bda8ce0162bda8df6a0000', 'haha', 'oNvZtv__To1bNI5clj3-oB05OO4U', null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:30:30', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:30:30');
=======
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.76
Source Server Version : 50715
Source Host           : 192.168.1.76:3306
Source Database       : hk_pms

Target Server Type    : MYSQL
Target Server Version : 50715
File Encoding         : 65001

Date: 2018-09-13 17:17:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` char(32) NOT NULL,
  `client_secret` varchar(50) NOT NULL,
  `resource_ids` varchar(100) DEFAULT NULL,
  `scope` varchar(50) NOT NULL,
  `authorized_grant_types` varchar(100) NOT NULL,
  `web_server_redirect_uri` varchar(50) DEFAULT NULL,
  `authorities` varchar(100) DEFAULT NULL,
  `access_token_validity` int(10) NOT NULL,
  `refresh_token_validity` int(10) NOT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(50) NOT NULL,
  PRIMARY KEY (`client_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('4028c08162b9340f0162b93427c40000', '{noop}4028c08162b9340f0162b93427c40000', null, 'all', 'authorization_code,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO `oauth_client_details` VALUES ('4028c0816371a097016371a38d5a0000', '{noop}4028c0816371a097016371a38d5a0000', null, 'all', 'authorization_code,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO `oauth_client_details` VALUES ('4028c0816371a097016371a38d650001', '{noop}4028c0816371a097016371a38d650001', null, 'all', 'authorization_code,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO `oauth_client_details` VALUES ('4028c0816371a097016371a38d650002', '{noop}4028c0816371a097016371a38d650002', null, 'all', 'password,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO `oauth_client_details` VALUES ('4028c0816371a097016371a38d660003', '{noop}4028c0816371a097016371a38d660003', null, 'all', 'refresh_token', null, null, '7200', '604800', null, 'true');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` char(32) NOT NULL,
  `app_code` varchar(50) NOT NULL COMMENT '应用编号',
  `app_name` varchar(100) NOT NULL COMMENT '应用名称',
  `domain_name` varchar(50) DEFAULT NULL COMMENT '域名',
  `app_host` varchar(50) NOT NULL COMMENT '应用id',
  `app_icon` varchar(100) NOT NULL COMMENT 'icon图标',
  `app_port` smallint(5) NOT NULL COMMENT '端口号',
  `app_status` tinyint(1) NOT NULL COMMENT '状态(1:启用,2:禁用)',
  `local_app` tinyint(1) NOT NULL COMMENT '是否本地app',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_code` (`app_code`) USING BTREE,
  KEY `create_by` (`created_by`),
  CONSTRAINT `create_by` FOREIGN KEY (`created_by`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用系统表';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('4028c08162b9340f0162b93427c40000', 'HK-PMS', '权限管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 17:33:46', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 17:33:46');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d5a0000', 'HK_EMI', '字典管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d650001', 'HK-FS', '文件管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d650002', 'HK-WEICHAT-TEST', '微信公账号测试', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d660003', 'GATEWAY-ZUUL', 'zuul', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d660004', 'Code4', 'Name4', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d670005', 'Code5', 'Name5', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d670006', 'Code6', 'Name6', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d680007', 'Code7', 'Name7', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d690008', 'Code8', 'Name8', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO `sys_app` VALUES ('4028c0816371a097016371a38d6a0009', 'Code9', 'Name9', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` char(32) NOT NULL,
  `dept_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`dept_id`) USING BTREE,
  KEY `FK_Reference_6` (`role_id`) USING BTREE,
  CONSTRAINT `sys_dept_role_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_org_dept` (`id`),
  CONSTRAINT `sys_dept_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门角色表';

-- ----------------------------
-- Records of sys_dept_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL,
  `org_code` varchar(20) NOT NULL,
  `org_name` varchar(50) NOT NULL COMMENT '机构名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `org_icon` varchar(100) DEFAULT NULL COMMENT '机构图标',
  `responsible_id` char(32) DEFAULT NULL COMMENT '责任人id',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_code` (`org_code`),
  KEY `FK_Reference_13` (`responsible_id`) USING BTREE,
  KEY `sys_org_parent_id` (`parent_id`) USING BTREE,
  CONSTRAINT `sys_org_ibfk_1` FOREIGN KEY (`responsible_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_org_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('402881e662ba5fff0162ba602bff0000', '402881e662ba5fff0162ba602bff0000', 'ADMIN', '根节点', null, 'a.png', '4028c08162bda8ce0162bda8df6a0000', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 23:01:27', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 23:01:28');

-- ----------------------------
-- Table structure for sys_org_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_dept`;
CREATE TABLE `sys_org_dept` (
  `id` char(32) NOT NULL,
  `org_id` char(32) NOT NULL,
  `dept_name` varchar(20) NOT NULL COMMENT '部门名称',
  `parent_id` char(32) NOT NULL COMMENT '上级部门id',
  `responsible_id` char(32) DEFAULT NULL COMMENT '责任人id',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`org_id`) USING BTREE,
  KEY `FK_Reference_14` (`responsible_id`) USING BTREE,
  KEY `sys_org_dept_ibfk_3` (`parent_id`) USING BTREE,
  CONSTRAINT `sys_org_dept_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`),
  CONSTRAINT `sys_org_dept_ibfk_2` FOREIGN KEY (`responsible_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_org_dept_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `sys_org_dept` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构部门表';

-- ----------------------------
-- Records of sys_org_dept
-- ----------------------------
INSERT INTO `sys_org_dept` VALUES ('4028c08162bda84d0162bda85d6b0000', '402881e662ba5fff0162ba602bff0000', '根机构部门', '4028c08162bda84d0162bda85d6b0000', '4028c08162bda8ce0162bda8df6a0000', null, '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:10', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:10');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` char(32) NOT NULL,
  `app_id` char(32) NOT NULL COMMENT '应用名称',
  `permission_code` varchar(20) NOT NULL COMMENT '权限编号',
  `permission_name` varchar(30) NOT NULL COMMENT '权限名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `permission_app_id` (`app_id`) USING BTREE,
  CONSTRAINT `sys_permission_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_list', '权限管理', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 13:59:23', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 13:59:23');
INSERT INTO `sys_permission` VALUES ('4028c081634dc9b001634dd5545a0000', '4028c08162b9340f0162b93427c40000', 'permission_create', '添加权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:13:36', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:13:36');
INSERT INTO `sys_permission` VALUES ('4028c081634dc9b001634dd5fb060001', '4028c08162b9340f0162b93427c40000', 'permission_edit', '修改权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:14:19', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:14:19');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0119890001', '4028c08162b9340f0162b93427c40000', 'permission_delete', '删除权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:25', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:25');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_list', '角色管理', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:27', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:27');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0124df0006', '4028c08162b9340f0162b93427c40000', 'role_create', '添加角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0126b90007', '4028c08162b9340f0162b93427c40000', 'role_edit', '修改角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28');
INSERT INTO `sys_permission` VALUES ('4028c081634dff6501634e0128cf0008', '4028c08162b9340f0162b93427c40000', 'role_delete', '删除角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:29', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:29');

-- ----------------------------
-- Table structure for sys_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_resource`;
CREATE TABLE `sys_permission_resource` (
  `id` char(32) NOT NULL,
  `permission_id` char(32) NOT NULL,
  `resource_id` char(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL,
  `app_id` char(32) NOT NULL,
  `resource_name` varchar(50) NOT NULL,
  `resource_uri` varchar(20) NOT NULL,
  `resource_type` tinyint(1) NOT NULL COMMENT '类型:(0:菜单)',
  `ordered` tinyint(3) NOT NULL,
  `icon` varchar(10) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `state` tinyint(1) NOT NULL COMMENT '是否可用(0:不可用，1：可用)',
  `target` varchar(10) NOT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_id` (`app_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('4028c0816376a097011371a38d6a0000', '4028c0816376a097011371a38d6a0000', '4028c08162b9340f0162b93427c40000', '系统管理', '/system', '0', '1', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:26:01', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:26:08');
INSERT INTO `sys_resource` VALUES ('4028c0816376a097011371a38d6a0001', '4028c0816376a097011371a38d6a0001', '4028c08162b9340f0162b93427c40000', '用户管理', '/user', '0', '2', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:28:21', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:28:23');
INSERT INTO `sys_resource` VALUES ('4028c0816376a097011371a38d6a0002', '4028c0816376a097011371a38d6a0002', '4028c08162b9340f0162b93427c40000', '角色管理', '/role', '0', '3', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:29:03', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:29:05');
INSERT INTO `sys_resource` VALUES ('4028c0816376a097011371a38d6a0003', '4028c0816376a097011371a38d6a0003', '4028c08162b9340f0162b93427c40000', '权限管理', '/permission', '0', '4', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:26:01', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:26:08');
INSERT INTO `sys_resource` VALUES ('4028c0816376a097011371a38d6a0004', '4028c0816376a097011371a38d6a0004', '4028c08162b9340f0162b93427c40000', '资源管理', '/resource', '0', '5', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:28:21', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:28:23');
INSERT INTO `sys_resource` VALUES ('4028c0816376a097011371a38d6a0005', '4028c0816376a097011371a38d6a0000', '4028c08162b9340f0162b93427c40000', '应用管理', '/system', '1', '1', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 13:53:24', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 13:53:28');
INSERT INTO `sys_resource` VALUES ('4028c0816376a097011371a38d6a0006', '4028c0816376a097011371a38d6a0005', '4028c08162b9340f0162b93427c40000', '添加', '/system/add', '2', '1', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 14:08:43', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 14:08:48');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` char(32) NOT NULL,
  `app_id` char(32) NOT NULL COMMENT '应用id',
  `role_code` varchar(20) NOT NULL COMMENT '角色编号',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_status` tinyint(1) NOT NULL COMMENT '角色状态(0:禁用,1:启用)',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `role_app_id` (`app_id`) USING BTREE,
  CONSTRAINT `sys_role_ibfk_1` FOREIGN KEY (`app_id`) REFERENCES `sys_app` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('4028c08162d2866a0162d28687770000', '4028c08162b9340f0162b93427c40000', 'ADMIN', '系统管理员', '1', null, '1', '2018-04-17 15:34:15', '1', '2018-04-17 15:34:15');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  `permission_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`permission_id`) USING BTREE,
  KEY `FK_Reference_9` (`role_id`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` char(32) NOT NULL COMMENT '主键',
  `org_id` char(32) NOT NULL,
  `account` varchar(20) NOT NULL COMMENT '登陆账号',
  `dept_id` char(32) NOT NULL COMMENT '部门id',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `password` varchar(100) NOT NULL COMMENT '登陆密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱虚',
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `user_type` tinyint(1) NOT NULL COMMENT '用户类型',
  `is_protect` tinyint(1) NOT NULL COMMENT '是否受保护的账号(0,否,1:是)，保护的账号有全部权限',
  `sex` tinyint(1) NOT NULL COMMENT '用户性别(1,男，2：女)',
  `icon_path` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `birth` date DEFAULT NULL COMMENT '生日',
  `province_id` char(32) DEFAULT NULL COMMENT '省份id',
  `city_id` char(32) DEFAULT NULL COMMENT '市id',
  `user_status` tinyint(1) NOT NULL COMMENT '用户状态(0,禁用,1:启用，9：已删除)',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL COMMENT '创建日期',
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`) USING BTREE,
  UNIQUE KEY `user_phone` (`phone`) USING BTREE,
  UNIQUE KEY `user_email` (`email`) USING BTREE,
  KEY `user_org_id` (`org_id`) USING BTREE,
  KEY `user_org_dept_id` (`dept_id`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_org_dept` (`id`),
  CONSTRAINT `sys_user_ibfk_2` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('4028c08162bda8ce0162bda8df6a0000', '402881e662ba5fff0162ba602bff0000', '18820136090', '4028c08162bda84d0162bda85d6b0000', '18820136090', '{bcrypt}$2a$10$Cl73dNsDXWV55uvHcLRNsu/LmvKdnCYAL50uOgcSn49AFopS3ZIaq', 'huankai@139.com', '系统管理员', '0', '1', '1', null, '2000-01-01', null, null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:44', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:44');
INSERT INTO `sys_user` VALUES ('4028c08162bda8ce0162bda8df6a0001', '402881e662ba5fff0162ba602bff0000', '18820132014', '4028c08162bda84d0162bda85d6b0000', '18820132014', '{bcrypt}$2a$10$Cl73dNsDXWV55uvHcLRNsu/LmvKdnCYAL50uOgcSn49AFopS3ZIaq', '18820132014@139.com', 'text', '0', '0', '1', null, null, null, null, '1', 'a', '2018-08-02 15:37:18', 'a', '2018-08-02 15:37:22');

-- ----------------------------
-- Table structure for sys_user_idcard
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_idcard`;
CREATE TABLE `sys_user_idcard` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `id_card` char(18) NOT NULL,
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `expire_date` date NOT NULL,
  `face_image` varchar(100) NOT NULL COMMENT '正面照',
  `back_image` varchar(100) NOT NULL COMMENT '反面照',
  `address` varchar(100) NOT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_idcard
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`role_id`) USING BTREE,
  KEY `sys_user_role_ibfk_1` (`user_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '4028c08162bda8ce0162bda8df6a0001', '4028c08162d2866a0162d28687770000');

-- ----------------------------
-- Table structure for sys_user_third
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_third`;
CREATE TABLE `sys_user_third` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL COMMENT '用户id',
  `user_third_name` varchar(50) NOT NULL COMMENT '用户名',
  `open_id` varchar(50) NOT NULL,
  `icon_url` varchar(100) DEFAULT NULL COMMENT '头像url',
  `account_type` tinyint(1) NOT NULL COMMENT '账号类型(见数据字典account_type)',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `FK_Reference_12` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_third_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户';

-- ----------------------------
-- Records of sys_user_third
-- ----------------------------
INSERT INTO `sys_user_third` VALUES ('4028c08162bdb2aa0162bdb2b9ea0000', '4028c08162bda8ce0162bda8df6a0000', 'haha', 'oNvZtv__To1bNI5clj3-oB05OO4U', null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:30:30', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:30:30');

