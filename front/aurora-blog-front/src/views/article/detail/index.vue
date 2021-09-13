<template>
  <div>
    <CommonLayout>
      <div slot="content">
        <div class="article-page-header">
          <div class="tags">
            <el-tag  effect="dark" :type="index | mapTagColors" v-for="(tag , index) in article.tagList" :key="tag.id">{{ tag.name }}
            </el-tag>
          </div>
          <p class="title">{{ article.title }}</p>
          <el-row>
            <el-col :xs="24" :sm="10" :md="10" :lg="10" style="padding-left: 0;padding-right: 0;">
              <p class="info"><span class="author"><a><i class="iconfont icon-zuozhe"></i>{{ article.author }}</a></span><span
                  class="publish-time"> <a><i class="iconfont icon-shijian"></i>{{ article.createTime | socialDate }}</a></span></p>
            </el-col>
            <el-col :xs="24" :sm="14" :md="14" :lg="14" style="padding-left: 0;padding-right: 0;">
              <p class="operate_info">
                <span class="comment"><a><i class="iconfont icon-comment"></i> {{ article.readNum }} 阅读</a></span>
                <span class="readings"><a><i class="iconfont icon-ico_yueduliang"></i> {{ article.readNum }} 阅读</a></span>
                <span class="likes"><a @click="likePost(article)"><i class="iconfont icon-xihuan"></i> {{ article.likeNum }} 喜欢</a></span>
              </p>
            </el-col>
          </el-row>
          <p class="abstract" v-if="article.description">
            {{ article.description }}
          </p>
        </div>
        <div id="article-page-content">
          <article id="article-main-page" class="typo container" ref="article"
                   v-html="article.contentFormat">
          </article>
        </div>
      </div>
      <iv-affix :offset-top="60" slot="side-toc">
        <SideToc></SideToc>
      </iv-affix>
    </CommonLayout>
  </div>
</template>

<script>
import CommonLayout from '../../../components/layout/BaseLayout/common'
import SideToc from '../../../components/aside/sidetoc'
import TOC from '../../../common/js/MarkdownToc'
// TOC滚动监听
import TocScrollSpy from '../../../common/js/TocScrollSpy'

