/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : lvcar

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-07-06 17:30:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cp_msg`
-- ----------------------------
DROP TABLE IF EXISTS `cp_msg`;
CREATE TABLE `cp_msg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL COMMENT '消息内容',
  `type` varchar(255) DEFAULT NULL COMMENT '消息类型（1:车寻人，车找人，找人2：人找车，人寻车',
  `way` varchar(255) DEFAULT NULL COMMENT '地址',
  `time` varchar(255) DEFAULT NULL,
  `start_addr` varchar(30) DEFAULT NULL,
  `end_addr` varchar(30) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `creator` int(11) DEFAULT NULL COMMENT '发布人',
  `status` int(11) DEFAULT NULL COMMENT '消息状态（1:已发布2：已撤销）',
  `sort` varchar(255) DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `modify_time` date DEFAULT NULL COMMENT '修改时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cp_msg
-- ----------------------------
INSERT INTO `cp_msg` VALUES ('1', '车找人：今天晚上22：00六里桥A口，过地下通道上来红路灯除回涿州，杜家坎上高速直达涿州北，华阳路，联合七号院，国富，香巴溪谷，翡翠城，凯旋门电话15311917018微信约车余2个座位', '车找人', '六里桥A口过地下通道上来红绿灯口处-涿州', null, null, null, null, '1', '1', '1', '15311917018', null, '1', null);
INSERT INTO `cp_msg` VALUES ('2', '人找车，周一早六点左右，玫瑰大街出发。到立水桥。电话15201284366', '人找车', '玫瑰大街->立水桥', '周一早六点', '玫瑰大街', '立水桥', '15201284366', null, null, null, null, null, null, null);
INSERT INTO `cp_msg` VALUES ('3', ' 车找人：明早6:40汇源出发--广场，政法街南口，国富，涿州北，终点六里桥a口。联系电话:13161519666首次拼车预付费。20/位', '车找人', '政法街南口->涿州北', '明早6:40', '政法街南口', '涿州北', '13161519666', null, null, null, null, null, null, null);
INSERT INTO `cp_msg` VALUES ('4', '车寻人，明早周日）6:40家乐福出发，华阳路沿线，国富（6:50），涿州北上高速，终点六里桥地铁18632215792，', '车找人', '华阳路->六里桥', '明早->周日->6:40->6:50', '华阳路', '六里桥', '18632215792', null, '1', null, null, null, null, '2019-07-06');
