/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.59 : Database - hello_job
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `hello_job`;

/*Table structure for table `host_info` */

DROP TABLE IF EXISTS `host_info`;

CREATE TABLE `host_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `protocol` varchar(32) NOT NULL COMMENT '协议，ssh',
  `host` varchar(64) NOT NULL,
  `port` int(11) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `passwd` varchar(32) DEFAULT NULL,
  `creater` bigint(20) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `host_info` */

insert  into `host_info`(`id`,`protocol`,`host`,`port`,`username`,`passwd`,`creater`,`create_time`) values (1,'本地执行','127.0.0.1',NULL,NULL,NULL,1,'2018-10-28 19:55:50'),(2,'ssh','192.168.80.101',22,'root','admin',1,'2018-10-28 19:55:50');

/*Table structure for table `job_basic_info` */

DROP TABLE IF EXISTS `job_basic_info`;

CREATE TABLE `job_basic_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_name` varchar(256) NOT NULL,
  `command` varchar(256) NOT NULL,
  `sche_type` bigint(20) DEFAULT NULL,
  `job_type` bigint(20) NOT NULL,
  `creater` bigint(20) NOT NULL,
  `host_id` int(20) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `job_basic_info` */

insert  into `job_basic_info`(`id`,`job_name`,`command`,`sche_type`,`job_type`,`creater`,`host_id`,`remark`,`create_time`) values (1,'获取ip','ifconfig',NULL,1,1,2,'','2018-10-28 20:03:00');

/*Table structure for table `job_instance` */

DROP TABLE IF EXISTS `job_instance`;

CREATE TABLE `job_instance` (
  `id` varchar(32) DEFAULT NULL,
  `job_id` bigint(20) DEFAULT NULL,
  `dt` int(11) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_instance` */

/*Table structure for table `job_log` */

DROP TABLE IF EXISTS `job_log`;

