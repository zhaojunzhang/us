/*
SQLyog v10.2 
MySQL - 5.5.17 : Database - usdatabase
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`usdatabase` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `usdatabase`;

/*Table structure for table `us_addresses` */

DROP TABLE IF EXISTS `us_addresses`;

CREATE TABLE `us_addresses` (
  `addressid` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户地址',
  `address` varchar(100) DEFAULT NULL COMMENT '用户地址',
  `uid` int(10) DEFAULT NULL,
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `us_addresses` */

/*Table structure for table `us_admin_groups` */

DROP TABLE IF EXISTS `us_admin_groups`;

CREATE TABLE `us_admin_groups` (
  `agid` int(10) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(50) DEFAULT NULL COMMENT '管理员分组名',
  `comment` varchar(100) DEFAULT NULL,
  `authority` varchar(100) DEFAULT NULL COMMENT '添加文章，添加商品，查询用户，更改用户权限，删除用户',
  PRIMARY KEY (`agid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `us_admin_groups` */

insert  into `us_admin_groups`(`agid`,`groupname`,`comment`,`authority`) values (1,'超级管理员',NULL,'1,1,1,1,1');

/*Table structure for table `us_admins` */

DROP TABLE IF EXISTS `us_admins`;

CREATE TABLE `us_admins` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `agid` int(10) NOT NULL COMMENT '管理员分组',
  `created` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `loged` bigint(14) DEFAULT NULL COMMENT '上次登录时间',
  `ip` varchar(40) DEFAULT NULL COMMENT '用户ip',
  `comment` varchar(100) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL COMMENT '用户的客户端系统',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `us_admins` */

insert  into `us_admins`(`id`,`username`,`password`,`agid`,`created`,`loged`,`ip`,`comment`,`agent`) values (1,'admin','admin',1,NULL,1445067922586,'127.0.0.1',NULL,'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36');

/*Table structure for table `us_articles` */

DROP TABLE IF EXISTS `us_articles`;

CREATE TABLE `us_articles` (
  `aid` int(10) NOT NULL AUTO_INCREMENT,
  `cid` int(10) NOT NULL COMMENT '分类表id',
  `ip` varchar(64) DEFAULT NULL COMMENT '用户IP',
  `agent` varchar(200) DEFAULT NULL COMMENT '客户端型号',
  `title` varchar(100) NOT NULL,
  `slug` varchar(100) DEFAULT NULL COMMENT '缩略语',
  `author` varchar(40) DEFAULT NULL,
  `created` bigint(14) DEFAULT NULL COMMENT '创建时间',
  `modified` bigint(14) DEFAULT NULL COMMENT '上一次修改的时间',
  `content` text COMMENT '文章内容',
  `template` varchar(20) DEFAULT NULL COMMENT '模板',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `status` int(5) DEFAULT NULL COMMENT '状态',
  `allowcomment` smallint(1) DEFAULT NULL COMMENT '是否允许评论',
  `views` int(10) DEFAULT NULL COMMENT '浏览次数',
  `authorid` int(10) DEFAULT NULL,
  `count` int(10) DEFAULT NULL COMMENT '点赞次数',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;

/*Data for the table `us_articles` */

insert  into `us_articles`(`aid`,`cid`,`ip`,`agent`,`title`,`slug`,`author`,`created`,`modified`,`content`,`template`,`type`,`status`,`allowcomment`,`views`,`authorid`,`count`) values (79,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维',NULL,'张兆军',1444817002676,1444817002676,'<p><span style=\"color: rgb(255, 0, 0);\"><em><strong><span style=\"text-decoration: underline;\">现在写文章真是好</span></strong></em></span><img src=\"ueditor/jsp/upload/image/20151014/1444816981720014882.jpg\" title=\"1444816981720014882.jpg\" alt=\"0dd9926b-0349-45a7-8117-e7445a1b572c.jpg\"/></p>',NULL,'',0,0,0,1,1),(80,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张兆军',1444821734326,1444821734326,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<img src=\"ueditor/jsp/upload/image/20151014/1444821725354019291.jpg\" title=\"1444821725354019291.jpg\" alt=\"0dd9926b-0349-45a7-8117-e7445a1b572c.jpg\"/><img src=\"ueditor/jsp/upload/image/20151014/1444821730950003213.jpg\" title=\"1444821730950003213.jpg\" alt=\"22d124c9-df52-4cd4-88b3-691005f1cafe.jpg\"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,12,1,1),(81,50,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张兆军',1444882109835,1444882109835,'<p>达瓦达瓦大哇<img src=\"ueditor/jsp/upload/image/20151015/1444882107315008844.jpg\" title=\"1444882107315008844.jpg\" alt=\"0dd9926b-0349-45a7-8117-e7445a1b572c.jpg\"/> &nbsp; &nbsp; &nbsp;&nbsp;</p>',NULL,NULL,0,0,2,1,1),(82,49,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','13029802829',1444895455562,1444895455562,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,30,40,0),(84,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张兆军',1444905054686,1444905054686,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<img src=\"ueditor/jsp/upload/image/20151015/1444905052638057549.jpg\" title=\"1444905052638057549.jpg\" alt=\"22d124c9-df52-4cd4-88b3-691005f1cafe.jpg\"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,1,1,1),(85,53,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张兆军',1444905076729,1444905076729,'<p><img src=\"ueditor/jsp/upload/image/20151015/1444905074421028613.jpg\" title=\"1444905074421028613.jpg\" alt=\"eaa2e89d-cbbf-4d6a-9852-96bacc54e7f6.jpg\"/>\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,1,0),(86,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','DWADWA','今天是好日子呀哈哈哈哈哈哈国语韩剧将尽快','张先生',1444905116233,1444905116233,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p><p><img src=\"ueditor/jsp/upload/image/20151015/1444905105278022258.jpg\" style=\"\" title=\"1444905105278022258.jpg\"/></p><p><img src=\"ueditor/jsp/upload/image/20151015/1444905105336088180.jpg\" style=\"\" title=\"1444905105336088180.jpg\"/></p><p><img src=\"ueditor/jsp/upload/image/20151015/1444905105390098793.jpg\" style=\"\" title=\"1444905105390098793.jpg\"/></p><p>&nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <br/></p>',NULL,NULL,0,0,0,1,0),(87,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈国语韩剧将尽快','张兆军',1444905209898,1444905209898,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,1,0),(88,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','','','',1444905214373,1444905214373,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,1,0),(89,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈国语韩剧将尽快','张兆军',1444905224740,1444905224740,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,1,0),(90,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张兆军',1444905235005,1444905235005,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,1,0),(91,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张兆军',1444905245308,1444905245308,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,1,0),(92,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打','今天是好日子呀哈哈哈哈哈哈','张先生',1444920042041,1444920042041,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,1,40,0),(93,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444920075287,1444920075287,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,2,40,0),(94,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','DWD','今天是好日子呀哈哈哈哈哈哈','张先生',1444920177486,1444920177486,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(95,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','DWD','今天是好日子呀哈哈哈哈哈哈','张先生',1444920312742,1444920312742,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(96,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','DWD','今天是好日子呀哈哈哈哈哈哈','张先生',1444920427297,1444920427297,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(97,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444920492994,1444920492994,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(98,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444920683492,1444920683492,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(99,48,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444920735099,1444920735099,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,1,40,0),(100,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444920847949,1444920847949,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(101,52,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈国语韩剧将尽快','张先生',1444920932307,1444920932307,'<p>wdadawdawd &nbsp; &nbsp; &nbsp;这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(102,53,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444921053718,1444921053718,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; dwadawdawd &nbsp; &nbsp; &nbsp; &nbsp;这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(103,53,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444921147157,1444921147157,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(104,53,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444921228936,1444921228936,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,0,40,0),(105,54,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444921581911,1444921581911,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,1,40,0),(106,54,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444921650460,1444921650460,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,1,40,0),(107,54,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444921686821,1444921686821,'<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,1,40,0),(108,49,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','打哈无ID哈维','今天是好日子呀哈哈哈哈哈哈','张先生',1444960983030,1444960983030,'<p><img src=\"ueditor/jsp/upload/image/20151016/1444960981765038965.jpg\" title=\"1444960981765038965.jpg\" alt=\"bae0a60a-521d-48ef-bea6-0854b89d7be0.jpg\"/>\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 这里写你的初始化内容\r\n &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>',NULL,NULL,0,0,2,40,0);

/*Table structure for table `us_categories` */

DROP TABLE IF EXISTS `us_categories`;

CREATE TABLE `us_categories` (
  `cid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL COMMENT '分类名称',
  `order` int(5) DEFAULT NULL COMMENT '排序',
  `count` int(5) DEFAULT NULL COMMENT '分类的数量',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `parent` int(10) DEFAULT NULL COMMENT '父级',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `us_categories` */

insert  into `us_categories`(`cid`,`name`,`order`,`count`,`description`,`parent`) values (48,'校园风云',0,0,NULL,0),(49,'校园八卦',0,0,NULL,0),(50,'校园资讯',0,0,NULL,0),(51,'校园活动',0,0,NULL,0),(52,'口碑商',0,0,NULL,0),(53,'便生活',0,0,NULL,0),(54,'有活动',0,0,NULL,0);

/*Table structure for table `us_comments` */

DROP TABLE IF EXISTS `us_comments`;

CREATE TABLE `us_comments` (
  `coid` int(10) NOT NULL AUTO_INCREMENT,
  `aid` int(10) NOT NULL COMMENT '文章id',
  `created` bigint(16) DEFAULT NULL COMMENT '评论创建时间',
  `author` varchar(50) DEFAULT NULL,
  `authorid` int(10) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(250) DEFAULT NULL,
  `text` text COMMENT '内容',
  `status` int(5) DEFAULT NULL COMMENT '评论的状态',
  `parent` int(15) DEFAULT NULL COMMENT '回复评论',
  PRIMARY KEY (`coid`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Data for the table `us_comments` */

insert  into `us_comments`(`coid`,`aid`,`created`,`author`,`authorid`,`ip`,`agent`,`text`,`status`,`parent`) values (33,80,1444824012153,'张兆军',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdawdawd',0,0),(34,80,1444824256460,'张兆军',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdawdawdwaaw',0,0),(35,80,1444878764508,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dwadawsadwdas',0,0),(36,82,1444895660496,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdwadwa',0,0),(37,82,1444895664668,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dwadawdawd',0,0),(38,82,1444895667151,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dwadawdawd',0,0),(39,82,1444895669480,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdawdawd',0,0),(40,82,1444895671918,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dwadawdawd',0,0),(41,82,1444895674484,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdawdawd',0,0),(42,82,1444895677406,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdawdawdwa',0,0),(43,82,1444895680647,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dwa',0,0),(44,82,1444895683371,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dwadawdawd',0,0),(45,82,1444895686318,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dwadawdawd',0,0),(46,82,1444895689148,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdawdawd',0,0),(47,82,1444895691675,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdawdaw',0,0),(48,82,1444895694269,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdawdwa',0,0),(49,82,1444895872506,'张先生',40,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','dawdaw',0,0);

/*Table structure for table `us_groups` */

DROP TABLE IF EXISTS `us_groups`;

CREATE TABLE `us_groups` (
  `gid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `authority` varchar(100) DEFAULT NULL COMMENT '发留言板，添加商品，八卦，风云，活动，资讯，口碑，有活动，便生活，',
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

/*Data for the table `us_groups` */

insert  into `us_groups`(`gid`,`name`,`comment`,`authority`) values (1,'user',NULL,'1,0,0,0,0,0,0,0,0'),(70,NULL,NULL,'1,1,1,1,1,1,0,0,0'),(74,'user',NULL,'1,1,0,0,0,0,0,0,0'),(75,'user',NULL,'1,1,0,0,1,1,0,0,0'),(76,'user',NULL,'1,1,1,0,0,0,0,1,0'),(82,'user',NULL,'1,1,1,0,0,0,0,1,0');

/*Table structure for table `us_images` */

DROP TABLE IF EXISTS `us_images`;

CREATE TABLE `us_images` (
  `imgid` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`imgid`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;

/*Data for the table `us_images` */

insert  into `us_images`(`imgid`,`pid`,`url`,`comment`) values (122,37,'/uploadProductsImage/1/14/2d20a06a-f093-4b09-aef1-4263918aeadd.png',NULL),(123,38,'/uploadProductsImage/4/0/08563f1f-fd78-416c-afc7-598149bb0279.png',NULL),(124,39,'/uploadProductsImage/5/0/99edc557-91e9-422d-9c05-8ff4a6a33002.png',NULL),(125,40,'/uploadProductsImage/12/11/e49469b5-32ca-4df1-9316-27ae1600eec7.png',NULL),(126,41,'/uploadProductsImage/8/0/a498204a-ee3f-4a6c-b7cc-daf56947457b.png',NULL),(127,42,'/uploadProductsImage/12/12/542cd5f0-1a22-4ce7-acca-9749a2e5eca4.png',NULL),(128,42,'/uploadProductsImage/0/14/78c50b5c-b202-41bb-a74b-4b0050cd6fa2.png',NULL),(129,43,'/uploadProductsImage/10/3/87276983-e020-4fef-83c9-b21cf2797094.png',NULL),(135,48,'/uploadProductsImage/13/12/82fe3dbc-17ec-497b-b7ed-36d4809ae1d4.png',NULL),(136,49,'/uploadProductsImage/5/10/ea33a91b-c123-42c5-a829-aa113749d3f0.png',NULL),(137,50,'/uploadProductsImage/11/3/9ef388e3-3fbf-4383-8b9d-30bedd75a9d9.png',NULL),(146,56,'/uploadProductsImage/6/10/3539606c-d6bf-4eff-b1b2-bb9bc5d47c09.png',NULL),(147,56,'/uploadProductsImage/4/3/8b0f2f36-b8dd-4737-a59c-506cf01dd90e.png',NULL),(148,57,'/uploadProductsImage/10/0/bbe0bbad-14a0-4130-8d74-5fbcfcaf2a21.png',NULL),(149,57,'/uploadProductsImage/9/15/1d017026-e36f-49c4-b403-92c3bd0a0b40.png',NULL),(150,58,'/uploadProductsImage/0/0/d7181a6e-c230-4d0b-9e2c-e1be780705a8.png',NULL),(151,58,'/uploadProductsImage/13/8/ce495d20-78fa-4e3d-8cd2-6269f52b84b9.png',NULL),(152,59,'/uploadProductsImage/3/8/fd729f12-8156-4e5f-aff0-5e5e4ac8e6c8.png',NULL),(153,59,'/uploadProductsImage/4/2/6d7588c7-716a-416c-8443-e8d7bd39b344.png',NULL),(154,60,'/uploadProductsImage/12/1/5720b47c-2d8b-4d9f-a49e-e0a827f082a7.png',NULL),(155,60,'/uploadProductsImage/12/8/efed42bb-a3b0-4136-977e-0beccae77a0e.png',NULL),(156,61,'/uploadProductsImage/1/13/36994d87-4408-4041-8a1d-0263e3d3b3ac.png',NULL),(157,61,'/uploadProductsImage/5/13/6dc114f0-9625-4273-91f1-917111ccb5b7.png',NULL),(158,62,'/uploadProductsImage/12/2/d6e7e497-5fa4-448e-9464-303121f93cf9.png',NULL),(159,62,'/uploadProductsImage/6/11/2c15d4ba-31eb-410a-affe-f4da4d043eb9.png',NULL),(160,63,'/uploadProductsImage/14/9/5f8f7c5d-0274-4d65-8292-3cccf183cec7.png',NULL),(161,63,'/uploadProductsImage/9/5/fd17a8a9-cd09-4269-bd16-f1e808cca039.png',NULL),(162,64,'/uploadProductsImage/15/14/d15379f1-493d-44e4-87de-6c8ebf341ec3.png',NULL),(163,64,'/uploadProductsImage/5/10/01936922-1f5f-4017-b7fe-11f6af7da25f.png',NULL),(164,64,'/uploadProductsImage/1/4/3797795c-74b1-4bda-8a73-20548e34c4ca.png',NULL),(165,65,'/uploadProductsImage/13/10/1f4ebc0f-4a7f-4ea5-b280-ed21303385fb.png',NULL),(166,65,'/uploadProductsImage/11/15/7b8c416e-af27-4bf3-917e-e9db341e9dbc.png',NULL),(167,64,'/uploadProductsImage/13/14/6ee81fee-446f-42e3-822b-621a62295197.png',NULL),(168,64,'/uploadProductsImage/8/11/2077e18e-4fb5-4e12-b14f-4337470881b5.png',NULL),(169,65,'/uploadProductsImage/13/10/f064dcf8-2265-4b53-92b1-6386a0abd041.png',NULL),(170,65,'/uploadProductsImage/14/2/d5365d87-1bb0-4675-9054-e0b8444798cf.png',NULL),(171,66,'/uploadProductsImage/7/1/52dc15a5-bb8c-42e1-9504-71fc37b04c0a.png',NULL),(172,66,'/uploadProductsImage/0/0/39b70028-9d0a-4583-9ddf-db4c370c31b1.png',NULL),(173,67,'/uploadProductsImage/15/13/b3f5c09f-1cc3-4b25-9080-c8d48b38e9df.png',NULL),(174,67,'/uploadProductsImage/1/3/af6ebf0f-b931-4999-a942-fceb0f16daea.png',NULL),(175,68,'/uploadProductsImage/15/6/616a90f8-7b9b-45b9-a6a0-1c02d2032574.png',NULL),(176,68,'/uploadProductsImage/1/11/2d5c88be-0c3a-40fe-a5c8-cd2be253c5d8.png',NULL),(177,69,'/uploadProductsImage/12/0/ac123b58-9b53-4082-afbd-78a2f6e5b9f9.jpg',NULL),(178,69,'/uploadProductsImage/3/9/26c0da4c-16b3-47a4-9f5c-a1076ca9a88c.jpg',NULL);

/*Table structure for table `us_mailbox` */

DROP TABLE IF EXISTS `us_mailbox`;

CREATE TABLE `us_mailbox` (
  `mailid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `ip` varchar(64) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `sendid` int(10) NOT NULL,
  `sendtime` bigint(16) DEFAULT NULL,
  `receivetime` bigint(16) DEFAULT NULL,
  PRIMARY KEY (`mailid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `us_mailbox` */

insert  into `us_mailbox`(`mailid`,`uid`,`title`,`content`,`ip`,`agent`,`sendid`,`sendtime`,`receivetime`) values (2,40,'达瓦大','达瓦大大',NULL,NULL,41,41,NULL),(3,40,'的哇','达瓦达瓦大',NULL,NULL,41,41,NULL),(4,40,'大娃娃','打问打问',NULL,NULL,41,41,NULL),(5,40,'达瓦达瓦大','达瓦达瓦',NULL,NULL,41,41,NULL),(6,40,'达瓦达瓦','达瓦达瓦大',NULL,NULL,41,41,NULL),(7,40,'达瓦达瓦大','达瓦达瓦大',NULL,NULL,41,41,NULL),(8,40,'达瓦达瓦','低洼地方税费少',NULL,NULL,41,41,NULL),(9,40,'额粉色发','粉色粉色',NULL,NULL,41,41,NULL),(10,40,'粉色粉色发',NULL,NULL,NULL,41,41,NULL),(11,40,'粉色粉色2',NULL,NULL,NULL,41,41,NULL),(12,41,'DWADWA','DAWDAWDAWDWA','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1443593142323,0),(13,41,'小三','打小三真好，真棒，就要打小三','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1443610250794,0),(15,41,'大家好',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1443625100933,0),(16,41,'你好','HAHAHAHAHAHAH','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1443625147110,0),(17,41,'DWADAW','AWDWADSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1443625937880,0),(18,40,'打哈无ID哈维','达瓦大晚上','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',44,1444028681916,0),(22,41,'打哈无ID哈维','dawdawd','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1444040983575,0),(23,40,'今天有点问题','你是知道啦','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',1,1444315717576,0),(26,1,'打哈无ID哈维','wdadwad','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1444356785318,0),(27,1,'DWADWA','wdadwadwa','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1444356827464,0),(28,1,'打哈无ID哈维','dawdawdwa','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1444356884507,0),(30,44,'多少啊','瓦达瓦大洼地1','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1444385088680,0),(31,41,'贝贝','贝贝唱的一般','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1444385440553,0),(33,41,'周杰伦','大家好我是周杰伦','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1444395530289,0),(34,40,'汪峰','汪峰哥还行','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',41,1444395657422,0),(35,44,'Dwai','mdwadjawdkadwa','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36',40,1444462518711,0),(36,40,'下意思','个叫我弟娃基地那我就地挖掘低洼大几万年井底蛙','127.0.0.1','Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; SMJB; rv:11.0) like Gecko',44,1444462614525,0);

/*Table structure for table `us_notes` */

DROP TABLE IF EXISTS `us_notes`;

CREATE TABLE `us_notes` (
  `noteid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL COMMENT '留言内容',
  `status` int(5) DEFAULT NULL COMMENT '审核',
  `comment` varchar(100) DEFAULT NULL,
  `ncreated` bigint(15) DEFAULT NULL,
  PRIMARY KEY (`noteid`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

/*Data for the table `us_notes` */

insert  into `us_notes`(`noteid`,`uid`,`content`,`status`,`comment`,`ncreated`) values (8,40,'为了中古京东佛典个非官方规定	',0,NULL,1445065769167),(9,40,'为了中古京东佛典个非官方规定	',0,NULL,1445065771509),(10,40,'为了中古京东佛典个非官方规定	',0,NULL,1446128867260),(11,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129035006),(12,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129180540),(13,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129215814),(14,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129245649),(15,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129359990),(16,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129488952),(17,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129765196),(18,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129850710),(19,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129872566),(20,40,'为了中古京东佛典个非官方规定	',0,NULL,1446129919661),(21,40,'			\r\n		000	',0,NULL,1446130048565),(22,40,'			\r\n		000	',0,NULL,1446130076899),(23,40,'			\r\n		000	',0,NULL,1446130098747),(24,40,'			\r\n		000	',0,NULL,1446130132092),(25,40,'			\r\n		000	',0,NULL,1446130208025),(26,40,'			\r\n		000	',0,NULL,1446130219649),(27,40,'			\r\n		000	',0,NULL,1446130234113),(28,40,'			\r\n		000	',0,NULL,1446130261117),(29,40,'			\r\n		000	',0,NULL,1446130329588),(30,40,'			\r\n		000	',0,NULL,1446130342423),(31,40,'			\r\n		000	',0,NULL,1446130531575),(32,40,'			\r\n			qqqqqqqqq',0,NULL,1446133505917),(33,40,'这是什么原因的		',0,NULL,1446133532082),(34,40,'			\r\n		轻轻巧巧	',0,NULL,1446133636470),(35,40,'			\r\n		轻轻巧巧	',0,NULL,1446133865082),(36,40,'			\r\n	吾问无为谓		',0,NULL,1446133998052),(37,45,'			qqq\r\n			',0,NULL,1446134304137),(38,45,'			qqq\r\n			',0,NULL,1446134329927),(39,45,'			qqq\r\n			',0,NULL,1446134390192),(40,45,'			qqq\r\n			',0,NULL,1446134422437),(41,45,'			qqq\r\n			',0,NULL,1446134432789),(44,45,'			\r\n		qqq	',0,NULL,1446803924995),(45,40,'ahha ',0,NULL,1447489714963),(46,40,'ahha ',0,NULL,1447489760813);

/*Table structure for table `us_orderitems` */

DROP TABLE IF EXISTS `us_orderitems`;

CREATE TABLE `us_orderitems` (
  `orderitemid` int(10) NOT NULL AUTO_INCREMENT,
  `orderid` int(10) NOT NULL,
  `pid` int(10) NOT NULL,
  `subtotal` decimal(10,4) DEFAULT NULL COMMENT '总的费用',
  `fare` decimal(10,4) DEFAULT NULL COMMENT '运费',
  `comment` varchar(100) DEFAULT NULL COMMENT '备注',
  `oquantity` int(10) DEFAULT NULL,
  `oprice` double DEFAULT NULL,
  PRIMARY KEY (`orderitemid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Data for the table `us_orderitems` */

insert  into `us_orderitems`(`orderitemid`,`orderid`,`pid`,`subtotal`,`fare`,`comment`,`oquantity`,`oprice`) values (14,13,60,'0.0000','0.0000',NULL,1,0),(15,14,40,'0.0000','0.0000',NULL,1,0),(16,15,43,'0.0000','0.0000',NULL,1,0),(17,16,37,'0.0000','0.0000',NULL,1,0),(18,17,37,'0.0000','0.0000',NULL,1,0),(19,18,38,'0.0000','0.0000',NULL,1,0),(20,19,38,'0.0000','0.0000',NULL,1,0),(21,20,39,'0.0000','0.0000',NULL,1,0),(22,21,39,'0.0000','0.0000',NULL,1,0),(24,23,43,'0.0000','0.0000',NULL,1,0),(25,24,41,'0.0000','0.0000',NULL,1,0),(27,26,39,'0.0000','0.0000',NULL,1,0),(28,27,39,'0.0000','0.0000',NULL,1,0),(29,28,62,'0.0000','0.0000',NULL,1,0),(30,29,39,'0.0000','0.0000',NULL,1,0),(31,30,48,'0.0000','0.0000',NULL,1,0),(32,31,39,'0.0000','0.0000',NULL,1,0),(33,32,48,'0.0000','0.0000',NULL,1,0),(34,33,41,'0.0000','0.0000',NULL,1,0),(35,34,43,'0.0000','0.0000',NULL,1,0),(36,35,69,'0.0000','0.0000',NULL,1,0),(37,36,69,'0.0000','0.0000',NULL,1,0),(38,37,68,'0.0000','0.0000',NULL,1,0),(39,38,67,'0.0000','0.0000',NULL,1,0);

/*Table structure for table `us_orders` */

DROP TABLE IF EXISTS `us_orders`;

CREATE TABLE `us_orders` (
  `orderid` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `type` varchar(40) DEFAULT NULL COMMENT '送货类型',
  `total` double DEFAULT NULL COMMENT '总的钱数',
  `created` bigint(15) DEFAULT NULL COMMENT '订单创建时间',
  `status` int(5) DEFAULT NULL COMMENT '状态码',
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `us_orders` */

insert  into `us_orders`(`orderid`,`uid`,`type`,`total`,`created`,`status`,`comment`) values (13,45,NULL,0,1446192152554,0,NULL),(14,45,NULL,0,1446192181441,0,NULL),(15,45,NULL,0,1446192194055,0,NULL),(16,45,NULL,0,1446192213171,0,NULL),(17,45,NULL,0,1446192281378,0,NULL),(18,45,NULL,0,1446192284931,0,NULL),(19,45,NULL,0,1446192289576,0,NULL),(20,45,NULL,0,1446192321362,0,NULL),(21,40,NULL,0,1446192411497,0,NULL),(22,40,NULL,0,1446192876593,0,NULL),(23,40,NULL,0,1446192880011,0,NULL),(24,40,NULL,0,1446192884423,0,NULL),(25,40,NULL,0,1446192886911,0,NULL),(26,45,NULL,0,1446198702521,0,NULL),(27,45,NULL,0,1446198783702,0,NULL),(28,45,NULL,0,1446200200254,0,NULL),(29,45,NULL,0,1446286612114,0,NULL),(30,45,NULL,0,1446286681081,0,NULL),(31,45,NULL,0,1446622852201,0,NULL),(32,45,NULL,0,1446622873833,0,NULL),(33,45,NULL,0,1446623721285,0,NULL),(34,45,NULL,0,1446630087804,0,NULL),(35,40,NULL,0,1447490666763,0,NULL),(36,40,NULL,0,1447490764116,0,NULL),(37,40,NULL,0,1447494748147,0,NULL),(38,40,NULL,0,1447494778765,0,NULL);

/*Table structure for table `us_praise` */

DROP TABLE IF EXISTS `us_praise`;

CREATE TABLE `us_praise` (
  `aid` int(10) NOT NULL,
  `uid` int(10) DEFAULT NULL,
  `status` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `us_praise` */

insert  into `us_praise`(`aid`,`uid`,`status`) values (68,40,1),(54,40,1),(53,40,1),(51,40,1),(59,40,1),(55,40,1),(52,40,1),(31,40,1),(69,40,1),(40,40,1),(38,40,1),(71,40,1),(72,40,1),(75,40,1),(77,40,1),(80,40,1),(79,40,1),(81,40,1),(84,40,1);

/*Table structure for table `us_product_categories` */

DROP TABLE IF EXISTS `us_product_categories`;

CREATE TABLE `us_product_categories` (
  `pcid` int(10) NOT NULL AUTO_INCREMENT,
  `pcname` varchar(50) DEFAULT NULL,
  `order` int(10) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `parent` int(10) DEFAULT NULL COMMENT '父',
  PRIMARY KEY (`pcid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `us_product_categories` */

insert  into `us_product_categories`(`pcid`,`pcname`,`order`,`description`,`parent`) values (5,'school',0,NULL,0),(6,'学习用品',0,NULL,0),(7,'食品',0,NULL,0),(8,'服装',0,NULL,0),(9,'生活用品',0,NULL,0),(10,'数码',0,NULL,0),(11,'--请选择--',0,NULL,0),(12,NULL,0,NULL,0),(13,NULL,0,NULL,0),(14,NULL,0,NULL,0),(15,NULL,0,NULL,0),(16,NULL,0,NULL,0),(17,NULL,0,NULL,0);

/*Table structure for table `us_product_comments` */

DROP TABLE IF EXISTS `us_product_comments`;

CREATE TABLE `us_product_comments` (
  `commentid` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) NOT NULL,
  `uid` int(10) NOT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '商品评论的内容',
  `created` bigint(16) DEFAULT NULL COMMENT '评论时间',
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`commentid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `us_product_comments` */

insert  into `us_product_comments`(`commentid`,`pid`,`uid`,`content`,`created`,`comment`) values (1,8,3,NULL,1444991805546,'djfiajj'),(2,12,3,NULL,1444991834786,'jjj'),(3,13,3,NULL,1444991858208,'wwww'),(4,11,3,NULL,1444991972326,'dawaw'),(5,8,3,NULL,1444995291973,'zhehsi '),(6,37,3,NULL,1446198155430,'这款手表如何？'),(7,38,3,NULL,1446374584741,'rrrr');

/*Table structure for table `us_products` */

DROP TABLE IF EXISTS `us_products`;

CREATE TABLE `us_products` (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `pcid` int(10) NOT NULL COMMENT '分类id',
  `name` varchar(50) NOT NULL COMMENT '商品名称',
  `soldprice` decimal(10,4) DEFAULT NULL COMMENT '售价',
  `oldprice` decimal(10,4) DEFAULT NULL COMMENT '原价',
  `quantity` int(10) DEFAULT NULL COMMENT '数量',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `summary` varchar(100) DEFAULT NULL COMMENT '摘要',
  `pdescription` text,
  `model` varchar(50) DEFAULT NULL COMMENT '型号',
  `views` int(10) DEFAULT NULL COMMENT '浏览次数',
  `commentcount` int(10) DEFAULT NULL COMMENT '评论次数',
  `address` varchar(100) DEFAULT NULL,
  `status` int(10) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `limit` int(10) DEFAULT NULL,
  `mainimages` varchar(100) DEFAULT NULL COMMENT '主图',
  `pcreated` bigint(15) DEFAULT NULL COMMENT '挂货时间',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

/*Data for the table `us_products` */

insert  into `us_products`(`pid`,`pcid`,`name`,`soldprice`,`oldprice`,`quantity`,`type`,`summary`,`pdescription`,`model`,`views`,`commentcount`,`address`,`status`,`comment`,`limit`,`mainimages`,`pcreated`) values (37,6,'书本','0.0000','0.0000',0,'闲互换','这是书本 ','这回就点击放大看妇科妇科大佛快点康复                  ',NULL,13,0,NULL,0,NULL,2,'/uploadProductsImage/1/14/2d20a06a-f093-4b09-aef1-4263918aeadd.png',1446175815311),(38,6,'222','0.0000','0.0000',20,'闲互换','这是好手机','            222222222222222222222222222222222222      ',NULL,13,0,NULL,1,NULL,2,'/uploadProductsImage/4/0/08563f1f-fd78-416c-afc7-598149bb0279.png',1446175918228),(39,6,'22','0.0000','0.0000',16,'闲互送','这是好手机','     222222222222222             ',NULL,10,0,NULL,1,NULL,22,'/uploadProductsImage/5/0/99edc557-91e9-422d-9c05-8ff4a6a33002.png',1446186001473),(40,9,'11','0.0000','0.0000',10,'聚便宜','这是好手机','    111111111111              ',NULL,9,0,NULL,1,NULL,1,'/uploadProductsImage/12/11/e49469b5-32ca-4df1-9316-27ae1600eec7.png',0),(41,9,'12','0.0000','0.0000',10,'闲互送','这是好手机','  1212121                ',NULL,2,0,NULL,1,NULL,12,'/uploadProductsImage/8/0/a498204a-ee3f-4a6c-b7cc-daf56947457b.png',1446186373558),(43,6,'33','0.0000','0.0000',30,'闲互送','这是好手机','      33333333333            ',NULL,2,0,NULL,1,NULL,3,'/uploadProductsImage/10/3/87276983-e020-4fef-83c9-b21cf2797094.png',1446186654553),(48,6,'222','0.0000','0.0000',20,'闲互换','这是好手机','    2222              ',NULL,3,0,NULL,1,NULL,22,'/uploadProductsImage/13/12/82fe3dbc-17ec-497b-b7ed-36d4809ae1d4.png',1446187573139),(49,10,'222','0.0000','0.0000',22,'闲互换','这是好手机','            2222      ',NULL,5,0,NULL,1,NULL,2,'/uploadProductsImage/5/10/ea33a91b-c123-42c5-a829-aa113749d3f0.png',1446187730345),(51,6,'333','0.0000','0.0000',33,'闲互换',NULL,'33333',NULL,6,0,NULL,1,NULL,3,'/uploadProductsImage/15/14/6aa4778d-ddca-47b9-94da-1a1e519b9ab7.png',1446187882076),(56,6,'qq','0.0000','0.0000',1,'聚便宜',' 1                 ','           1       ',NULL,5,0,NULL,1,NULL,1,'/uploadProductsImage/6/10/3539606c-d6bf-4eff-b1b2-bb9bc5d47c09.png',1446189250348),(57,6,'12','0.0000','0.0000',2,'闲互送',NULL,'2222',NULL,0,0,NULL,0,NULL,2,'/uploadProductsImage/10/0/bbe0bbad-14a0-4130-8d74-5fbcfcaf2a21.png',0),(58,6,'12','0.0000','0.0000',2,'闲互送',NULL,'2222',NULL,0,0,NULL,2,NULL,2,'/uploadProductsImage/0/0/d7181a6e-c230-4d0b-9e2c-e1be780705a8.png',0),(59,6,'11','0.0000','0.0000',11,'闲互送','   11               ','           1111       ',NULL,0,0,NULL,0,NULL,11,'/uploadProductsImage/3/8/fd729f12-8156-4e5f-aff0-5e5e4ac8e6c8.png',0),(60,6,'毛笔','0.0000','0.0000',5,'闲互送','     很好用的毛笔             ','   酒店房间按甲方及到家啊               ',NULL,10,0,NULL,0,NULL,3,'/uploadProductsImage/12/1/5720b47c-2d8b-4d9f-a49e-e0a827f082a7.png',0),(61,9,'222','0.0000','0.0000',222,'闲互换','   22222               ','       22222222222222           ',NULL,4,0,NULL,1,NULL,22,'/uploadProductsImage/1/13/36994d87-4408-4041-8a1d-0263e3d3b3ac.png',1446199013023),(62,6,'英语书','0.0000','0.0000',2,'聚便宜','这是一本英语书','    很好看的额 年底交付的甲方及东方健康              ',NULL,7,0,NULL,0,NULL,3,'/uploadProductsImage/12/2/d6e7e497-5fa4-448e-9464-303121f93cf9.png',1446199649543),(63,6,'钢笔','0.0000','0.0000',3,'聚便宜','每用过的钢笔','想要的请到我这里来拿            ',NULL,10,0,NULL,3,NULL,1,'/uploadProductsImage/14/9/5f8f7c5d-0274-4d65-8292-3cccf183cec7.png',1446200382096),(64,12,'水杯','0.0000','0.0000',4,'闲互送','保温杯啊','到地下创业园拿                  ',NULL,1,0,NULL,0,NULL,2,'/uploadProductsImage/13/14/6ee81fee-446f-42e3-822b-621a62295197.png',1446691035531),(65,13,'手机','0.0000','0.0000',4,'闲互送','新款手机','这是黑色么么门口                  ',NULL,0,0,NULL,0,NULL,2,'/uploadProductsImage/13/10/f064dcf8-2265-4b53-92b1-6386a0abd041.png',1446691263415),(66,14,'键盘','0.0000','0.0000',2,'闲互送','青轴的键盘','很好用的                  ',NULL,0,0,NULL,1,NULL,2,'/uploadProductsImage/7/1/52dc15a5-bb8c-42e1-9504-71fc37b04c0a.png',1446691432965),(67,15,'苹果','0.0000','0.0000',2,'闲互送','新鲜的苹果',' 好好几哈发见附件        ',NULL,0,0,NULL,1,NULL,3,'/uploadProductsImage/15/13/b3f5c09f-1cc3-4b25-9080-c8d48b38e9df.png',1446691916951),(68,16,'红枣','0.0000','0.0000',11,'闲互送','新疆和田大枣','好好吃的                  ',NULL,0,0,NULL,1,NULL,2,'/uploadProductsImage/15/6/616a90f8-7b9b-45b9-a6a0-1c02d2032574.png',1446804694381),(69,17,'苹果','0.0000','0.0000',0,'闲互送','好甜的苹果','这是很好的苹果                  ',NULL,1,0,NULL,1,NULL,2,'/uploadProductsImage/12/0/ac123b58-9b53-4082-afbd-78a2f6e5b9f9.jpg',1446943399109);

/*Table structure for table `us_tags` */

DROP TABLE IF EXISTS `us_tags`;

CREATE TABLE `us_tags` (
  `tid` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL COMMENT '标签名称',
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table `us_tags` */

insert  into `us_tags`(`tid`,`name`,`comment`) values (15,'校园',NULL),(16,'awdawd',NULL),(17,'活动',NULL),(18,'dwadawadaw',NULL),(19,'校园d',NULL),(20,'1111',NULL),(21,'1',NULL),(22,'热血',NULL),(23,'',NULL);

/*Table structure for table `us_tags_articles` */

DROP TABLE IF EXISTS `us_tags_articles`;

CREATE TABLE `us_tags_articles` (
  `tid` int(10) NOT NULL COMMENT '标签表id',
  `aid` int(10) NOT NULL COMMENT '文章表id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `us_tags_articles` */

insert  into `us_tags_articles`(`tid`,`aid`) values (15,31),(15,36),(15,37),(18,38),(15,39),(15,40),(15,41),(15,42),(15,43),(15,44),(16,45),(19,46),(15,47),(15,48),(15,51),(16,52),(15,53),(15,54),(15,55),(15,56),(15,58),(20,59),(15,60),(15,61),(15,62),(16,63),(15,66),(15,67),(15,68),(22,69),(15,70),(15,71),(15,72),(15,73),(15,76),(15,77),(15,78),(15,79),(15,80),(15,81),(15,82),(15,84),(15,85),(15,86),(15,87),(23,88),(15,89),(15,90),(15,91),(15,92),(15,93),(15,94),(15,95),(15,96),(15,97),(15,98),(15,99),(15,100),(15,101),(15,102),(15,103),(15,104),(15,105),(15,106),(15,107),(15,108);

/*Table structure for table `us_users` */

DROP TABLE IF EXISTS `us_users`;

CREATE TABLE `us_users` (
  `uid` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `male` varchar(10) DEFAULT NULL COMMENT '性别',
  `type` int(10) DEFAULT NULL COMMENT '用户类型，扩展用',
  `avator` varchar(100) DEFAULT NULL COMMENT '头像',
  `created` bigint(15) DEFAULT NULL COMMENT '用户注册时间',
  `loged` bigint(15) DEFAULT NULL COMMENT '上一次登录时间',
  `ip` varchar(64) DEFAULT NULL COMMENT '用户ip',
  `gid` int(10) DEFAULT NULL COMMENT '用户分组',
  `agent` varchar(200) DEFAULT NULL COMMENT '用户客户端系统',
  `nickname` varchar(40) DEFAULT NULL COMMENT '用户昵称',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `us_users` */

insert  into `us_users`(`uid`,`username`,`password`,`email`,`phone`,`male`,`type`,`avator`,`created`,`loged`,`ip`,`gid`,`agent`,`nickname`) values (40,'13029802829','202cb962ac59075b964b07152d234b70','971796045@qq.com','13029802829','male',0,'/uploadProductsImage/11/3/9ef388e3-3fbf-4383-8b9d-30bedd75a9d9.png',1443334594095,1447494739622,'127.0.0.1',76,'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36','张先生'),(41,'12445678912','202cb962ac59075b964b07152d234b70','971796045@qq.com','12445678912','male',0,'/uploadAvator/10/15/e8dc6d76-5208-4fbf-90f5-5b480b3316c0.png',1443334813391,1444718105858,'127.0.0.1',1,'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','小飞'),(42,'13895895491','202cb962ac59075b964b07152d234b70','971796042@qq.com','13895895491','male',0,'/uploadAvator/10/15/e8dc6d76-5208-4fbf-90f5-5b480b3316c0.png',1443510000835,1444374242423,'127.0.0.1',1,'Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36','13895895491'),(44,'12345678912','250cf8b51c773f3f8dc8b4be867a9a02','971796042@qq.com','12345678912','female',0,'/uploadAvator/10/15/e8dc6d76-5208-4fbf-90f5-5b480b3316c0.png',1444028552814,1444462565379,'127.0.0.1',1,'Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; SMJB; rv:11.0) like Gecko','郝先生'),(45,'15754605681','202cb962ac59075b964b07152d234b70','','15754605681','male',0,'/uploadAvator/10/15/e8dc6d76-5208-4fbf-90f5-5b480b3316c0.png',1446132187152,1447126750811,'127.0.0.1',82,'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.80 Safari/537.36','15754605681'),(47,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
