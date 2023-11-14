/*
 Navicat Premium Data Transfer

 Source Server         : courese
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : bj-cynosdbmysql-grp-j3h2qsj4.sql.tencentcdb.com:29746
 Source Schema         : courese_course

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 11/11/2023 11:49:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO `categories` VALUES (0, '全部', '全部');
INSERT INTO `categories` VALUES (1, '工科', '好东西');
INSERT INTO `categories` VALUES (2, '理科', '抽象');
INSERT INTO `categories` VALUES (3, '玛卡巴卡', '玛卡巴卡');

-- ----------------------------
-- Table structure for course_subscriptions
-- ----------------------------
DROP TABLE IF EXISTS `course_subscriptions`;
CREATE TABLE `course_subscriptions`  (
  `user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `subscription_date` date NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_subscriptions
-- ----------------------------
INSERT INTO `course_subscriptions` VALUES (4, 3, '2023-11-09');
INSERT INTO `course_subscriptions` VALUES (4, 4, '2023-11-09');
INSERT INTO `course_subscriptions` VALUES (4, 1, '2023-11-09');
INSERT INTO `course_subscriptions` VALUES (7, 1, '2023-11-10');
INSERT INTO `course_subscriptions` VALUES (7, 2, '2023-11-10');
INSERT INTO `course_subscriptions` VALUES (7, 3, '2023-11-10');
INSERT INTO `course_subscriptions` VALUES (7, 4, '2023-11-10');
INSERT INTO `course_subscriptions` VALUES (4, 1, '2023-11-10');

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `category_id` int(11) NULL DEFAULT NULL,
  `creation_date` date NOT NULL,
  `course_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_time` int(11) NULL DEFAULT NULL,
  `course_people` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 'math', '我该怎么给你解释什么叫数学课呢？', 2, 1, '2023-10-31', 'https://p.ssl.qhimg.com/d/dy_9105900134cc2785d28973fcd38ed1d1.', '入门', 64, 288);
INSERT INTO `courses` VALUES (2, 'plane', '上了这门课后，就可以飞起来辣！！！', 2, 2, '2023-11-02', 'https://p.ssl.qhimg.com/d/dy_9105900134cc2785d28973fcd38ed1d1.', '初级', 128, 882);
INSERT INTO `courses` VALUES (3, '冰红茶', '一般', 2, 1, '2023-11-08', 'https://p.ssl.qhimg.com/d/dy_9105900134cc2785d28973fcd38ed1d1.', '初级', 64, 100);
INSERT INTO `courses` VALUES (4, '好处多多', '客串', 3, 2, '2023-11-08', 'https://p.ssl.qhimg.com/d/dy_9105900134cc2785d28973fcd38ed1d1.', '高级', 64, 40);
INSERT INTO `courses` VALUES (5, '计算机网络', '就反对开挂', 3, 2, '2023-11-10', 'https://tse1-mm.cn.bing.net/th/id/OIP-C.WK-Btjd-VwYKUtzZhELv6wHaEL?pid=ImgDet&rs=1', '中级', 128, 60);
INSERT INTO `courses` VALUES (6, '大雪物理', '书法家', 3, 3, '2023-11-10', 'https://ts1.cn.mm.bing.net/th/id/R-C.6e2c609bd6959cb0b84846484abe960e?rik=LhxYKPIMR%2bOMew&riu=http%3a%2f%2fp6.qhimg.com%2ft01792c88888d5904c3.jpg%3fsize%3d580x326&ehk=1bUHjK5Sxqh%2bSbq1iq54MoOJm14EYm7LlPINrjk1dJg%3d&risl=&pid=ImgRaw&r=0', '入门', 64, 30);
INSERT INTO `courses` VALUES (7, '思维', '滴答滴答的', 2, 3, '2023-11-10', 'https://ts1.cn.mm.bing.net/th/id/R-C.9e5857d45a641e70f7015df8078fa318?rik=oQBKxAt69KyzxQ&riu=http%3a%2f%2fp0.qhimg.com%2ft01b2a6c8b838a1ba26.jpg%3fsize%3d691x431&ehk=SEIQgoJdVL8eZbTcSZ9jrkTx0WSN0rPexi1oqsDfi5c%3d&risl=&pid=ImgRaw&r=0', '中级', 128, 46);

SET FOREIGN_KEY_CHECKS = 1;