export default {
  data() {
    return {
      article: {
        "id":4,
        "title":"用最简单的话告诉你什么是ElasticSearch",
        "description":"Elasticsearch 是一个分布式可扩展的实时搜索和分析引擎,一个建立在全文搜索引擎 Apache Lucene(TM) 基础上的搜索引擎.当然 Elasticsearch 并不仅仅是 Lucene 那么简单，下面就介绍ElasticSearch为什么是分布式的，可扩展，高性能，高可用。\n\n\n作者：fallinjava\n链接：https://juejin.im/post/5c790b4b51882545194f84f0\n来源：掘金\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。",
        "author":"Bobbi",
        "content":"\n> 转载:https://juejin.im/post/5c790b4b51882545194f84f0\n\n## 1.什么是搜索\n在我们想知道一些信息时，就会使用一些搜索引擎来获取我们想要的数据，比如搜索我们喜欢的一款游戏，或者喜欢的一本书等等，这就是提到搜索的的第一印象，说直白点就是在任何场景下找寻你想要知道的信息，这就是搜索。\n- 现在的搜索也称为垂直搜索 垂直搜索引针对某一个行业的专业搜索引擎，比如说电商网站，新闻网站，各种app内部等等，他们都是搜索引擎的细分和延伸，在抽取出需要的数据进行处理后再以某种形式返回给用户。\n\n## 2.如果用数据库来做搜索会怎么样\n例如我们这里有一张商品表，现在我们要搜索\"衣服\"这个关键字，，执行了 select * from products where product_name like %衣服%，（假设这里没有其他任何提升效率的设置）来进行搜索，或者进行其他字段的匹配，可以分析一下这个方式的缺点。\n\n1. 比如说，每条记录的指定字段的数据会很长，比如说“商品介绍”这个字段，可能会有几千或者几万个字符，那么搜索的时候就会去这些字符里面进行匹配是否包含要搜索的关键词。\n2. 这种方式只能搜索到完全包含“衣服”这个两个字符的记录，但是可能会有一些特殊的情况，某几条记录里面的“衣服”关键词并不是连续的，可能衣服中间会插入某些字符，这个时候就搜索不出来这些记录了，但是这个商品又是我们希望搜索出来的，这个时候这种方式的弊端就十分明显了。\n\n总的来说用数据库来实现搜索是不太靠谱的，性能会很差。\n\n## 3.什么是全文检索\n首先需要了解什么是倒排索引？我们这里先上一幅图，里面有4条记录。\n![1.jpg](http://oss.dblearn.cn/dbblog/20190305/fb6809d46e7744e78c5a686bbd096506.jpg)\n现在将这4条记录的内容进行拆分成一些词条，这个过程叫做分词\n![2.jpg](http://oss.dblearn.cn/dbblog/20190305/0271f31d280f49b68ae9e942f286a3e4.jpg)\n现在我们得到了这4条记录拆分出来词语，然后将这写词语放到一个列表中，并记录他们的ID，这个分析出来的就是 倒排索引\n![3.jpg](http://oss.dblearn.cn/dbblog/20190305/567fa7ca43d34eab886a5b8dc4c83b4f.jpg)\n\n现在我们输入 生化电影 这个关键词，这个时候搜素引擎将我们输入的内容分词为 生化 和 电影 这两个关键词，然后使用这个两个关键词去倒排索引里面匹配，发现包含 生化 这个关键词的记录有ID为 1,2,3,4这四条记录，包含 电影 这个关键词的有ID为1这条记录，由于ID为1这条记录已经被录入了，所以就被排除在外了， 这时候我们就得到了想要ID为1234这4条记录，同理，如果我们只输入 电影 这个关键词，那么符合条件的只有ID为1这条记录了。  \n\n全文检索就是从拆分词语，存入倒排索引，然后分析用户输入的内容，在倒排索引里面进行匹配，这个过程就是全文检索。\n\n## 4.什么是ElasticSearch\n  首先需要知道什么是Lucence，Lucence它就是一个Java的jar包，里面实现了倒排索引的算法和其他的全文检索相关的东西，ElasticSearch就是对Lunence进行了封装，为什么有Lucence了还要ElasticSeaearch来干什么呢？  \n\n 当数据量很大的时候，比如有1PB的数据，这个时候数据放在同一台机器上基本就不行了，那么把数据分开来放在多台机器上呢？那就变成分布式了，这个时候数据前端获取数据的时候到底去那一台机器上面去获取数据呢？这个时候就很麻烦了，如果某一台机器宕机了，那么这个机器上的数据就获取不到了，这也就无法保证高可用性了，还有数据存储的时候怎么到底存入那台机器等等，这些都需要人为的处理和维护。这个时候ElasticSearch就应运而生了，它就将Lucence这些弊端给完全解决了。  \n\n举例一些优点：\n\n1. 高性能，自动维护数据分布到多个节点进行索引的建立，还有搜索请求分布到多个节点的执行。\n2. 高可用，自动维护数据的冗余副本，保证说，一些机器宕机了，不会造成数据的丢失。\n3. 封装了更多的高级功能，以给我们提供更多的高级支持，让我们快速的开发应用，开发更加复杂的应用，复杂的搜索功能，聚合分析的功能，基于地理位置的搜索(比如周围一公里内有几家咖啡厅)等等。\n4. 动态扩容，当我们数据量急剧提升的时候，我们只需要增加机器就行了，比如两台机器存放1.2T数据，那么没台机器存放就是600G，但是如果600G对于服务器的压力太大了，这个时候就需要增加第三台机器，让他们每人负责400G的数据，这个过程不需要人为的去分配，只需要将汲取加入集群中就自动完成。\n\n\n",
        "readNum":14,
        "likeNum":1,
        "cover":"https://img1.baidu.com/it/u=3886212450,854269223&fm=26&fmt=auto&gp=0.jpg",
        "coverType":0,
        "createTime":1552172978000,
        "updateTime":1551812396000,
        "recommend":false,
        "categoryId":"9,11,12",
        "publish":true,
        "top":false,
        "contentFormat":"<blockquote>\n<p>转载:<a href=\"https://juejin.im/post/5c790b4b51882545194f84f0\">https://juejin.im/post/5c790b4b51882545194f84f0</a></p>\n</blockquote>\n<h2 id=\"1-\">1.什么是搜索</h2>\n<p>在我们想知道一些信息时，就会使用一些搜索引擎来获取我们想要的数据，比如搜索我们喜欢的一款游戏，或者喜欢的一本书等等，这就是提到搜索的的第一印象，说直白点就是在任何场景下找寻你想要知道的信息，这就是搜索。</p>\n<ul>\n<li>现在的搜索也称为垂直搜索 垂直搜索引针对某一个行业的专业搜索引擎，比如说电商网站，新闻网站，各种app内部等等，他们都是搜索引擎的细分和延伸，在抽取出需要的数据进行处理后再以某种形式返回给用户。</li>\n</ul>\n<h2 id=\"2-\">2.如果用数据库来做搜索会怎么样</h2>\n<p>例如我们这里有一张商品表，现在我们要搜索衣服这个关键字，，执行了 select * from products where product_name like %衣服%，（假设这里没有其他任何提升效率的设置）来进行搜索，或者进行其他字段的匹配，可以分析一下这个方式的缺点。</p>\n<ol>\n<li>比如说，每条记录的指定字段的数据会很长，比如说“商品介绍”这个字段，可能会有几千或者几万个字符，那么搜索的时候就会去这些字符里面进行匹配是否包含要搜索的关键词。</li>\n<li>这种方式只能搜索到完全包含“衣服”这个两个字符的记录，但是可能会有一些特殊的情况，某几条记录里面的“衣服”关键词并不是连续的，可能衣服中间会插入某些字符，这个时候就搜索不出来这些记录了，但是这个商品又是我们希望搜索出来的，这个时候这种方式的弊端就十分明显了。</li>\n</ol>\n<p>总的来说用数据库来实现搜索是不太靠谱的，性能会很差。</p>\n<h2 id=\"3-\">3.什么是全文检索</h2>\n<p>首先需要了解什么是倒排索引？我们这里先上一幅图，里面有4条记录。\n<img src=\"http://oss.dblearn.cn/dbblog/20190305/fb6809d46e7744e78c5a686bbd096506.jpg\" alt=\"1.jpg\">\n现在将这4条记录的内容进行拆分成一些词条，这个过程叫做分词\n<img src=\"http://oss.dblearn.cn/dbblog/20190305/0271f31d280f49b68ae9e942f286a3e4.jpg\" alt=\"2.jpg\">\n现在我们得到了这4条记录拆分出来词语，然后将这写词语放到一个列表中，并记录他们的ID，这个分析出来的就是 倒排索引\n<img src=\"http://oss.dblearn.cn/dbblog/20190305/567fa7ca43d34eab886a5b8dc4c83b4f.jpg\" alt=\"3.jpg\"></p>\n<p>现在我们输入 生化电影 这个关键词，这个时候搜素引擎将我们输入的内容分词为 生化 和 电影 这两个关键词，然后使用这个两个关键词去倒排索引里面匹配，发现包含 生化 这个关键词的记录有ID为 1,2,3,4这四条记录，包含 电影 这个关键词的有ID为1这条记录，由于ID为1这条记录已经被录入了，所以就被排除在外了， 这时候我们就得到了想要ID为1234这4条记录，同理，如果我们只输入 电影 这个关键词，那么符合条件的只有ID为1这条记录了。  </p>\n<p>全文检索就是从拆分词语，存入倒排索引，然后分析用户输入的内容，在倒排索引里面进行匹配，这个过程就是全文检索。</p>\n<h2 id=\"4-elasticsearch\">4.什么是ElasticSearch</h2>\n<p>  首先需要知道什么是Lucence，Lucence它就是一个Java的jar包，里面实现了倒排索引的算法和其他的全文检索相关的东西，ElasticSearch就是对Lunence进行了封装，为什么有Lucence了还要ElasticSeaearch来干什么呢？  </p>\n<p> 当数据量很大的时候，比如有1PB的数据，这个时候数据放在同一台机器上基本就不行了，那么把数据分开来放在多台机器上呢？那就变成分布式了，这个时候数据前端获取数据的时候到底去那一台机器上面去获取数据呢？这个时候就很麻烦了，如果某一台机器宕机了，那么这个机器上的数据就获取不到了，这也就无法保证高可用性了，还有数据存储的时候怎么到底存入那台机器等等，这些都需要人为的处理和维护。这个时候ElasticSearch就应运而生了，它就将Lucence这些弊端给完全解决了。  </p>\n<p>举例一些优点：</p>\n<ol>\n<li>高性能，自动维护数据分布到多个节点进行索引的建立，还有搜索请求分布到多个节点的执行。</li>\n<li>高可用，自动维护数据的冗余副本，保证说，一些机器宕机了，不会造成数据的丢失。</li>\n<li>封装了更多的高级功能，以给我们提供更多的高级支持，让我们快速的开发应用，开发更加复杂的应用，复杂的搜索功能，聚合分析的功能，基于地理位置的搜索(比如周围一公里内有几家咖啡厅)等等。</li>\n<li>动态扩容，当我们数据量急剧提升的时候，我们只需要增加机器就行了，比如两台机器存放1.2T数据，那么没台机器存放就是600G，但是如果600G对于服务器的压力太大了，这个时候就需要增加第三台机器，让他们每人负责400G的数据，这个过程不需要人为的去分配，只需要将汲取加入集群中就自动完成。</li>\n</ol>\n",
        "categoryListStr":null,
        "tagList":[
          {
            "id":9,
            "name":"ElasticSearch",
            "type":0
          },
          {
            "id":7,
            "name":"Java",
            "type":0
          }
        ]
      },
    };
  },

  components: {
    CommonLayout,
    SideToc
  },

  computed: {},

  mounted() {
    this.addCodeLineNumber()
    this.refreshDiectory()
  },

  methods: {
    likePost(post) {
      this.$http({
        url: this.$http.adornUrl('/article/like/' + post.id),
        method: 'put',
        data: this.$http.adornData()
      }).then(({data}) => {
        if (data && data.code === 200) {
          post.likeNum += 1
          this.$Message.success('点赞成功')
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    addCodeLineNumber () {
      // 添加行号
      let blocks = this.$refs.article.querySelectorAll('pre code')
      blocks.forEach((block) => {
        window.hljs.highlightBlock(block)
        // 去前后空格并添加行号
        block.innerHTML = '<ul><li>' + block.innerHTML.replace(/(^\s*)|(\s*$)/g, '').replace(/\n/g, '\n</li><li>') + '\n</li></ul>'
      })
    },
    getArticle (articleId) {
      this.$http({
        url: this.$http.adornUrl('/article/' + articleId),
        method: 'get'
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.article = data.article
          // 更新目录、高亮代码
          this.$nextTick(function () {
            this.addCodeLineNumber()
            this.refreshDiectory()
            this.refreshMobileDirectory()
            document.title = this.article.title + ' | Bobbi的个人博客 | 一个努力成长中的Java后端程序猿'
          })
        }
      })
    },
    refreshDiectory () {
      /* eslint-disable*/
      new TOC('article-main-page', {
        'level': 5,
        'top': 200,
        'class': 'list',
        'targetId': 'side-toc'
      })
      /* eslint-disable */
      new TocScrollSpy('article-main-page', 'side-toc', {
        'spayLevel': 5,
        'articleMarginTop': 0
      })
    },
    // refreshMobileDirectory () {
    //   /* eslint-disable */
    //   new TOC('article-main-page', {
    //     'level': 5,
    //     'top': 200,
    //     'class': 'list',
    //     'targetId': 'sidebar-toc'
    //   })
    //   new TocScrollSpy('article-main-page', 'sidebar-toc', {
    //     'spayLevel': 5,
    //     'articleMarginTop': 15
    //   })
    // }
  }
}
</script>

<style scoped>
@import "../../../assets/css/article.css";
.article-page-header {
  text-align: left;
  padding: 25px 5px 10px 5px;
}

@media only screen and (max-width: 768px) {
  .article-page-header {
    padding-top: 10px;
  }
}

@media screen and (min-width: 768px) {
  .article-page-header {
    padding-top: 10px;
  }
}

@media screen and (min-width: 992px) {
  .article-page-header {
    padding-top: 25px;
  }
}

.article-page-header .tags {
  margin-bottom: 18px;
}

.article-page-header .tags span{
  margin-right: 5px;
}

.article-page-header .title {
  font-size: 27px;
  line-height: 33px;
  font-weight: 500;
  color: #333;
  margin-bottom: 23px;
}

.article-page-header .info {
  margin-top: 10px;
  font-size: 14px;
  line-height: 18px;
  font-weight: 200;
}

.article-page-header .info a {
  color: #777;
  cursor: pointer;
}

.article-page-header .info a:hover {
  color: #409EFF;
  text-decoration: none;
}

.article-page-header .info .publish-time {
  margin-left: 20px;
}

.article-page-header .operate_info {
  text-align: right;
  font-size: 14px;
  margin: 15px 0;
}

@media only screen and (max-width: 768px) {
  .article-page-header .operate_info {
    text-align: left;
  }
}

.article-page-header .operate_info span {
  margin-right: 10px;
}

.article-page-header .operate_info span + span {
  margin-left: 10px;
}

.article-page-header .operate_info span a {
  cursor: pointer;
}

.article-page-header .operate_info span a:hover {
  color: #409EFF;
  text-decoration: none;
}

.article-page-header .abstract {
  color: #444;
  border: 1px dashed #eee;
  background: #eee;
}

@media only screen and (max-width: 768px) {
  .article-page-header .abstract {
    padding: 8px;
    margin-top: 8px;
    font-size: 13px;
    line-height: 18px;
  }
}

@media screen and (min-width: 768px) {
  .article-page-header .abstract {
    padding: 15px;
    margin-top: 8px;
    font-size: 14px;
    line-height: 24px;
  }
}

@media screen and (min-width: 768px) {
  .article-page-header .abstract {
    padding: 20px;
    margin-top: 15px;
    line-height: 28px;
  }
}

@media screen and (min-width: 1200px) {
  .article-page-header .abstract {
    padding: 20px;
    margin-top: 15px;
    line-height: 28px;
  }
}

#article-page-content {
  padding: 5px
}
</style>
