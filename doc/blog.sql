/*
 Navicat Premium Data Transfer

 Source Server         : aliyun
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-bp1eum760d0td93gh9o.mysql.rds.aliyuncs.com:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 18/01/2022 14:16:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `parent` bigint(20) NOT NULL DEFAULT '0' COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='分类';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (100, 0, '前端开发', 100);
INSERT INTO `category` VALUES (101, 100, 'Vue', 101);
INSERT INTO `category` VALUES (102, 100, 'HTML & CSS', 102);
INSERT INTO `category` VALUES (200, 0, 'Java', 200);
INSERT INTO `category` VALUES (201, 200, '基础应用', 201);
INSERT INTO `category` VALUES (202, 200, '框架应用', 202);
INSERT INTO `category` VALUES (300, 0, 'Python', 300);
INSERT INTO `category` VALUES (301, 300, '基础应用', 301);
INSERT INTO `category` VALUES (302, 300, '进阶方向应用', 302);
INSERT INTO `category` VALUES (400, 0, '数据库', 400);
INSERT INTO `category` VALUES (401, 400, 'MySQL', 401);
INSERT INTO `category` VALUES (500, 0, '其它', 500);
INSERT INTO `category` VALUES (501, 500, '服务器', 501);
INSERT INTO `category` VALUES (502, 500, '开发工具', 502);
INSERT INTO `category` VALUES (503, 500, '热门服务端语言', 503);
INSERT INTO `category` VALUES (135039935056056320, 300, 'Django', 303);
INSERT INTO `category` VALUES (138254443333423104, 100, 'uniapp', 323);
COMMIT;

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content` (
  `id` bigint(20) NOT NULL COMMENT '文档id',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文档内容';

-- ----------------------------
-- Records of content
-- ----------------------------
BEGIN;
INSERT INTO `content` VALUES (4, '<p><span>文档2.1的内容</span></p>');
INSERT INTO `content` VALUES (6, '<p><span>一行文字???</span></p><p><span>uvhgvghvghv</span></p><p><span><br/></span></p><p><span>yvgvg</span></p>');
INSERT INTO `content` VALUES (135765199176208384, '<p><span>666</span></p>');
INSERT INTO `content` VALUES (135765248161484800, '<p><span>8998989898</span></p>');
INSERT INTO `content` VALUES (135774055012896768, '<blockquote><p>这是一段引用</p></blockquote><h1 id=\"gnh7x\">一级</h1><h2 id=\"hopxe\">二级</h2><h3 id=\"hufwh\">三级</h3><h4 id=\"w8nnf\">四级</h4><p><b><font size=\"6\">44444</font></b></p><ul><li><b><font size=\"6\">http</font></b><a href=\"https\" target=\"_blank\" style=\"font-size: 14px; font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, &quot;Helvetica Neue&quot;, Arial, &quot;Noto Sans&quot;, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;, &quot;Segoe UI Symbol&quot;, &quot;Noto Color Emoji&quot;;\">test</a></li></ul><ul class=\"w-e-todo\"><li><span contenteditable=\"false\"><input type=\"checkbox\"/></span>😎<br/></li></ul><pre><code class=\"C++\"><span class=\"hljs-meta\">#<span class=\"hljs-keyword\">include</span> <span class=\"hljs-string\">&lt;iostream&gt;</span></span>\n<span class=\"hljs-meta\">#<span class=\"hljs-keyword\">include</span> <span class=\"hljs-string\">&lt;cstring&gt;</span></span>\n<span class=\"hljs-meta\">#<span class=\"hljs-keyword\">include</span> <span class=\"hljs-string\">&lt;algorithm&gt;</span></span>\n<span class=\"hljs-meta\">#<span class=\"hljs-keyword\">include</span> <span class=\"hljs-string\">&lt;map&gt;</span></span>\n\n<span class=\"hljs-keyword\">using</span> <span class=\"hljs-keyword\">namespace</span> std;\n\nmap&lt;<span class=\"hljs-type\">int</span>, <span class=\"hljs-type\">int</span>&gt; mp;\n\n<span class=\"hljs-function\"><span class=\"hljs-type\">int</span> <span class=\"hljs-title\">main</span><span class=\"hljs-params\">()</span>\n</span>{\n    <span class=\"hljs-type\">int</span> n, x, u = <span class=\"hljs-number\">0</span>;\n    string op;\n\n    cin &gt;&gt; n;\n    <span class=\"hljs-keyword\">while</span> (n -- )\n    {\n        cin &gt;&gt; x &gt;&gt; op;\n        <span class=\"hljs-keyword\">if</span>(op == <span class=\"hljs-string\">\"R\"</span>)\n        {\n            mp[u]++;\n            u += x;\n            mp[u]--;\n        }\n        <span class=\"hljs-keyword\">else</span>\n        {\n            mp[u]--;\n            u -= x;\n            mp[u]++;\n        }\n    }\n\n    <span class=\"hljs-type\">int</span> sum = <span class=\"hljs-number\">0</span>, res = <span class=\"hljs-number\">0</span>;\n    <span class=\"hljs-keyword\">for</span>(<span class=\"hljs-keyword\">auto</span> &amp;t : mp)\n    {\n        <span class=\"hljs-comment\">// cout &lt;&lt; t.first &lt;&lt; \' \' &lt;&lt; t.second &lt;&lt; endl;</span>\n        <span class=\"hljs-keyword\">if</span>(sum &gt;= <span class=\"hljs-number\">2</span>)\n            res += t.first - u;\n        u = t.first;\n        sum += t.second;\n    }\n\n    cout &lt;&lt; res &lt;&lt; endl;\n    <span class=\"hljs-keyword\">return</span> <span class=\"hljs-number\">0</span>;\n}</code></pre>');
INSERT INTO `content` VALUES (136070208015175680, '<p><span>666</span></p>');
INSERT INTO `content` VALUES (136070362319425536, '<p><span><br></span></p>');
INSERT INTO `content` VALUES (136118556789182464, '<p><span><br></span></p><pre><code ><span>django 666</span></code></pre><p><span><br></span></p>');
INSERT INTO `content` VALUES (136118688079286272, '<p><span><br></span></p>');
INSERT INTO `content` VALUES (136166702911197184, '<p>66</p>');
INSERT INTO `content` VALUES (137409398304804864, '');
INSERT INTO `content` VALUES (137956404945358848, '<h1 id=\"b5lwg\">人生苦短，我用Python</h1>');
INSERT INTO `content` VALUES (137958976766742528, '');
INSERT INTO `content` VALUES (137958980998795264, '');
INSERT INTO `content` VALUES (137958983372771328, '');
COMMIT;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='测试';

-- ----------------------------
-- Records of demo
-- ----------------------------
BEGIN;
INSERT INTO `demo` VALUES (1, '测试');
COMMIT;

-- ----------------------------
-- Table structure for doc
-- ----------------------------
DROP TABLE IF EXISTS `doc`;
CREATE TABLE `doc` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `ebook_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '电子书id',
  `parent` bigint(20) NOT NULL DEFAULT '0' COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '顺序',
  `view_count` int(11) DEFAULT '0' COMMENT '阅读数',
  `vote_count` int(11) DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='文档';

-- ----------------------------
-- Records of doc
-- ----------------------------
BEGIN;
INSERT INTO `doc` VALUES (1, 1, 0, '文档1', 1, 14, 4);
INSERT INTO `doc` VALUES (2, 1, 1, '文档1.1', 1, 15, 3);
INSERT INTO `doc` VALUES (3, 1, 0, '文档2', 2, 8, 2);
INSERT INTO `doc` VALUES (4, 1, 3, '文档2.1', 1, 13, 3);
INSERT INTO `doc` VALUES (5, 1, 3, '文档2.2', 2, 11, 3);
INSERT INTO `doc` VALUES (6, 1, 5, '文档2.2.1', 1, 9, 1);
INSERT INTO `doc` VALUES (135762682463784960, 2, 0, 'test', 5, 130, 8);
INSERT INTO `doc` VALUES (135765199176208384, 2, 135762682463784960, 'test1', 3, 75, 5);
INSERT INTO `doc` VALUES (135765248161484800, 2, 135765199176208384, 'test2', 4, 91, 5);
INSERT INTO `doc` VALUES (135774055012896768, 2, 135765248161484800, '我的第一篇博客', 1, 70, 5);
INSERT INTO `doc` VALUES (136070208015175680, 1, 0, 'test', 1, 9, 1);
INSERT INTO `doc` VALUES (136070362319425536, 1, 0, 'id', 0, 51, 1);
INSERT INTO `doc` VALUES (136118556789182464, 135699248724447232, 0, 'django还是牛逼的', 1, 14, 1);
INSERT INTO `doc` VALUES (136118688079286272, 135699248724447232, 136118556789182464, '确实牛逼', 2, 9, 1);
INSERT INTO `doc` VALUES (136166702911197184, 1, 0, 'ttest', 3, 3, 2);
INSERT INTO `doc` VALUES (137409398304804864, 1, 0, 'testjks', 4, 3, 2);
INSERT INTO `doc` VALUES (137956404945358848, 3, 0, 'Python从入门到入坟', 1, 22, 3);
INSERT INTO `doc` VALUES (137958976766742528, 3, 0, '1', 1, 6, 3);
INSERT INTO `doc` VALUES (137958980998795264, 3, 0, '1', 1, 4, 1);
INSERT INTO `doc` VALUES (137958983372771328, 3, 0, '1', 1, 4, 1);
COMMIT;

-- ----------------------------
-- Table structure for ebook
-- ----------------------------
DROP TABLE IF EXISTS `ebook`;
CREATE TABLE `ebook` (
  `id` bigint(20) unsigned NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `category1_id` bigint(20) DEFAULT NULL COMMENT '分类1',
  `category2_id` bigint(20) DEFAULT NULL COMMENT '分类2',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `cover` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '封面',
  `doc_count` int(11) DEFAULT '0' COMMENT '文档数',
  `view_count` int(11) DEFAULT '0' COMMENT '阅读数',
  `vote_count` int(11) DEFAULT '0' COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='电子书';

-- ----------------------------
-- Records of ebook
-- ----------------------------
BEGIN;
INSERT INTO `ebook` VALUES (1, 'Spring Boot 入门教程', 200, 202, '零基础入门 Java 开发，企业级应用开发最佳首选框架', '/image/cover1.png', 10, 136, 22);
INSERT INTO `ebook` VALUES (2, 'Vue 入门教程', 100, 101, '零基础入门 Vue 开发，企业级应用开发最佳首选框架', '/image/cover2.png', 4, 366, 23);
INSERT INTO `ebook` VALUES (3, 'Python 入门教程', 300, 301, '零基础入门 Python 开发，企业级应用开发最佳首选框架', '/image/cover1.png', 4, 36, 8);
INSERT INTO `ebook` VALUES (4, 'Mysql 入门教程', 400, 401, '零基础入门 Mysql 开发，企业级应用开发最佳首选框架', '/image/cover2.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (5, 'Oracle 入门教程', 400, 401, '零基础入门 Oracle 开发，企业级应用开发最佳首选框架', '/image/cover1.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (11472626342756, '667', 500, 503, NULL, '/image/cover1.png', 0, 0, 0);
INSERT INTO `ebook` VALUES (135699248724447232, 'Django基础', 300, 135039935056056320, 'Python主流的Web框架之一', NULL, 2, 23, 2);
INSERT INTO `ebook` VALUES (135751931292422144, 'IDEA入门教程', 500, 502, NULL, NULL, 0, 0, 0);
INSERT INTO `ebook` VALUES (136390948337881088, 'test', 200, 202, NULL, NULL, 0, 0, 0);
INSERT INTO `ebook` VALUES (136392015318487040, 'tt', 300, 135039935056056320, NULL, NULL, 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for ebook_snapshot
-- ----------------------------
DROP TABLE IF EXISTS `ebook_snapshot`;
CREATE TABLE `ebook_snapshot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ebook_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '电子书id',
  `date` date NOT NULL COMMENT '快照日期',
  `view_count` int(11) NOT NULL DEFAULT '0' COMMENT '阅读数',
  `vote_count` int(11) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `view_increase` int(11) NOT NULL DEFAULT '0' COMMENT '阅读增长',
  `vote_increase` int(11) NOT NULL DEFAULT '0' COMMENT '点赞增长',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ebook_id_date_unique` (`ebook_id`,`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='电子书快照表';

-- ----------------------------
-- Records of ebook_snapshot
-- ----------------------------
BEGIN;
INSERT INTO `ebook_snapshot` VALUES (1, 1, '2022-01-16', 94, 17, 94, 17);
INSERT INTO `ebook_snapshot` VALUES (2, 2, '2022-01-16', 214, 18, 214, 18);
INSERT INTO `ebook_snapshot` VALUES (3, 3, '2022-01-16', 18, 4, 18, 4);
INSERT INTO `ebook_snapshot` VALUES (4, 4, '2022-01-16', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (5, 5, '2022-01-16', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (6, 11472626342756, '2022-01-16', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (7, 135699248724447232, '2022-01-16', 21, 2, 21, 2);
INSERT INTO `ebook_snapshot` VALUES (8, 135751931292422144, '2022-01-16', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (9, 136390948337881088, '2022-01-16', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (10, 136392015318487040, '2022-01-16', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (16, 1, '2022-01-17', 128, 22, 34, 5);
INSERT INTO `ebook_snapshot` VALUES (17, 2, '2022-01-17', 320, 19, 106, 1);
INSERT INTO `ebook_snapshot` VALUES (18, 3, '2022-01-17', 26, 6, 8, 2);
INSERT INTO `ebook_snapshot` VALUES (19, 4, '2022-01-17', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (20, 5, '2022-01-17', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (21, 11472626342756, '2022-01-17', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (22, 135699248724447232, '2022-01-17', 23, 2, 2, 0);
INSERT INTO `ebook_snapshot` VALUES (23, 135751931292422144, '2022-01-17', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (24, 136390948337881088, '2022-01-17', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (25, 136392015318487040, '2022-01-17', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (31, 1, '2022-01-18', 136, 22, 8, 0);
INSERT INTO `ebook_snapshot` VALUES (32, 2, '2022-01-18', 366, 23, 46, 4);
INSERT INTO `ebook_snapshot` VALUES (33, 3, '2022-01-18', 36, 8, 10, 2);
INSERT INTO `ebook_snapshot` VALUES (34, 4, '2022-01-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (35, 5, '2022-01-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (36, 11472626342756, '2022-01-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (37, 135699248724447232, '2022-01-18', 23, 2, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (38, 135751931292422144, '2022-01-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (39, 136390948337881088, '2022-01-18', 0, 0, 0, 0);
INSERT INTO `ebook_snapshot` VALUES (40, 136392015318487040, '2022-01-18', 0, 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='测试';

-- ----------------------------
-- Records of test
-- ----------------------------
BEGIN;
INSERT INTO `test` VALUES (1, '测试', 'password');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登陆名',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `login_name_unique` (`login_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='用户';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'test', '测试1', 'fb09b64e309e1c33e7cf8cfe99b07bd5');
INSERT INTO `user` VALUES (136313113409622016, 'jks', '请叫我杰克桑', 'ae94d222ee50df869a10a62ce9b677f9');
INSERT INTO `user` VALUES (136757021574828032, 'ziuch', 'ziuch', 'fb09b64e309e1c33e7cf8cfe99b07bd5');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
