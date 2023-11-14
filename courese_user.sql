/*
 Navicat Premium Data Transfer

 Source Server         : courese
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : bj-cynosdbmysql-grp-j3h2qsj4.sql.tencentcdb.com:29746
 Source Schema         : courese_user

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 11/11/2023 11:49:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, '老师');
INSERT INTO `roles` VALUES (2, '学生');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (2, 1);
INSERT INTO `user_roles` VALUES (3, 1);
INSERT INTO `user_roles` VALUES (4, 2);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `intro` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `profile_picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `date_registered` date NOT NULL,
  `last_login` date NOT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'ada', 'ada', 'ada', 'haha', 'https://pic2.zhimg.com/v2-2c59e60f77578261a1023345e8dd4b6d_r.jpg?source=1940ef5c', '2023-10-27', '2023-10-27', '男');
INSERT INTO `users` VALUES (2, '张有志', 'cecilriver', '21301031@bjtu.edu.cn', '我喜欢三星S23 Ultra手机', 'https://pic2.zhimg.com/v2-2c59e60f77578261a1023345e8dd4b6d_r.jpg?source=1940ef5c', '2023-11-01', '2023-11-02', '男');
INSERT INTO `users` VALUES (3, '李大炮', 'ddd', 'ddd', 'ddd', 'https://pic2.zhimg.com/v2-2c59e60f77578261a1023345e8dd4b6d_r.jpg?source=1940ef5c', '2023-11-08', '2023-11-08', '男');
INSERT INTO `users` VALUES (4, '陈晓君', 'aaa', '21301031@bjtu.edu.cn', '我是陈晓君，我只用三星', 'https://pic2.zhimg.com/v2-2c59e60f77578261a1023345e8dd4b6d_r.jpg?source=1940ef5c', '2023-11-09', '2023-11-09', '男');
INSERT INTO `users` VALUES (5, 'test', 'test', NULL, NULL, NULL, '2023-11-10', '2023-11-10', NULL);
INSERT INTO `users` VALUES (6, 'test1', 'test1', NULL, NULL, NULL, '2023-11-10', '2023-11-10', NULL);
INSERT INTO `users` VALUES (7, 'test2', 'test2', NULL, NULL, NULL, '2023-11-10', '2023-11-10', NULL);

SET FOREIGN_KEY_CHECKS = 1;
