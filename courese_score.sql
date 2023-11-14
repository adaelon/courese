/*
 Navicat Premium Data Transfer

 Source Server         : courese
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : bj-cynosdbmysql-grp-j3h2qsj4.sql.tencentcdb.com:29746
 Source Schema         : courese_score

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 11/11/2023 11:49:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course_ratings
-- ----------------------------
DROP TABLE IF EXISTS `course_ratings`;
CREATE TABLE `course_ratings`  (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NULL DEFAULT NULL,
  `rater_id` int(11) NULL DEFAULT NULL,
  `rating_value` decimal(3, 2) NULL DEFAULT NULL,
  `feedback` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `rating_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`rating_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_ratings
-- ----------------------------

-- ----------------------------
-- Table structure for instructor_ratings
-- ----------------------------
DROP TABLE IF EXISTS `instructor_ratings`;
CREATE TABLE `instructor_ratings`  (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `instructor_id` int(11) NULL DEFAULT NULL,
  `rater_id` int(11) NULL DEFAULT NULL,
  `rating_value` decimal(3, 2) NULL DEFAULT NULL,
  `feedback` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `rating_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`rating_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of instructor_ratings
-- ----------------------------

-- ----------------------------
-- Table structure for reviewer_ratings
-- ----------------------------
DROP TABLE IF EXISTS `reviewer_ratings`;
CREATE TABLE `reviewer_ratings`  (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `reviewer_id` int(11) NULL DEFAULT NULL,
  `rater_id` int(11) NULL DEFAULT NULL,
  `rating_value` decimal(3, 2) NULL DEFAULT NULL,
  `feedback` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `rating_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`rating_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reviewer_ratings
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
