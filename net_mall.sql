/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : net_mall

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2020-08-05 08:59:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES ('9', '1', '3', '华为 HUAWEI P20', '3299', '管理员大帅哥', '0', '2020-08-05 01:46:38', '2020-08-05 01:46:38', '2');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` varchar(32) NOT NULL,
  `cname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('c001', '服装');
INSERT INTO `category` VALUES ('c002', '电子产品');
INSERT INTO `category` VALUES ('c003', '配饰');
INSERT INTO `category` VALUES ('c004', '食品');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `cname` varchar(255) DEFAULT NULL,
  `cmail` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('20', 'Gloxxxxi', '1169550631@qq.com', '吃饱饱，身体好', '2020-08-04 16:37:42');
INSERT INTO `comment` VALUES ('21', 'Xseven', 'niko1@163.com', '要永远觉得祖国的土地是稳固地在你脚下，要与集体一起生活，要记住，是集体教育了你。那一天你若和集体脱离，那便是末路的开始。 \n—— 奥斯特洛夫斯基', '2020-08-04 16:38:51');
INSERT INTO `comment` VALUES ('22', 'mysun233', 'mysun@ws.com', '土地是以它的肥沃和收获而被估价的；才能也是土地，不过它生产的不是粮食，而是真理。如果只能滋生瞑想和幻想的话，即使再大的才能也只是砂地或盐池，那上面连小草也长不出来的。\n —— 别林斯基', '2020-08-04 16:39:28');
INSERT INTO `comment` VALUES ('23', 'Gloxxxxi', '1169550631@qq.com', '一个人最怕不老实，青年人最可贵的是老实作风。\"老实\"就是不自欺欺人，做到不欺骗人家容易，不欺骗自己最难。\"老实作风\"就是脚踏实地，不占便宜。世界上没有便宜的事，谁想占便宜水就会吃亏。 \n—— 徐特立', '2020-08-04 16:39:50');
INSERT INTO `comment` VALUES ('24', 'Gloxxxxi', '1169550631@qq.com', '啊吧啊吧', '2020-08-04 16:40:00');
INSERT INTO `comment` VALUES ('25', 'Lec', 'cellec@qq.com', '“神舟十一号，地面信号异常，现在请汇报您的具体位置？”\n“现在我们正在祖国上空”“你们怎么知道的？”\n“刚才试验了一下，Twitter和FaceBook都打不开。”', '2020-08-04 16:41:45');
INSERT INTO `comment` VALUES ('26', '马哥13178', 'marger@163.com', '手机问题，其他行业的人，以为程序员们，什么都会，\n程序员中，女程序员以为男程序员，什么都会，\n男程序员中，一般程序员以为技术好的程序员，什么都会，\n技术好的程序员，每次都在网上苦苦找答案。。。。。。', '2020-08-04 16:42:54');
INSERT INTO `comment` VALUES ('27', 'Gloxxxxi', '1169550631@qq.com', '一个程序员在肯德基编程，一个乞丐边上坐下来，向他乞讨。他给了一块钱，继续写代码。乞丐没有走，看着他。过了一会，悄悄地说：“这行少了一个分号” 他惊诧地睁大眼睛问：“这个你也懂? ” 乞丐满眼含泪：“就是因为懂这个，所以我才落的今天这下场。”一个程序员在肯德基编程，一个乞丐边上坐下来，向他乞讨。他给了一块钱，继续写代码。乞丐没有走，看着他。过了一会，悄悄地说：“这行少了一个分号” 他惊诧地睁大眼睛问：“这个你也懂? ” 乞丐满眼含泪：“就是因为懂这个，所以我才落的今天这下场。”', '2020-08-04 16:47:16');

