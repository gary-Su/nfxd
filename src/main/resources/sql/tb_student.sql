/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : personal

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2019-06-04 17:11:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生信息ID',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `sex` varchar(8) NOT NULL,
  `phone` varchar(20) NOT NULL COMMENT '电话号码',
  `qqorwechat` varchar(50) NOT NULL,
  `school` varchar(100) NOT NULL,
  `subject` varchar(20) NOT NULL COMMENT '高中文理科',
  `grade` varchar(10) NOT NULL COMMENT '高考分数',
  `major` varchar(20) NOT NULL COMMENT '意向专业',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('1', '张三', '男', '1321324324', '121sseee', 'sdfdslfjdlfdsf', '文科', '111', 'J2EE');
INSERT INTO `tb_student` VALUES ('2', '李四', '男', '132132432422', '121sseee前期', 'sdfdslfjdlfdsf', '理科', '222', '.NET');
INSERT INTO `tb_student` VALUES ('3', '王五', '男', '132132432422', '121sseee11', 'sdfdslfjdlfdsf11', '中职', '121', '.NET');
