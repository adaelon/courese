/*
 Navicat Premium Data Transfer

 Source Server         : courese
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : bj-cynosdbmysql-grp-j3h2qsj4.sql.tencentcdb.com:29746
 Source Schema         : courese_peer

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 11/11/2023 11:49:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for anomalies
-- ----------------------------
DROP TABLE IF EXISTS `anomalies`;
CREATE TABLE `anomalies`  (
  `anomaly_id` int(11) NOT NULL AUTO_INCREMENT,
  `reviewer_id` int(11) NULL DEFAULT NULL,
  `submission_id` int(11) NULL DEFAULT NULL,
  `anomaly_description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `detection_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`anomaly_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of anomalies
-- ----------------------------
INSERT INTO `anomalies` VALUES (1, 4, 1, '链路', '2023-11-10');

-- ----------------------------
-- Table structure for auto_scores
-- ----------------------------
DROP TABLE IF EXISTS `auto_scores`;
CREATE TABLE `auto_scores`  (
  `auto_score_id` int(11) NOT NULL AUTO_INCREMENT,
  `submission_id` int(11) NULL DEFAULT NULL,
  `score_algorithm` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `score` decimal(5, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`auto_score_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of auto_scores
-- ----------------------------

-- ----------------------------
-- Table structure for peer_review_assignments
-- ----------------------------
DROP TABLE IF EXISTS `peer_review_assignments`;
CREATE TABLE `peer_review_assignments`  (
  `reviewer_id` int(11) NOT NULL,
  `submission_id` int(11) NOT NULL,
  PRIMARY KEY (`reviewer_id`, `submission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of peer_review_assignments
-- ----------------------------

-- ----------------------------
-- Table structure for peer_reviews
-- ----------------------------
DROP TABLE IF EXISTS `peer_reviews`;
CREATE TABLE `peer_reviews`  (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `submission_id` int(11) NULL DEFAULT NULL,
  `reviewer_id` int(11) NULL DEFAULT NULL,
  `score` decimal(5, 2) NULL DEFAULT NULL,
  `feedback` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `review_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of peer_reviews
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