-- ----------------------------
-- Table structure for member
-- ----------------------------
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

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'admin', 'admin1', '888888', 'http://q1.qlogo.cn/g?b=qq&nk=1355275335&s=640', '管理员大帅哥', '四川轻化工大学team45', '2020-07-31 02:03:35', '1', '0', '2');
INSERT INTO `member` VALUES ('2', 'xxx', '111', '888888', 'front-end/img/elavetor/small/l-8.jpg', '我真的帅', '四川轻化工大学team45', '2020-07-31 03:14:55', '1', '0', '2');
INSERT INTO `member` VALUES ('6', 'shuaige', '111', '888888', 'front-end/img/elavetor/small/l-8.jpg', '真的帅', '四川轻化工大学team45', '2020-08-02 21:09:16', '1', '0', '0');
INSERT INTO `member` VALUES ('7', '222', '222', '11111', 'front-end/img/elavetor/small/l-8.jpg', '彭于晏', '四川轻化工大学team45', '2020-07-31 03:15:00', '0', '1', '0');
INSERT INTO `member` VALUES ('8', '333', '333', '333', 'front-end/img/elavetor/small/l-8.jpg', '333', '四川轻化工大学team45', '2020-08-02 21:09:16', '1', '0', '0');
INSERT INTO `member` VALUES ('10', '1111', '1', '111', 'front-end/img/elavetor/small/l-8.jpg', '11', '四川轻化工大学team45', '2020-08-02 21:09:16', '1', '0', '0');
INSERT INTO `member` VALUES ('11', '11111', '11', '111', 'front-end/img/elavetor/small/l-8.jpg', '1111', '四川轻化工大学team45', '2020-08-02 21:09:16', '1', '0', '0');
INSERT INTO `member` VALUES ('12', 'abc', '111', '333', 'front-end/img/elavetor/small/l-8.jpg', 'haha', '四川轻化工大学team45', '2020-08-02 21:09:16', '0', '1', '0');
INSERT INTO `member` VALUES ('13', 'xx', '1', '11', 'front-end/img/elavetor/small/l-8.jpg', '1', '四川轻化工大学team45', '2020-08-02 21:09:16', '0', '1', '0');
INSERT INTO `member` VALUES ('14', 'xxxx', '123', '2313123123', 'front-end/img/elavetor/small/l-8.jpg', 'xxxx', '四川轻化工大学team45', '2020-08-02 21:09:16', '1', '0', '0');
INSERT INTO `member` VALUES ('15', 'hhyzuishuai', '123456', '123', 'http://q1.qlogo.cn/g?b=qq&nk=3170486043&s=640', 'shuai', null, '2020-08-04 01:29:53', '1', '0', '0');
INSERT INTO `member` VALUES ('16', '111122', '123456', '888888', 'front-end/img/elavetor/small/l-8.jpg', '333', null, '2020-08-04 01:34:34', '1', '0', '0');
INSERT INTO `member` VALUES ('17', 'Gloxxxxi', '88888888', '888888', 'http://q1.qlogo.cn/g?b=qq&nk=1169550631&s=640', 'Gloxxxxi', '', '2020-08-04 01:37:15', '1', '0', '2');
INSERT INTO `member` VALUES ('18', '帅气黄大仙', '1234567', '189000000', null, '帅气黄大仙的昵称', null, '2020-08-04 16:43:45', '1', '0', '0');
INSERT INTO `member` VALUES ('19', '帅气黄大仙2', '1234567', '189000000', 'dist/img/user1-128x128.jpg', '帅气黄大仙的昵称2', null, '2020-08-04 16:53:30', '1', '0', '0');

-- ----------------------------
-- Table structure for money_code
-- ----------------------------
DROP TABLE IF EXISTS `money_code`;
CREATE TABLE `money_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` double DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `md5` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of money_code
-- ----------------------------
INSERT INTO `money_code` VALUES ('1', '500', '897uit234', 'b6c8e529f3387cc700e22167ea04395f', '0');
INSERT INTO `money_code` VALUES ('2', '1000', '314opq098', '47c159d1ae965722878d14b30edaa9b4', '1');
INSERT INTO `money_code` VALUES ('6', '1000', 'Ddz6k8iDc', '9a3f4c78ab2b00739c5f73c521e1bf16', '1');
INSERT INTO `money_code` VALUES ('7', '2000', 'KBRFynLbW', 'bbca9823e75fb0ffd31cb761608f43ec', '1');
INSERT INTO `money_code` VALUES ('8', '100', 'yu5OGc0RP', '9ebdb86232e17b0249ca7a5e2ec499e5', '0');

