<template>
  <div class="hotRead">
    <panel :title="'最热阅读'">
      <div slot="content" class="content">
        <div class="top">
          <a :href="'/' + topHotRead.urlType + '/' + topHotRead.linkId">
            <p class="title">{{ topHotRead.title }}</p>
            <div class="tags">
              <el-tag :type="index | mapTagColors" v-for="(tag , index)  in topHotRead.tagList" :key="tag.id"
                      effect="dark" size="mini" style="margin-right: 4px; margin-bottom:5px; float: left;">
                {{ tag.name }}
              </el-tag>
            </div>
            <div class="img">
              <img :src="topHotRead.cover" alt="">
            </div>
            <p class="desc">{{ topHotRead.description | textLineBreak(60) }}</p>
            <p class="info">
              <span class="time"><a><i class="iconfont icon-shijian"></i>{{
                  topHotRead.createTime | socialDate
                }}</a></span>
              <span class="likes"><a><i class="iconfont icon-xihuan"></i> {{ topHotRead.likeNum }} </a></span>
              <span class="comments"><a><i class="iconfont icon-comment"></i>{{ topHotRead.commentNum }} </a></span>
              <span class="readings"><a><i
                  class="iconfont icon-ico_yueduliang"></i> {{ topHotRead.readNum }} </a></span>
            </p>
          </a>
        </div>
        <ul class="others">
          <li v-for="hotRead in hotReadList" :key="hotRead.id">
            <a :href="'/' + hotRead.urlType + '/' +hotRead.linkId">
              <p class="title">{{ hotRead.title }}</p>
              <p class="info">
                <span class="time"><a><i class="iconfont icon-shijian"></i>{{
                    hotRead.createTime | socialDate
                  }}</a></span>
                <span class="likes"><a><i class="iconfont icon-xihuan"></i> {{ hotRead.likeNum }} </a></span>
                <span class="comments"><a><i class="iconfont icon-comment"></i> {{ hotRead.commentNum }} </a></span>
                <span class="readings"><a><i class="iconfont icon-ico_yueduliang"></i> {{ hotRead.readNum }} </a></span>
              </p>
            </a>
          </li>
        </ul>
      </div>
    </panel>
  </div>
</template>

<script>

export default {
  data() {
    return {
      hotReadList: [{
        "id": 3,
        "linkId": 3,
        "type": 0,
        "orderNum": null,
        "title": "2019Java最新面试题——Java基础（持续更新）",
        "top": false,
        "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
        "readNum": 36,
        "commentNum": 66,
        "likeNum": 2,
        "urlType": "article",
        "cover": "https://img1.baidu.com/it/u=3886212450,854269223&fm=26&fmt=auto&gp=0.jpg",
        "createTime": 1552172978000,
        "tagList": [
          {
            "id": 7,
            "name": "Java",
            "type": 0
          },
          {
            "id": 8,
            "name": "面试",
            "type": 0
          }
        ]
      },
        {
          "id": 4,
          "linkId": 4,
          "type": 0,
          "orderNum": null,
          "title": "用最简单的话告诉你什么是ElasticSearch",
          "top": false,
          "description": "Elasticsearch 是一个分布式可扩展的实时搜索和分析引擎,一个建立在全文搜索引擎 Apache Lucene(TM) 基础上的搜索引擎.当然 Elasticsearch 并不仅仅是 Lucene 那么简单，下面就介绍ElasticSearch为什么是分布式的，可扩展，高性能，高可用。\n\n\n作者：fallinjava\n链接：https://juejin.im/post/5c790b4b51882545194f84f0\n来源：掘金\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。",
          "readNum": 13,
          "commentNum": 66,
          "likeNum": 0,
          "urlType": "article",
          "cover": "https://img1.baidu.com/it/u=3886212450,854269223&fm=26&fmt=auto&gp=0.jpg",
          "createTime": 1552172978000,
          "tagList": [
            {
              "id": 9,
              "name": "ElasticSearch",
              "type": 0
            },
            {
              "id": 7,
              "name": "Java",
              "type": 0
            }
          ]
        },
        {
          "id": 1,
          "linkId": 1,
          "type": 2,
          "orderNum": null,
          "title": "Java虚拟机01——Java内存数据区域和内存溢出异常",
          "top": null,
          "description": "Java虚拟机在执行Java程序的过程中会把它所管理的内存划分为若干个不同的数据区域。这些区域都有各自的用途，以及创建和销毁的时间，有的区域随着虚拟机进程的启动而存在，有些区域则依赖用户线程的启动和结束而建立和销毁.",
          "readNum": 12,
          "commentNum": 66,
          "likeNum": 1,
          "urlType": "bookNote",
          "cover": null,
          "createTime": 1552173065000,
          "tagList": null
        },
        {
          "id": 5,
          "linkId": 5,
          "type": 0,
          "orderNum": null,
          "title": "性能分析利器《Arthas》总结",
          "top": false,
          "description": "Arthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。",
          "readNum": 8,
          "commentNum": 66,
          "likeNum": 23,
          "urlType": "article",
          "cover": null,
          "createTime": 1552172978000,
          "tagList": [
            {
              "id": 10,
              "name": "Arthas",
              "type": 0
            }
          ]
        }],
      topHotRead: {
        "id": 9,
        "linkId": 3,
        "type": 0,
        "orderNum": null,
        "title": "2019Java最新面试题——Java基础（持续更新）",
        "top": false,
        "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
        "readNum": 36,
        "commentNum": 34,
        "likeNum": 2,
        "urlType": "article",
        "cover": "https://img1.baidu.com/it/u=3886212450,854269223&fm=26&fmt=auto&gp=0.jpg",
        "createTime": 1552172978000,
        "tagList": [
          {
            "id": 7,
            "name": "Java",
            "type": 0
          },
          {
            "id": 8,
            "name": "面试",
            "type": 0
          }
        ]
      }
    };
  },

  components: {},

  computed: {},

  mounted() {
  },

  methods: {}
}
</script>

