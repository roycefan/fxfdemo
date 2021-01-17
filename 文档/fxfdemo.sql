-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        5.7.17-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 fxfdemo 的数据库结构
DROP DATABASE IF EXISTS `fxfdemo`;
CREATE DATABASE IF NOT EXISTS `fxfdemo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fxfdemo`;

-- 导出  表 fxfdemo.position 结构
DROP TABLE IF EXISTS `position`;
CREATE TABLE IF NOT EXISTS `position` (
  `Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `SecurityCode` varchar(10) NOT NULL DEFAULT '0' COMMENT '证券代码',
  `Quantity` int(11) NOT NULL DEFAULT '0' COMMENT '数量',
  `LastModifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`Id`),
  UNIQUE KEY `SecurityCode` (`SecurityCode`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='头寸';

-- 正在导出表  fxfdemo.position 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
/*!40000 ALTER TABLE `position` ENABLE KEYS */;

-- 导出  表 fxfdemo.transactionrecord 结构
DROP TABLE IF EXISTS `transactionrecord`;
CREATE TABLE IF NOT EXISTS `transactionrecord` (
  `TransactionId` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `TradeId` int(10) unsigned NOT NULL DEFAULT '0',
  `Version` int(10) unsigned NOT NULL DEFAULT '0',
  `SecurityCode` varchar(100) NOT NULL DEFAULT '0',
  `Quantity` int(11) NOT NULL DEFAULT '0',
  `Action` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Insert(1) Update(2) Cancel(3)',
  `TransactionType` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'Buy(1) Sell(2) ',
  `LastModifyTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TransactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='交易记录';

-- 正在导出表  fxfdemo.transactionrecord 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `transactionrecord` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactionrecord` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