-- ----------------------------
-- Table structure for m_type
-- ----------------------------
DROP TABLE IF EXISTS `m_type`;
CREATE TABLE `m_type` (
  `tid` int(11) NOT NULL,
  `tname` varchar(255) NOT NULL,
  KEY `tid` (`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_type
-- ----------------------------
INSERT INTO `m_type` VALUES ('1', '管理员');
INSERT INTO `m_type` VALUES ('0', '普通用户');
INSERT INTO `m_type` VALUES ('2', '超级管理员');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cart_id` int(11) DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `member_nickname` varchar(255) DEFAULT NULL,
  `pay_amount` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `pro_id` int(11) DEFAULT NULL,
  `pro_name` varchar(255) DEFAULT NULL,
  `pro_price` decimal(10,0) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `total` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('1', '1', null, null, null, null, null);
INSERT INTO `order_item` VALUES ('2', null, null, null, null, null, null);
INSERT INTO `order_item` VALUES ('3', null, null, null, null, null, null);
INSERT INTO `order_item` VALUES ('4', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
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

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('2', 'ipad air Max', 'front-end/img/dianzi/ipad.jpeg', '1', '1', '1999', '史上最牛批的ipad，打游戏不卡，听歌无损音质，上网快的一匹，最主要是拍照美颜高效，所以这是最好的一款ipad神器，买到就是赚到。', '66', 'c002');
INSERT INTO `product` VALUES ('3', '华为 HUAWEI P20', '/front-end/img/upload/P20-1.jpg', '0', '0', '3299', '优惠100元【6期免息】Huawei/华为 畅享20 Pro 5G手机 10plus官方旗舰店 畅想 10e mate30', '566', 'c002');
INSERT INTO `product` VALUES ('4', 'ipad mini 2', 'front-end\\img\\dianzi\\ipad.jpg', '0', '1', '4999', 'Apple/苹果 new iPadwifi版2019新款iPad10.2平板电脑2019iPad7代 ', '67', 'c002');
INSERT INTO `product` VALUES ('5', 'OnePlus 7Pro', 'front-end/img/dianzi/oneplus7pro.jpg', '0', '1', '4399', '【享12期 套餐赠银耳2T+手机壳】一加 OnePlus 8 5G旗舰 90Hz高清柔性屏骁龙865超清超广角拍照游戏手机一加8', '998', 'c002');
INSERT INTO `product` VALUES ('6', 'oppoR11', 'front-end/img/dianzi/oppoR11.jpg', '0', '1', '3000', '优惠500低至899 OPPO R11 oppoa11手机新款上市oppoa11 a11x a52 a72 a92s a9 oppo官网旗舰官 opop手机正品', '998', 'c002');
INSERT INTO `product` VALUES ('7', 'iphonex', 'front-end/img/dianzi/iphonexs.jpg', '0', '1', '4399', 'Apple/苹果 iPhone XS Max新苹果 iPhone xs手机xr国行有锁美版8x ', '99', 'c002');
INSERT INTO `product` VALUES ('8', '绝味鸭脖', 'front-end/img/food/1.jpg', '0', '1', '29', '绝味鸭脖零食礼盒送礼大礼包1000g 独立小包装卤味休闲零食 ', '55', 'c004');
INSERT INTO `product` VALUES ('9', '泡椒凤爪', 'front-end/img/food/2.jpg', '0', '1', '19', '【有友】泡椒凤爪山椒味泡椒味鸡爪好吃的鸡脚麻辣小零食70g*2袋', '55', 'c004');
INSERT INTO `product` VALUES ('10', '手撕酱板鸭', 'front-end/img/food/3.jpg', '0', '1', '15', '酱板鸭湖南常德特产肉食熟食小零食正宗手撕风干特辣烤鸭板鸭美食', '55', 'c004');
INSERT INTO `product` VALUES ('11', '手撕牛肉', 'front-end/img/food/4.jpg', '1', '1', '16', '风干牛肉干四川特产内蒙古手撕耗牛肉干500g袋装牦牛正宗麻辣零食', '55', 'c004');
INSERT INTO `product` VALUES ('12', '麻辣鱼尾', 'front-end/img/food/5.jpg', '0', '1', '12', '味芝元劲辣鱼棒15g*50包香辣小鱼仔毛毛鱼鱼干湖南特产麻辣零食', '55', 'c004');
INSERT INTO `product` VALUES ('13', '劲仔小鱼干', 'front-end/img/food/6.jpg', '0', '1', '9', '味芝元劲辣鱼棒15g*50包香辣小鱼仔毛毛鱼鱼干湖南特产麻辣零食', '99', 'c004');
INSERT INTO `product` VALUES ('14', '连衣裙', 'front-end/img/clothes/1.jpg', '0', '1', '399', '云地素20新款法式蕾丝娃娃裙长袖网纱裙刺绣连衣裙女裙子3C3O620', '88', 'c001');
INSERT INTO `product` VALUES ('15', '碎花裙', 'front-end/img/clothes/2.jpg', '0', '1', '599', ' 2020新款夏V领收腰遮肚子显瘦抽绳气质法式小众真丝桑蚕丝连衣裙', '75', 'c001');
INSERT INTO `product` VALUES ('16', '长裙', 'front-end/img/clothes/3.jpg', '0', '1', '1599', 'TOUCH MISS优雅气质显瘦女装金线双层真丝连衣裙系带套头中长裙', '125', 'c001');
INSERT INTO `product` VALUES ('17', '开衫毛衣', 'front-end/img/clothes/4.jpg', '0', '1', '489', '网红毛衣开衫薄款二八月外套时尚潮流港味复古温柔风韩版针织洋气', '78', 'c001');
INSERT INTO `product` VALUES ('18', '牛仔短裤', 'front-end/img/clothes/5.jpg', '0', '1', '399', '【国内现货】OFF OW 18FW新加坡限定渐变箭头牛仔短裤热裤女夏季', '85', 'c001');
INSERT INTO `product` VALUES ('19', '工装服', 'front-end/img/clothes/6.jpg', '0', '1', '699', '日系棉衣女中长款工装棉服韩版宽松学生加厚棉袄ins港风冬季外套 ', '71', 'c001');

-- ----------------------------
-- Table structure for wallet
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES ('1', '1', '管理员大帅哥', '6700', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('2', '19', '帅气黄大仙的昵称2', '0', '0', '2020-08-04 16:53:30');
INSERT INTO `wallet` VALUES ('3', '2', '我真的帅', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('4', '6', '真的帅', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('5', '7', '彭于晏', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('6', '8', '333', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('7', '10', '11', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('8', '11', '1111', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('9', '12', 'haha', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('10', '13', '1', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('11', '14', 'xxxx', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('12', '15', 'shuai', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('13', '16', '333', '0', '0', '2020-08-01 17:32:30');
INSERT INTO `wallet` VALUES ('14', '17', 'Gloxxxxi', '0', '0', '2020-08-01 17:32:30');
