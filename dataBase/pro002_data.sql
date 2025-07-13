/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 90300
 Source Host           : localhost:3306
 Source Schema         : b_myproject

 Target Server Type    : MySQL
 Target Server Version : 90300
 File Encoding         : 65001

 Date: 13/07/2025 14:09:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
BEGIN;
INSERT INTO `admin` (`id`, `username`, `password`, `role`, `name`) VALUES (1, 'adminppp', '654321', 'ADMIN', '管理员1号');
INSERT INTO `admin` (`id`, `username`, `password`, `role`, `name`) VALUES (2, 'adminzzz', '123456', 'ADMIN', '新增修改2号');
INSERT INTO `admin` (`id`, `username`, `password`, `role`, `name`) VALUES (3, 'adminttt', '123456', 'ADMIN', '管理员3号');
INSERT INTO `admin` (`id`, `username`, `password`, `role`, `name`) VALUES (4, 'admin444', '123456', 'ADMIN', '增加4号');
INSERT INTO `admin` (`id`, `username`, `password`, `role`, `name`) VALUES (5, 'adminMac', '654321', 'ADMIN', '来自macOS的5号');
COMMIT;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` (`id`, `name`) VALUES (1, '采购部');
INSERT INTO `department` (`id`, `name`) VALUES (2, '技术部');
INSERT INTO `department` (`id`, `name`) VALUES (3, '销售部');
COMMIT;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '工号',
  `age` int DEFAULT NULL COMMENT '年龄',
  `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '个人介绍',
  `department_id` int DEFAULT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of employee
-- ----------------------------
BEGIN;
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (1, 'aaa', '123456', 'EMP', 'whir1号', '男', '001', 23, '这是一条平平无奇的关于我们一号员工的简介', 1);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (2, 'sss', '123456', 'EMP', 'whir2号', '女', '005', 18, '这是一条平平无奇的关于我们五号员工的简介', 3);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (3, 'ddd', '123456', 'EMP', 'whir3号', '女', '008', 22, '换个别的简介来介绍我们的八号员工', 2);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (4, 'fff', '123456', 'EMP', '张三', '男', '012', 30, '十二号员工是张三', 3);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (5, 'ggg', '123456', 'EMP', '李四', '男', '015', 35, '十五号员工是李四', 1);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (6, 'hhh', '123456', 'EMP', '王五', '女', '017', 32, '十七号员工是王五', 2);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (7, 'jjj', '123456', 'EMP', 'whir7号', '女', '018', 19, '这是一条平平无奇的关于我们十八号员工的简介', 1);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (8, 'kkk', '123456', 'EMP', 'whir8号', '男', '101', 22, '这是一条平平无奇的关于我们101号员工的简介', 2);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (9, '111', '123456', 'EMP', 'whir9号', '男', '106', 24, '换个别的简介来介绍我们的106号员工', 3);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (10, '444', '123456', 'EMP', 'whirX', '女', '118', 18, '118号员工是whirX', 3);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (11, '888', '123456', 'EMP', '钱六', '男', '121', 40, '121号员工名叫钱六', 2);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (12, 'vvv', '123456', 'EMP', '赵七', '男', '125', 42, '125号员工名叫赵七', 1);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (13, 'ppp', '123456', 'EMP', '陈八', '女', '128', 38, '这是一条平平无奇的关于我们128号员工的简介', 2);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (14, 'nnn', '123456', 'EMP', '手动添加1号', '男', '201', 21, '手动添加的第一位员工', 2);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (16, 'mmm', '123456', 'EMP', '手动添加2号', '女', '204', 19, '手动添加的第二位员工', 1);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (17, 'rrr', '123456', 'EMP', '手动添加3号', '男', '209', 41, '手动添加的第三位员工', 3);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (18, 'zhuce1', '123456', 'EMP', 'zhuce1', '男', '301', 20, '成功注册的第一位用户', NULL);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (19, 'zhuce2', '1234567', 'EMP', '注册二', '女', '302', 16, '成功注册的第二个员工', NULL);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (20, 'testmac1', '1234567', 'EMP', 'whirMac', '女', '355', 18, '我们的第355号员工', NULL);
INSERT INTO `employee` (`id`, `username`, `password`, `role`, `name`, `sex`, `no`, `age`, `description`, `department_id`) VALUES (21, 'testmac2', '123456', 'EMP', 'whirMac2', '男', '393', 17, '进行第二次mac后端数据库的测试', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
