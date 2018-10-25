/*
Navicat MySQL Data Transfer

Source Server         : 192.168.64.150
Source Server Version : 50723
Source Host           : 192.168.64.150:3306
Source Database       : hk_pms

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-10-25 15:39:40
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
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('7872c6f7f0cb41f0ba0f8aa060cb5c37', '{noop}7872c6f7f0cb41f0ba0f8aa060cb5c37', '', 'all', 'authorization_code,refresh_token', null, '', '7200', '72000', '{}', 'true');
INSERT INTO `oauth_client_details` VALUES ('f2f77124b224479fa928f49786568251', '{noop}f2f77124b224479fa928f49786568251', '', 'all', 'authorization_code,refresh_token', null, '', '7200', '72000', '{}', 'true');
INSERT INTO `oauth_client_details` VALUES ('fafcc2196db94fd19a92a1d479a2ca8b', '{noop}fafcc2196db94fd19a92a1d479a2ca8b', '', 'all', 'authorization_code,refresh_token', null, '', '7200', '72000', '{}', 'true');

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `id` char(32) NOT NULL,
  `app_code` varchar(50) NOT NULL COMMENT '应用编号',
  `app_name` varchar(100) NOT NULL COMMENT '应用名称',
  `app_host` varchar(50) NOT NULL COMMENT '应用id',
  `app_icon` varchar(100) NOT NULL COMMENT 'icon图标',
  `app_status` char(1) NOT NULL COMMENT '状态(1:启用,2:禁用)',
  `start_date` datetime NOT NULL COMMENT '启用时间',
  `expire_date` datetime DEFAULT NULL COMMENT '过期时间,为空表示不过期',
  `local_app` tinyint(1) NOT NULL COMMENT '是否本地app',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `app_code` (`app_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='应用系统表';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('7872c6f7f0cb41f0ba0f8aa060cb5c37', 'HK-PMS', '权限管理系统', '127.0.0.1', 'e7af0e6e6d0f4a5cb9b8b2ee8b51b7e9.png', '0', '2018-10-25 14:57:45', null, '1', '0', '2018-10-25 14:57:45', '0', '2018-10-25 14:57:45');
INSERT INTO `sys_app` VALUES ('f2f77124b224479fa928f49786568251', 'HK-FS', '文件管理系统', '127.0.0.1', '5262e534d14547b5a84da3bccd5e547b.png', '1', '2018-10-25 14:57:45', null, '1', '0', '2018-10-25 14:57:45', '0', '2018-10-25 14:57:45');
INSERT INTO `sys_app` VALUES ('fafcc2196db94fd19a92a1d479a2ca8b', 'HK-EMI', '字典管理系统', '127.0.0.1', '2253b41da26c496b975e4eac4855941c.png', '1', '2018-10-25 14:57:45', null, '1', '0', '2018-10-25 14:57:45', '0', '2018-10-25 14:57:45');

-- ----------------------------
-- Table structure for sys_app_apply
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_apply`;
CREATE TABLE `sys_app_apply` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `app_code` varchar(50) NOT NULL,
  `app_name` varchar(100) NOT NULL,
  `audit_status` tinyint(1) NOT NULL COMMENT '状态（0：待审核、1：审核通过，9：审核不通过）,审核通过后，根据 sys_config表中的app有效期在 sys_app表中添加一条记录，并设置过期 时间值',
  `representative` varchar(20) NOT NULL,
  `representative_phone` varchar(11) DEFAULT NULL COMMENT '法人手机号',
  `representative_email` varchar(50) DEFAULT NULL COMMENT '法人邮箱号',
  `business_licence` varchar(50) NOT NULL COMMENT '营业执照',
  `licence_image` varchar(50) NOT NULL COMMENT '营业执照图片',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `apply_date` datetime DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_26` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_apply
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app_renewal_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_renewal_detail`;
CREATE TABLE `sys_app_renewal_detail` (
  `id` char(32) NOT NULL,
  `app_id` char(32) NOT NULL,
  `renewal_type` varchar(10) NOT NULL,
  `renewal_start_date` date NOT NULL,
  `renewal_end_date` date NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_27` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_renewal_detail
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_user`;
CREATE TABLE `sys_app_user` (
  `id` char(32) NOT NULL,
  `app_id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_22` (`app_id`) USING BTREE,
  KEY `FK_Reference_23` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` char(32) NOT NULL,
  `key_` char(32) NOT NULL,
  `name_` varchar(20) NOT NULL,
  `value_` varchar(10) NOT NULL,
  `value_type` tinyint(1) NOT NULL COMMENT '值类型(1:byte,2:int,3:BigDecimal,4:double,5:boolean,其它:string)',
  `description` varchar(200) DEFAULT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `config_key` (`key_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置';

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_role`;
CREATE TABLE `sys_dept_role` (
  `id` char(32) NOT NULL,
  `dept_id` char(32) NOT NULL,
  `role_id` char(32) NOT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_28` (`role_id`) USING BTREE,
  KEY `FK_Reference_29` (`dept_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `org_tag` varchar(100) NOT NULL COMMENT '标签',
  `province_id` char(32) NOT NULL,
  `city_id` char(32) NOT NULL,
  `area_id` char(32) NOT NULL,
  `address` varchar(100) NOT NULL COMMENT '详细地址',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `org_code` (`org_code`) USING BTREE,
  KEY `FK_Reference_13` (`responsible_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('402881e662ba5fff0162ba602bff0000', '402881e662ba5fff0162ba602bff0000', 'ADMIN', '根节点', null, 'a.png', '4028c08162bda8ce0162bda8df6a0000', 'all', '4028c08162bda8ce0162bda8df6a0000', '4028c08162bda8ce0162bda8df6a0000', '4028c08162bda8ce0162bda8df6a0000', 'a', '4028c08162bda8ce0162bda8df6a0000', '2018-09-21 14:48:44', '4028c08162bda8ce0162bda8df6a0000', '2018-09-21 14:48:48');

-- ----------------------------
-- Table structure for sys_org_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_dept`;
CREATE TABLE `sys_org_dept` (
  `id` char(32) NOT NULL,
  `org_id` char(32) NOT NULL,
  `parent_id` char(32) NOT NULL,
  `dept_name` varchar(20) NOT NULL COMMENT '部门名称',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_24` (`org_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构部门表';

-- ----------------------------
-- Records of sys_org_dept
-- ----------------------------
INSERT INTO `sys_org_dept` VALUES ('4028c08162bda84d0162bda85d6b0000', '402881e662ba5fff0162ba602bff0000', '4028c08162bda84d0162bda85d6b0000', '根机构部门', null, '4028c08162bda84d0162bda85d6b0000', '2018-09-21 14:49:42', '4028c08162bda84d0162bda85d6b0000', '2018-09-21 14:49:44');

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
  KEY `permission_app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_resource`;
CREATE TABLE `sys_permission_resource` (
  `id` char(32) NOT NULL,
  `permission_id` char(32) NOT NULL,
  `resource_id` char(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_16` (`permission_id`) USING BTREE,
  KEY `FK_Reference_17` (`resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源表';

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
  `resource_type` char(1) NOT NULL COMMENT '类型:(0:菜单)',
  `ordered` char(3) NOT NULL,
  `icon` varchar(10) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `state` char(1) NOT NULL COMMENT '是否可用(0:不可用，1：可用)',
  `target` varchar(10) NOT NULL,
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` char(32) NOT NULL,
  `app_id` char(32) NOT NULL COMMENT '应用id',
  `role_code` varchar(20) NOT NULL COMMENT '角色编号',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_status` char(1) NOT NULL COMMENT '角色状态(0:禁用,1:启用)',
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
  `dept_id` char(32) NOT NULL,
  `account` varchar(20) NOT NULL COMMENT '登陆账号',
  `phone` varchar(15) NOT NULL COMMENT '手机号',
  `password` varchar(100) NOT NULL COMMENT '登陆密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱虚',
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `user_type` char(1) NOT NULL COMMENT '用户类型(0:管理人员、1:医生、2:客户用户)',
  `is_protect` tinyint(1) NOT NULL COMMENT '是否受保护的账号(0,否,1:是)，保护的账号有全部权限',
  `sex` char(1) NOT NULL COMMENT '用户性别(1,男，2：女)',
  `icon_path` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `birth` date DEFAULT NULL COMMENT '生日',
  `province_id` char(32) DEFAULT NULL COMMENT '省份id',
  `city_id` char(32) DEFAULT NULL COMMENT '市id',
  `user_status` char(1) NOT NULL COMMENT '用户状态(0,禁用,1:启用，9：已删除)',
  `created_by` char(32) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL COMMENT '创建日期',
  `last_modified_by` char(32) DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL COMMENT '最后修改日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`) USING BTREE,
  UNIQUE KEY `user_phone` (`phone`) USING BTREE,
  KEY `user_org_id` (`org_id`) USING BTREE,
  KEY `FK_Reference_20` (`dept_id`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`dept_id`) REFERENCES `sys_org_dept` (`id`),
  CONSTRAINT `sys_user_ibfk_2` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('8bb70a2cf0a94c44b4f86fd711e42acd', '402881e662ba5fff0162ba602bff0000', '4028c08162bda84d0162bda85d6b0000', '18820136090', '18820136090', '{bcrypt}$2a$10$mWGLgPqty2ThJmXun2Dr2ulspSES9RfEhjjMX3aGrEc4N7mMulnsu', '18820136090', '18820136090', '0', '1', '1', null, null, null, null, '1', '0', '2018-10-25 14:19:47', '0', '2018-10-25 14:19:47');
INSERT INTO `sys_user` VALUES ('e03a0ab635cf48bd97d76f0ab90f14c8', '402881e662ba5fff0162ba602bff0000', '4028c08162bda84d0162bda85d6b0000', '18820132014', '18820132014', '{bcrypt}$2a$10$durtXYx.eYYSf4ut8RvLIOFq5M2Dn0ZI2PKANRgIlBmuAzrEE6Cfe', '18820132014', '18820132014', '0', '1', '1', null, null, null, null, '1', '0', '2018-10-25 14:19:48', '0', '2018-10-25 14:19:48');

-- ----------------------------
-- Table structure for sys_user_card
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_card`;
CREATE TABLE `sys_user_card` (
  `id` char(32) NOT NULL,
  `user_id` char(32) NOT NULL,
  `id_card` char(18) NOT NULL,
  `card_type` tinyint(1) NOT NULL COMMENT '卡类型',
  `real_name` varchar(20) NOT NULL COMMENT '真实名称',
  `expire_date` date NOT NULL,
  `face_image` varchar(100) NOT NULL COMMENT '正面照',
  `back_image` varchar(100) NOT NULL COMMENT '反面照',
  `address` varchar(100) NOT NULL,
  `card_date` date NOT NULL COMMENT '发卡时间',
  `created_by` char(32) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_modified_by` char(32) NOT NULL,
  `last_modified_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_card_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户证件表';

-- ----------------------------
-- Records of sys_user_card
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
  KEY `sys_user_role_ibfk_1` (`user_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

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
  `account_type` tinyint(1) NOT NULL COMMENT '账号类型(见数据字典account_type,如微信、QQ等)',
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
