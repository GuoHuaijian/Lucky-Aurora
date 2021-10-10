/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : aurora_blog

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 14/09/2021 16:17:36
*/

SET NAMES utf8mb4;
SET
    FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_about
-- ----------------------------
DROP TABLE IF EXISTS `blog_about`;
CREATE TABLE `blog_about`
(
    `about_id`    int(11)                                                       NOT NULL COMMENT '关于id',
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关于内容',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`about_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_about
-- ----------------------------

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article`
(
    `article_id`     int(11)                                               NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章标题',
    `description`    text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin        NULL COMMENT '文章描述',
    `author`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT NULL COMMENT '文章作者',
    `content`        longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin    NULL COMMENT '文章内容',
    `content_format` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin    NULL COMMENT 'html的content',
    `read_num`       int(11)                                               NULL     DEFAULT 0 COMMENT '阅读量',
    `comment_num`    int(11)                                               NULL     DEFAULT 0 COMMENT '评论量',
    `like_num`       int(11)                                               NULL     DEFAULT 0 COMMENT '点赞量',
    `cover_type`     int(11)                                               NULL     DEFAULT NULL COMMENT '文章展示类别,1:普通，2：大图片，3：无图片',
    `cover`          text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin        NULL COMMENT '封面',
    `is_recommend`   tinyint(1)                                            NOT NULL DEFAULT 0 COMMENT '是否推荐文章',
    `category_id`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT NULL COMMENT '分类类别存在多级分类，用逗号隔开',
    `publish`        tinyint(4)                                            NULL     DEFAULT 0 COMMENT '发布状态',
    `is_top`         tinyint(1)                                            NULL     DEFAULT 0 COMMENT '是否置顶',
    `create_time`    datetime(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time`    datetime(0)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`article_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '文章'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_article
-- ----------------------------
INSERT INTO `blog_article`
VALUES (1, '2', '3', '4', '5', NULL, 0, 0, 0, NULL, NULL, 0, NULL, 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article`
VALUES (2, '7', '8', '3', '5', NULL, 0, 0, 0, NULL, NULL, 0, NULL, 0, 0, '2021-09-07 12:50:35', '2021-09-07 12:50:35');

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tag_id`     int(11) NULL DEFAULT NULL COMMENT '标签Id',
    `article_id` int(11) NULL DEFAULT NULL COMMENT '文章Id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '标签文章多对多维护表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------

-- ----------------------------
-- Table structure for blog_carousel
-- ----------------------------
DROP TABLE IF EXISTS `blog_carousel`;
CREATE TABLE `blog_carousel`
(
    `carousel_id` int(11)                                                       NOT NULL AUTO_INCREMENT COMMENT '轮播图id',
    `img_url`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图url',
    `title`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图标题',
    `url`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '轮播图跳转地址',
    `display`     char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL DEFAULT NULL COMMENT '是否展示该轮播图，1展示，2不展示',
    `type`        tinyint(1)                                                    NULL DEFAULT NULL COMMENT '1外链，2文章',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '修改时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '轮播图'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_carousel
-- ----------------------------

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category`
(
    `category_id` int(11)                                                NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '名称',
    `rank`        int(11)                                                NULL DEFAULT NULL COMMENT '级别',
    `parent_id`   int(11)                                                NULL DEFAULT 0 COMMENT '父主键',
    `create_time` datetime(0)                                            NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                            NULL DEFAULT NULL COMMENT '修改时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`category_id`) USING BTREE,
    UNIQUE INDEX `operation_category_id_uindex` (`category_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_category
-- ----------------------------

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment`
(
    `id`          varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '评论主键id',
    `type`        tinyint(1)                                                    NOT NULL COMMENT '评论类型：0:留言 1:文章',
    `owner_id`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '被评论id，可以是单个文章id、项目、资源',
    `parent_id`   varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '0' COMMENT '评论id 第一级为0',
    `name`        varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '评论者名字',
    `avatar`      varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT '' COMMENT '评论者头像',
    `like_num`    int(11)                                                       NULL     DEFAULT 0 COMMENT '点赞的数量',
    `dislike_num` int(11)                                                       NULL     DEFAULT 0 COMMENT '踩的数量',
    `content`     varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL     DEFAULT NULL COMMENT '评论内容',
    `reply_id`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '回复的id',
    `reply_name`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '回复评论者名字',
    `create_time` timestamp(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `update_time` timestamp(0)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `owner_id` (`owner_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '评论表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
INSERT INTO `blog_comment`
VALUES ('1', 0, '9527', '0', '犀利的评论家', 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', 10, 20,
        '非常靠谱的程序员', NULL, NULL, '2021-09-14 14:27:00', '2021-09-14 14:27:03');
INSERT INTO `blog_comment`
VALUES ('2', 0, '9527', '1', '夕阳红', 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', 3, 4,
        '赞同，很靠谱，水平很高', '1', '犀利的评论家', '2021-09-14 14:29:13', '2021-09-14 14:29:37');
INSERT INTO `blog_comment`
VALUES ('3', 0, '9527', '1', '清晨一缕阳光',
        'http://imgsrc.baidu.com/imgad/pic/item/c2fdfc039245d688fcba1b80aec27d1ed21b245d.jpg', 6, 3, '大神一个', '2', '夕阳红',
        '2021-09-14 14:30:38', '2021-09-14 14:30:54');
INSERT INTO `blog_comment`
VALUES ('4', 0, '9527', '0', '毒蛇郭德纲', 'http://ww1.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2q2p8pj30v90uzmzz.jpg', 8, 2,
        '从没见过这么优秀的人', NULL, NULL, '2021-09-14 14:31:42', '2021-09-14 14:31:45');
INSERT INTO `blog_comment`
VALUES ('5', 0, '9527', '4', '夕阳红', 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', 6, 3,
        '赞同，很靠谱，水平很高', '4', '毒蛇郭德纲', '2021-09-14 14:32:57', '2021-09-14 14:33:03');

-- ----------------------------
-- Table structure for blog_notice
-- ----------------------------
DROP TABLE IF EXISTS `blog_notice`;
CREATE TABLE `blog_notice`
(
    `notice_id`      int(4)                                                         NOT NULL AUTO_INCREMENT COMMENT '公告ID',
    `notice_title`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '公告标题',
    `notice_type`    tinyint(1)                                                     NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容',
    `status`         tinyint(1)                                                     NULL DEFAULT 0 COMMENT '公告状态（0正常 1关闭）',
    `create_time`    datetime(0)                                                    NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime(0)                                                    NULL DEFAULT NULL COMMENT '更新时间',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '公告表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_notice
-- ----------------------------

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag`
(
    `tag_id`      int(11)                                                NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL DEFAULT NULL COMMENT '标签名字',
    `create_time` datetime(0)                                            NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                            NULL DEFAULT NULL COMMENT '修改时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = '标签'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of blog_tag
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `log_id`         bigint(20)                                                     NOT NULL AUTO_INCREMENT COMMENT '日志主键',
    `value`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT '' COMMENT '模块标题',
    `log_type`       int(2)                                                         NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT '' COMMENT '请求方式',
    `operator_type`  int(1)                                                         NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NULL DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
    `status`         int(1)                                                         NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime(0)                                                    NULL DEFAULT NULL COMMENT '操作时间',
    PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 197
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log`
VALUES (100, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', 'null', 1, '', NULL);
INSERT INTO `sys_log`
VALUES (101, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', '{\"msg\":\"OK\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_log`
VALUES (102, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', '{\"msg\":\"hello\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_log`
VALUES (103, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', '{\"msg\":\"hello\",\"code\":200}', 0, '', '2021-09-07 12:27:07');
INSERT INTO `sys_log`
VALUES (104, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', '{\"msg\":\"hello\",\"code\":200}', 0, '', '2021-09-07 12:28:19');
INSERT INTO `sys_log`
VALUES (105, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', '{\"msg\":\"hello\",\"code\":200}', 0, '', '2021-09-07 12:29:57');
INSERT INTO `sys_log`
VALUES (106, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', '{\"msg\":\"hello\",\"code\":200}', 0, '', '2021-09-07 12:32:01');
INSERT INTO `sys_log`
VALUES (107, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', '{\"msg\":\"hello\",\"code\":200}', 0, '', '2021-09-07 12:35:18');
INSERT INTO `sys_log`
VALUES (108, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'POST', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', '{\"msg\":\"hello\",\"code\":200}', 0, '', '2021-09-07 12:36:50');
INSERT INTO `sys_log`
VALUES (109, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', 'null', 1,
        '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Table \'aurora_blog.article\' doesn\'t exist\r\n### The error may exist in file [D:\\study\\aurora\\AuroraBlog\\aurora-admin\\target\\classes\\mapper\\ArticleMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: SELECT count(0) FROM article\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'aurora_blog.article\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'aurora_blog.article\' doesn\'t exist',
        '2021-09-07 12:51:37');
INSERT INTO `sys_log`
VALUES (110, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', 'null', 1, '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Table \'aurora_blog.article\' doesn\'t exist\r\n### The error may exist in file [D:\\study\\aurora\\AuroraBlog\\aurora-admin\\target\\classes\\mapper\\ArticleMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: SELECT count(0) FROM article\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'aurora_blog.article\' doesn\'t exist\n;
bad
SQL grammar []; nested
exception is java.sql.SQLSyntaxErrorException: Table
\'aurora_blog.article\' doesn
\'t exist', '2021-09-07 12:54:46');
INSERT INTO `sys_log`
VALUES (111, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', 'null', 1,
        '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Table \'aurora_blog.article\' doesn\'t exist\r\n### The error may exist in file [D:\\study\\aurora\\AuroraBlog\\aurora-admin\\target\\classes\\mapper\\ArticleMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: SELECT count(0) FROM article\r\n### Cause: java.sql.SQLSyntaxErrorException: Table \'aurora_blog.article\' doesn\'t exist\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Table \'aurora_blog.article\' doesn\'t exist',
        '2021-09-07 13:02:16');
INSERT INTO `sys_log`
VALUES (112, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '', 'null', 1, '\r\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'id\' in \'field list\'\r\n### The error may exist in file [D:\\study\\aurora\\AuroraBlog\\aurora-admin\\target\\classes\\mapper\\ArticleMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: select                            id,
        title, description, author, content, content_format, read_num, comment_num, like_num, cover_type, cover,
        create_time, update_time, recommend, category_id, publish,
        top from blog_article                    order by update_time desc  LIMIT ?\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'id\' in \'field list\'\n;
bad
SQL grammar []; nested
exception is java.sql.SQLSyntaxErrorException: Unknown column
\'id\' in
\'field list\'', '
2021-09-07 13:03:15
');
INSERT INTO `sys_log`
VALUES (113, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '
127.0.0.1
', '', '', 'null', 1, '
\r
\n### Error querying database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column
\'id\' in
\'field list\'
\r
\n### The error may exist in file [D:
\\study
\\aurora
\\AuroraBlog
\\aurora-admin
\\target
\\classes
\\mapper
\\ArticleMapper.xml]
\r
\n### The error may involve defaultParameterMap
\r
\n### The error occurred while setting parameters
\r
\n### SQL:
select id,
       title,
       description,
       author,
       content,
       content_format,
       read_num,
       comment_num,
       like_num,
       cover_type,
       cover,
       create_time,
       update_time,
       recommend,
       category_id,
       publish,
       top
from blog_article
order by update_time desc LIMIT ? \r \n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'id\' in \'field list\' \n;
bad
SQL grammar []; nested
exception is java.sql.SQLSyntaxErrorException: Unknown column
\'id\' in
\'field list\'', '
2021-09-07 13:05:19
');
INSERT INTO `sys_log`
VALUES (114, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '
127.0.0.1
', '', '', '{
\"msg\":\"OK\",\"code\":200,\"data\":{\"data\":[],\"total\":0}}', 0, '', '2021-09-07 13:33:45');
INSERT INTO `sys_log`
VALUES (115, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', '', '',
        '{\"msg\":\"OK\",\"code\":200,\"data\":{\"data\":[{\"articleId\":1,\"author\":\"4\",\"commentNum\":0,\"content\":\"5\",\"createTime\":1630990204000,\"description\":\"3\",\"isRecommend\":false,\"isTop\":false,\"likeNum\":0,\"publish\":0,\"readNum\":0,\"title\":\"2\",\"updateTime\":1630990204000}],\"total\":1}}',
        0, '', '2021-09-07 13:34:58');
INSERT INTO `sys_log`
VALUES (116, '文章列表', 0, 'com.aurora.admin.controller.ArticleController.listArticle()', 'GET', 1, 'admin', '',
        '/admin/article/list', '127.0.0.1', 'XX-XX-内网IP-', '',
        '{\"msg\":\"OK\",\"code\":200,\"data\":{\"data\":[{\"articleId\":1,\"author\":\"4\",\"commentNum\":0,\"content\":\"5\",\"createTime\":1630990204000,\"description\":\"3\",\"isRecommend\":false,\"isTop\":false,\"likeNum\":0,\"publish\":0,\"readNum\":0,\"title\":\"2\",\"updateTime\":1630990204000}],\"total\":1}}',
        0, '', '2021-09-07 14:43:26');
INSERT INTO `sys_log`
VALUES (117, '测试', 0, 'com.aurora.admin.controller.ArticleController.test()', 'POST', 1, 'admin', '',
        '/admin/article/test/2', '127.0.0.1', 'XX-XX-内网IP-', '2', 'null', 1, '/ by zero', '2021-09-07 14:54:15');
INSERT INTO `sys_log`
VALUES (118, '测试', 0, 'com.aurora.admin.controller.ArticleController.test()', 'POST', 1, 'admin', '',
        '/admin/article/test/2', '127.0.0.1', 'XX-XX-内网IP-', '2', 'null', 1, '/ by zero', '2021-09-07 14:56:00');
INSERT INTO `sys_log`
VALUES (119, '测试', 0, 'com.aurora.admin.controller.ArticleController.test()', 'POST', 1, 'admin', '',
        '/admin/article/test/2', '127.0.0.1', 'XX-XX-内网IP-', '2', 'null', 1, '/ by zero', '2021-09-07 14:56:14');
INSERT INTO `sys_log`
VALUES (120, '测试', 0, 'com.aurora.admin.controller.ArticleController.test()', 'POST', 1, 'admin', '',
        '/admin/article/test/2', '127.0.0.1', 'XX-XX-内网IP-', '2', 'null', 1, '/ by zero', '2021-09-07 14:59:47');
INSERT INTO `sys_log`
VALUES (121, '测试', 0, 'com.aurora.admin.controller.ArticleController.test()', 'POST', 1, 'admin', '',
        '/admin/article/test/2', '127.0.0.1', 'XX-XX-内网IP-', '2', '{\"msg\":\"/ by zero\",\"code\":500}', 1,
        '/ by zero', '2021-09-07 15:22:45');
INSERT INTO `sys_log`
VALUES (122, '', 0, '', '', 0, '', '', '', '', '', '', '', 0, '', '2021-09-09 10:43:31');
INSERT INTO `sys_log`
VALUES (123, '', 0, '', '', 0, '', '', '', '', '', '', '', 0, '', '2021-09-09 10:51:09');
INSERT INTO `sys_log`
VALUES (124, '', 0, '', '', 0, '', '', '', '', '', '', '', 0, '', '2021-09-09 10:52:57');
INSERT INTO `sys_log`
VALUES (125, '', 0, '', '', 0, '', '', '', '', '', '', '', 0, '', '2021-09-09 10:53:43');
INSERT INTO `sys_log`
VALUES (126, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 10:58:21');
INSERT INTO `sys_log`
VALUES (127, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:00:29');
INSERT INTO `sys_log`
VALUES (128, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:04:13');
INSERT INTO `sys_log`
VALUES (129, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:04:52');
INSERT INTO `sys_log`
VALUES (130, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:05:36');
INSERT INTO `sys_log`
VALUES (131, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:08:29');
INSERT INTO `sys_log`
VALUES (132, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:12:00');
INSERT INTO `sys_log`
VALUES (133, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:14:54');
INSERT INTO `sys_log`
VALUES (134, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:15:50');
INSERT INTO `sys_log`
VALUES (135, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:19:54');
INSERT INTO `sys_log`
VALUES (136, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:20:10');
INSERT INTO `sys_log`
VALUES (137, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:22:28');
INSERT INTO `sys_log`
VALUES (138, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:26:59');
INSERT INTO `sys_log`
VALUES (139, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:36:53');
INSERT INTO `sys_log`
VALUES (140, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:40:01');
INSERT INTO `sys_log`
VALUES (141, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:40:11');
INSERT INTO `sys_log`
VALUES (142, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:44:27');
INSERT INTO `sys_log`
VALUES (143, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:45:38');
INSERT INTO `sys_log`
VALUES (144, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 11:49:37');
INSERT INTO `sys_log`
VALUES (145, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', '获取IP地址异常：UnknownHostException: ip.aliyun.com', '', '\"成功\"', 0, '',
        '2021-09-09 12:37:23');
INSERT INTO `sys_log`
VALUES (146, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', '获取IP地址异常：UnknownHostException: ip.aliyun.com', '', '\"成功\"', 0, '',
        '2021-09-09 12:39:04');
INSERT INTO `sys_log`
VALUES (147, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', '获取IP地址异常：UnknownHostException: ip.aliyun.com', '', '\"成功\"', 0, '',
        '2021-09-09 12:41:11');
INSERT INTO `sys_log`
VALUES (148, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', '获取IP地址异常：UnknownHostException: ip.aliyun.com', '', '\"成功\"', 0, '',
        '2021-09-09 12:43:51');
INSERT INTO `sys_log`
VALUES (149, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', '获取IP地址异常：UnknownHostException: ip.aliyun.com', '', '\"成功\"', 0, '',
        '2021-09-09 12:48:32');
INSERT INTO `sys_log`
VALUES (150, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', '获取IP地址异常：UnknownHostException: ip.aliyun.com', '', '\"成功\"', 0, '',
        '2021-09-09 12:50:00');
INSERT INTO `sys_log`
VALUES (151, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', '获取IP地址异常：UnknownHostException: ip.aliyun.com', '', '\"成功\"', 0, '',
        '2021-09-09 12:53:25');
INSERT INTO `sys_log`
VALUES (152, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 12:59:58');
INSERT INTO `sys_log`
VALUES (153, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 13:04:22');
INSERT INTO `sys_log`
VALUES (154, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:14:15');
INSERT INTO `sys_log`
VALUES (155, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:31:46');
INSERT INTO `sys_log`
VALUES (156, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:35:10');
INSERT INTO `sys_log`
VALUES (157, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:40:10');
INSERT INTO `sys_log`
VALUES (158, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:40:10');
INSERT INTO `sys_log`
VALUES (159, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:40:31');
INSERT INTO `sys_log`
VALUES (160, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:41:56');
INSERT INTO `sys_log`
VALUES (161, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:41:56');
INSERT INTO `sys_log`
VALUES (162, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:41:56');
INSERT INTO `sys_log`
VALUES (163, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 15:52:09');
INSERT INTO `sys_log`
VALUES (164, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:03:10');
INSERT INTO `sys_log`
VALUES (165, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:03:39');
INSERT INTO `sys_log`
VALUES (166, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:10:36');
INSERT INTO `sys_log`
VALUES (167, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:13:18');
INSERT INTO `sys_log`
VALUES (168, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:07');
INSERT INTO `sys_log`
VALUES (169, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:11');
INSERT INTO `sys_log`
VALUES (170, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:11');
INSERT INTO `sys_log`
VALUES (171, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:11');
INSERT INTO `sys_log`
VALUES (172, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:11');
INSERT INTO `sys_log`
VALUES (173, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:11');
INSERT INTO `sys_log`
VALUES (174, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:12');
INSERT INTO `sys_log`
VALUES (175, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:12');
INSERT INTO `sys_log`
VALUES (176, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-09 16:15:47');
INSERT INTO `sys_log`
VALUES (177, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:36');
INSERT INTO `sys_log`
VALUES (178, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:37');
INSERT INTO `sys_log`
VALUES (179, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:38');
INSERT INTO `sys_log`
VALUES (180, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:38');
INSERT INTO `sys_log`
VALUES (181, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:38');
INSERT INTO `sys_log`
VALUES (182, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:38');
INSERT INTO `sys_log`
VALUES (183, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:38');
INSERT INTO `sys_log`
VALUES (184, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:38');
INSERT INTO `sys_log`
VALUES (185, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:00:38');
INSERT INTO `sys_log`
VALUES (186, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 11:02:52');
INSERT INTO `sys_log`
VALUES (187, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:49');
INSERT INTO `sys_log`
VALUES (188, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');
INSERT INTO `sys_log`
VALUES (189, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');
INSERT INTO `sys_log`
VALUES (190, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');
INSERT INTO `sys_log`
VALUES (191, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');
INSERT INTO `sys_log`
VALUES (192, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');
INSERT INTO `sys_log`
VALUES (193, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');
INSERT INTO `sys_log`
VALUES (194, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');
INSERT INTO `sys_log`
VALUES (195, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');
INSERT INTO `sys_log`
VALUES (196, '添加文章', 0, 'com.aurora.admin.controller.ArticleController.add()', 'POST', 1, 'admin', '',
        '/admin/article/test', '127.0.0.1', 'XX-XX-内网IP-', '', '\"成功\"', 0, '', '2021-09-10 13:01:53');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `menu_id`     bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '菜单名称',
    `parent_id`   bigint(20)                                                    NULL DEFAULT 0 COMMENT '父菜单ID',
    `order_num`   int(4)                                                        NULL DEFAULT 0 COMMENT '显示顺序',
    `path`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
    `component`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
    `is_frame`    int(1)                                                        NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
    `menu_type`   char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible`     char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci      NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `perms`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
    `icon`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
    PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1060
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu`
VALUES (1, '系统管理', 0, 1, 'system', NULL, 1, 'M', '0', '', 'system', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO `sys_menu`
VALUES (2, '系统监控', 0, 2, 'monitor', NULL, 1, 'M', '0', '', 'monitor', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO `sys_menu`
VALUES (3, '博客管理', 0, 3, 'tool', NULL, 1, 'M', '0', '', 'tool', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO `sys_menu`
VALUES (4, '日志管理', 0, 4, 'log', 'system/log/index', 1, 'M', '0', '', 'log', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO `sys_menu`
VALUES (5, '若依官网', 0, 5, 'http://ruoyi.vip', NULL, 0, 'M', '0', '', 'guide', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '若依官网地址');
INSERT INTO `sys_menu`
VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 'C', '0', 'system:user:list', 'user', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO `sys_menu`
VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 'C', '0', 'system:role:list', 'peoples', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO `sys_menu`
VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', 1, 'C', '0', 'system:config:list', 'edit', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO `sys_menu`
VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', 1, 'C', '0', 'system:notice:list', 'message', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO `sys_menu`
VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', 1, 'C', '0', 'monitor:online:list', 'online', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO `sys_menu`
VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', 1, 'C', '0', 'monitor:job:list', 'job', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '定时任务菜单');
INSERT INTO `sys_menu`
VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', 1, 'C', '0', 'monitor:druid:list', 'druid', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO `sys_menu`
VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', 1, 'C', '0', 'monitor:server:list', 'server', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO `sys_menu`
VALUES (113, '文章管理', 3, 1, 'build', 'tool/build/index', 1, 'C', '0', 'tool:build:list', 'build', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO `sys_menu`
VALUES (114, '分类管理', 3, 2, 'gen', 'tool/gen/index', 1, 'C', '0', 'tool:gen:list', 'code', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '代码生成菜单');
INSERT INTO `sys_menu`
VALUES (115, '系统接口', 1, 5, 'swagger', 'tool/swagger/index', 1, 'C', '0', 'tool:swagger:list', 'swagger', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO `sys_menu`
VALUES (500, '操作日志', 4, 1, 'operlog', 'monitor/operlog/index', 1, 'C', '0', 'monitor:operlog:list', 'form', 'admin',
        '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO `sys_menu`
VALUES (501, '登录日志', 4, 2, 'logininfor', 'monitor/logininfor/index', 1, 'C', '0', 'monitor:logininfor:list',
        'logininfor', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO `sys_menu`
VALUES (1001, '用户查询', 100, 1, '', '', 1, 'F', '0', 'system:user:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1002, '用户新增', 100, 2, '', '', 1, 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1003, '用户修改', 100, 3, '', '', 1, 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1004, '用户删除', 100, 4, '', '', 1, 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1005, '用户导出', 100, 5, '', '', 1, 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1006, '用户导入', 100, 6, '', '', 1, 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1007, '重置密码', 100, 7, '', '', 1, 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1008, '角色查询', 101, 1, '', '', 1, 'F', '0', 'system:role:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1009, '角色新增', 101, 2, '', '', 1, 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1010, '角色修改', 101, 3, '', '', 1, 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1011, '角色删除', 101, 4, '', '', 1, 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1012, '角色导出', 101, 5, '', '', 1, 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1013, '菜单查询', 102, 1, '', '', 1, 'F', '0', 'system:menu:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1014, '菜单新增', 102, 2, '', '', 1, 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1015, '菜单修改', 102, 3, '', '', 1, 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1016, '菜单删除', 102, 4, '', '', 1, 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1017, '部门查询', 103, 1, '', '', 1, 'F', '0', 'system:dept:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1018, '部门新增', 103, 2, '', '', 1, 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1019, '部门修改', 103, 3, '', '', 1, 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1020, '部门删除', 103, 4, '', '', 1, 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1021, '岗位查询', 104, 1, '', '', 1, 'F', '0', 'system:post:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1022, '岗位新增', 104, 2, '', '', 1, 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1023, '岗位修改', 104, 3, '', '', 1, 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1024, '岗位删除', 104, 4, '', '', 1, 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1025, '岗位导出', 104, 5, '', '', 1, 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1026, '字典查询', 105, 1, '#', '', 1, 'F', '0', 'system:dict:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1027, '字典新增', 105, 2, '#', '', 1, 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1028, '字典修改', 105, 3, '#', '', 1, 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1029, '字典删除', 105, 4, '#', '', 1, 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1030, '字典导出', 105, 5, '#', '', 1, 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1031, '参数查询', 106, 1, '#', '', 1, 'F', '0', 'system:config:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1032, '参数新增', 106, 2, '#', '', 1, 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1033, '参数修改', 106, 3, '#', '', 1, 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1034, '参数删除', 106, 4, '#', '', 1, 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1035, '参数导出', 106, 5, '#', '', 1, 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1036, '公告查询', 107, 1, '#', '', 1, 'F', '0', 'system:notice:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1037, '公告新增', 107, 2, '#', '', 1, 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1038, '公告修改', 107, 3, '#', '', 1, 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1039, '公告删除', 107, 4, '#', '', 1, 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1040, '操作查询', 500, 1, '#', '', 1, 'F', '0', 'monitor:operlog:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1041, '操作删除', 500, 2, '#', '', 1, 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1042, '日志导出', 500, 4, '#', '', 1, 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1043, '登录查询', 501, 1, '#', '', 1, 'F', '0', 'monitor:logininfor:query', '#', 'admin', '2018-03-16 11:33:00',
        'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1044, '登录删除', 501, 2, '#', '', 1, 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00',
        'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1045, '日志导出', 501, 3, '#', '', 1, 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00',
        'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1046, '在线查询', 109, 1, '#', '', 1, 'F', '0', 'monitor:online:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1047, '批量强退', 109, 2, '#', '', 1, 'F', '0', 'monitor:online:batchLogout', '#', 'admin', '2018-03-16 11:33:00',
        'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1048, '单条强退', 109, 3, '#', '', 1, 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00',
        'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1049, '任务查询', 110, 1, '#', '', 1, 'F', '0', 'monitor:job:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1050, '任务新增', 110, 2, '#', '', 1, 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1051, '任务修改', 110, 3, '#', '', 1, 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1052, '任务删除', 110, 4, '#', '', 1, 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1053, '状态修改', 110, 5, '#', '', 1, 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00',
        'ry', '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1054, '任务导出', 110, 7, '#', '', 1, 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1055, '生成查询', 114, 1, '#', '', 1, 'F', '0', 'tool:gen:query', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1056, '生成修改', 114, 2, '#', '', 1, 'F', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1057, '生成删除', 114, 3, '#', '', 1, 'F', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1058, '预览代码', 114, 4, '#', '', 1, 'F', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');
INSERT INTO `sys_menu`
VALUES (1059, '生成代码', 114, 5, '#', '', 1, 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry',
        '2018-03-16 11:33:00', '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `role_id`     bigint(20)                                                    NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名字',
    `role_code`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色code值',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '修改时间',
    `remark`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role`
VALUES (1, '管理员', 'admin', '2021-01-30 18:09:48', '2021-01-30 18:09:51', '管理员');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `role_id` bigint(20) NOT NULL COMMENT '角色id',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu`
VALUES (1, 1, 100);
INSERT INTO `sys_role_menu`
VALUES (2, 1, 101);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `user_id`      bigint(255)                                                   NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `user_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `nick_name`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
    `email`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
    `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL DEFAULT NULL COMMENT '用户电话',
    `avatar`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
    `password`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
    `status`       tinyint(1)                                                    NULL DEFAULT NULL COMMENT '用户状态',
    `create_time`  datetime(6)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime(6)                                                   NULL DEFAULT NULL COMMENT '修改时间',
    `remark`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, 'admin', 'jack', NULL, NULL, NULL, '$2a$10$ApYW1WoU6A4LYCq3vr15wu5SczBHyviCWe.DlZOUpJnT8BYI8JgSa', NULL,
        '2021-01-30 18:14:56.000000', '2021-01-30 18:14:59.000000', NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL COMMENT '用户id',
    `role_id` bigint(20) NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role`
VALUES (1, 1, 1);

SET
    FOREIGN_KEY_CHECKS = 1;