CREATE TABLE `job_log` (
  `id` varchar(36) NOT NULL,
  `job_id` bigint(20) DEFAULT NULL,
  `job_state` varchar(12) DEFAULT NULL,
  `dt` int(11) DEFAULT NULL,
  `begin_time` varchar(19) DEFAULT NULL,
  `end_time` varchar(19) DEFAULT NULL,
  `log` mediumtext,
  `job_img` text,
  `application_id` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `job_log` */

/*Table structure for table `job_owner` */

DROP TABLE IF EXISTS `job_owner`;

CREATE TABLE `job_owner` (
  `job_id` bigint(20) DEFAULT NULL COMMENT '作业id',
  `user_id` bigint(1) DEFAULT NULL COMMENT '用户id'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `job_owner` */

insert  into `job_owner`(`job_id`,`user_id`) values (1,13);

/*Table structure for table `job_type` */

DROP TABLE IF EXISTS `job_type`;

CREATE TABLE `job_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `job_type` */

insert  into `job_type`(`id`,`name`,`seq`,`create_time`) values (1,'测试',10,'2018-10-28 19:59:06');

/*Table structure for table `organization` */

DROP TABLE IF EXISTS `organization`;

CREATE TABLE `organization` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '组织名',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `code` varchar(64) NOT NULL COMMENT '编号',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级主键',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='组织机构';

/*Data for the table `organization` */

insert  into `organization`(`id`,`name`,`address`,`code`,`icon`,`pid`,`seq`,`create_time`) values (1,'总经办','王家桥','01','glyphicon-lock ',NULL,0,'2014-02-19 01:00:00'),(3,'技术部','','02','glyphicon-wrench ',NULL,1,'2015-10-01 13:10:42'),(5,'产品部','','03','glyphicon-send ',NULL,2,'2015-12-06 12:15:30'),(6,'测试组','','04','glyphicon-headphones ',3,0,'2015-12-06 13:12:18');

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '资源名称',
  `url` varchar(100) DEFAULT NULL COMMENT '资源路径',
  `open_mode` varchar(32) DEFAULT NULL COMMENT '打开方式 ajax,iframe',
  `description` varchar(255) DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) DEFAULT NULL COMMENT '资源图标',
  `pid` bigint(19) DEFAULT NULL COMMENT '父级资源id',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  `opened` tinyint(2) NOT NULL DEFAULT '1' COMMENT '打开状态',
  `resource_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '资源类别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8 COMMENT='资源';

/*Data for the table `resource` */

insert  into `resource`(`id`,`name`,`url`,`open_mode`,`description`,`icon`,`pid`,`seq`,`status`,`opened`,`resource_type`,`create_time`) values (1,'权限管理','','','系统管理','glyphicon-folder-open ',NULL,4,0,0,0,'2014-02-19 01:00:00'),(11,'资源管理','/resource/manager','ajax','资源管理','glyphicon-th ',1,1,0,1,0,'2014-02-19 01:00:00'),(12,'角色管理','/role/manager','ajax','角色管理','glyphicon-eye-open ',1,2,0,1,0,'2014-02-19 01:00:00'),(13,'用户管理','/user/manager','ajax','用户管理','glyphicon-user ',1,3,0,1,0,'2014-02-19 01:00:00'),(14,'部门管理','/organization/manager','ajax','部门管理','glyphicon-lock ',1,4,0,1,0,'2014-02-19 01:00:00'),(111,'列表','/resource/treeGrid','ajax','资源列表','glyphicon-list ',11,0,0,1,1,'2014-02-19 01:00:00'),(112,'添加','/resource/add','ajax','资源添加','glyphicon-plus icon-green',11,0,0,1,1,'2014-02-19 01:00:00'),(113,'编辑','/resource/edit','ajax','资源编辑','glyphicon-pencil icon-blue',11,0,0,1,1,'2014-02-19 01:00:00'),(114,'删除','/resource/delete','ajax','资源删除','glyphicon-trash icon-red',11,0,0,1,1,'2014-02-19 01:00:00'),(121,'列表','/role/dataGrid','ajax','角色列表','glyphicon-list ',12,0,0,1,1,'2014-02-19 01:00:00'),(122,'添加','/role/add','ajax','角色添加','glyphicon-plus icon-green',12,0,0,1,1,'2014-02-19 01:00:00'),(123,'编辑','/role/edit','ajax','角色编辑','glyphicon-pencil icon-blue',12,0,0,1,1,'2014-02-19 01:00:00'),(124,'删除','/role/delete','ajax','角色删除','glyphicon-trash icon-red',12,0,0,1,1,'2014-02-19 01:00:00'),(125,'授权','/role/grant','ajax','角色授权','glyphicon-ok icon-green',12,0,0,1,1,'2014-02-19 01:00:00'),(131,'列表','/user/dataGrid','ajax','用户列表','glyphicon-list ',13,0,0,1,1,'2014-02-19 01:00:00'),(132,'添加','/user/add','ajax','用户添加','glyphicon-plus icon-green',13,0,0,1,1,'2014-02-19 01:00:00'),(133,'编辑','/user/edit','ajax','用户编辑','glyphicon-pencil icon-blue',13,0,0,1,1,'2014-02-19 01:00:00'),(134,'删除','/user/delete','ajax','用户删除','glyphicon-trash icon-red',13,0,0,1,1,'2014-02-19 01:00:00'),(141,'列表','/organization/treeGrid','ajax','用户列表','glyphicon-list ',14,0,0,1,1,'2014-02-19 01:00:00'),(142,'添加','/organization/add','ajax','部门添加','glyphicon-plus icon-green',14,0,0,1,1,'2014-02-19 01:00:00'),(143,'编辑','/organization/edit','ajax','部门编辑','glyphicon-pencil icon-blue',14,0,0,1,1,'2014-02-19 01:00:00'),(144,'删除','/organization/delete','ajax','部门删除','glyphicon-trash icon-red',14,0,0,1,1,'2014-02-19 01:00:00'),(221,'日志监控','','',NULL,'glyphicon-dashboard ',NULL,10,0,0,0,'2015-12-01 11:44:20'),(223,'官方网站','https://www.dreamlu.net','iframe',NULL,'glyphicon-globe ',222,0,0,1,0,'2015-12-06 12:42:42'),(224,'jfinal视频','http://blog.dreamlu.net/blog/79','iframe',NULL,'glyphicon-blackboard ',222,1,0,1,0,'2015-12-06 12:45:28'),(226,'修改密码','/user/editPwdPage','ajax',NULL,'glyphicon-eye-close ',NULL,4,0,1,1,'2015-12-07 20:23:06'),(227,'登录日志','/sysLog/manager','ajax',NULL,'glyphicon-exclamation-sign ',221,0,0,1,0,'2016-09-30 22:10:53'),(228,'Druid监控','/druid','iframe',NULL,'glyphicon-sunglasses ',221,0,0,1,0,'2016-09-30 22:12:50'),(229,'系统图标','/icons.html','ajax',NULL,'glyphicon-picture ',221,0,0,1,0,'2016-12-24 15:53:47'),(230,'作业中心','','无(用于上层菜单)',NULL,'glyphicon-folder-open ',NULL,1,0,1,0,'2016-12-24 15:53:47'),(231,'作业管理','/job/jobBasicInfo','iframe',NULL,'',230,0,0,1,0,'2016-12-24 15:53:47'),(233,'业务类型','/jobType/jobType','iframe',NULL,'',230,2,0,1,0,'2018-04-13 17:45:19'),(234,'作业日志','/jobLog/jobLog','iframe',NULL,'',230,4,0,1,0,'2018-04-13 18:09:13'),(236,'邮箱账号','/email/account','iframe',NULL,'',235,1,0,1,0,'2018-04-15 18:39:01'),(238,'yarn集群','/yarn/clusterInfo','iframe',NULL,'',237,1,0,1,0,'2018-04-21 11:52:07'),(239,'执行主机','/host/hostInfo','iframe',NULL,'',230,5,0,1,0,'2018-05-25 21:37:25');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) NOT NULL COMMENT '角色名',
  `seq` tinyint(2) NOT NULL DEFAULT '0' COMMENT '排序号',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`seq`,`description`,`status`) values (1,'admin',0,'超级管理员',0),(2,'de',0,'技术部经理',0),(7,'pm',0,'产品部经理',0),(8,'test',0,'测试账户',0);

