/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.6.45 : Database - shiro1
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE
DATABASE /*!32312 IF NOT EXISTS*/`shiro1` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE
`shiro1`;

/*Table structure for table `t_operate_log` */

DROP TABLE IF EXISTS `t_operate_log`;

CREATE TABLE `t_operate_log`
(
    `log_id`               varchar(100) NOT NULL,
    `login_user`           varchar(100) DEFAULT NULL,
    `request_url`          varchar(100) DEFAULT NULL,
    `request_param`        varchar(100) DEFAULT NULL,
    `request_ip`           varchar(30)  DEFAULT NULL,
    `request_type`         varchar(20)  DEFAULT NULL,
    `request_class_method` varchar(100) DEFAULT NULL,
    `ope_time`             datetime     DEFAULT NULL,
    PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_operate_log` */

insert into `t_operate_log`(`log_id`, `login_user`, `request_url`, `request_param`, `request_ip`, `request_type`,
                            `request_class_method`, `ope_time`)
values ('1618883092611', 'guest', '/login', 'username=guest&password=guest&rememberMe=true', '127.0.0.1', 'POST',
        'com.zhenhao.controller.LoginController', '2021-04-20 09:44:53'),
       ('1618883145705', 'guest', '/index', NULL, '127.0.0.1', 'GET', 'com.zhenhao.controller.LoginController',
        '2021-04-20 09:45:46'),
       ('1618883272328', 'guest', '/login', 'username=guest&password=guest&rememberMe=true', '172.18.74.207', 'POST',
        'com.zhenhao.controller.LoginController', '2021-04-20 09:47:52'),
       ('1618884457852', 'guest', '/login', 'username=guest&password=guest&rememberMe=true', '172.18.74.207', 'POST',
        'com.zhenhao.controller.LoginControllerlogin', '2021-04-20 10:07:38'),
       ('1618884479070', 'guest', '/login', NULL, '172.18.74.207', 'GET', 'com.zhenhao.controller.LoginControllerlogin',
        '2021-04-20 10:07:59'),
       ('1618884499223', 'guest', '/login', NULL, '172.18.74.207', 'GET', 'com.zhenhao.controller.LoginControllerlogin',
        '2021-04-20 10:08:19'),
       ('1618884505847', 'guest', '/index', NULL, '172.18.74.207', 'GET', 'com.zhenhao.controller.LoginControllerindex',
        '2021-04-20 10:08:26'),
       ('1618885189863', 'guest', '/login', 'username=guest&password=guest&rememberMe=true', '172.18.74.207', 'POST',
        'com.zhenhao.controller.LoginControllerlogin', '2021-04-20 10:19:50'),
       ('1618885276038', 'guest', '/login', 'username=guest&password=guest&rememberMe=true', '172.18.74.207', 'POST',
        'com.zhenhao.controller.LoginController.login', '2021-04-20 10:21:16');

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission`
(
    `id`   int(10) NOT NULL,
    `url`  varchar(256) DEFAULT NULL,
    `name` varchar(64)  DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_permission` */

insert into `t_permission`(`id`, `url`, `name`)
values (1, '/user/list', 'user:user'),
       (2, '/user/add', 'user:add'),
       (3, '/user/delete', 'user:delete'),
       (4, '/index', NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role`
(
    `id`   int(11) NOT NULL,
    `name` varchar(32) DEFAULT NULL,
    `memo` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_role` */

insert into `t_role`(`id`, `name`, `memo`)
values (1, 'admin', '?????'),
       (2, 'test', '????');

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission`
(
    `rid` int(10) DEFAULT NULL,
    `pid` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_role_permission` */

insert into `t_role_permission`(`rid`, `pid`)
values (1, 2),
       (1, 3),
       (2, 1),
       (1, 1),
       (1, 4),
       (2, 4),
       (2, 2),
       (2, 3);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user`
(
    `id`          int(11) NOT NULL,
    `username`    varchar(20)  NOT NULL,
    `passwd`      varchar(128) NOT NULL,
    `create_time` datetime DEFAULT NULL,
    `status`      char(1)      NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_user` */

insert into `t_user`(`id`, `username`, `passwd`, `create_time`, `status`)
values (2, 'tester', '243e29429b340192700677d48c09d992', '2021-04-16 11:57:53', '1'),
       (1, 'mrbird', '42ee25d1e43e9f57119a00d0a39e5250', '2021-04-16 11:57:53', '1'),
       (3, 'admin', '13bfb9844ba36ac930ba142d8c3db629', '2021-04-16 14:08:05', '1'),
       (4, 'guest', '075a7f7d81fc73e79f2b5bc371962913', NULL, '');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role`
(
    `user_id` int(10) DEFAULT NULL,
    `rid`     int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `t_user_role` */

insert into `t_user_role`(`user_id`, `rid`)
values (1, 1),
       (2, 2),
       (3, 1),
       (4, 2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
