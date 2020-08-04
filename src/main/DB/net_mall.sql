/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.21-log : Database - net_mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`net_mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `net_mall`;

/*Table structure for table `cart` */

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` decimal(10,0) DEFAULT NULL,
  `member_nickname` varchar(255) DEFAULT NULL,
  `delete_status` int(11) DEFAULT '0',
  `create_time` varchar(32) DEFAULT NULL,
  `product_pic` text,
  `count` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `cart` */

insert  into `cart`(`id`,`member_id`,`product_id`,`product_name`,`product_price`,`member_nickname`,`delete_status`,`create_time`,`product_pic`,`count`) values (1,18,2,'ipad air Max','1999','帅气黄大仙的昵称',1,'2020-08-04 16:44:40','front-end/img/dianzi/ipad.jpeg',0),(2,18,12,'麻辣鱼尾','12','帅气黄大仙的昵称',1,'2020-08-04 16:46:55','front-end/img/food/5.jpg',8),(3,18,10,'手撕酱板鸭','15','帅气黄大仙的昵称',1,'2020-08-04 16:46:57','front-end/img/food/3.jpg',3),(4,18,8,'绝味鸭脖','29','帅气黄大仙的昵称',0,'2020-08-04 16:54:52','front-end/img/food/1.jpg',1),(5,18,5,'OnePlus 7Pro','4399','帅气黄大仙的昵称',0,'2020-08-04 16:58:46','front-end/img/dianzi/oneplus7pro.jpg',1),(6,18,7,'iphonex','4399','帅气黄大仙的昵称',0,'2020-08-04 17:00:20','front-end/img/dianzi/iphonexs.jpg',1);

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`cid`,`cname`) values ('c001','服装'),('c002','电子产品'),('c003','配饰'),('c004','食品');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `cmail` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

/*Table structure for table `m_type` */

DROP TABLE IF EXISTS `m_type`;