/*Table structure for table `role_resource` */

DROP TABLE IF EXISTS `role_resource`;

CREATE TABLE `role_resource` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`),
  KEY `idx_role_resource_ids` (`role_id`,`resource_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=481 DEFAULT CHARSET=utf8 COMMENT='角色资源';

/*Data for the table `role_resource` */

insert  into `role_resource`(`id`,`role_id`,`resource_id`) values (409,1,1),(410,1,11),(415,1,12),(421,1,13),(426,1,14),(411,1,111),(412,1,112),(413,1,113),(414,1,114),(416,1,121),(417,1,122),(418,1,123),(419,1,124),(420,1,125),(422,1,131),(423,1,132),(424,1,133),(425,1,134),(427,1,141),(428,1,142),(429,1,143),(430,1,144),(434,1,221),(432,1,223),(433,1,224),(435,1,227),(436,1,228),(437,2,1),(438,2,13),(439,2,131),(440,2,132),(441,2,133),(445,2,221),(443,2,223),(444,2,224),(446,2,227),(447,2,228),(158,3,1),(159,3,11),(164,3,12),(170,3,13),(175,3,14),(160,3,111),(161,3,112),(162,3,113),(163,3,114),(165,3,121),(166,3,122),(167,3,123),(168,3,124),(169,3,125),(171,3,131),(172,3,132),(173,3,133),(174,3,134),(176,3,141),(177,3,142),(178,3,143),(179,3,144),(359,7,1),(360,7,14),(361,7,141),(362,7,142),(363,7,143),(367,7,221),(365,7,223),(366,7,224),(368,7,226),(448,8,1),(449,8,11),(451,8,12),(453,8,13),(455,8,14),(450,8,111),(452,8,121),(454,8,131),(456,8,141),(460,8,221),(458,8,223),(459,8,224),(461,8,227),(462,8,228),(478,8,229),(479,8,230),(480,8,231);

