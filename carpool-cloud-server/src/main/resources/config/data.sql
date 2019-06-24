/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : carpool

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-06-24 11:26:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cp_user
-- ----------------------------
DROP TABLE IF EXISTS `cp_user`;
CREATE TABLE `cp_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `union_id` varchar(32) DEFAULT '' COMMENT 'unionId',
  `open_id` varchar(32) DEFAULT NULL COMMENT 'open_id',
  `real_name` varchar(32)   DEFAULT '' COMMENT '真实名字',
  `user_name` varchar(32)  DEFAULT NULL COMMENT '昵称',
  `mobile` varchar(32) DEFAULT '' COMMENT '手机号',
  `head_img` varchar(150) DEFAULT '' COMMENT '头像',
  `status` tinyint(1) DEFAULT 1 COMMENT '用户状态0.冻结1.正常',
  `deleted` tinyint(4) DEFAULT 0 COMMENT '逻辑删除  1 删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_open_id` (`open_id`) USING BTREE
);

-- ----------------------------
-- Records of cp_user
-- ----------------------------
INSERT INTO `cp_user` VALUES ('1', 'oZoMOw-zrsuC_VUmadpvqk5XOup4', 'oN0rI5QN80iynu9oeIuKzvoHE8z0', '超', '薛超', '15711066461', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoSLMMV0Gic9Zy3Nk56dZfkdm9DB5vWYQyucYOon0BG1vfVaz2d8kcsnetwM2eVzXgTJsvN2Pq45Fg/132', '1', '0', '21', '2019-05-27 17:56:36');
