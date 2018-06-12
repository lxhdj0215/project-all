--创建数据库
CREATE DATABASE seckill;
--使用数据库
use seckill;

--库存表
CREATE TABLE seckill(
`seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
`name` VARCHAR(120) NOT NULL COMMENT '商品名称',
`number` int  NOT NULL COMMENT '库存数量',
`start_time` timestamp NOT NULL  DEFAULT "2016-05-07 13:28:00",
`end_time` timestamp NOT NULL  DEFAULT "2016-05-07 13:28:00",
`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)EnGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

---初始化数据
insert into seckill(name,number,start_time,end_time)
VALUES
('1000元秒杀iphone6',100,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('500元秒杀ipad2',200,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('300元秒杀小米4',300,'2015-11-01 00:00:00','2015-11-02 00:00:00'),
('200元秒杀红米note',400,'2015-11-01 00:00:00','2015-11-02 00:00:00');

--秒杀成功明细表
--用户登录认证相关的信息
create table success_killed(
`seckill_id` bigint NOT NULL COMMENT '秒杀商品id',
`user_phone` bigint NOT NULL COMMENT '用户手机号',
`state` tinyint NOT NULL DEFAULT 0 COMMENT '状态标示:-1:无效 0:成功 1:已付款',
`create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
PRIMARY  KEY (seckill_id,user_phone),/*联合主键*/
KEY idx_create_time(create_time)
)EnGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='秒杀明细表';



create database test;

Create TABLE `user` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`userName` varchar(50) DEFAULT NULL,
`userAge` int(11) DEFAULT NULL,
`userAddress` varchar(200) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

Insert INTO `user` VALUES ('1', 'summer', '100', 'shanghai,pudong');

Drop TABLE IF EXISTS `article`;
Create TABLE `article` (
`id` int(11) NOT NULL auto_increment,
`userid` int(11) NOT NULL,
`title` varchar(100) NOT NULL,
`content` text NOT NULL,
PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- 添加几条测试数据
-- ----------------------------
Insert INTO `article` VALUES ('1', '1', 'test_title', 'test_content');
Insert INTO `article` VALUES ('2', '1', 'test_title_2', 'test_content_2');
Insert INTO `article` VALUES ('3', '1', 'test_title_3', 'test_content_3');
Insert INTO `article` VALUES ('4', '1', 'test_title_4', 'test_content_4');