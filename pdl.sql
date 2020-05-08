/*
SQLyog Ultimate v11.27 (32 bit)
MySQL - 5.5.23 : Database - pdl
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pdl` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `pdl`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `adminId` int(15) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  `adminPwd` varchar(15) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `admin` */

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `blogId` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '标签id',
  `blogName` varchar(15) COLLATE utf8mb4_bin NOT NULL COMMENT '标签具体名字',
  PRIMARY KEY (`blogId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `blog` */

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaintId` int(12) NOT NULL AUTO_INCREMENT COMMENT '投诉Id主键',
  `belong` int(15) NOT NULL COMMENT '投诉者的用户Id',
  `targetId` int(15) NOT NULL COMMENT '被投诉的房子Id',
  `content` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '投诉具体内容',
  `time` datetime NOT NULL COMMENT '提交时间',
  `ischeck` tinyint(1) NOT NULL COMMENT '管理员是否查看',
  PRIMARY KEY (`complaintId`),
  KEY `targetId` (`targetId`),
  KEY `belong` (`belong`),
  CONSTRAINT `complaint_ibfk_1` FOREIGN KEY (`targetId`) REFERENCES `house` (`houseId`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `complaint_ibfk_2` FOREIGN KEY (`belong`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `complaint` */

/*Table structure for table `house` */

DROP TABLE IF EXISTS `house`;

CREATE TABLE `house` (
  `houseId` int(15) NOT NULL AUTO_INCREMENT,
  `houseName` varchar(120) COLLATE utf8mb4_bin NOT NULL COMMENT '房子名称',
  `houseAdder` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '房子地址',
  `pirce` double NOT NULL COMMENT '房子租金',
  `owner` int(15) NOT NULL COMMENT '房东用户的Id',
  `rent` int(15) DEFAULT NULL COMMENT '租客用户的id',
  `version` int(1) DEFAULT NULL COMMENT '乐观锁操纵',
  `details` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '房源详细文字介绍',
  `tagsList` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '特点分组集合',
  `uploadTime` datetime NOT NULL COMMENT '上传的日期',
  `previousTime` datetime DEFAULT NULL COMMENT '上一次出租时间',
  `lowerTime` datetime DEFAULT NULL COMMENT '下架时间',
  `status` enum('等待','招租','被租','下架') COLLATE utf8mb4_bin NOT NULL COMMENT '房源状态',
  `housePic` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片信息',
  PRIMARY KEY (`houseId`),
  KEY `owner` (`owner`),
  KEY `rent` (`rent`),
  CONSTRAINT `house_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `house_ibfk_2` FOREIGN KEY (`rent`) REFERENCES `user` (`userid`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `house` */

insert  into `house`(`houseId`,`houseName`,`houseAdder`,`pirce`,`owner`,`rent`,`version`,`details`,`tagsList`,`uploadTime`,`previousTime`,`lowerTime`,`status`,`housePic`) values (3,'青年公寓','广州市增城区光明街道123号',1200,3,NULL,0,'统一经营模式、专业管理系统，专业管理团队','单身*经济','2020-05-03 15:27:37','2020-05-03 15:27:44','2020-05-03 15:27:46','招租','/housePic/3.jpg'),(4,'雅居乐林语城','滁州市汊河工业园区104国道东侧',999,3,NULL,0,'户型方正，小区楼王位置，采光好！雅居乐品牌物业，小区物业服务好，且小区绿化做的非常好。','单身*经济','2020-05-03 15:59:01','2020-05-03 15:59:03','2020-05-03 15:59:06','招租','/housepic/4.jpg'),(5,'中材社区','江宁区东麒路66号众彩生活区内',678,8,NULL,0,'1、房间全新干净整洁、自带独卫、独门独户 2、1.5米大床、书桌、椅子、沙发、茶几、衣柜、鞋柜、空调、热水器、洗衣机、晒衣杆等齐全，真正的拎包入住！ 3、楼下生活配套齐全，有餐饮、超市、网咖等，穿个拖鞋搞定一切 4、一楼大厅台配有公区，提供公共厨房、健身房、台球桌、观影区、读书角、休闲吧等 5、水电物业费另付外，无其他费用','单身*经济','2020-05-03 16:30:04','2020-05-03 16:30:07','2020-05-03 16:30:09','招租','/housepic/5.jpg'),(6,'龙湖冠寓河西大街店','河西大街66号',1234,8,NULL,0,'该房源距离地铁10号线中胜站350米，交通方便。定位为24小时全方位租住生活服务空间。宽敞明亮、舒适呵护、安全便捷。 品质装修：一级资质单位施工、环保材料、全套海尔家电、乐家洁具、摩恩龙头、中央空调、24小时热水、全明房间……尽享优质生活。','学生','2020-05-03 16:31:36','2020-05-03 16:31:38','2020-05-03 16:31:41','下架','/housepic/6.jpg'),(7,'E+青年公寓水西门店','南京市建邺区水西门大街262号 ',1928,6,NULL,0,'该房源为青年公寓，地址位于2号线云锦路和莫愁湖站之间。交通很方便，周围生活配套齐全。24小时热水供应、管家式服务。','上班族','2020-05-03 16:33:12','2020-05-03 16:33:14','2020-05-03 16:33:17','招租','/housepic/7.jpg'),(8,'康桥圣菲',' 文苑路与学衡路交叉口',2599,8,NULL,0,'康桥圣菲50平米标准两房，长期居家出租，生活氛围浓厚。','聚会','2020-05-03 16:41:13','2020-05-03 16:41:15','2020-05-03 16:41:19','被租','/housepic/8.jpg'),(9,'东方万汇城南区','浦口万汇城地铁站3号口南150米 ',659,8,NULL,0,'东方万汇城位于10号线万汇城地铁站旁，周边配套齐全，交通便利。','经济','2020-05-03 16:42:31','2020-05-03 16:42:34','2020-05-03 16:42:36','招租','/housepic/9.jpg'),(10,'大型合租的选择','华侨路',4000,3,NULL,0,'居家精装修，品牌家电，环保材料，首次出租，希望爱干净人士入住。可以长期住。','聚会*家庭','2020-05-03 16:45:03','2020-05-03 16:45:05','2020-05-03 16:45:07','招租','/housepic/10.jpg'),(11,'德基紫金南苑','紫金东路2号',3599,8,NULL,0,'采光好，物管佳，交通便捷','聚会*家庭','2020-05-03 16:47:07','2020-05-03 16:47:09','2020-05-03 16:47:11','招租','/housepic/11.jpg'),(12,'紫金东郡','苏宁大道8号',1400,8,NULL,0,'房东直租，周边生活方便，靠近地铁4号线和2号线，紧靠徐庄软件园，苏宁总部，途牛，先声药业等企业。包物业费','经济','2020-05-03 17:01:24','2020-05-03 17:01:26','2020-05-03 17:01:29','招租','/housepic/12.jpg'),(13,'白鹭花园凤栖苑','福园街 ',1899,6,NULL,0,'周边配套设施齐全, 出门就有公交, 步.行5分钟到地铁站 房型方正,通透,特别是主卧的采光非常好 屋内设施齐全,小区周边环境良好,交通便利,周边超市、菜场、餐馆等一应倶全 房子室内干净整清进去直接就能入住,','聚会*家庭','2020-05-03 17:03:17','2020-05-03 17:03:19','2020-05-03 17:03:21','招租','/housepic/13.jpg'),(14,'长乐花园','长乐路2号 ',500,8,NULL,0,'房间宽敞可以住两个人.合租人均为周边上班青年.房内家具家电齐全我们的任何服务都是为了提高租客的住户体验.谢谢您看了我的帖子.有意可致电.看房方便','上班族','2020-05-03 17:05:59','2020-05-03 17:06:01','2020-05-03 17:06:05','招租','/housepic/14.jpg');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userid` int(15) NOT NULL AUTO_INCREMENT COMMENT '唯一标识，自增',
  `username` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `pwd` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `isowner` enum('房客','房东') COLLATE utf8mb4_bin NOT NULL COMMENT '只有两个选项<房客，房东>',
  `tags` varchar(35) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '可空,偏好标签，用于推荐房源',
  `phone` varchar(11) COLLATE utf8mb4_bin NOT NULL,
  `sex` enum('男','女') COLLATE utf8mb4_bin NOT NULL,
  `picUrl` varchar(255) COLLATE utf8mb4_bin NOT NULL DEFAULT 'http://pig.stadc.cn/back.jpg' COMMENT '头像',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

/*Data for the table `user` */

insert  into `user`(`userid`,`username`,`pwd`,`isowner`,`tags`,`phone`,`sex`,`picUrl`) values (2,'张三','abcd','房客',NULL,'10086','男','http://pig.stadc.cn/back.jpg'),(3,'张三','abcd','房东',NULL,'123456','男','http://pig.stadc.cn/back.jpg'),(4,'张创建','aa','房客','上班*合租*经济*','13071575896','男','http://pig.stadc.cn/back.jpg'),(5,'我代表没人租房','','房客','经济*','','男','http://pig.stadc.cn/back.jpg'),(6,'大房东','123','房东','经济*','13071575896','男','http://pig.stadc.cn/back.jpg'),(7,'实习人员','aaa','房客','家庭*合租*经济*','13071575896','男','http://pig.stadc.cn/back.jpg'),(8,'彭快活','swds','房东','学生*','13071575896','男','http://pig.stadc.cn/back.jpg'),(9,'最新租客','qq','房客','上班*合租*经济*','13071575896','女','http://pig.stadc.cn/back.jpg'),(10,'最新房东','gg','房东','学生*经济*','13071575896','男','http://pig.stadc.cn/back.jpg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
