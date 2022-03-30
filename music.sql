/*
 Navicat MySQL Data Transfer

 Source Server         : mybook
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : music

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 30/03/2022 14:02:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `musicid` int(0) NOT NULL,
  `musicname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌曲名称',
  `musicsinger` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '演唱者',
  `musicurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌曲链接',
  `musicDatetime` datetime(0) NULL DEFAULT NULL COMMENT '发布时间',
  `musicType` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌曲类型',
  `musicbackurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌曲背景',
  `musicoutlink` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌曲外链',
  PRIMARY KEY (`musicid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of music
-- ----------------------------
INSERT INTO `music` VALUES (66272, '夕阳无限好', '陈奕迅', NULL, '2021-12-29 17:50:20', '摇滚', '网易云轮播模糊9.jpg', 'https://music.163.com/outchain/player?type=2&id=66272&auto=1&height=66');
INSERT INTO `music` VALUES (2117962, 'Complicated', 'Avril Lavigne', 'Complicated.mp3', '2021-12-26 15:45:35', '伤感', 'Complicated.png', 'https://music.163.com/outchain/player?type=2&id=2117962&auto=1&height=66');
INSERT INTO `music` VALUES (4875084, '恶作剧', '王蓝茵', NULL, '2022-03-30 04:20:45', '流行', '1.gif', 'https://music.163.com/outchain/player?type=2&id=4875084&auto=1&height=66');
INSERT INTO `music` VALUES (5088489, 'never say never', 'The Fray', 'Never Say Never.mp3', '2021-12-26 15:40:06', '摇滚', 'nerver say nerver.jpg', 'https://music.163.com/outchain/player?type=2&id=5088489&auto=1&height=66');
INSERT INTO `music` VALUES (16431842, 'Innocence', 'Avril Lavigne', 'Innocence.mp3', '2021-12-26 15:44:36', '摇滚', 'Innocence.jpg', 'https://music.163.com/outchain/player?type=2&id=16431842&auto=1&height=66');
INSERT INTO `music` VALUES (18607254, 'down', 'Jason Walker', 'Down.mp3', '2021-12-26 15:42:32', '摇滚', 'down.jpg', 'https://music.163.com/outchain/player?type=2&id=18607254&auto=1&height=66');
INSERT INTO `music` VALUES (27846868, 'Almost Lover', 'A Fine Frenzy', 'Almost Lover.mp3', '2021-12-26 15:47:52', '伤感', 'almost lover.jpg', 'https://music.163.com/outchain/player?type=2&id=27846868&auto=1&height=66');
INSERT INTO `music` VALUES (28563317, '阴天快乐', '陈奕迅', NULL, '2021-12-28 15:46:39', '流行', '网易云轮播模糊6.jpg', 'https://music.163.com/outchain/player?type=2&id=28563317&auto=1&height=66');
INSERT INTO `music` VALUES (28875146, '海阔天空', 'Beyond', NULL, '2021-12-29 04:16:21', '摇滚', '网易云轮播模糊4.jpg', 'https://music.163.com/outchain/player?type=2&id=28875146&auto=1&height=66');
INSERT INTO `music` VALUES (28940043, '世界变了样', 'Supper Moment', NULL, '2021-12-29 17:45:04', '摇滚', '不能说的秘密.jpg', 'https://music.163.com/outchain/player?type=2&id=28940043&auto=1&height=66');
INSERT INTO `music` VALUES (28940048, '无尽', 'Supper Moment', '无尽.mp3', '2021-12-26 15:46:10', '流行', '无尽.jpg', 'https://music.163.com/outchain/player?type=2&id=28940048&auto=1&height=66');
INSERT INTO `music` VALUES (441102547, ' Listen', 'ONE OK ROCK', 'Listen.mp3', '2021-12-26 15:47:01', '摇滚', 'Listen.jpg', 'https://music.163.com/outchain/player?type=2&id=441102547&auto=1&height=66');
INSERT INTO `music` VALUES (441491828, '水星记', '郭顶', NULL, '2021-12-28 11:50:06', '流行', '1.gif', 'https://music.163.com/outchain/player?type=2&id=441491828&auto=1&height=66');
INSERT INTO `music` VALUES (449577226, '你就不要想起我', '田馥甄', NULL, '2021-12-29 04:14:32', '伤感', '网易云轮播迷糊2.png', 'https://music.163.com/outchain/player?type=2&id=449577226&auto=1&height=66');
INSERT INTO `music` VALUES (454828887, '遇见', '孙燕姿', NULL, '2021-12-29 17:09:09', '伤感', '网易云轮播迷糊2.png', 'https://music.163.com/outchain/player?type=2&id=454828887&auto=1&height=66');
INSERT INTO `music` VALUES (486188225, '刚好遇见你', '李玉刚', NULL, '2021-12-30 10:10:41', '伤感', '不能说的秘密.jpg', 'https://music.163.com/outchain/player?type=2&id=486188225&auto=1&height=66');
INSERT INTO `music` VALUES (1359356908, '晚安', '颜人中', NULL, '2022-01-18 14:09:12', '伤感', '1.gif', 'https://music.163.com/outchain/player?type=2&id=1359356908&auto=1&height=66');
INSERT INTO `music` VALUES (1366053794, '够钟', '周柏豪', NULL, '2021-12-30 06:06:10', '伤感', '网易云轮播模糊4.jpg', 'https://music.163.com/outchain/player?type=2&id=1366053794&auto=1&height=66');
INSERT INTO `music` VALUES (1382830142, '假装', '姜云升', NULL, '2021-12-28 11:53:33', '流行', '微信图片_20190916134256.jpg', 'https://music.163.com/outchain/player?type=2&id=1382830142&auto=1&height=66');
INSERT INTO `music` VALUES (1398663411, '冬眠', '司南', '冬眠.mp3', '2021-12-26 15:41:51', '流行', '冬眠.jpg', 'https://music.163.com/outchain/player?type=2&id=1398663411&auto=1&height=66');
INSERT INTO `music` VALUES (1416174014, ' 颗粒季', '希林娜依·高', NULL, '2021-12-28 13:15:41', '流行', '1.gif', 'https://music.163.com/outchain/player?type=2&id=1416174014&auto=1&height=66');
INSERT INTO `music` VALUES (1492901592, '未曾许诺的花园', '欧阳娜娜', NULL, '2021-12-28 13:22:18', '流行', '微信图片_20190916134256.jpg', 'https://music.163.com/outchain/player?type=2&id=1492901592&auto=1&height=66');
INSERT INTO `music` VALUES (1816573754, '送我一朵小红花', '厦门六中', NULL, '2022-01-18 14:10:17', '摇滚', '1.gif', 'https://music.163.com/outchain/player?type=2&id=1816573754&auto=1&height=66');
INSERT INTO `music` VALUES (1832582017, 'LMLY', '王嘉尔', NULL, '2021-12-30 08:28:11', '摇滚', '网易云轮播迷糊2.png', 'https://music.163.com/outchain/player?type=2&id=1832582017&auto=1&height=66');
INSERT INTO `music` VALUES (1838373934, '约束', 'Lily Sayonara', NULL, '2021-12-29 17:05:26', '伤感', '网易云轮播模糊9.jpg', 'https://music.163.com/outchain/player?type=2&id=1838373934&auto=1&height=66');
INSERT INTO `music` VALUES (1843019396, '不能说的秘密', 'Young', '不能说的秘密 (Live).mp3', '2021-12-26 15:43:47', '伤感', '不能说的秘密.jpg', 'https://music.163.com/outchain/player?type=2&id=1843019396&auto=1&height=66');
INSERT INTO `music` VALUES (1860615053, '藏藏', '欧阳娜娜', NULL, '2021-12-28 22:14:39', '流行', '网易云轮播模糊6.jpg', 'https://music.163.com/outchain/player?type=2&id=1860615053&auto=1&height=66');
INSERT INTO `music` VALUES (1870733018, '搁浅', '周杰伦', NULL, '2021-12-29 05:53:22', '摇滚', '网易云轮播迷糊2.png', 'https://music.163.com/outchain/player?type=2&id=1870733018&auto=1&height=66');
INSERT INTO `music` VALUES (1874158536, 'SAD', '姜云升', NULL, '2021-12-28 11:59:20', '伤感', '1.gif', 'https://music.163.com/outchain/player?type=2&id=1874158536&auto=1&height=66');
INSERT INTO `music` VALUES (1876989243, 'YOUTH', 'Troye Sivan', NULL, '2021-12-29 17:00:10', '摇滚', '网易云轮播模糊7.jpg', 'https://music.163.com/outchain/player?type=2&id=1876989243&auto=1&height=66');
INSERT INTO `music` VALUES (1879229107, '小宇', '蓝心羽', NULL, '2021-12-29 16:58:51', '伤感', '网易云轮播迷糊2.png', 'https://music.163.com/outchain/player?type=2&id=1879229107&auto=1&height=66');
INSERT INTO `music` VALUES (1886371886, '百年孤寂', '魏晗', NULL, '2021-12-31 10:51:26', '伤感', '网易云轮播模糊8.jpg', 'https://music.163.com/outchain/player?type=2&id=1886371886&auto=1&height=66');
INSERT INTO `music` VALUES (1887917182, '浪漫主义', '姜云升', NULL, '2021-12-29 08:15:53', '摇滚', '冬眠.jpg', 'https://music.163.com/outchain/player?type=2&id=1887917182&auto=1&height=66');
INSERT INTO `music` VALUES (1897480578, '晴天', '周杰伦', NULL, '2021-12-28 15:32:25', '流行', '1.gif', 'https://music.163.com/outchain/player?type=2&id=1897480578&auto=1&height=66');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userid` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `userpwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '黄冠滨', '123');
INSERT INTO `user` VALUES (3, 'g', '1234');

-- ----------------------------
-- Table structure for userlove
-- ----------------------------
DROP TABLE IF EXISTS `userlove`;
CREATE TABLE `userlove`  (
  `userid` int(0) NOT NULL COMMENT '我的最爱的用户id',
  `musicid` int(0) NOT NULL COMMENT '用户收藏的歌曲id',
  PRIMARY KEY (`userid`, `musicid`) USING BTREE,
  INDEX `pk1`(`userid`) USING BTREE,
  INDEX `pk2`(`musicid`) USING BTREE,
  CONSTRAINT `primaryk1` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `primaryk2` FOREIGN KEY (`musicid`) REFERENCES `music` (`musicid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userlove
-- ----------------------------
INSERT INTO `userlove` VALUES (1, 2117962);
INSERT INTO `userlove` VALUES (1, 5088489);
INSERT INTO `userlove` VALUES (1, 16431842);
INSERT INTO `userlove` VALUES (1, 18607254);
INSERT INTO `userlove` VALUES (1, 27846868);
INSERT INTO `userlove` VALUES (1, 28563317);
INSERT INTO `userlove` VALUES (1, 28875146);
INSERT INTO `userlove` VALUES (1, 28940043);
INSERT INTO `userlove` VALUES (1, 1366053794);
INSERT INTO `userlove` VALUES (1, 1870733018);
INSERT INTO `userlove` VALUES (3, 28875146);
INSERT INTO `userlove` VALUES (3, 449577226);

SET FOREIGN_KEY_CHECKS = 1;
