/*
Navicat PGSQL Data Transfer

Source Server         : 192.168.64.135
Source Server Version : 100500
Source Host           : 192.168.64.135:5432
Source Database       : hk_pms
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 100500
File Encoding         : 65001

Date: 2018-09-19 14:21:19
*/


-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_details";
CREATE TABLE "public"."oauth_client_details" (
"client_id" char(32) COLLATE "default" NOT NULL,
"client_secret" varchar(50) COLLATE "default" NOT NULL,
"resource_ids" varchar(100) COLLATE "default",
"scope" varchar(50) COLLATE "default" NOT NULL,
"authorized_grant_types" varchar(100) COLLATE "default" NOT NULL,
"web_server_redirect_uri" varchar(50) COLLATE "default",
"authorities" varchar(100) COLLATE "default",
"access_token_validity" int4 NOT NULL,
"refresh_token_validity" int4 NOT NULL,
"additional_information" varchar(255) COLLATE "default",
"autoapprove" varchar(50) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO "public"."oauth_client_details" VALUES ('4028c08162b9340f0162b93427c40000', '{noop}4028c08162b9340f0162b93427c40000', null, 'all', 'authorization_code,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO "public"."oauth_client_details" VALUES ('4028c0816371a097016371a38d5a0000', '{noop}4028c0816371a097016371a38d5a0000', null, 'all', 'authorization_code,refresh_token,password', null, null, '7200', '604800', null, 'true');
INSERT INTO "public"."oauth_client_details" VALUES ('4028c0816371a097016371a38d650001', '{noop}4028c0816371a097016371a38d650001', null, 'all', 'authorization_code,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO "public"."oauth_client_details" VALUES ('4028c0816371a097016371a38d650002', '{noop}4028c0816371a097016371a38d650002', null, 'all', 'password,refresh_token', null, null, '7200', '604800', null, 'true');
INSERT INTO "public"."oauth_client_details" VALUES ('4028c0816371a097016371a38d660003', '{noop}4028c0816371a097016371a38d660003', null, 'all', 'refresh_token', null, null, '7200', '604800', null, 'true');

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS "public"."persistent_logins";
CREATE TABLE "public"."persistent_logins" (
"username" varchar(64) COLLATE "default" NOT NULL,
"series" varchar(64) COLLATE "default" NOT NULL,
"token" varchar(64) COLLATE "default" NOT NULL,
"last_used" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_app";
CREATE TABLE "public"."sys_app" (
"id" char(32) COLLATE "default" NOT NULL,
"app_code" varchar(50) COLLATE "default" NOT NULL,
"app_name" varchar(100) COLLATE "default" NOT NULL,
"domain_name" varchar(50) COLLATE "default",
"app_host" varchar(50) COLLATE "default" NOT NULL,
"app_icon" varchar(100) COLLATE "default" NOT NULL,
"app_port" int2 NOT NULL,
"app_status" int2 NOT NULL,
"local_app" int2 NOT NULL,
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_app" IS '应用系统表';
COMMENT ON COLUMN "public"."sys_app"."app_code" IS '应用编号';
COMMENT ON COLUMN "public"."sys_app"."app_name" IS '应用名称';
COMMENT ON COLUMN "public"."sys_app"."domain_name" IS '域名';
COMMENT ON COLUMN "public"."sys_app"."app_host" IS '应用id';
COMMENT ON COLUMN "public"."sys_app"."app_icon" IS 'icon图标';
COMMENT ON COLUMN "public"."sys_app"."app_port" IS '端口号';
COMMENT ON COLUMN "public"."sys_app"."app_status" IS '状态(1:启用,2:禁用)';
COMMENT ON COLUMN "public"."sys_app"."local_app" IS '是否本地app';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO "public"."sys_app" VALUES ('4028c08162b9340f0162b93427c40000', 'HK-PMS', '权限管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 17:33:46', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 17:33:46');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d5a0000', 'HK_EMI', '字典管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d650001', 'HK-FS', '文件管理系统', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d650002', 'HK-WEICHAT-TEST', '微信公账号测试', null, '127.0.0.1', 'a.png', '80', '1', '1', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d660003', 'GATEWAY-ZUUL', 'zuul', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d660004', 'Code4', 'Name4', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d670005', 'Code5', 'Name5', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d670006', 'Code6', 'Name6', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d680007', 'Code7', 'Name7', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d690008', 'Code8', 'Name8', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');
INSERT INTO "public"."sys_app" VALUES ('4028c0816371a097016371a38d6a0009', 'Code9', 'Name9', null, '127.0.0.1', 'a.png', '80', '1', '0', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34', '4028c08162bda8ce0162bda8df6a0000', '2018-05-18 13:05:34');

-- ----------------------------
-- Table structure for sys_dept_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_dept_role";
CREATE TABLE "public"."sys_dept_role" (
"id" char(32) COLLATE "default" NOT NULL,
"dept_id" char(32) COLLATE "default" NOT NULL,
"role_id" char(32) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_dept_role" IS '部门角色表';

-- ----------------------------
-- Records of sys_dept_role
-- ----------------------------

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_org";
CREATE TABLE "public"."sys_org" (
"id" char(32) COLLATE "default" NOT NULL,
"parent_id" char(32) COLLATE "default" NOT NULL,
"org_code" varchar(20) COLLATE "default" NOT NULL,
"org_name" varchar(50) COLLATE "default" NOT NULL,
"description" varchar(200) COLLATE "default",
"org_icon" varchar(100) COLLATE "default",
"responsible_id" char(32) COLLATE "default",
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_org" IS '机构表';
COMMENT ON COLUMN "public"."sys_org"."org_name" IS '机构名称';
COMMENT ON COLUMN "public"."sys_org"."description" IS '描述';
COMMENT ON COLUMN "public"."sys_org"."org_icon" IS '机构图标';
COMMENT ON COLUMN "public"."sys_org"."responsible_id" IS '责任人id';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO "public"."sys_org" VALUES ('402881e662ba5fff0162ba602bff0000', '402881e662ba5fff0162ba602bff0000', 'ADMIN', '根节点', null, 'a.png', '4028c08162bda8ce0162bda8df6a0000', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 23:01:27', '4028c08162bda8ce0162bda8df6a0000', '2018-04-12 23:01:28');

-- ----------------------------
-- Table structure for sys_org_dept
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_org_dept";
CREATE TABLE "public"."sys_org_dept" (
"id" char(32) COLLATE "default" NOT NULL,
"org_id" char(32) COLLATE "default" NOT NULL,
"dept_name" varchar(20) COLLATE "default" NOT NULL,
"parent_id" char(32) COLLATE "default" NOT NULL,
"responsible_id" char(32) COLLATE "default",
"description" varchar(200) COLLATE "default",
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_org_dept" IS '机构部门表';
COMMENT ON COLUMN "public"."sys_org_dept"."dept_name" IS '部门名称';
COMMENT ON COLUMN "public"."sys_org_dept"."parent_id" IS '上级部门id';
COMMENT ON COLUMN "public"."sys_org_dept"."responsible_id" IS '责任人id';
COMMENT ON COLUMN "public"."sys_org_dept"."description" IS '描述';

-- ----------------------------
-- Records of sys_org_dept
-- ----------------------------
INSERT INTO "public"."sys_org_dept" VALUES ('4028c08162bda84d0162bda85d6b0000', '402881e662ba5fff0162ba602bff0000', '根机构部门', '4028c08162bda84d0162bda85d6b0000', '4028c08162bda8ce0162bda8df6a0000', null, '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:10', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:10');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_permission";
CREATE TABLE "public"."sys_permission" (
"id" char(32) COLLATE "default" NOT NULL,
"app_id" char(32) COLLATE "default" NOT NULL,
"permission_code" varchar(20) COLLATE "default" NOT NULL,
"permission_name" varchar(30) COLLATE "default" NOT NULL,
"description" varchar(200) COLLATE "default",
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_permission" IS '权限表';
COMMENT ON COLUMN "public"."sys_permission"."app_id" IS '应用名称';
COMMENT ON COLUMN "public"."sys_permission"."permission_code" IS '权限编号';
COMMENT ON COLUMN "public"."sys_permission"."permission_name" IS '权限名称';
COMMENT ON COLUMN "public"."sys_permission"."description" IS '描述';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO "public"."sys_permission" VALUES ('4028c081634dc57001634dc84de80001', '4028c08162b9340f0162b93427c40000', 'permission_list', '权限管理', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 13:59:23', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 13:59:23');
INSERT INTO "public"."sys_permission" VALUES ('4028c081634dc9b001634dd5545a0000', '4028c08162b9340f0162b93427c40000', 'permission_create', '添加权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:13:36', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:13:36');
INSERT INTO "public"."sys_permission" VALUES ('4028c081634dc9b001634dd5fb060001', '4028c08162b9340f0162b93427c40000', 'permission_edit', '修改权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:14:19', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 14:14:19');
INSERT INTO "public"."sys_permission" VALUES ('4028c081634dff6501634e0119890001', '4028c08162b9340f0162b93427c40000', 'permission_delete', '删除权限', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:25', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:25');
INSERT INTO "public"."sys_permission" VALUES ('4028c081634dff6501634e0122c00005', '4028c08162b9340f0162b93427c40000', 'role_list', '角色管理', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:27', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:27');
INSERT INTO "public"."sys_permission" VALUES ('4028c081634dff6501634e0124df0006', '4028c08162b9340f0162b93427c40000', 'role_create', '添加角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28');
INSERT INTO "public"."sys_permission" VALUES ('4028c081634dff6501634e0126b90007', '4028c08162b9340f0162b93427c40000', 'role_edit', '修改角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:28');
INSERT INTO "public"."sys_permission" VALUES ('4028c081634dff6501634e0128cf0008', '4028c08162b9340f0162b93427c40000', 'role_delete', '删除角色', null, '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:29', '4028c08162bda8ce0162bda8df6a0000', '2018-05-11 15:01:29');

-- ----------------------------
-- Table structure for sys_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_permission_resource";
CREATE TABLE "public"."sys_permission_resource" (
"id" char(32) COLLATE "default" NOT NULL,
"permission_id" char(32) COLLATE "default" NOT NULL,
"resource_id" char(32) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of sys_permission_resource
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_resource";
CREATE TABLE "public"."sys_resource" (
"id" char(32) COLLATE "default" NOT NULL,
"parent_id" char(32) COLLATE "default" NOT NULL,
"app_id" char(32) COLLATE "default" NOT NULL,
"resource_name" varchar(50) COLLATE "default" NOT NULL,
"resource_uri" varchar(20) COLLATE "default" NOT NULL,
"resource_type" int2 NOT NULL,
"ordered" int2 NOT NULL,
"icon" varchar(10) COLLATE "default",
"remark" varchar(200) COLLATE "default",
"state" int2 NOT NULL,
"target" varchar(10) COLLATE "default" NOT NULL,
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."sys_resource"."resource_type" IS '类型:(0:菜单)';
COMMENT ON COLUMN "public"."sys_resource"."state" IS '是否可用(0:不可用，1：可用)';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO "public"."sys_resource" VALUES ('4028c0816376a097011371a38d6a0000', '4028c0816376a097011371a38d6a0000', '4028c08162b9340f0162b93427c40000', '系统管理', '/system', '0', '1', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:26:01', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:26:08');
INSERT INTO "public"."sys_resource" VALUES ('4028c0816376a097011371a38d6a0001', '4028c0816376a097011371a38d6a0001', '4028c08162b9340f0162b93427c40000', '用户管理', '/user', '0', '2', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:28:21', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:28:23');
INSERT INTO "public"."sys_resource" VALUES ('4028c0816376a097011371a38d6a0002', '4028c0816376a097011371a38d6a0002', '4028c08162b9340f0162b93427c40000', '角色管理', '/role', '0', '3', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:29:03', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:29:05');
INSERT INTO "public"."sys_resource" VALUES ('4028c0816376a097011371a38d6a0003', '4028c0816376a097011371a38d6a0003', '4028c08162b9340f0162b93427c40000', '权限管理', '/permission', '0', '4', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:26:01', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:26:08');
INSERT INTO "public"."sys_resource" VALUES ('4028c0816376a097011371a38d6a0004', '4028c0816376a097011371a38d6a0004', '4028c08162b9340f0162b93427c40000', '资源管理', '/resource', '0', '5', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:28:21', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 12:28:23');
INSERT INTO "public"."sys_resource" VALUES ('4028c0816376a097011371a38d6a0005', '4028c0816376a097011371a38d6a0000', '4028c08162b9340f0162b93427c40000', '应用管理', '/system', '1', '1', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 13:53:24', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 13:53:28');
INSERT INTO "public"."sys_resource" VALUES ('4028c0816376a097011371a38d6a0006', '4028c0816376a097011371a38d6a0005', '4028c08162b9340f0162b93427c40000', '添加', '/system/add', '2', '1', null, null, '1', '_self', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 14:08:43', '4028c08162bda8ce0162bda8df6a0000', '2018-08-29 14:08:48');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role";
CREATE TABLE "public"."sys_role" (
"id" char(32) COLLATE "default" NOT NULL,
"app_id" char(32) COLLATE "default" NOT NULL,
"role_code" varchar(20) COLLATE "default" NOT NULL,
"role_name" varchar(30) COLLATE "default" NOT NULL,
"role_status" int2 NOT NULL,
"description" varchar(200) COLLATE "default",
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_role" IS '角色表';
COMMENT ON COLUMN "public"."sys_role"."app_id" IS '应用id';
COMMENT ON COLUMN "public"."sys_role"."role_code" IS '角色编号';
COMMENT ON COLUMN "public"."sys_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "public"."sys_role"."role_status" IS '角色状态(0:禁用,1:启用)';
COMMENT ON COLUMN "public"."sys_role"."description" IS '描述';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "public"."sys_role" VALUES ('4028c08162d2866a0162d28687770000', '4028c08162b9340f0162b93427c40000', 'ADMIN', '系统管理员', '1', null, '1                               ', '2018-04-17 15:34:15', '1                               ', '2018-04-17 15:34:15');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_role_permission";
CREATE TABLE "public"."sys_role_permission" (
"id" char(32) COLLATE "default" NOT NULL,
"role_id" char(32) COLLATE "default" NOT NULL,
"permission_id" char(32) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_role_permission" IS '角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user";
CREATE TABLE "public"."sys_user" (
"id" char(32) COLLATE "default" NOT NULL,
"org_id" char(32) COLLATE "default" NOT NULL,
"account" varchar(20) COLLATE "default" NOT NULL,
"dept_id" char(32) COLLATE "default" NOT NULL,
"phone" varchar(15) COLLATE "default" NOT NULL,
"password" varchar(100) COLLATE "default" NOT NULL,
"email" varchar(50) COLLATE "default",
"real_name" varchar(20) COLLATE "default" NOT NULL,
"user_type" int2 NOT NULL,
"is_protect" int2 NOT NULL,
"sex" int2 NOT NULL,
"icon_path" varchar(100) COLLATE "default",
"birth" date,
"province_id" char(32) COLLATE "default",
"city_id" char(32) COLLATE "default",
"user_status" int2 NOT NULL,
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_user" IS '用户基本信息表';
COMMENT ON COLUMN "public"."sys_user"."id" IS '主键';
COMMENT ON COLUMN "public"."sys_user"."account" IS '登陆账号';
COMMENT ON COLUMN "public"."sys_user"."dept_id" IS '部门id';
COMMENT ON COLUMN "public"."sys_user"."phone" IS '手机号';
COMMENT ON COLUMN "public"."sys_user"."password" IS '登陆密码';
COMMENT ON COLUMN "public"."sys_user"."email" IS '邮箱虚';
COMMENT ON COLUMN "public"."sys_user"."real_name" IS '真实名称';
COMMENT ON COLUMN "public"."sys_user"."user_type" IS '用户类型';
COMMENT ON COLUMN "public"."sys_user"."is_protect" IS '是否受保护的账号(0,否,1:是)，保护的账号有全部权限';
COMMENT ON COLUMN "public"."sys_user"."sex" IS '用户性别(1,男，2：女)';
COMMENT ON COLUMN "public"."sys_user"."icon_path" IS '用户头像';
COMMENT ON COLUMN "public"."sys_user"."birth" IS '生日';
COMMENT ON COLUMN "public"."sys_user"."province_id" IS '省份id';
COMMENT ON COLUMN "public"."sys_user"."city_id" IS '市id';
COMMENT ON COLUMN "public"."sys_user"."user_status" IS '用户状态(0,禁用,1:启用，9：已删除)';
COMMENT ON COLUMN "public"."sys_user"."created_date" IS '创建日期';
COMMENT ON COLUMN "public"."sys_user"."last_modified_date" IS '最后修改日期';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "public"."sys_user" VALUES ('4028c08162bda8ce0162bda8df6a0000', '402881e662ba5fff0162ba602bff0000', '18820136090', '4028c08162bda84d0162bda85d6b0000', '18820136090', '{bcrypt}$2a$10$Cl73dNsDXWV55uvHcLRNsu/LmvKdnCYAL50uOgcSn49AFopS3ZIaq', 'huankai@139.com', '系统管理员', '0', '1', '1', null, '2000-01-01', null, null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:44', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:19:44');
INSERT INTO "public"."sys_user" VALUES ('4028c08162bda8ce0162bda8df6a0001', '402881e662ba5fff0162ba602bff0000', '18820132014', '4028c08162bda84d0162bda85d6b0000', '18820132014', '{bcrypt}$2a$10$Cl73dNsDXWV55uvHcLRNsu/LmvKdnCYAL50uOgcSn49AFopS3ZIaq', '18820132014@139.com', 'text', '0', '0', '1', null, null, null, null, '1', 'a                               ', '2018-08-02 15:37:18', 'a                               ', '2018-08-02 15:37:22');

-- ----------------------------
-- Table structure for sys_user_idcard
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_idcard";
CREATE TABLE "public"."sys_user_idcard" (
"id" char(32) COLLATE "default" NOT NULL,
"user_id" char(32) COLLATE "default" NOT NULL,
"id_card" char(18) COLLATE "default" NOT NULL,
"real_name" varchar(20) COLLATE "default" NOT NULL,
"expire_date" date NOT NULL,
"face_image" varchar(100) COLLATE "default" NOT NULL,
"back_image" varchar(100) COLLATE "default" NOT NULL,
"address" varchar(100) COLLATE "default" NOT NULL,
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."sys_user_idcard"."real_name" IS '真实名称';
COMMENT ON COLUMN "public"."sys_user_idcard"."face_image" IS '正面照';
COMMENT ON COLUMN "public"."sys_user_idcard"."back_image" IS '反面照';

-- ----------------------------
-- Records of sys_user_idcard
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_role";
CREATE TABLE "public"."sys_user_role" (
"id" char(32) COLLATE "default" NOT NULL,
"user_id" char(32) COLLATE "default" NOT NULL,
"role_id" char(32) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_user_role" IS '用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "public"."sys_user_role" VALUES ('1                               ', '4028c08162bda8ce0162bda8df6a0001', '4028c08162d2866a0162d28687770000');

-- ----------------------------
-- Table structure for sys_user_third
-- ----------------------------
DROP TABLE IF EXISTS "public"."sys_user_third";
CREATE TABLE "public"."sys_user_third" (
"id" char(32) COLLATE "default" NOT NULL,
"user_id" char(32) COLLATE "default" NOT NULL,
"user_third_name" varchar(50) COLLATE "default" NOT NULL,
"open_id" varchar(50) COLLATE "default" NOT NULL,
"icon_url" varchar(100) COLLATE "default",
"account_type" int2 NOT NULL,
"created_by" char(32) COLLATE "default" NOT NULL,
"created_date" timestamp(6) NOT NULL,
"last_modified_by" char(32) COLLATE "default" NOT NULL,
"last_modified_date" timestamp(6) NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."sys_user_third" IS '第三方用户';
COMMENT ON COLUMN "public"."sys_user_third"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."sys_user_third"."user_third_name" IS '用户名';
COMMENT ON COLUMN "public"."sys_user_third"."icon_url" IS '头像url';
COMMENT ON COLUMN "public"."sys_user_third"."account_type" IS '账号类型(见数据字典account_type)';

-- ----------------------------
-- Records of sys_user_third
-- ----------------------------
INSERT INTO "public"."sys_user_third" VALUES ('4028c08162bdb2aa0162bdb2b9ea0000', '4028c08162bda8ce0162bda8df6a0000', 'haha', 'oNvZtv__To1bNI5clj3-oB05OO4U', null, '1', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:30:30', '4028c08162bda8ce0162bda8df6a0000', '2018-04-13 14:30:30');

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table oauth_client_details
-- ----------------------------
ALTER TABLE "public"."oauth_client_details" ADD PRIMARY KEY ("client_id");

-- ----------------------------
-- Primary Key structure for table persistent_logins
-- ----------------------------
ALTER TABLE "public"."persistent_logins" ADD PRIMARY KEY ("series");

-- ----------------------------
-- Indexes structure for table sys_app
-- ----------------------------
CREATE UNIQUE INDEX "app_code" ON "public"."sys_app" USING btree ("app_code");
CREATE INDEX "create_by" ON "public"."sys_app" USING btree ("created_by");

-- ----------------------------
-- Primary Key structure for table sys_app
-- ----------------------------
ALTER TABLE "public"."sys_app" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_dept_role
-- ----------------------------
CREATE INDEX "FK_Reference_5" ON "public"."sys_dept_role" USING btree ("dept_id");
CREATE INDEX "FK_Reference_6" ON "public"."sys_dept_role" USING btree ("role_id");

-- ----------------------------
-- Primary Key structure for table sys_dept_role
-- ----------------------------
ALTER TABLE "public"."sys_dept_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_org
-- ----------------------------
CREATE INDEX "FK_Reference_13" ON "public"."sys_org" USING btree ("responsible_id");
CREATE UNIQUE INDEX "org_code" ON "public"."sys_org" USING btree ("org_code");
CREATE INDEX "sys_org_parent_id" ON "public"."sys_org" USING btree ("parent_id");

-- ----------------------------
-- Primary Key structure for table sys_org
-- ----------------------------
ALTER TABLE "public"."sys_org" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_org_dept
-- ----------------------------
CREATE INDEX "FK_Reference_1" ON "public"."sys_org_dept" USING btree ("org_id");
CREATE INDEX "FK_Reference_14" ON "public"."sys_org_dept" USING btree ("responsible_id");
CREATE INDEX "sys_org_dept_ibfk_3" ON "public"."sys_org_dept" USING btree ("parent_id");

-- ----------------------------
-- Primary Key structure for table sys_org_dept
-- ----------------------------
ALTER TABLE "public"."sys_org_dept" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_permission
-- ----------------------------
CREATE INDEX "permission_app_id" ON "public"."sys_permission" USING btree ("app_id");

-- ----------------------------
-- Primary Key structure for table sys_permission
-- ----------------------------
ALTER TABLE "public"."sys_permission" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_permission_resource
-- ----------------------------
ALTER TABLE "public"."sys_permission_resource" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_resource
-- ----------------------------
CREATE INDEX "app_id" ON "public"."sys_resource" USING btree ("app_id");

-- ----------------------------
-- Primary Key structure for table sys_resource
-- ----------------------------
ALTER TABLE "public"."sys_resource" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_role
-- ----------------------------
CREATE INDEX "role_app_id" ON "public"."sys_role" USING btree ("app_id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_role_permission
-- ----------------------------
CREATE INDEX "FK_Reference_10" ON "public"."sys_role_permission" USING btree ("permission_id");
CREATE INDEX "FK_Reference_9" ON "public"."sys_role_permission" USING btree ("role_id");

-- ----------------------------
-- Primary Key structure for table sys_role_permission
-- ----------------------------
ALTER TABLE "public"."sys_role_permission" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_user
-- ----------------------------
CREATE UNIQUE INDEX "account" ON "public"."sys_user" USING btree ("account");
CREATE UNIQUE INDEX "user_email" ON "public"."sys_user" USING btree ("email");
CREATE INDEX "user_org_dept_id" ON "public"."sys_user" USING btree ("dept_id");
CREATE INDEX "user_org_id" ON "public"."sys_user" USING btree ("org_id");
CREATE UNIQUE INDEX "user_phone" ON "public"."sys_user" USING btree ("phone");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_user_idcard
-- ----------------------------
CREATE UNIQUE INDEX "user_id" ON "public"."sys_user_idcard" USING btree ("user_id");

-- ----------------------------
-- Primary Key structure for table sys_user_idcard
-- ----------------------------
ALTER TABLE "public"."sys_user_idcard" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_user_role
-- ----------------------------
CREATE INDEX "FK_Reference_8" ON "public"."sys_user_role" USING btree ("role_id");
CREATE INDEX "sys_user_role_ibfk_1" ON "public"."sys_user_role" USING btree ("user_id");

-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "public"."sys_user_role" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table sys_user_third
-- ----------------------------
CREATE INDEX "FK_Reference_12" ON "public"."sys_user_third" USING btree ("user_id");

-- ----------------------------
-- Primary Key structure for table sys_user_third
-- ----------------------------
ALTER TABLE "public"."sys_user_third" ADD PRIMARY KEY ("id", "user_id");

-- ----------------------------
-- Foreign Key structure for table "public"."oauth_client_details"
-- ----------------------------
ALTER TABLE "public"."oauth_client_details" ADD FOREIGN KEY ("client_id") REFERENCES "public"."sys_app" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_app"
-- ----------------------------
ALTER TABLE "public"."sys_app" ADD FOREIGN KEY ("created_by") REFERENCES "public"."sys_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_dept_role"
-- ----------------------------
ALTER TABLE "public"."sys_dept_role" ADD FOREIGN KEY ("role_id") REFERENCES "public"."sys_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."sys_dept_role" ADD FOREIGN KEY ("dept_id") REFERENCES "public"."sys_org_dept" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_org"
-- ----------------------------
ALTER TABLE "public"."sys_org" ADD FOREIGN KEY ("parent_id") REFERENCES "public"."sys_org" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_org_dept"
-- ----------------------------
ALTER TABLE "public"."sys_org_dept" ADD FOREIGN KEY ("parent_id") REFERENCES "public"."sys_org_dept" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."sys_org_dept" ADD FOREIGN KEY ("org_id") REFERENCES "public"."sys_org" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_permission"
-- ----------------------------
ALTER TABLE "public"."sys_permission" ADD FOREIGN KEY ("app_id") REFERENCES "public"."sys_app" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_role"
-- ----------------------------
ALTER TABLE "public"."sys_role" ADD FOREIGN KEY ("app_id") REFERENCES "public"."sys_app" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_role_permission"
-- ----------------------------
ALTER TABLE "public"."sys_role_permission" ADD FOREIGN KEY ("permission_id") REFERENCES "public"."sys_permission" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."sys_role_permission" ADD FOREIGN KEY ("role_id") REFERENCES "public"."sys_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_user"
-- ----------------------------
ALTER TABLE "public"."sys_user" ADD FOREIGN KEY ("dept_id") REFERENCES "public"."sys_org_dept" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."sys_user" ADD FOREIGN KEY ("org_id") REFERENCES "public"."sys_org" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_user_idcard"
-- ----------------------------
ALTER TABLE "public"."sys_user_idcard" ADD FOREIGN KEY ("user_id") REFERENCES "public"."sys_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_user_role"
-- ----------------------------
ALTER TABLE "public"."sys_user_role" ADD FOREIGN KEY ("role_id") REFERENCES "public"."sys_role" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."sys_user_role" ADD FOREIGN KEY ("user_id") REFERENCES "public"."sys_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."sys_user_third"
-- ----------------------------
ALTER TABLE "public"."sys_user_third" ADD FOREIGN KEY ("user_id") REFERENCES "public"."sys_user" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