/*Table structure for table `sche_basic_info` */

DROP TABLE IF EXISTS `sche_basic_info`;

CREATE TABLE `sche_basic_info` (
  `job_id` bigint(20) DEFAULT NULL,
  `creater` bigint(20) DEFAULT NULL,
  `sche_type` varchar(32) DEFAULT NULL,
  `cron` varchar(32) DEFAULT NULL,
  `is_self_rely` varchar(2) DEFAULT NULL,
  `begin_time` varchar(19) DEFAULT NULL,
  `end_time` varchar(19) DEFAULT NULL,
  `try_count` int(11) DEFAULT NULL,
  `try_interval` int(11) DEFAULT NULL,
  `receiver` varchar(256) DEFAULT NULL,
  `create_time` varchar(19) DEFAULT NULL,
  UNIQUE KEY `job_id` (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sche_basic_info` */

/*Table structure for table `sche_rely_job` */

DROP TABLE IF EXISTS `sche_rely_job`;

CREATE TABLE `sche_rely_job` (
  `pid` bigint(20) DEFAULT NULL,
  `job_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sche_rely_job` */

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(255) DEFAULT NULL COMMENT '登陆名',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `opt_content` varchar(1024) DEFAULT NULL COMMENT '内容',
  `client_ip` varchar(255) DEFAULT NULL COMMENT '客户端ip',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统日志';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`login_name`,`role_name`,`opt_content`,`client_ip`,`create_time`) values (1,'admin','admin','[类名]:com.helloJob.controller.job.JobTypeController,[方法]:add,[参数]:name=测试&seq=10&','0:0:0:0:0:0:0:1','2018-10-28 19:59:06'),(2,'admin','admin','[类名]:com.helloJob.controller.job.JobBasicInfoController,[方法]:add,[参数]:jobType=1&jobName=获取ip&command=ifconfig&remark=&hostId=2&ownerIds[]=13&','0:0:0:0:0:0:0:1','2018-10-28 20:03:00');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) NOT NULL COMMENT '登陆名',
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `salt` varchar(36) DEFAULT NULL COMMENT '密码加密盐',
  `sex` tinyint(2) NOT NULL DEFAULT '0' COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(64) DEFAULT NULL,
  `user_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户类别',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `organization_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属机构',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDx_user_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户';

/*Data for the table `user` */

insert  into `user`(`id`,`login_name`,`name`,`password`,`salt`,`sex`,`phone`,`email`,`user_type`,`status`,`organization_id`,`create_time`) values (1,'admin','admin','05a671c66aefea124cc08b76ea6d30bb','test',0,'18707173000','18707173000@qq.com',0,0,1,'2015-12-06 13:14:05'),(13,'snoopy','snoopy','05a671c66aefea124cc08b76ea6d30bb','test',0,'18707173000','18707173000@qq.com',1,0,3,'2015-10-01 13:12:07'),(14,'dreamlu','dreamlu','05a671c66aefea124cc08b76ea6d30bb','test',0,'18707173000','18707173000@qq.com',1,0,5,'2015-10-11 23:12:58'),(15,'test','test','05a671c66aefea124cc08b76ea6d30bb','test',0,'18707173000','18707173000@qq.com',1,0,6,'2015-12-06 13:13:03');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`),
  KEY `idx_user_role_ids` (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8 COMMENT='用户角色';

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (66,1,1),(67,1,2),(68,1,7),(69,1,8),(63,13,2),(64,14,7),(53,15,8);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
