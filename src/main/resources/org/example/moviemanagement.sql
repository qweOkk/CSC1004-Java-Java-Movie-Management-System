/*
 Navicat MySQL Data Transfer

 Source Server         : qweokk
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : moviemanagement

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 09/05/2023 21:22:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1112 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'qweokk', '123');
INSERT INTO `admin` VALUES (2, 'lwy', 'abc');
INSERT INTO `admin` VALUES (3, 'Admin', 'abc123');
INSERT INTO `admin` VALUES (5, 'qwe', '123');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `mid` int NOT NULL,
  `moviename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `comment` char(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `picture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 6, 'qweokk', 1, 'Harry Potter', 'adssfdsgaagas', 'non_exist');
INSERT INTO `comment` VALUES (8, 6, 'qweokk', 1, 'Harry Potter', 'asas', 'non_exist');
INSERT INTO `comment` VALUES (9, 2, 'qwe', 1, 'Harry Potter', 'asafsaf', 'non_exist');
INSERT INTO `comment` VALUES (10, 2, 'qwe', 1, 'Harry Potter', 'fasfasfafas', 'non_exist');
INSERT INTO `comment` VALUES (11, 2, 'qwe', 1, 'Harry Potter', 'asfasf', 'non_exist');
INSERT INTO `comment` VALUES (24, 16, 'qwqwe', 1, 'Harry Potter', 'eqeqweqwe', 'non_exist');
INSERT INTO `comment` VALUES (29, 6, 'qweokk', 1, 'Harry Potter', '', 'non_exist');
INSERT INTO `comment` VALUES (30, 6, 'qweokk', 1, 'Harry Potter', '', 'non_exist');
INSERT INTO `comment` VALUES (36, 6, 'qweokk', 1, 'Harry Potter', 'asdasd', 'non_exist');
INSERT INTO `comment` VALUES (43, 6, 'qweokk', 3, 's', 'afsas', 'ev05b15.jpg');
INSERT INTO `comment` VALUES (44, 6, 'qweokk', 3, 's', 'adas', 'Default.jpg');

-- ----------------------------
-- Table structure for commonuser
-- ----------------------------
DROP TABLE IF EXISTS `commonuser`;
CREATE TABLE `commonuser`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `password` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `age` int NOT NULL,
  `tel` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `sid` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commonuser
-- ----------------------------
INSERT INTO `commonuser` VALUES (2, 'qwe', '123', 10, '11', '11');
INSERT INTO `commonuser` VALUES (3, 'zky', '114514', 30, '1919810', '');
INSERT INTO `commonuser` VALUES (5, 'aaa', 'aa', 90, 'aa', 'aa');
INSERT INTO `commonuser` VALUES (6, 'qweokk', '123', 40, '123', '1233');
INSERT INTO `commonuser` VALUES (7, 'qweaaa', 'aaaaaa', 11, 'aa', 'aa');
INSERT INTO `commonuser` VALUES (12, 'aaa1', 'aaa', 123, '123121', 'a');
INSERT INTO `commonuser` VALUES (16, 'qwqwe', 'qweqw', 12, 'qwe', 'qweq');
INSERT INTO `commonuser` VALUES (18, 'a', 'aaa', 123, 'aa', 'aaa');
INSERT INTO `commonuser` VALUES (19, 'qq', 'qqq', 12, '11', '111');

-- ----------------------------
-- Table structure for mclass
-- ----------------------------
DROP TABLE IF EXISTS `mclass`;
CREATE TABLE `mclass`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `className` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2334 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mclass
-- ----------------------------
INSERT INTO `mclass` VALUES (1, 'comedy');
INSERT INTO `mclass` VALUES (2, 'science fiction');
INSERT INTO `mclass` VALUES (3, 'documentary');
INSERT INTO `mclass` VALUES (4, 'fantasy');
INSERT INTO `mclass` VALUES (5, 'comic');

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `coverPath` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
  `name` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `director` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `actor` varchar(60) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `intro` varchar(140) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `classNumber` int NOT NULL DEFAULT 2333,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `classNumber`(`classNumber` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2333334 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, 'iml.jpg', 'Harry Potter', 'Chris Columbus', 'Daniel Jacob Radcliffe', 'An interesting film.', 1);
INSERT INTO `movie` VALUES (2, 'im0815_.jpg', 'a', 'aa', 'aaa', 'aaaa', 2);
INSERT INTO `movie` VALUES (3, 'interstellar-4.jpg', 's', 'ss', 'sss', 'ssss', 4);

-- ----------------------------
-- Table structure for rate
-- ----------------------------
DROP TABLE IF EXISTS `rate`;
CREATE TABLE `rate`  (
  `uid` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `mid` int NOT NULL,
  `moviename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `rate` int NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rate
-- ----------------------------
INSERT INTO `rate` VALUES (18, 'a', 1, 'Harry Potter', 3);
INSERT INTO `rate` VALUES (2, 'qwe', 1, 'Harry Potter', 4);
INSERT INTO `rate` VALUES (19, 'qq', 1, 'Harry Potter', 3);
INSERT INTO `rate` VALUES (6, 'qweokk', 1, 'Harry Potter', 4);
INSERT INTO `rate` VALUES (6, 'qweokk', 2, 'a', 2);
INSERT INTO `rate` VALUES (6, 'qweokk', 3, 's', 5);

SET FOREIGN_KEY_CHECKS = 1;
