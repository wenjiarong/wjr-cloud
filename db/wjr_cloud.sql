/*
Navicat MySQL Data Transfer

Source Server         : 本地MySql
Source Server Version : 80011
Source Host           : 127.0.0.1:13306
Source Database       : wjr_cloud

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2020-04-05 00:33:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(256) DEFAULT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `DEPT_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级部门ID',
  `DEPT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `ORDER_NUM` double(20,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`DEPT_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '0', '开发部', '1', '2018-01-04 15:42:26', '2019-01-05 21:08:27');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `MENU_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单/按钮ID',
  `PARENT_ID` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `MENU_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单/按钮名称',
  `PATH` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对应路由path',
  `COMPONENT` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '对应路由组件component',
  `PERMS` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限标识',
  `ICON` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标',
  `TYPE` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型 0菜单 1按钮',
  `ORDER_NUM` double(20,0) DEFAULT NULL COMMENT '排序',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`MENU_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '0', '系统管理', '/system', 'Layout', null, 'el-icon-set-up', '0', '1', '2017-12-27 16:39:07', '2019-07-20 16:19:04');
INSERT INTO `t_menu` VALUES ('2', '1', '用户管理', '/system/user', 'wjr/system/user/Index', 'user:view', '', '0', '1', '2017-12-27 16:47:13', '2019-01-22 06:45:55');
INSERT INTO `t_menu` VALUES ('3', '2', '新增用户', '', '', 'user:add', null, '1', null, '2017-12-27 17:02:58', null);
INSERT INTO `t_menu` VALUES ('4', '2', '修改用户', '', '', 'user:update', null, '1', null, '2017-12-27 17:04:07', null);
INSERT INTO `t_menu` VALUES ('5', '2', '删除用户', '', '', 'user:delete', null, '1', null, '2017-12-27 17:04:58', null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `ROLE_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `ROLE_NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `REMARK` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色描述',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ROLE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '管理员', '管理员', '2019-08-08 16:23:11', '2019-08-09 14:38:59');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `ROLE_ID` bigint(20) NOT NULL,
  `MENU_ID` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES ('1', '1');
INSERT INTO `t_role_menu` VALUES ('1', '2');
INSERT INTO `t_role_menu` VALUES ('1', '3');
INSERT INTO `t_role_menu` VALUES ('1', '4');
INSERT INTO `t_role_menu` VALUES ('1', '5');

-- ----------------------------
-- Table structure for t_trade_log
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_log`;
CREATE TABLE `t_trade_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `goods_id` int(11) NOT NULL COMMENT '商品ID',
  `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_trade_log
-- ----------------------------
INSERT INTO `t_trade_log` VALUES ('1', '1', '跑车', '下单并支付成功', '2020-03-31 19:20:09');
INSERT INTO `t_trade_log` VALUES ('2', '1', '跑车', '打包完毕，开始物流配送！', '2020-03-31 19:20:42');
INSERT INTO `t_trade_log` VALUES ('3', '1', '雷克萨斯', '下单并支付成功', '2020-03-31 19:49:44');
INSERT INTO `t_trade_log` VALUES ('4', '1', '雷克萨斯', '打包完毕，开始物流配送！', '2020-03-31 19:49:45');

-- ----------------------------
-- Table structure for t_transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `t_transaction_log`;
CREATE TABLE `t_transaction_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `transaction_Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '事务id',
  `remark` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '备注',
  PRIMARY KEY (`id`,`transaction_Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_transaction_log
-- ----------------------------
INSERT INTO `t_transaction_log` VALUES ('1', '67653b6e-a078-4369-828d-260bf6154271', '事务ID为67653b6e-a078-4369-828d-260bf6154271的本地事务执行成功');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `PASSWORD` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `DEPT_ID` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `EMAIL` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系电话',
  `STATUS` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 0锁定 1有效',
  `CREATE_TIME` datetime NOT NULL COMMENT '创建时间',
  `MODIFY_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL COMMENT '最近访问时间',
  `SSEX` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别 0男 1女 2保密',
  `AVATAR` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `DESCRIPTION` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`USER_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'wenjiarong', '$2a$10$gzhiUb1ldc1Rf3lka4k/WOoFKKGPepHSzJxzcPSN5/65SzkMdc.SK', '1', '631041490@qq.com', '17788888888', '1', '2019-06-14 20:39:22', '2020-03-20 16:16:32', '2019-08-02 15:57:00', '1', 'default.jpg', '我是帅比');
INSERT INTO `t_user` VALUES ('2', 'wenjiahua', '$2a$10$vBJrmPQ9ud7GdbkIu3iDk.7aDr.x69PNFXFv6twyISMVnydCPjVqe', null, '631041490@qq.com', '18126670755', '1', '2020-03-20 16:02:39', '2020-03-22 11:14:41', null, '1', 'default.jpg', null);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `USER_ID` bigint(20) NOT NULL COMMENT '用户ID',
  `ROLE_ID` bigint(20) NOT NULL COMMENT '角色ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关联表';

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '1');
INSERT INTO `t_user_role` VALUES ('2', '1');
