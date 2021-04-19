-- ----------------------------
-- Table structure for T_PERMISSION
-- ----------------------------
CREATE TABLE T_PERMISSION
(
    id   INT(10) NOT NULL,
    url  VARCHAR(256) NULL,
    NAME VARCHAR(64) NULL
);


-- ----------------------------
-- Records of T_PERMISSION
-- ----------------------------
INSERT INTO t_permission
VALUES ('1', '/ user ', 'user:user');
INSERT INTO t_permission
VALUES ('2', '/ user / add ', 'user:add');
INSERT INTO t_permission
VALUES ('3', '/ user / delete ', 'user:delete');

-- ----------------------------
-- Table structure for T_ROLE
-- ----------------------------
CREATE TABLE t_role
(
    id   INT NOT NULL,
    NAME VARCHAR(32) NULL,
    memo VARCHAR(32) NULL
);


-- ----------------------------
-- Records of T_ROLE
-- ----------------------------
INSERT INTO t_role
VALUES ('1', 'admin', '超级管理员');
INSERT INTO t_role
VALUES ('2', 'test', '测`t_permission`试账户');

-- ----------------------------
-- Table structure for T_ROLE_PERMISSION
-- ----------------------------
CREATE TABLE t_role_permission
(
    rid INT(10) NULL,
    pid INT(10) NULL
);


-- ----------------------------
-- Records of T_ROLE_PERMISSION
-- ----------------------------
INSERT INTO t_role_permission
VALUES ('1', '2');
INSERT INTO t_role_permission
VALUES ('1', '3');
INSERT INTO t_role_permission
VALUES ('2', '1');
INSERT INTO t_role_permission
VALUES ('1', '1');

-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
CREATE TABLE t_user
(
    `t_user``t_role_permission``t_role_permission`
                id INT NOT NULL,
    username    VARCHAR(20)  NOT NULL,
    passwd      VARCHAR(128) NOT NULL,
    create_time DATETIME NULL,
    STATUS      CHAR(1)      NOT NULL
);

-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO t_user
VALUES ('2', 'tester', '243e29429b340192700677d48c09d992', NOW(), '1');
INSERT INTO t_user
VALUES ('1', 'mrbird', '42ee25d1e43e9f57119a00d0a39e5250', NOW(), '1');

-- ----------------------------
-- Table structure for T_USER_ROLE
-- ----------------------------
CREATE TABLE t_user_role
(
    user_id INT(10) NULL,
    rid     INT(10) NULL
);


-- ----------------------------
-- Records of T_USER_ROLE
-- ----------------------------
INSERT INTO t_user_role
VALUES ('1', '1');
INSERT INTO t_user_role
VALUES ('2', '2');