CREATE TABLE `m_type` (
  `tid` int(11) NOT NULL,
  `tname` varchar(255) NOT NULL,
  KEY `tid` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `m_type` */

insert  into `m_type`(`tid`,`tname`) values (1,'管理员'),(0,'普通用户'),(2,'超级管理员');

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `nick_name` varchar(64) DEFAULT NULL,
  `address` text,
  `create_time` varchar(32) DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '0账号异常 1账号正常',
  `is_deleted` int(1) NOT NULL DEFAULT '0',
  `type` int(1) NOT NULL DEFAULT '0' COMMENT '0是普通用户，1是admin',
  PRIMARY KEY (`id`),
  KEY `m_tp` (`type`),
  KEY `username` (`username`),
  KEY `username_2` (`username`,`nick_name`),
  KEY `nick_name` (`nick_name`),
  CONSTRAINT `m_tp` FOREIGN KEY (`type`) REFERENCES `m_type` (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `member` */

insert  into `member`(`id`,`username`,`password`,`phone`,`avatar`,`nick_name`,`address`,`create_time`,`status`,`is_deleted`,`type`) values (1,'admin','admin1','888888','http://q1.qlogo.cn/g?b=qq&nk=1355275335&s=640','管理员大帅哥','四川轻化工大学team45','2020-07-31 02:03:35',1,0,2),(2,'xxx','111','888888','front-end/img/elavetor/small/l-8.jpg','我真的帅','四川轻化工大学team45','2020-07-31 03:14:55',1,0,2),(6,'shuaige','111','888888','front-end/img/elavetor/small/l-8.jpg','真的帅','四川轻化工大学team45','2020-08-02 21:09:16',1,0,0),(7,'222','222','11111','front-end/img/elavetor/small/l-8.jpg','彭于晏','四川轻化工大学team45','2020-07-31 03:15:00',0,1,0),(8,'333','333','333','front-end/img/elavetor/small/l-8.jpg','333','四川轻化工大学team45','2020-08-02 21:09:16',1,0,0),(10,'1111','1','111','front-end/img/elavetor/small/l-8.jpg','11','四川轻化工大学team45','2020-08-02 21:09:16',1,0,0),(11,'11111','11','111','front-end/img/elavetor/small/l-8.jpg','1111','四川轻化工大学team45','2020-08-02 21:09:16',1,0,0),(12,'abc','111','333','front-end/img/elavetor/small/l-8.jpg','haha','四川轻化工大学team45','2020-08-02 21:09:16',0,1,0),(13,'xx','1','11','front-end/img/elavetor/small/l-8.jpg','1','四川轻化工大学team45','2020-08-02 21:09:16',0,1,0),(14,'xxxx','123123123','2313123123','front-end/img/elavetor/small/l-8.jpg','xxxx','四川轻化工大学team45','2020-08-02 21:09:16',0,0,0),(15,'hhyzuishuai','123456','123','http://q1.qlogo.cn/g?b=qq&nk=3170486043&s=640','shuai',NULL,'2020-08-04 01:29:53',1,0,0),(16,'111122','123456','888888','front-end/img/elavetor/small/l-8.jpg','333',NULL,'2020-08-04 01:34:34',1,0,0),(17,'Gloxxxxi','88888888','888888','http://q1.qlogo.cn/g?b=qq&nk=1169550631&s=640','Gloxxxxi','','2020-08-04 01:37:15',1,0,2),(18,'帅气黄大仙','1234567','189000000',NULL,'帅气黄大仙的昵称',NULL,'2020-08-04 16:43:45',1,0,0),(19,'帅气黄大仙2','1234567','189000000','dist/img/user1-128x128.jpg','帅气黄大仙的昵称2',NULL,'2020-08-04 16:53:30',1,0,0);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_id` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `member_nickname` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_num` int(11) DEFAULT NULL,
  `pay_amount` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `order_item` */

DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `pro_id` int(11) DEFAULT NULL,
  `pro_name` varchar(255) DEFAULT NULL,
  `pro_price` decimal(10,0) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `total` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order_item` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `pic` text,
  `delete_status` int(1) unsigned zerofill DEFAULT '1',
  `publish_status` int(255) DEFAULT '0',
  `price` double DEFAULT NULL,
  `desciption` text,
  `num` int(11) DEFAULT NULL,
  `category_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `P_2_C` (`category_id`),
  CONSTRAINT `P_2_C` FOREIGN KEY (`category_id`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`pic`,`delete_status`,`publish_status`,`price`,`desciption`,`num`,`category_id`) values (2,'ipad air Max','front-end/img/dianzi/ipad.jpeg',0,1,1999,'史上最牛批的ipad，打游戏不卡，听歌无损音质，上网快的一匹，最主要是拍照美颜高效，所以这是最好的一款ipad神器，买到就是赚到。',66,'c002'),(3,'华为 HUAWEI P20','front-end\\img\\dianzi\\P20-q.jpg',0,1,3299,'优惠100元【6期免息】Huawei/华为 畅享20 Pro 5G手机 10plus官方旗舰店 畅想 10e mate30',566,'c002'),(4,'ipad mini 2','front-end\\img\\dianzi\\ipad.jpg',0,1,4999,'Apple/苹果 new iPadwifi版2019新款iPad10.2平板电脑2019iPad7代 ',67,'c002'),(5,'OnePlus 7Pro','front-end/img/dianzi/oneplus7pro.jpg',0,1,4399,'【享12期 套餐赠银耳2T+手机壳】一加 OnePlus 8 5G旗舰 90Hz高清柔性屏骁龙865超清超广角拍照游戏手机一加8',998,'c002'),(6,'oppoR11','front-end/img/dianzi/oppoR11.jpg',0,1,3000,'优惠500低至899 OPPO R11 oppoa11手机新款上市oppoa11 a11x a52 a72 a92s a9 oppo官网旗舰官 opop手机正品',998,'c002'),(7,'iphonex','front-end/img/dianzi/iphonexs.jpg',0,1,4399,'Apple/苹果 iPhone XS Max新苹果 iPhone xs手机xr国行有锁美版8x ',99,'c002'),(8,'绝味鸭脖','front-end/img/food/1.jpg',0,1,29,'绝味鸭脖零食礼盒送礼大礼包1000g 独立小包装卤味休闲零食 ',55,'c004'),(9,'泡椒凤爪','front-end/img/food/2.jpg',0,1,19,'【有友】泡椒凤爪山椒味泡椒味鸡爪好吃的鸡脚麻辣小零食70g*2袋',55,'c004'),(10,'手撕酱板鸭','front-end/img/food/3.jpg',0,1,15,'酱板鸭湖南常德特产肉食熟食小零食正宗手撕风干特辣烤鸭板鸭美食',55,'c004'),(11,'手撕牛肉','front-end/img/food/4.jpg',1,1,16,'风干牛肉干四川特产内蒙古手撕耗牛肉干500g袋装牦牛正宗麻辣零食',55,'c004'),(12,'麻辣鱼尾','front-end/img/food/5.jpg',0,1,12,'味芝元劲辣鱼棒15g*50包香辣小鱼仔毛毛鱼鱼干湖南特产麻辣零食',55,'c004'),(13,'劲仔小鱼干','front-end/img/food/6.jpg',0,1,9,'味芝元劲辣鱼棒15g*50包香辣小鱼仔毛毛鱼鱼干湖南特产麻辣零食',99,'c004'),(14,'连衣裙','front-end/img/clothes/1.jpg',0,1,399,'云地素20新款法式蕾丝娃娃裙长袖网纱裙刺绣连衣裙女裙子3C3O620',88,'c001'),(15,'碎花裙','front-end/img/clothes/2.jpg',0,1,599,' 2020新款夏V领收腰遮肚子显瘦抽绳气质法式小众真丝桑蚕丝连衣裙',75,'c001'),(16,'长裙','front-end/img/clothes/3.jpg',0,1,1599,'TOUCH MISS优雅气质显瘦女装金线双层真丝连衣裙系带套头中长裙',125,'c001'),(17,'开衫毛衣','front-end/img/clothes/4.jpg',0,1,489,'网红毛衣开衫薄款二八月外套时尚潮流港味复古温柔风韩版针织洋气',78,'c001'),(18,'牛仔短裤','front-end/img/clothes/5.jpg',0,1,399,'【国内现货】OFF OW 18FW新加坡限定渐变箭头牛仔短裤热裤女夏季',85,'c001'),(19,'工装服','front-end/img/clothes/6.jpg',0,1,699,'日系棉衣女中长款工装棉服韩版宽松学生加厚棉袄ins港风冬季外套 ',71,'c001');

/*Table structure for table `wallet` */

DROP TABLE IF EXISTS `wallet`;

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL,
  `member_nickname` varchar(255) DEFAULT NULL,
  `balance` double(255,0) DEFAULT '0',
  `level` decimal(10,0) DEFAULT NULL,
  `update_time` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `wallet_user` (`member_id`),
  KEY `wallet_nick` (`member_nickname`),
  CONSTRAINT `wallet_nick` FOREIGN KEY (`member_nickname`) REFERENCES `member` (`nick_name`),
  CONSTRAINT `wallet_user` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `wallet` */

insert  into `wallet`(`id`,`member_id`,`member_nickname`,`balance`,`level`,`update_time`) values (1,1,'管理员大帅哥',1480,'0','2020-08-01 17:32:30'),(2,19,'帅气黄大仙的昵称2',0,'0','2020-08-04 16:53:30');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
