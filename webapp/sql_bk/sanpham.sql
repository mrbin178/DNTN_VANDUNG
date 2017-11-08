/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : satthep_vandung

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-11-09 00:54:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sanpham
-- ----------------------------
DROP TABLE IF EXISTS `sanpham`;
CREATE TABLE `sanpham` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `MaSanPham` varchar(50) DEFAULT NULL,
  `TenSanPham` varchar(100) DEFAULT NULL,
  `GiaVon` varchar(20) DEFAULT '0',
  `GiaBan` varchar(20) DEFAULT '0',
  `TrangThai` int(1) DEFAULT '0' COMMENT '1: Active; 2: UnActive; 3: Block',
  `NgayTao` datetime DEFAULT NULL,
  `NgayUpdate` datetime DEFAULT NULL,
  `DonViTinh` varchar(50) DEFAULT NULL,
  `TonKho` varchar(50) DEFAULT NULL,
  `NguoiTao` varchar(100) DEFAULT NULL,
  `NguoiCapNhat` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sanpham
-- ----------------------------
INSERT INTO `sanpham` VALUES ('1', 'MSP0000001', 'Sản phẩm 1', '100000', '150000', '1', '2017-11-08 18:36:16', null, 'Cái', '1', 'Admin', null);
INSERT INTO `sanpham` VALUES ('2', 'MSP0000002', 'Sản phẩm 2', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '2', 'Admin', null);
INSERT INTO `sanpham` VALUES ('3', 'MSP0000002', 'Sản phẩm 3', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '3', 'Admin', null);
INSERT INTO `sanpham` VALUES ('4', 'MSP0000002', 'Sản phẩm 4', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '4', 'Admin', null);
INSERT INTO `sanpham` VALUES ('5', 'MSP0000002', 'Sản phẩm 5', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '5', 'Admin', null);
INSERT INTO `sanpham` VALUES ('6', 'MSP0000002', 'Sản phẩm 6', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '6', 'Admin', null);
INSERT INTO `sanpham` VALUES ('7', 'MSP0000002', 'Sản phẩm 7', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '7', 'Admin', null);
INSERT INTO `sanpham` VALUES ('8', 'MSP0000002', 'Sản phẩm 8', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '8', 'Admin', null);
INSERT INTO `sanpham` VALUES ('9', 'MSP0000002', 'Sản phẩm 9', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '9', 'Admin', null);
INSERT INTO `sanpham` VALUES ('10', 'MSP0000002', 'Sản phẩm 10', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '10', 'Admin', null);
INSERT INTO `sanpham` VALUES ('11', 'MSP0000002', 'Sản phẩm 11', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '11', 'Admin', null);
INSERT INTO `sanpham` VALUES ('12', 'MSP0000002', 'Sản phẩm 12', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '12', 'Admin', null);
INSERT INTO `sanpham` VALUES ('13', 'MSP0000002', 'Sản phẩm 13', '200000', '250000', '1', '2017-11-08 18:36:16', null, 'Bộ', '13', 'Admin', null);