<style>
.hotRead .others {
  padding-left: 0px;
}

.hotRead .content {
  padding: 5px 20px;
}

.hotRead .top a,
.hotRead .others a {
  display: block;
  overflow: hidden;
}

.hotRead .top a .tags,
.hotRead .others a .tags {
  margin-bottom: 10px;
}

.hotRead .top a .title,
.hotRead .others a .title {
  text-align: justify;
  color: #696969;
  font-size: 16px;
  line-height: 23px;
  margin-top: 0px;
  margin-bottom: 5px;
}

.hotRead .top a .info,
.hotRead .others a .info {
  height: 100%;
  overflow: hidden;
  margin: 5px 0 0px;
}

.hotRead .top a .info span,
.hotRead .others a .info span {
  float: left;
  font-size: 13px;
  line-height: 18px;
  font-weight: 100;
  color: #878D99;

}

.hotRead .top a .info span + span,
.hotRead .others a .info span + span {
  float: right;
  margin-left: 10px;
}

.hotRead .top a .info a,
.hotRead .others a .info a {
  display: inline-block;
  color: #777;
  cursor: pointer;
}

.hotRead .top a .info a:hover,
.hotRead .others a .info a:hover {
  color: #409EFF;
  text-decoration: none;
}

.hotRead .top a .img,
.hotRead .others a .img {
  padding-bottom: 40%;
  width: 100%;
  height: 0;
  margin: 5px 0;
  overflow: hidden;
}

.hotRead .top a .img img,
.hotRead .others a .img img {
  width: 100%;
  transition: All 0.4s ease-in-out;
  transform: scale(1);
  zoom: 1;
}

.hotRead .top a .desc,
.hotRead .others a .desc {
  text-align: justify;
  color: #878D99;
  font-size: 13px;
  line-height: 20px;
  margin: 5px 0 0;
}

.hotRead .top a:hover .title,
.hotRead .others a:hover .title {
  color: #409EFF;
}

.hotRead .top a:hover img,
.hotRead .others a:hover img {
  transition: All 0.4s ease-in-out;
  transform: scale(1.05);
  zoom: 1.05;
}

.hotRead .others li {
  list-style-type: none;
  margin-top: 10px;
  padding-top: 10px;
  border-top: 1px solid #eee;
}
</style>
