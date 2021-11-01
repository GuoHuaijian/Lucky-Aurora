/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : aurora_blog

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 01/11/2021 22:35:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_about
-- ----------------------------
DROP TABLE IF EXISTS `blog_about`;
CREATE TABLE `blog_about` (
  `about_id` int NOT NULL COMMENT '关于id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关于内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`about_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_about
-- ----------------------------
BEGIN;
INSERT INTO `blog_about` VALUES (1, '关于我\n氧化钡，英文名Bobbi，是一名努力成长中的Java爱好者  \n以下是微信，欢迎互相交流\n![名片.jpg](http://oss.dblearn.cn/dbblog/20190303/63ba034bf1e24e918aa53cccb3fb66dc.jpg)\n# 关于本站\n本站前端Vue，后台是Java\n项目还会持续更新，欢迎大家star,谢谢！\n[>>点击进入](https://github.com/llldddbbb/dbblog)\n', '2021-10-24 13:03:39', NULL);
COMMIT;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `article_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '文章标题',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '文章描述',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '文章作者',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '文章内容',
  `content_format` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT 'html的content',
  `read_num` int DEFAULT '0' COMMENT '阅读量',
  `comment_num` int DEFAULT '0' COMMENT '评论量',
  `like_num` int DEFAULT '0' COMMENT '点赞量',
  `cover_type` int DEFAULT NULL COMMENT '文章展示类别,1:普通，2：大图片，3：无图片',
  `cover_url` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '封面',
  `is_recommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否推荐文章',
  `category_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类类别存在多级分类，用逗号隔开',
  `publish` tinyint DEFAULT '0' COMMENT '发布状态',
  `is_top` tinyint(1) DEFAULT '0' COMMENT '是否置顶',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='文章';

-- ----------------------------
-- Records of blog_article
-- ----------------------------
BEGIN;
INSERT INTO `blog_article` VALUES (1, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n> public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', '<h3 id=\"java基础\">Java基础</h3>\n<h4 id=\"1-两个对象的-hashcode相同，则-equals也一定为-true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\">1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？</h4>\n<p>不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:</p>\n<pre><code class=\"language-java\">String str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n</code></pre>\n<p>执行的结果： </p>\n<p>str1：1179395 | str2：1179395</p>\n<p>false</p>\n<p>很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。</p>\n<p>此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：</p>\n<blockquote>\n<p>public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。</p>\n</blockquote>\n<p>&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。</p>\n<p><strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong></p>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 1, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (2, '用最简单的话告诉你什么是ElasticSearch', 'Elasticsearch 是一个分布式可扩展的实时搜索和分析引擎,一个建立在全文搜索引擎 Apache Lucene(TM) 基础上的搜索引擎.当然 Elasticsearch 并不仅仅是 Lucene 那么简单，下面就介绍ElasticSearch为什么是分布式的，可扩展，高性能，高可用。\n\n\n作者：fallinjava\n链接：https://juejin.im/post/5c790b4b51882545194f84f0\n来源：掘金\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。', 'admin', '> 转载:https://juejin.im/post/5c790b4b51882545194f84f0\n\n## 1.什么是搜索\n在我们想知道一些信息时，就会使用一些搜索引擎来获取我们想要的数据，比如搜索我们喜欢的一款游戏，或者喜欢的一本书等等，这就是提到搜索的的第一印象，说直白点就是在任何场景下找寻你想要知道的信息，这就是搜索。\n- 现在的搜索也称为垂直搜索 垂直搜索引针对某一个行业的专业搜索引擎，比如说电商网站，新闻网站，各种app内部等等，他们都是搜索引擎的细分和延伸，在抽取出需要的数据进行处理后再以某种形式返回给用户。\n\n## 2.如果用数据库来做搜索会怎么样\n例如我们这里有一张商品表，现在我们要搜索\"衣服\"这个关键字，，执行了 select * from products where product_name like %衣服%，（假设这里没有其他任何提升效率的设置）来进行搜索，或者进行其他字段的匹配，可以分析一下这个方式的缺点。\n\n1. 比如说，每条记录的指定字段的数据会很长，比如说“商品介绍”这个字段，可能会有几千或者几万个字符，那么搜索的时候就会去这些字符里面进行匹配是否包含要搜索的关键词。\n2. 这种方式只能搜索到完全包含“衣服”这个两个字符的记录，但是可能会有一些特殊的情况，某几条记录里面的“衣服”关键词并不是连续的，可能衣服中间会插入某些字符，这个时候就搜索不出来这些记录了，但是这个商品又是我们希望搜索出来的，这个时候这种方式的弊端就十分明显了。\n\n总的来说用数据库来实现搜索是不太靠谱的，性能会很差。\n\n## 3.什么是全文检索\n首先需要了解什么是倒排索引？我们这里先上一幅图，里面有4条记录。\n![1.jpg](http://oss.dblearn.cn/dbblog/20190305/fb6809d46e7744e78c5a686bbd096506.jpg)\n现在将这4条记录的内容进行拆分成一些词条，这个过程叫做分词\n![2.jpg](http://oss.dblearn.cn/dbblog/20190305/0271f31d280f49b68ae9e942f286a3e4.jpg)\n现在我们得到了这4条记录拆分出来词语，然后将这写词语放到一个列表中，并记录他们的ID，这个分析出来的就是 倒排索引\n![3.jpg](http://oss.dblearn.cn/dbblog/20190305/567fa7ca43d34eab886a5b8dc4c83b4f.jpg)\n\n现在我们输入 生化电影 这个关键词，这个时候搜素引擎将我们输入的内容分词为 生化 和 电影 这两个关键词，然后使用这个两个关键词去倒排索引里面匹配，发现包含 生化 这个关键词的记录有ID为 1,2,3,4这四条记录，包含 电影 这个关键词的有ID为1这条记录，由于ID为1这条记录已经被录入了，所以就被排除在外了， 这时候我们就得到了想要ID为1234这4条记录，同理，如果我们只输入 电影 这个关键词，那么符合条件的只有ID为1这条记录了。  \n\n全文检索就是从拆分词语，存入倒排索引，然后分析用户输入的内容，在倒排索引里面进行匹配，这个过程就是全文检索。\n\n## 4.什么是ElasticSearch\n&emsp;&emsp;首先需要知道什么是Lucence，Lucence它就是一个Java的jar包，里面实现了倒排索引的算法和其他的全文检索相关的东西，ElasticSearch就是对Lunence进行了封装，为什么有Lucence了还要ElasticSeaearch来干什么呢？  \n\n&emsp;当数据量很大的时候，比如有1PB的数据，这个时候数据放在同一台机器上基本就不行了，那么把数据分开来放在多台机器上呢？那就变成分布式了，这个时候数据前端获取数据的时候到底去那一台机器上面去获取数据呢？这个时候就很麻烦了，如果某一台机器宕机了，那么这个机器上的数据就获取不到了，这也就无法保证高可用性了，还有数据存储的时候怎么到底存入那台机器等等，这些都需要人为的处理和维护。这个时候ElasticSearch就应运而生了，它就将Lucence这些弊端给完全解决了。  \n\n举例一些优点：\n\n1. 高性能，自动维护数据分布到多个节点进行索引的建立，还有搜索请求分布到多个节点的执行。\n2. 高可用，自动维护数据的冗余副本，保证说，一些机器宕机了，不会造成数据的丢失。\n3. 封装了更多的高级功能，以给我们提供更多的高级支持，让我们快速的开发应用，开发更加复杂的应用，复杂的搜索功能，聚合分析的功能，基于地理位置的搜索(比如周围一公里内有几家咖啡厅)等等。\n4. 动态扩容，当我们数据量急剧提升的时候，我们只需要增加机器就行了，比如两台机器存放1.2T数据，那么没台机器存放就是600G，但是如果600G对于服务器的压力太大了，这个时候就需要增加第三台机器，让他们每人负责400G的数据，这个过程不需要人为的去分配，只需要将汲取加入集群中就自动完成。', '<blockquote>\n<p>转载:<a href=\"https://juejin.im/post/5c790b4b51882545194f84f0\">https://juejin.im/post/5c790b4b51882545194f84f0</a></p>\n</blockquote>\n<h2 id=\"1什么是搜索\">1.什么是搜索</h2>\n<p>在我们想知道一些信息时，就会使用一些搜索引擎来获取我们想要的数据，比如搜索我们喜欢的一款游戏，或者喜欢的一本书等等，这就是提到搜索的的第一印象，说直白点就是在任何场景下找寻你想要知道的信息，这就是搜索。</p>\n<ul>\n<li>现在的搜索也称为垂直搜索 垂直搜索引针对某一个行业的专业搜索引擎，比如说电商网站，新闻网站，各种app内部等等，他们都是搜索引擎的细分和延伸，在抽取出需要的数据进行处理后再以某种形式返回给用户。</li>\n</ul>\n<h2 id=\"2如果用数据库来做搜索会怎么样\">2.如果用数据库来做搜索会怎么样</h2>\n<p>例如我们这里有一张商品表，现在我们要搜索&quot;衣服&quot;这个关键字，，执行了 select * from products where product_name like %衣服%，（假设这里没有其他任何提升效率的设置）来进行搜索，或者进行其他字段的匹配，可以分析一下这个方式的缺点。</p>\n<ol>\n<li>比如说，每条记录的指定字段的数据会很长，比如说“商品介绍”这个字段，可能会有几千或者几万个字符，那么搜索的时候就会去这些字符里面进行匹配是否包含要搜索的关键词。</li>\n<li>这种方式只能搜索到完全包含“衣服”这个两个字符的记录，但是可能会有一些特殊的情况，某几条记录里面的“衣服”关键词并不是连续的，可能衣服中间会插入某些字符，这个时候就搜索不出来这些记录了，但是这个商品又是我们希望搜索出来的，这个时候这种方式的弊端就十分明显了。</li>\n</ol>\n<p>总的来说用数据库来实现搜索是不太靠谱的，性能会很差。</p>\n<h2 id=\"3什么是全文检索\">3.什么是全文检索</h2>\n<p>首先需要了解什么是倒排索引？我们这里先上一幅图，里面有4条记录。\n<img src=\"http://oss.dblearn.cn/dbblog/20190305/fb6809d46e7744e78c5a686bbd096506.jpg\" alt=\"1.jpg\">\n现在将这4条记录的内容进行拆分成一些词条，这个过程叫做分词\n<img src=\"http://oss.dblearn.cn/dbblog/20190305/0271f31d280f49b68ae9e942f286a3e4.jpg\" alt=\"2.jpg\">\n现在我们得到了这4条记录拆分出来词语，然后将这写词语放到一个列表中，并记录他们的ID，这个分析出来的就是 倒排索引\n<img src=\"http://oss.dblearn.cn/dbblog/20190305/567fa7ca43d34eab886a5b8dc4c83b4f.jpg\" alt=\"3.jpg\"></p>\n<p>现在我们输入 生化电影 这个关键词，这个时候搜素引擎将我们输入的内容分词为 生化 和 电影 这两个关键词，然后使用这个两个关键词去倒排索引里面匹配，发现包含 生化 这个关键词的记录有ID为 1,2,3,4这四条记录，包含 电影 这个关键词的有ID为1这条记录，由于ID为1这条记录已经被录入了，所以就被排除在外了， 这时候我们就得到了想要ID为1234这4条记录，同理，如果我们只输入 电影 这个关键词，那么符合条件的只有ID为1这条记录了。  </p>\n<p>全文检索就是从拆分词语，存入倒排索引，然后分析用户输入的内容，在倒排索引里面进行匹配，这个过程就是全文检索。</p>\n<h2 id=\"4什么是elasticsearch\">4.什么是ElasticSearch</h2>\n<p>&emsp;&emsp;首先需要知道什么是Lucence，Lucence它就是一个Java的jar包，里面实现了倒排索引的算法和其他的全文检索相关的东西，ElasticSearch就是对Lunence进行了封装，为什么有Lucence了还要ElasticSeaearch来干什么呢？  </p>\n<p>&emsp;当数据量很大的时候，比如有1PB的数据，这个时候数据放在同一台机器上基本就不行了，那么把数据分开来放在多台机器上呢？那就变成分布式了，这个时候数据前端获取数据的时候到底去那一台机器上面去获取数据呢？这个时候就很麻烦了，如果某一台机器宕机了，那么这个机器上的数据就获取不到了，这也就无法保证高可用性了，还有数据存储的时候怎么到底存入那台机器等等，这些都需要人为的处理和维护。这个时候ElasticSearch就应运而生了，它就将Lucence这些弊端给完全解决了。  </p>\n<p>举例一些优点：</p>\n<ol>\n<li>高性能，自动维护数据分布到多个节点进行索引的建立，还有搜索请求分布到多个节点的执行。</li>\n<li>高可用，自动维护数据的冗余副本，保证说，一些机器宕机了，不会造成数据的丢失。</li>\n<li>封装了更多的高级功能，以给我们提供更多的高级支持，让我们快速的开发应用，开发更加复杂的应用，复杂的搜索功能，聚合分析的功能，基于地理位置的搜索(比如周围一公里内有几家咖啡厅)等等。</li>\n<li>动态扩容，当我们数据量急剧提升的时候，我们只需要增加机器就行了，比如两台机器存放1.2T数据，那么没台机器存放就是600G，但是如果600G对于服务器的压力太大了，这个时候就需要增加第三台机器，让他们每人负责400G的数据，这个过程不需要人为的去分配，只需要将汲取加入集群中就自动完成。</li>\n</ol>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/039da9c9-b2c4-4ad4-b0a8-05b900d1d662.jpeg', 0, '1', 0, 0, '2021-10-24 10:28:47', '2021-10-24 10:28:47');
INSERT INTO `blog_article` VALUES (3, '性能分析利器《Arthas》总结', 'Arthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。', 'admin', '## 性能分析利器《Arthas》总结\nArthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：\n\n- 当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。\n- 当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。\n- 当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。\n- 当你发现系统突然报出某个类的Exception，却无法找到这个类是从哪个Jar包加载的。\n- 当你发现有时候排查一个问题需要上游重复调用这个方法，于是你只能想尽办法利用postman等工具重现这个请求。\n\n\n下面我将会介绍一下Arthas的一些常用的命令和用法，看看是如何解决我们实际中的问题的，至于安装教程可以参考Arthas的github。\n> https://github.com/alibaba/arthas\n\n## 常用命令\ndashboard 当前系统的实时数据面板  \n\njvm 查看当前JVM信息  \n\nthread 可直接查看线程的cpu占用比  \n\nsc sm 快速搜索类和方法信息  \n\ngetstatic 查看类静态变量  \n\njad 反编译class文件  \n\nmc 编译.java文件生成.class  \n\nredefine 加载外部class文件到应用程序中  \n\nmonitor 监测方法调用次数、成功次数、失败次数、平均RT等  \n\nwatch tt 观测方法执行的前、后、结束、异常、耗时过大时，入参（入参属性深度可调）、返回值、异常，支持实时监测每次方法执行和方法的所有调用执行。  \n\ntrace 查看方法调用树耗时  \n\nstack 查看方法的所有调用树路径\n\n## 1.奇怪的类加载错误\n&emsp;&emsp;相信大家都遇到过NoSuchMethodError这个错误，一般老司机看见这个错误第一反应就是jar包版本号冲突，这种问题一般来说使用maven的一些插件就能轻松解决。  \n\n&emsp;&emsp;之前遇到个奇怪的问题，我们有两个服务的client-jar包，有个类的包名和类名均是一致，在编写代码的时候没有注意到这个问题，在编译阶段由于包名和类名都是一致，所有编译阶段并没有报错，在线下的运行阶段没有问题，但是测试环境的机器中的运行阶段缺报出了问题。这个和之前的jar包版本号冲突有点不同，因为在排查的时候我们想使用A服务的client-jar包的这个类，但是这个jar包的版本号在Maven中的确是唯一的。\n这个时候Arthas就可以大显神通了。\n### 1.1 sc命令\n找到对应的类，然后输出下面的命令(用例使用的是官方提供的用例):\n``` shell\n$ sc -d demo.MathGame\nclass-info        demo.MathGame\ncode-source       /private/tmp/arthas-demo.jar\nname              demo.MathGame\nisInterface       false\nisAnnotation      false\nisEnum            false\nisAnonymousClass  false\nisArray           false\nisLocalClass      false\nisMemberClass     false\nisPrimitive       false\nisSynthetic       false\nsimple-name       MathGame\nmodifier          public\nannotation\ninterfaces\nsuper-class       +-java.lang.Object\nclass-loader      +-sun.misc.Launcher$AppClassLoader@3d4eac69\n                    +-sun.misc.Launcher$ExtClassLoader@66350f69\nclassLoaderHash   3d4eac69\n \nAffect(row-cnt:1) cost in 875 ms.\n\n```\n可以看见打印出了code-source,当时发现了code-source并不是从对应的Jar包取出来的，于是发现了两个服务对于同一个类使用了同样的包名和类名，导致了这个奇怪的问题，后续通过修改包名和类名进行解决。\n\n### 1.2 jad\nArthas还提供了一个命令jad用来反编译,对于解决类冲突错误很有用，比如我们想知道这个类里面的代码到底是什么，直接一个jad命令就能搞定:\n```shell\n$ jad java.lang.String\n \nClassLoader:\n \nLocation:\n \n/*\n* Decompiled with CFR 0_132.\n*/\npackage java.lang;\n \nimport java.io.ObjectStreamField;\n...\npublic final class String\nimplements Serializable,\nComparable<String>,\nCharSequence {\n    private final char[] value;\n    private int hash;\n    private static final long serialVersionUID = -6849794470754667710L;\n    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];\n    public static final Comparator<String> CASE_INSENSITIVE_ORDER = new CaseInsensitiveComparator();\n \n    public String(byte[] arrby, int n, int n2) {\n        String.checkBounds(arrby, n, n2);\n        this.value = StringCoding.decode(arrby, n, n2);\n    }\n...\n```\n\n一般通过这个命令我们就能发现和你所期待的类是否缺少了某些方法，或者某些方法有些改变，从而确定jar包冲突。\n\n## 2.动态修改日志级别\n有很多同学可能会觉得动态修改日志有什么用呢？好像自己也没怎么用过呢？\n一般来说下面这几个场景可以需要:  \n\n一般大家日志级别默认是info，有时候需要查看debug的日志可能需要重新上线。\n当线上某个应用流量比较大的时候，如何业务出现问题，可能会短时间之内产生大量日志，由于日志会写盘，会消耗大量的内存和磁盘IO进一步加重我们的问题严重性，进而引起雪崩。\n我们可以使用动态修改日志解决我们上面两个问题\n\n### 2.1 ognl\nognl是一门表达式语言，在Arthas中你可以利用这个表达式语言做很多事，比如执行某个方法，获取某个信息。再这里我们可以通过下面的命令来动态的修改日志级别:\n``` shell\n$ ognl \'@com.lz.test@LOGGER.logger.privateConfig\'\n@PrivateConfig[\n    loggerConfig=@LoggerConfig[root],\n    loggerConfigLevel=@Level[INFO],\n    intLevel=@Integer[400],\n]\n$ ognl \'@com.lz.test@LOGGER.logger.setLevel(@org.apache.logging.log4j.Level@ERROR)\'\nnull\n$ ognl \'@com.lz.test@LOGGER.logger.privateConfig\'\n@PrivateConfig[\n    loggerConfig=@LoggerConfig[root],\n    loggerConfigLevel=@Level[ERROR],\n    intLevel=@Integer[200],\n  \n]\n\n```\n\n\n## 3.如何知道某个方法是否调用\n很多时候我们方法执行的情况和我们预期不符合，但是我们又不知道到底哪里不符合，Arthas的watch命令就能帮助我们解决这个问题。\n\n### 3.1 watch\nwatch命令顾名思义观察，他可以观察指定方法调用情况，定义了4个观察事件点， -b 方法调用前，-e 方法异常后，-s 方法返回后，-f 方法结束后。默认是-f\n比如我们想知道某个方法执行的时候，参数和返回值到底是什么。注意这里的参数是方法执行完成的时候的参数，和入参不同有可能会发生变化。\n``` shell\n$ watch demo.MathGame primeFactors \"{params,returnObj}\" -x 2\nPress Ctrl+C to abort.\nAffect(class-cnt:1 , method-cnt:1) cost in 44 ms.\nts=2018-12-03 19:16:51; [cost=1.280502ms] result=@ArrayList[\n    @Object[][\n        @Integer[535629513],\n    ],\n    @ArrayList[\n        @Integer[3],\n        @Integer[19],\n        @Integer[191],\n        @Integer[49199],\n    ],\n]\n```\n\n你能得到参数和返回值的情况，以及方法时间消耗的等信息。\n\n## 4.如何知道某个方法耗时较多\n当某个方法耗时较长，这个时候你需要排查到底是某一处发生了长时间的耗时，一般这种问题比较难排查，都是通过全链路追踪trace图去进行排查，但是在本地的应用中没有trace图，这个时候需要Arthas的trace命令来进行排查问题。\n### 4.1 trace\ntrace 命令能主动搜索 class-pattern／method-pattern 对应的方法调用路径，渲染和统计整个调用链路上的所有性能开销和追踪调用链路。\n但是trace只能追踪一层的调用链路，如果一层的链路信息不够用，可以把该链路上有问题的方法再次进行trace。\ntrace使用例子如下。\n``` shell\n$ trace demo.MathGame run\nPress Ctrl+C to abort.\nAffect(class-cnt:1 , method-cnt:1) cost in 42 ms.\n`---ts=2018-12-04 00:44:17;thread_name=main;id=1;is_daemon=false;priority=5;TCCL=sun.misc.Launcher$AppClassLoader@3d4eac69\n    `---[10.611029ms] demo.MathGame:run()\n        +---[0.05638ms] java.util.Random:nextInt()\n        +---[10.036885ms] demo.MathGame:primeFactors()\n        `---[0.170316ms] demo.MathGame:print()\n\n```\n\n可以看见上述耗时最多的方法是primeFactors，所以我们可以对其进行trace进行再一步的排查。\n\n## 5.如何使用命令重发请求？\n有时候排查一个问题需要上游再次调用这个方法，比如使用postMan等工具，当然Arthas提供了一个命令让替代我们来回手动请求。\n### 5.1 tt\ntt官方介绍: 方法执行数据的时空隧道，记录下指定方法每次调用的入参和返回信息，并能对这些不同的时间下调用进行观测。可以看见tt可以用于录制请求，当然也支持我们重放。\n如果要录制某个方法，可以用下面命令:\n```shell\n$ tt -t demo.MathGame primeFactors\nPress Ctrl+C to abort.\nAffect(class-cnt:1 , method-cnt:1) cost in 66 ms.\n INDEX   TIMESTAMP            COST(ms)  IS-RET  IS-EXP   OBJECT         CLASS                          METHOD\n-------------------------------------------------------------------------------------------------------------------------------------\n 1000    2018-12-04 11:15:38  1.096236  false   true     0x4b67cf4d     MathGame                       primeFactors\n 1001    2018-12-04 11:15:39  0.191848  false   true     0x4b67cf4d     MathGame                       primeFactors\n 1002    2018-12-04 11:15:40  0.069523  false   true     0x4b67cf4d     MathGame                       primeFactors\n 1003    2018-12-04 11:15:41  0.186073  false   true     0x4b67cf4d     MathGame                       primeFactors\n 1004    2018-12-04 11:15:42  17.76437  true    false    0x4b67cf4d     MathGame                       primeFactors\n\n```\n\n上面录制了5个调用环境现场，也可以看做是录制了5个请求返回信息。比如我们想选择index为1004个的请求来重放，可以输入下面的命令。\n```shell\n$ tt -i 1004 -p\n RE-INDEX       1004\n GMT-REPLAY     2018-12-04 11:26:00\n OBJECT         0x4b67cf4d\n CLASS          demo.MathGame\n METHOD         primeFactors\n PARAMETERS[0]  @Integer[946738738]\n IS-RETURN      true\n IS-EXCEPTION   false\n RETURN-OBJ     @ArrayList[\n                    @Integer[2],\n                    @Integer[11],\n                    @Integer[17],\n                    @Integer[2531387],\n                ]\nTime fragment[1004] successfully replayed.\nAffect(row-cnt:1) cost in 14 ms.\n```\n\n注意重放请求需要关注两点:\n\nThreadLocal 信息丢失:由于使用的是Arthas线程调用，会让threadLocal信息丢失，比如一些TraceId信息可能会丢失\n引用的对象:保存的入参是保存的引用，而不是拷贝，所以如果参数中的内容被修改，那么入参其实也是被修改的。\n\n## 6.一些耗时的方法，经常被触发，如何知道谁调用的?\n有时候有些方法非常耗时或者非常重要，需要知道到底是谁发起的调用，比如System.gc(),有时候如果你发现fullgc频繁是因为System.gc()引起的，你需要查看到底是什么应用调用的，那么你就可以使用下面的命令\n我们可以输入下面的命令:\n```shell\n$ options unsafe true\n NAME    BEFORE-VALUE  AFTER-VALUE                                                                                                                                                                        \n-----------------------------------                                                                                                                                                                       \n unsafe  false         true                                                                                                                                                                               \n$ stack java.lang.System gc\nPress Ctrl+C to abort.\nAffect(class-cnt:1 , method-cnt:1) cost in 50 ms.\nts=2019-01-20 21:14:05;thread_name=main;id=1;is_daemon=false;priority=5;TCCL=sun.misc.Launcher$AppClassLoader@14dad5dc\n    @java.lang.System.gc()\n        at com.lz.test.Test.main(Test.java:322)\n\n```\n\n首先输入options unsafe true允许我们对jdk增强，然后对System.gc进行进行监视，然后记录当前的堆栈来获取是什么位置进行的调用。\n## 7.如何重定义某个类?\n有些时候我们找了所有的命令，发现和我们的需求并不符合的时候，那么这个时候我们可以重新定义这个类，我们可以用使用下面的命令。\n### 7.1 redefine\nredefine命令提供了我们可以重新定义jvm中的class，但是使用这个命令之后class不可恢复。我们首先需要把重写的class编译出来，然后上传到我们指定的目录，进行下面的操作:\n```shell\n redefine -p /tmp/Test.class\n```\n\n可以重定义我们的Test.class。从而修改逻辑，完成我们自定义的需求。', '<h2 id=\"性能分析利器《arthas》总结\">性能分析利器《Arthas》总结</h2>\n<p>Arthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：</p>\n<ul>\n<li>当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。</li>\n<li>当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。</li>\n<li>当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。</li>\n<li>当你发现系统突然报出某个类的Exception，却无法找到这个类是从哪个Jar包加载的。</li>\n<li>当你发现有时候排查一个问题需要上游重复调用这个方法，于是你只能想尽办法利用postman等工具重现这个请求。</li>\n</ul>\n<p>下面我将会介绍一下Arthas的一些常用的命令和用法，看看是如何解决我们实际中的问题的，至于安装教程可以参考Arthas的github。</p>\n<blockquote>\n<p><a href=\"https://github.com/alibaba/arthas\">https://github.com/alibaba/arthas</a></p>\n</blockquote>\n<h2 id=\"常用命令\">常用命令</h2>\n<p>dashboard 当前系统的实时数据面板  </p>\n<p>jvm 查看当前JVM信息  </p>\n<p>thread 可直接查看线程的cpu占用比  </p>\n<p>sc sm 快速搜索类和方法信息  </p>\n<p>getstatic 查看类静态变量  </p>\n<p>jad 反编译class文件  </p>\n<p>mc 编译.java文件生成.class  </p>\n<p>redefine 加载外部class文件到应用程序中  </p>\n<p>monitor 监测方法调用次数、成功次数、失败次数、平均RT等  </p>\n<p>watch tt 观测方法执行的前、后、结束、异常、耗时过大时，入参（入参属性深度可调）、返回值、异常，支持实时监测每次方法执行和方法的所有调用执行。  </p>\n<p>trace 查看方法调用树耗时  </p>\n<p>stack 查看方法的所有调用树路径</p>\n<h2 id=\"1奇怪的类加载错误\">1.奇怪的类加载错误</h2>\n<p>&emsp;&emsp;相信大家都遇到过NoSuchMethodError这个错误，一般老司机看见这个错误第一反应就是jar包版本号冲突，这种问题一般来说使用maven的一些插件就能轻松解决。  </p>\n<p>&emsp;&emsp;之前遇到个奇怪的问题，我们有两个服务的client-jar包，有个类的包名和类名均是一致，在编写代码的时候没有注意到这个问题，在编译阶段由于包名和类名都是一致，所有编译阶段并没有报错，在线下的运行阶段没有问题，但是测试环境的机器中的运行阶段缺报出了问题。这个和之前的jar包版本号冲突有点不同，因为在排查的时候我们想使用A服务的client-jar包的这个类，但是这个jar包的版本号在Maven中的确是唯一的。\n这个时候Arthas就可以大显神通了。</p>\n<h3 id=\"11-sc命令\">1.1 sc命令</h3>\n<p>找到对应的类，然后输出下面的命令(用例使用的是官方提供的用例):</p>\n<pre><code class=\"language-shell\">$ sc -d demo.MathGame\nclass-info        demo.MathGame\ncode-source       /private/tmp/arthas-demo.jar\nname              demo.MathGame\nisInterface       false\nisAnnotation      false\nisEnum            false\nisAnonymousClass  false\nisArray           false\nisLocalClass      false\nisMemberClass     false\nisPrimitive       false\nisSynthetic       false\nsimple-name       MathGame\nmodifier          public\nannotation\ninterfaces\nsuper-class       +-java.lang.Object\nclass-loader      +-sun.misc.Launcher$AppClassLoader@3d4eac69\n                    +-sun.misc.Launcher$ExtClassLoader@66350f69\nclassLoaderHash   3d4eac69\n \nAffect(row-cnt:1) cost in 875 ms.\n</code></pre>\n<p>可以看见打印出了code-source,当时发现了code-source并不是从对应的Jar包取出来的，于是发现了两个服务对于同一个类使用了同样的包名和类名，导致了这个奇怪的问题，后续通过修改包名和类名进行解决。</p>\n<h3 id=\"12-jad\">1.2 jad</h3>\n<p>Arthas还提供了一个命令jad用来反编译,对于解决类冲突错误很有用，比如我们想知道这个类里面的代码到底是什么，直接一个jad命令就能搞定:</p>\n<pre><code class=\"language-shell\">$ jad java.lang.String\n \nClassLoader:\n \nLocation:\n \n/*\n* Decompiled with CFR 0_132.\n*/\npackage java.lang;\n \nimport java.io.ObjectStreamField;\n...\npublic final class String\nimplements Serializable,\nComparable&lt;String&gt;,\nCharSequence {\n    private final char[] value;\n    private int hash;\n    private static final long serialVersionUID = -6849794470754667710L;\n    private static final ObjectStreamField[] serialPersistentFields = new ObjectStreamField[0];\n    public static final Comparator&lt;String&gt; CASE_INSENSITIVE_ORDER = new CaseInsensitiveComparator();\n \n    public String(byte[] arrby, int n, int n2) {\n        String.checkBounds(arrby, n, n2);\n        this.value = StringCoding.decode(arrby, n, n2);\n    }\n...\n</code></pre>\n<p>一般通过这个命令我们就能发现和你所期待的类是否缺少了某些方法，或者某些方法有些改变，从而确定jar包冲突。</p>\n<h2 id=\"2动态修改日志级别\">2.动态修改日志级别</h2>\n<p>有很多同学可能会觉得动态修改日志有什么用呢？好像自己也没怎么用过呢？\n一般来说下面这几个场景可以需要:  </p>\n<p>一般大家日志级别默认是info，有时候需要查看debug的日志可能需要重新上线。\n当线上某个应用流量比较大的时候，如何业务出现问题，可能会短时间之内产生大量日志，由于日志会写盘，会消耗大量的内存和磁盘IO进一步加重我们的问题严重性，进而引起雪崩。\n我们可以使用动态修改日志解决我们上面两个问题</p>\n<h3 id=\"21-ognl\">2.1 ognl</h3>\n<p>ognl是一门表达式语言，在Arthas中你可以利用这个表达式语言做很多事，比如执行某个方法，获取某个信息。再这里我们可以通过下面的命令来动态的修改日志级别:</p>\n<pre><code class=\"language-shell\">$ ognl &#39;@com.lz.test@LOGGER.logger.privateConfig&#39;\n@PrivateConfig[\n    loggerConfig=@LoggerConfig[root],\n    loggerConfigLevel=@Level[INFO],\n    intLevel=@Integer[400],\n]\n$ ognl &#39;@com.lz.test@LOGGER.logger.setLevel(@org.apache.logging.log4j.Level@ERROR)&#39;\nnull\n$ ognl &#39;@com.lz.test@LOGGER.logger.privateConfig&#39;\n@PrivateConfig[\n    loggerConfig=@LoggerConfig[root],\n    loggerConfigLevel=@Level[ERROR],\n    intLevel=@Integer[200],\n  \n]\n</code></pre>\n<h2 id=\"3如何知道某个方法是否调用\">3.如何知道某个方法是否调用</h2>\n<p>很多时候我们方法执行的情况和我们预期不符合，但是我们又不知道到底哪里不符合，Arthas的watch命令就能帮助我们解决这个问题。</p>\n<h3 id=\"31-watch\">3.1 watch</h3>\n<p>watch命令顾名思义观察，他可以观察指定方法调用情况，定义了4个观察事件点， -b 方法调用前，-e 方法异常后，-s 方法返回后，-f 方法结束后。默认是-f\n比如我们想知道某个方法执行的时候，参数和返回值到底是什么。注意这里的参数是方法执行完成的时候的参数，和入参不同有可能会发生变化。</p>\n<pre><code class=\"language-shell\">$ watch demo.MathGame primeFactors &quot;{params,returnObj}&quot; -x 2\nPress Ctrl+C to abort.\nAffect(class-cnt:1 , method-cnt:1) cost in 44 ms.\nts=2018-12-03 19:16:51; [cost=1.280502ms] result=@ArrayList[\n    @Object[][\n        @Integer[535629513],\n    ],\n    @ArrayList[\n        @Integer[3],\n        @Integer[19],\n        @Integer[191],\n        @Integer[49199],\n    ],\n]\n</code></pre>\n<p>你能得到参数和返回值的情况，以及方法时间消耗的等信息。</p>\n<h2 id=\"4如何知道某个方法耗时较多\">4.如何知道某个方法耗时较多</h2>\n<p>当某个方法耗时较长，这个时候你需要排查到底是某一处发生了长时间的耗时，一般这种问题比较难排查，都是通过全链路追踪trace图去进行排查，但是在本地的应用中没有trace图，这个时候需要Arthas的trace命令来进行排查问题。</p>\n<h3 id=\"41-trace\">4.1 trace</h3>\n<p>trace 命令能主动搜索 class-pattern／method-pattern 对应的方法调用路径，渲染和统计整个调用链路上的所有性能开销和追踪调用链路。\n但是trace只能追踪一层的调用链路，如果一层的链路信息不够用，可以把该链路上有问题的方法再次进行trace。\ntrace使用例子如下。</p>\n<pre><code class=\"language-shell\">$ trace demo.MathGame run\nPress Ctrl+C to abort.\nAffect(class-cnt:1 , method-cnt:1) cost in 42 ms.\n`---ts=2018-12-04 00:44:17;thread_name=main;id=1;is_daemon=false;priority=5;TCCL=sun.misc.Launcher$AppClassLoader@3d4eac69\n    `---[10.611029ms] demo.MathGame:run()\n        +---[0.05638ms] java.util.Random:nextInt()\n        +---[10.036885ms] demo.MathGame:primeFactors()\n        `---[0.170316ms] demo.MathGame:print()\n</code></pre>\n<p>可以看见上述耗时最多的方法是primeFactors，所以我们可以对其进行trace进行再一步的排查。</p>\n<h2 id=\"5如何使用命令重发请求？\">5.如何使用命令重发请求？</h2>\n<p>有时候排查一个问题需要上游再次调用这个方法，比如使用postMan等工具，当然Arthas提供了一个命令让替代我们来回手动请求。</p>\n<h3 id=\"51-tt\">5.1 tt</h3>\n<p>tt官方介绍: 方法执行数据的时空隧道，记录下指定方法每次调用的入参和返回信息，并能对这些不同的时间下调用进行观测。可以看见tt可以用于录制请求，当然也支持我们重放。\n如果要录制某个方法，可以用下面命令:</p>\n<pre><code class=\"language-shell\">$ tt -t demo.MathGame primeFactors\nPress Ctrl+C to abort.\nAffect(class-cnt:1 , method-cnt:1) cost in 66 ms.\n INDEX   TIMESTAMP            COST(ms)  IS-RET  IS-EXP   OBJECT         CLASS                          METHOD\n-------------------------------------------------------------------------------------------------------------------------------------\n 1000    2018-12-04 11:15:38  1.096236  false   true     0x4b67cf4d     MathGame                       primeFactors\n 1001    2018-12-04 11:15:39  0.191848  false   true     0x4b67cf4d     MathGame                       primeFactors\n 1002    2018-12-04 11:15:40  0.069523  false   true     0x4b67cf4d     MathGame                       primeFactors\n 1003    2018-12-04 11:15:41  0.186073  false   true     0x4b67cf4d     MathGame                       primeFactors\n 1004    2018-12-04 11:15:42  17.76437  true    false    0x4b67cf4d     MathGame                       primeFactors\n</code></pre>\n<p>上面录制了5个调用环境现场，也可以看做是录制了5个请求返回信息。比如我们想选择index为1004个的请求来重放，可以输入下面的命令。</p>\n<pre><code class=\"language-shell\">$ tt -i 1004 -p\n RE-INDEX       1004\n GMT-REPLAY     2018-12-04 11:26:00\n OBJECT         0x4b67cf4d\n CLASS          demo.MathGame\n METHOD         primeFactors\n PARAMETERS[0]  @Integer[946738738]\n IS-RETURN      true\n IS-EXCEPTION   false\n RETURN-OBJ     @ArrayList[\n                    @Integer[2],\n                    @Integer[11],\n                    @Integer[17],\n                    @Integer[2531387],\n                ]\nTime fragment[1004] successfully replayed.\nAffect(row-cnt:1) cost in 14 ms.\n</code></pre>\n<p>注意重放请求需要关注两点:</p>\n<p>ThreadLocal 信息丢失:由于使用的是Arthas线程调用，会让threadLocal信息丢失，比如一些TraceId信息可能会丢失\n引用的对象:保存的入参是保存的引用，而不是拷贝，所以如果参数中的内容被修改，那么入参其实也是被修改的。</p>\n<h2 id=\"6一些耗时的方法，经常被触发，如何知道谁调用的\">6.一些耗时的方法，经常被触发，如何知道谁调用的?</h2>\n<p>有时候有些方法非常耗时或者非常重要，需要知道到底是谁发起的调用，比如System.gc(),有时候如果你发现fullgc频繁是因为System.gc()引起的，你需要查看到底是什么应用调用的，那么你就可以使用下面的命令\n我们可以输入下面的命令:</p>\n<pre><code class=\"language-shell\">$ options unsafe true\n NAME    BEFORE-VALUE  AFTER-VALUE                                                                                                                                                                        \n-----------------------------------                                                                                                                                                                       \n unsafe  false         true                                                                                                                                                                               \n$ stack java.lang.System gc\nPress Ctrl+C to abort.\nAffect(class-cnt:1 , method-cnt:1) cost in 50 ms.\nts=2019-01-20 21:14:05;thread_name=main;id=1;is_daemon=false;priority=5;TCCL=sun.misc.Launcher$AppClassLoader@14dad5dc\n    @java.lang.System.gc()\n        at com.lz.test.Test.main(Test.java:322)\n</code></pre>\n<p>首先输入options unsafe true允许我们对jdk增强，然后对System.gc进行进行监视，然后记录当前的堆栈来获取是什么位置进行的调用。</p>\n<h2 id=\"7如何重定义某个类\">7.如何重定义某个类?</h2>\n<p>有些时候我们找了所有的命令，发现和我们的需求并不符合的时候，那么这个时候我们可以重新定义这个类，我们可以用使用下面的命令。</p>\n<h3 id=\"71-redefine\">7.1 redefine</h3>\n<p>redefine命令提供了我们可以重新定义jvm中的class，但是使用这个命令之后class不可恢复。我们首先需要把重写的class编译出来，然后上传到我们指定的目录，进行下面的操作:</p>\n<pre><code class=\"language-shell\"> redefine -p /tmp/Test.class\n</code></pre>\n<p>可以重定义我们的Test.class。从而修改逻辑，完成我们自定义的需求。</p>\n', 11, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/fd839977-86d7-4e4c-823f-7532af563f61.jpeg', 0, '1', 0, 0, '2021-10-24 10:30:16', '2021-10-24 10:30:16');
INSERT INTO `blog_article` VALUES (4, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (5, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-11-01 16:07:27', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (6, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-11-01 16:07:42', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (7, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-11-01 16:07:49', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (8, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (9, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (10, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (11, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (12, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (13, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (14, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
INSERT INTO `blog_article` VALUES (15, '2019Java最新面试题——Java基础（持续更新）', '网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。', 'admin', '### Java基础\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\n```java\nString str1 = \"通话\";\nString str2 = \"重地\";\nSystem.out.println(String.format(\"str1：%d | str2：%d\",  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n```\n执行的结果： \n\nstr1：1179395 | str2：1179395\n\nfalse\n\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n\n**总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。**', 'Java基础\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\n代码实例:\nString str1 = &quot;通话&quot;;\nString str2 = &quot;重地&quot;;\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\nSystem.out.println(str1.equals(str2));\n\n执行的结果： \nstr1：1179395 | str2：1179395\nfalse\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\n下面的话来自JDK：\n\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\nhashCode 的常规协定是： \n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\n\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\n', 10, 10, 10, 0, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', 1, '1', 0, 0, '2021-09-07 12:50:04', '2021-09-07 12:50:04');
COMMIT;

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tag_id` int DEFAULT NULL COMMENT '标签Id',
  `article_id` int DEFAULT NULL COMMENT '文章Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='标签文章多对多维护表';

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------
BEGIN;
INSERT INTO `blog_article_tag` VALUES (22, 1, 1);
INSERT INTO `blog_article_tag` VALUES (24, 1, 3);
INSERT INTO `blog_article_tag` VALUES (25, 1, 2);
INSERT INTO `blog_article_tag` VALUES (26, 2, 2);
INSERT INTO `blog_article_tag` VALUES (28, 2, 4);
INSERT INTO `blog_article_tag` VALUES (29, 3, 4);
INSERT INTO `blog_article_tag` VALUES (30, 1, 4);
INSERT INTO `blog_article_tag` VALUES (31, 3, 10);
INSERT INTO `blog_article_tag` VALUES (32, 2, 9);
INSERT INTO `blog_article_tag` VALUES (33, 3, 9);
INSERT INTO `blog_article_tag` VALUES (34, 2, 8);
INSERT INTO `blog_article_tag` VALUES (35, 2, 6);
INSERT INTO `blog_article_tag` VALUES (36, 3, 6);
INSERT INTO `blog_article_tag` VALUES (37, 1, 7);
INSERT INTO `blog_article_tag` VALUES (38, 3, 7);
INSERT INTO `blog_article_tag` VALUES (39, 2, 5);
INSERT INTO `blog_article_tag` VALUES (40, 1, 5);
INSERT INTO `blog_article_tag` VALUES (41, 2, 11);
INSERT INTO `blog_article_tag` VALUES (42, 3, 11);
INSERT INTO `blog_article_tag` VALUES (43, 1, 11);
INSERT INTO `blog_article_tag` VALUES (44, 2, 12);
INSERT INTO `blog_article_tag` VALUES (45, 2, 13);
INSERT INTO `blog_article_tag` VALUES (46, 1, 13);
INSERT INTO `blog_article_tag` VALUES (47, 2, 14);
INSERT INTO `blog_article_tag` VALUES (48, 1, 15);
COMMIT;

-- ----------------------------
-- Table structure for blog_carousel
-- ----------------------------
DROP TABLE IF EXISTS `blog_carousel`;
CREATE TABLE `blog_carousel` (
  `carousel_id` int NOT NULL AUTO_INCREMENT COMMENT '轮播图id',
  `img_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '轮播图url',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '轮播图标题',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '轮播图跳转地址',
  `display` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否展示该轮播图，1展示，2不展示',
  `type` tinyint(1) DEFAULT NULL COMMENT '1外链，2文章',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`carousel_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='轮播图';

-- ----------------------------
-- Records of blog_carousel
-- ----------------------------
BEGIN;
INSERT INTO `blog_carousel` VALUES (1, 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', '测试', 'https://www.huya.com/g/wzry', '0', 2, '2021-10-24 12:14:46', NULL, '测试数据');
INSERT INTO `blog_carousel` VALUES (2, 'https://cdn.jsdelivr.net/gh/GuoHuaijian/picture@main/data/1.jpeg', 'hello', 'https://www.huya.com/g/wzry', '0', 2, '2021-11-01 14:18:28', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE KEY `operation_category_id_uindex` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
BEGIN;
INSERT INTO `blog_category` VALUES (1, 'Java基础', '2021-10-18 21:13:59', '2021-10-18 21:14:03', 'Java基础知识');
INSERT INTO `blog_category` VALUES (2, 'MySql数据库', '2021-10-23 23:28:27', '2021-10-23 23:28:30', 'MySql数据库知识');
COMMIT;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `comment_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论主键id',
  `type` tinyint(1) NOT NULL COMMENT '评论类型：0:留言 1:文章',
  `owner_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '被评论id，可以是单个文章id、项目、资源',
  `parent_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '评论id 第一级为0',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论者名字',
  `avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '评论者头像',
  `like_num` int DEFAULT '0' COMMENT '点赞的数量',
  `dislike_num` int DEFAULT '0' COMMENT '踩的数量',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '评论内容',
  `reply_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '回复的id',
  `reply_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '回复评论者名字',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`comment_id`) USING BTREE,
  KEY `owner_id` (`owner_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='评论表';

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
BEGIN;
INSERT INTO `blog_comment` VALUES ('1', 1, '9527', '0', '犀利的评论家', 'http://ww4.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2pddjuj30v90uvagf.jpg', 10, 20, '非常靠谱的程序员', NULL, NULL, '2021-09-14 14:27:00', '2021-10-24 12:09:46');
INSERT INTO `blog_comment` VALUES ('2', 1, '9527', '1', '夕阳红', 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', 3, 4, '赞同，很靠谱，水平很高', '1', '犀利的评论家', '2021-09-14 14:29:13', '2021-10-24 12:09:49');
INSERT INTO `blog_comment` VALUES ('3', 1, '9527', '1', '清晨一缕阳光', 'http://imgsrc.baidu.com/imgad/pic/item/c2fdfc039245d688fcba1b80aec27d1ed21b245d.jpg', 6, 3, '大神一个', '2', '夕阳红', '2021-09-14 14:30:38', '2021-10-24 12:09:51');
INSERT INTO `blog_comment` VALUES ('4', 1, '9527', '0', '毒蛇郭德纲', 'http://ww1.sinaimg.cn/bmiddle/006DLFVFgy1ft0j2q2p8pj30v90uzmzz.jpg', 8, 2, '从没见过这么优秀的人', NULL, NULL, '2021-09-14 14:31:42', '2021-10-24 12:09:53');
INSERT INTO `blog_comment` VALUES ('5', 1, '9527', '4', '夕阳红', 'https://wx4.sinaimg.cn/mw690/69e273f8gy1ft1541dmb7j215o0qv7wh.jpg', 6, 3, '赞同，很靠谱，水平很高', '4', '毒蛇郭德纲', '2021-09-14 14:32:57', '2021-10-24 12:09:56');
COMMIT;

-- ----------------------------
-- Table structure for blog_friend
-- ----------------------------
DROP TABLE IF EXISTS `blog_friend`;
CREATE TABLE `blog_friend` (
  `friend_id` int NOT NULL COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '友链名',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标url',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `url` varchar(255) DEFAULT NULL COMMENT '网站地址',
  `mail` varchar(255) DEFAULT NULL COMMENT '站长邮箱',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态 0待审 1通过 2拒绝',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`friend_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='友链';

-- ----------------------------
-- Records of blog_friend
-- ----------------------------
BEGIN;
INSERT INTO `blog_friend` VALUES (1, '百度', 'http://1.14.72.150:9000/aurora/2021/10/24/b5d676c2-71eb-4a51-8306-83a8f5e973a4.jpeg', '测试数据', 'www.baidu.com', '564559079@qq.com', 0, '2021-10-24 12:59:20', NULL, '测试数据');
COMMIT;

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `tag_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '标签名字',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='标签';

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
BEGIN;
INSERT INTO `blog_tag` VALUES (1, 'Java', '2021-10-18 21:20:16', '2021-10-18 21:20:18', 'Java知识');
INSERT INTO `blog_tag` VALUES (2, 'Spring', '2021-10-19 20:57:26', '2021-10-19 20:57:28', 'Sping知识');
INSERT INTO `blog_tag` VALUES (3, 'Mysql', '2021-11-01 19:05:03', '2021-11-01 19:05:03', '数据库');
COMMIT;

-- ----------------------------
-- Table structure for clientdetails
-- ----------------------------
DROP TABLE IF EXISTS `clientdetails`;
CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of clientdetails
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of oauth_access_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS `oauth_approvals`;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NULL DEFAULT NULL,
  `lastModifiedAt` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of oauth_approvals
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('aurora_client', NULL, '$2a$10$mEYJRcWq3loIOSqlAcRmbOQPxyQuy/X7hPW6r8KbXUEcU69THv9cK', 'all', 'password,refresh_token', '', NULL, 1800, 3600, NULL, '');
INSERT INTO `oauth_client_details` VALUES ('user', NULL, '$2a$10$mEYJRcWq3loIOSqlAcRmbOQPxyQuy/X7hPW6r8KbXUEcU69THv9cK', 'all', 'authorization_code,refresh_token', 'http://www.baidu.com', NULL, 1800, 3600, NULL, 'true');
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of oauth_client_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of oauth_code
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of oauth_refresh_token
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2021-09-15 22:25:45', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2021-09-15 22:25:45', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2021-09-15 22:25:45', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-验证码开关', 'sys.account.captchaOnOff', 'true', 'Y', 'admin', '2021-09-15 22:25:45', '', NULL, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2021-09-15 22:25:45', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 0, '已发布', '0', 'admin_article_status', NULL, 'success', 'N', '0', 'admin', NULL, 'admin', NULL, '发布状态');
INSERT INTO `sys_dict_data` VALUES (101, 1, '已下架', '1', 'admin_article_status', NULL, 'info', 'N', '0', 'admin', NULL, 'admin', NULL, '下架状态');
INSERT INTO `sys_dict_data` VALUES (102, 0, '留言', '0', 'admin_comment_type', NULL, 'primary', 'N', '0', 'admin', NULL, 'admin', NULL, '留言类型');
INSERT INTO `sys_dict_data` VALUES (103, 1, '文章', '1', 'admin_comment_type', NULL, 'success', 'N', '0', 'admin', NULL, 'admin', NULL, '文章类型');
INSERT INTO `sys_dict_data` VALUES (104, 0, '展示', '0', 'admin_carousel_status', NULL, 'success', 'N', '0', 'admin', NULL, 'admin', NULL, '展示状态');
INSERT INTO `sys_dict_data` VALUES (105, 1, '不展示', '1', 'admin_carousel_status', NULL, 'info', 'N', '0', 'admin', NULL, 'admin', NULL, '不展示状态');
INSERT INTO `sys_dict_data` VALUES (106, 0, '待审批', '0', 'admin_friend_status', NULL, 'info', 'N', '0', 'admin', NULL, 'admin', NULL, '待审批状态');
INSERT INTO `sys_dict_data` VALUES (107, 1, '已通过', '1', 'admin_friend_status', NULL, 'success', 'N', '0', 'admin', NULL, 'admin', NULL, '已通过状态');
INSERT INTO `sys_dict_data` VALUES (108, 2, '已拒绝', '2', 'admin_friend_status', NULL, 'danger', 'N', '0', 'admin', NULL, '', NULL, '已拒绝状态');
INSERT INTO `sys_dict_data` VALUES (109, 0, '外链', '1', 'admin_carousel_type', NULL, 'primary', 'N', '0', 'admin', NULL, 'admin', NULL, '外链类型');
INSERT INTO `sys_dict_data` VALUES (110, 1, '文章', '2', 'admin_carousel_type', NULL, 'success', 'N', '0', 'admin', NULL, '', NULL, '文章类型');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2021-09-15 22:25:45', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (11, '博文状态', 'admin_article_status', '0', 'admin', '2021-10-24 11:31:39', 'admin', NULL, '博文状态列表');
INSERT INTO `sys_dict_type` VALUES (12, '评论类型', 'admin_comment_type', '0', 'admin', '2021-10-24 11:31:43', '', NULL, '评论类型列表');
INSERT INTO `sys_dict_type` VALUES (102, '轮播图状态', 'admin_carousel_status', '0', 'admin', NULL, '', NULL, '轮播图状态列表');
INSERT INTO `sys_dict_type` VALUES (103, '友链状态', 'admin_friend_status', '0', 'admin', NULL, '', NULL, '友链状态列表');
INSERT INTO `sys_dict_type` VALUES (104, '轮播图类型', 'admin_carousel_type', '0', 'admin', NULL, '', NULL, '轮播图类型列表');
COMMIT;

-- ----------------------------
-- Table structure for sys_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_log`;
CREATE TABLE `sys_login_log` (
  `login_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`login_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统访问记录';

-- ----------------------------
-- Records of sys_login_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_login_log` VALUES (1, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'OSX', '1', '获取token异常', '2021-10-28 23:24:37');
INSERT INTO `sys_login_log` VALUES (2, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'OSX', '0', '登录成功', '2021-10-28 23:24:46');
INSERT INTO `sys_login_log` VALUES (3, 'admin', '127.0.0.1', '内网IP', 'Chrome', 'OSX', '0', '登录成功', '2021-11-01 19:04:15');
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '路由参数',
  `is_frame` int DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '#' COMMENT '菜单图标',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2035 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 2, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', '2021-09-15 22:25:45', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 3, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', '2021-09-15 22:25:45', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '博客管理', 0, 1, 'blog', NULL, '', 1, 0, 'M', '0', '0', '', 'education', '2021-09-15 22:25:45', '2021-09-22 20:09:26', '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, 'GitHub', 0, 4, 'https://github.com/GuoHuaijian', NULL, '', 0, 0, 'M', '0', '0', '', 'guide', '2021-09-15 22:25:45', NULL, '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', '2021-09-15 22:25:45', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', '2021-09-15 22:25:45', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', '2021-09-15 22:25:45', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 4, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', '2021-09-15 22:25:45', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 5, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', '2021-09-15 22:25:45', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 6, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', '2021-09-15 22:25:45', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 8, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', '2021-09-15 22:25:45', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', '2021-09-15 22:25:45', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', '2021-09-15 22:25:45', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', '2021-09-15 22:25:45', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', '2021-09-15 22:25:45', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '博文管理', 3, 1, 'article', 'blog/article/index', NULL, 1, 0, 'C', '0', '0', 'admin:article:list', 'article', '2021-09-22 20:27:49', '2021-09-22 20:28:47', '');
INSERT INTO `sys_menu` VALUES (115, '分类管理', 3, 2, 'classify', 'blog/classify/index', '', 1, 0, 'C', '0', '0', 'admin:category:list', 'category', '2021-09-15 22:25:45', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (116, '标签管理', 3, 3, 'tag', 'blog/tag/index', '', 1, 0, 'C', '0', '0', 'admin:tag:list', 'tag', '2021-09-15 22:25:45', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (117, '评论管理', 3, 4, 'comment', 'blog/comment/index', NULL, 1, 0, 'C', '0', '0', 'admin:comment:list', 'comment', '2021-09-22 21:01:43', NULL, '');
INSERT INTO `sys_menu` VALUES (118, '轮播图管理', 3, 5, 'carousel', 'blog/carousel/index', NULL, 1, 0, 'C', '0', '0', 'admin:carousel:list', 'slideshow', '2021-09-22 21:07:39', NULL, '');
INSERT INTO `sys_menu` VALUES (119, '友链管理', 3, 6, 'friend', 'blog/friend/index', NULL, 1, 0, 'C', '0', '0', 'admin:friend:list', 'link-exchange', '2021-09-22 20:39:31', '2021-09-22 20:52:44', '');
INSERT INTO `sys_menu` VALUES (120, '关于', 3, 7, 'about', 'blog/about/index', NULL, 1, 0, 'C', '0', '0', 'admin:about:list', 'about', '2021-10-24 00:53:01', NULL, '');
INSERT INTO `sys_menu` VALUES (122, '系统接口', 1, 7, 'swagger', 'blog/swagger/index', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', '2021-09-15 22:25:45', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'system/log/operate/index', '', 1, 0, 'C', '0', '0', 'system:operatelog:list', 'form', '2021-09-15 22:25:45', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'login', 'system/log/login/index', '', 1, 0, 'C', '0', '0', 'system:loginlog:list', 'logininfor', '2021-09-15 22:25:45', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (502, '访问日志', 108, 3, 'visitlog', 'system/log/visit/index', NULL, 1, 0, 'C', '0', '0', 'system:visitlog:list', 'logininfor', '2021-10-24 20:55:47', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:operatelog:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:operatelog:remove', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:operatelog:export', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:loginlog:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:loginlog:remove', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:loginlog:export', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', '2021-09-15 22:25:45', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '访问查询', 502, 1, '#', NULL, NULL, 1, 0, 'F', '0', '0', 'system:visitlog:query', '#', '2021-10-24 21:00:17', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '访问删除', 502, 2, '#', NULL, NULL, 1, 0, 'F', '0', '0', 'system:visitlog:remove', '#', '2021-10-24 21:01:30', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '日志导出', 502, 3, '#', NULL, NULL, 1, 0, 'F', '0', '0', 'system:visitlog:export', '#', '2021-10-24 21:02:58', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, '文章查询', 114, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:article:query', '#', '2021-10-23 20:45:47', NULL, '');
INSERT INTO `sys_menu` VALUES (2001, '文章新增', 114, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:article:add', '#', '2021-10-23 20:45:47', NULL, '');
INSERT INTO `sys_menu` VALUES (2002, '文章修改', 114, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:article:edit', '#', '2021-10-23 20:45:47', NULL, '');
INSERT INTO `sys_menu` VALUES (2003, '文章删除', 114, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:article:remove', '#', '2021-10-23 20:45:47', NULL, '');
INSERT INTO `sys_menu` VALUES (2004, '文章导出', 114, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:article:export', '#', '2021-10-23 20:45:47', NULL, '');
INSERT INTO `sys_menu` VALUES (2005, '轮播图查询', 118, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:carousel:query', '#', '2021-10-23 20:48:22', NULL, '');
INSERT INTO `sys_menu` VALUES (2006, '轮播图新增', 118, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:carousel:add', '#', '2021-10-23 20:48:22', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '轮播图修改', 118, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:carousel:edit', '#', '2021-10-23 20:48:22', NULL, '');
INSERT INTO `sys_menu` VALUES (2008, '轮播图删除', 118, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:carousel:remove', '#', '2021-10-23 20:48:22', NULL, '');
INSERT INTO `sys_menu` VALUES (2009, '轮播图导出', 118, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:carousel:export', '#', '2021-10-23 20:48:22', NULL, '');
INSERT INTO `sys_menu` VALUES (2010, '评论查询', 117, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:comment:query', '#', '2021-10-23 20:52:21', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '评论新增', 117, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:comment:add', '#', '2021-10-23 20:52:21', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '评论修改', 117, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:comment:edit', '#', '2021-10-23 20:52:21', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '评论删除', 117, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:comment:remove', '#', '2021-10-23 20:52:21', NULL, '');
INSERT INTO `sys_menu` VALUES (2014, '评论导出', 117, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:comment:export', '#', '2021-10-23 20:52:21', NULL, '');
INSERT INTO `sys_menu` VALUES (2015, '标签查询', 116, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:tag:query', '#', '2021-10-23 20:54:27', NULL, '');
INSERT INTO `sys_menu` VALUES (2016, '标签新增', 116, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:tag:add', '#', '2021-10-23 20:54:27', NULL, '');
INSERT INTO `sys_menu` VALUES (2017, '标签修改', 116, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:tag:edit', '#', '2021-10-23 20:54:27', NULL, '');
INSERT INTO `sys_menu` VALUES (2018, '标签删除', 116, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:tag:remove', '#', '2021-10-23 20:55:56', NULL, '');
INSERT INTO `sys_menu` VALUES (2019, '标签导出', 116, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:tag:export', '#', '2021-10-23 20:55:56', NULL, '');
INSERT INTO `sys_menu` VALUES (2020, '关于我查询', 120, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:about:query', '#', '2021-10-23 20:58:54', NULL, '');
INSERT INTO `sys_menu` VALUES (2021, '关于我新增', 120, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:about:add', '#', '2021-10-23 20:58:54', NULL, '');
INSERT INTO `sys_menu` VALUES (2022, '关于我修改', 120, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:about:edit', '#', '2021-10-23 20:58:54', NULL, '');
INSERT INTO `sys_menu` VALUES (2023, '关于我删除', 120, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:about:remove', '#', '2021-10-23 20:58:54', NULL, '');
INSERT INTO `sys_menu` VALUES (2024, '关于我导出', 120, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:about:export', '#', '2021-10-23 20:58:54', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '分类查询', 115, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:category:query', '#', '2021-10-23 21:02:06', NULL, '');
INSERT INTO `sys_menu` VALUES (2026, '分类新增', 115, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:category:add', '#', '2021-10-23 21:02:06', NULL, '');
INSERT INTO `sys_menu` VALUES (2027, '分类修改', 115, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:category:edit', '#', '2021-10-23 21:02:06', NULL, '');
INSERT INTO `sys_menu` VALUES (2028, '分类删除', 115, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:category:remove', '#', '2021-10-23 21:02:06', NULL, '');
INSERT INTO `sys_menu` VALUES (2029, '分类导出', 115, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:category:export', '#', '2021-10-23 21:02:06', NULL, '');
INSERT INTO `sys_menu` VALUES (2030, '友链查询', 119, 1, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:friend:query', '#', '2021-10-24 00:45:40', NULL, '');
INSERT INTO `sys_menu` VALUES (2031, '友链新增', 119, 2, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:friend:add', '#', '2021-10-24 00:45:40', NULL, '');
INSERT INTO `sys_menu` VALUES (2032, '友链修改', 119, 3, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:friend:edit', '#', '2021-10-24 00:45:40', NULL, '');
INSERT INTO `sys_menu` VALUES (2033, '友链删除', 119, 4, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:friend:remove', '#', '2021-10-24 00:45:40', NULL, '');
INSERT INTO `sys_menu` VALUES (2034, '友链导出', 119, 5, '#', '', NULL, 1, 0, 'F', '0', '0', 'admin:friend:export', '#', '2021-10-24 00:45:40', NULL, '');
COMMIT;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通知公告表';

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
BEGIN;
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2021-09-15 22:25:45', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2021-09-15 22:25:45', '', NULL, '管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
  `operate_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '模块标题',
  `log_type` int DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `operator` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作人员',
  `operate_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
  `operate_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '主机地址',
  `operate_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作地点',
  `operate_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
  `status` int DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '错误消息',
  `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`operate_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_operate_log` VALUES (1, '操作日志', 9, 'com.aurora.system.controller.SysOperateLogController.clean()', 'DELETE', 1, 'admin', '/system/operateLog/clean', '127.0.0.1', '内网IP', '', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-10-28 23:01:53');
INSERT INTO `sys_operate_log` VALUES (2, '登录日志', 9, 'com.aurora.system.controller.SysLoginLogController.clean()', 'DELETE', 1, 'admin', '/system/loginLog/clean', '127.0.0.1', '内网IP', '', '{\"msg\":\"OK\",\"code\":200}', 0, '', NULL);
INSERT INTO `sys_operate_log` VALUES (3, '标签', 1, 'com.aurora.admin.controller.BlogTagController.add()', 'POST', 1, 'admin', '/admin/tag', '127.0.0.1', '内网IP', '{\"createTime\":1635764703236,\"tagId\":3,\"name\":\"Mysql\",\"remark\":\"数据库\",\"updateTime\":1635764703236}', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:05:03');
INSERT INTO `sys_operate_log` VALUES (4, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":4,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:05:23');
INSERT INTO `sys_operate_log` VALUES (5, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":10,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:05:40');
INSERT INTO `sys_operate_log` VALUES (6, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":9,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:05:50');
INSERT INTO `sys_operate_log` VALUES (7, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":8,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:06:02');
INSERT INTO `sys_operate_log` VALUES (8, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":6,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:06:12');
INSERT INTO `sys_operate_log` VALUES (9, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":7,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:06:27');
INSERT INTO `sys_operate_log` VALUES (10, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":5,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:06:37');
INSERT INTO `sys_operate_log` VALUES (11, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":11,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:06:55');
INSERT INTO `sys_operate_log` VALUES (12, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":12,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:07:04');
INSERT INTO `sys_operate_log` VALUES (13, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":13,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:07:12');
INSERT INTO `sys_operate_log` VALUES (14, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":14,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:07:19');
INSERT INTO `sys_operate_log` VALUES (15, '文章', 2, 'com.aurora.admin.controller.BlogArticleController.edit()', 'PUT', 1, 'admin', '/admin/article', '127.0.0.1', '内网IP', '{\"isRecommend\":true,\"author\":\"admin\",\"contentFormat\":\"Java基础\\n1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\nString str1 = &quot;通话&quot;;\\nString str2 = &quot;重地&quot;;\\nSystem.out.println(String.format(&quot;str1：%d | str2：%d&quot;,  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n\\n执行的结果： \\nstr1：1179395 | str2：1179395\\nfalse\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n\\npublic int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表的性能==。\\nhashCode 的常规协定是： \\n&emsp;&emsp;在 Java 应用程序执行期间，在对同一对象多次调用hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行equals比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。\\n\\n&emsp;&emsp;如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode 方法不要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。\\n<strong>总的来说：重写equals方法时请必须重写hashcode，以保证equals方法相等时两个对象hashcode返回相同的值。如果你不按照规范来，就不一定相同了。</strong>\\n\",\"articleId\":15,\"description\":\"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。\",\"updateTime\":1630990204000,\"title\":\"2019Java最新面试题——Java基础（持续更新）\",\"content\":\"### Java基础\\n#### 1. 两个对象的 hashCode()相同，则 equals()也一定为 true，对吗？两个对象用equals方法比较为true，它们的hashcode值相同吗？\\n不对，两个对象的hashCode相同，equals()不一定true，equals()方法为true，他们的hashcode也不一定相同。\\n代码实例:\\n```java\\nString str1 = \\\"通话\\\";\\nString str2 = \\\"重地\\\";\\nSystem.out.println(String.format(\\\"str1：%d | str2：%d\\\",  str1.hashCode(),str2.hashCode()));\\nSystem.out.println(str1.equals(str2));\\n```\\n执行的结果： \\n\\nstr1：1179395 | str2：1179395\\n\\nfalse\\n\\n很显然“通话”和“重地”的 hashCode() 相同，然而 equals() 则为 false，因为在散列表中，hashCode()相等即两个键值对的哈希值相等，然而哈希值相等，并不一定能得出键值对相等。\\n\\n此外，两个对象用equals方法比较为true，它们的hashcode值也不一定相同\\n下面的话来自JDK：\\n&gt; public int hashCode()返回该对象的哈希码值。支持此方法是为了==提高哈希表', '{\"msg\":\"OK\",\"code\":200}', 0, '', '2021-11-01 19:07:35');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色名字',
  `role_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色code值',
  `role_sort` int DEFAULT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色状态（0正常 1正常）',
  `menu_check_strictly` tinyint(1) DEFAULT NULL COMMENT '菜单树选择项是否关联显示',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 1, '0', 1, '2021-01-30 18:09:48', '2021-01-30 18:09:51', '管理员');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL COMMENT '角色id',
  `menu_id` bigint NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1, 100);
INSERT INTO `sys_role_menu` VALUES (2, 1, 101);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户邮箱',
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户电话',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像',
  `status` tinyint(1) DEFAULT NULL COMMENT '用户状态',
  `sex` int(1) unsigned zerofill DEFAULT '0' COMMENT '性别 0男 1女',
  `user_type` int(1) unsigned zerofill DEFAULT '0' COMMENT '用户类型 0后台 1门户 3第三方',
  `login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录ip',
  `login_time` datetime DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime(6) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'jack', '$2a$10$ApYW1WoU6A4LYCq3vr15wu5SczBHyviCWe.DlZOUpJnT8BYI8JgSa', '564559079@qq.com', '18881204330', '', 0, 0, 0, '127.0.0.1', '2021-11-01 19:04:15', '2021-01-30 18:14:56.000000', '2021-11-01 19:04:15.045000', '超级用户');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_visit_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_visit_log`;
CREATE TABLE `sys_visit_log` (
  `visit_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求的模块',
  `blog_id` int DEFAULT NULL COMMENT '博客文章的id',
  `visit_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'ip地址',
  `visit_location` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '访问地址',
  `browser` varchar(64) DEFAULT NULL COMMENT '浏览器类型',
  `os` varchar(64) DEFAULT NULL COMMENT '操作系统',
  `entry_url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求地址',
  `url` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '请求地址',
  `spider` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '爬虫',
  `error_msg` varchar(256) DEFAULT NULL COMMENT '访问错误的提示信息',
  `status` int DEFAULT NULL COMMENT '访问状态,0表示正常，1表示不正常',
  `visit_time` datetime DEFAULT NULL,
  PRIMARY KEY (`visit_id`),
  UNIQUE KEY `visit_id` (`visit_id`),
  KEY `ip_addr` (`visit_ip`)
) ENGINE=InnoDB AUTO_INCREMENT=25805 DEFAULT CHARSET=utf8mb3 COMMENT='访问日志记录';

-- ----------------------------
-- Records of sys_visit_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_visit_log` VALUES (1, '首页', NULL, '127.0.0.1', '四川省成都市', '谷歌', 'osx', NULL, '/index', NULL, NULL, 0, '2021-10-24 21:32:35');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
