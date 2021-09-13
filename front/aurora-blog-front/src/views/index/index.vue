<template>
  <div>
    <CommonLayout>
      <div slot="profile">
        <Profile/>
      </div>
      <div slot="aside-content">
        <HotRead/>
      </div>
      <div slot="friend-links">
        <FriendLinks/>
      </div>
      <template v-slot:main>
        <Carousel/>
        <ArticlesHeader :main-title="'文章'" :sub-title="'Articles'"/>
        <ArticleCell v-for="article in articleList" :article="article" :key="article.id"/>
        <BrowseMore @browseMore="browseMore" :noMoreData="noMoreData"  ref="browseMore"></BrowseMore>
      </template>
    </CommonLayout>
  </div>
</template>

<script>
import HotRead from '../../components/aside/hotread'
import Profile from '../../components/aside/profile'
import FriendLinks from '../../components/aside/friendlinks'
import CommonLayout from '../../components/layout/BaseLayout/common'
import Carousel from '../../components/carousel'
import ArticlesHeader from '../../components/article/articleHeader'
import ArticleCell from '../../components/article/articleCell'

export default {
  data() {
    return {
      articleList: [{
        "id": 1,
        "title": "关于本站和博主",
        "description": "关于本站和博主",
        "author": "Bobbi",
        "content": null,
        "readNum": 54,
        "likeNum": 1,
        "cover": "https://img1.baidu.com/it/u=3886212450,854269223&fm=26&fmt=auto&gp=0.jpg",
        "coverType": 0,
        "createTime": 1552172978000,
        "updateTime": null,
        "recommend": null,
        "categoryId": null,
        "publish": null,
        "top": true,
        "contentFormat": null,
        "tagList": [
          {
            "id": 1,
            "name": "本站相关",
            "type": 0
          },
          {
            "id": 2,
            "name": "关于",
            "type": 0
          }
        ]
      },
        {
          "id": 3,
          "title": "2019Java最新面试题——Java基础（持续更新）",
          "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
          "author": "Bobbi",
          "content": null,
          "readNum": 35,
          "likeNum": 2,
          "cover": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbaidulogo.bj.bcebos.com%2Feditor%2Fk2hsdqg1baxoup1bv1rckp40pmfa264n.jpeg&refer=http%3A%2F%2Fbaidulogo.bj.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633616657&t=feff303ee93d4e8fb889a989858cd511",
          "coverType": 0,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
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
          "title": "用最简单的话告诉你什么是ElasticSearch",
          "description": "Elasticsearch 是一个分布式可扩展的实时搜索和分析引擎,一个建立在全文搜索引擎 Apache Lucene(TM) 基础上的搜索引擎.当然 Elasticsearch 并不仅仅是 Lucene 那么简单，下面就介绍ElasticSearch为什么是分布式的，可扩展，高性能，高可用。\n\n\n作者：fallinjava\n链接：https://juejin.im/post/5c790b4b51882545194f84f0\n来源：掘金\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。",
          "author": "Bobbi",
          "content": null,
          "readNum": 13,
          "likeNum": 0,
          "cover": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic4.zhimg.com%2Fv2-b329f69fdda73767bb726cea4546d8c4_1200x500.jpg&refer=http%3A%2F%2Fpic4.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633616715&t=ed44708f70f4a7c59c0b4a75856c153d",
          "coverType": 0,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
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
          "id": 5,
          "title": "性能分析利器《Arthas》总结",
          "description": "Arthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。",
          "author": "Bobbi",
          "content": null,
          "readNum": 8,
          "likeNum": 23,
          "cover": null,
          "coverType": 2,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
          "tagList": [
            {
              "id": 10,
              "name": "Arthas",
              "type": 0
            }
          ]
        }, {
          "id": 16,
          "title": "2019Java最新面试题——Java基础（持续更新）",
          "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
          "author": "Bobbi",
          "content": null,
          "readNum": 35,
          "likeNum": 2,
          "cover": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbaidulogo.bj.bcebos.com%2Feditor%2Fk2hsdqg1baxoup1bv1rckp40pmfa264n.jpeg&refer=http%3A%2F%2Fbaidulogo.bj.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633616657&t=feff303ee93d4e8fb889a989858cd511",
          "coverType": 0,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
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
        }, {
          "id": 15,
          "title": "2019Java最新面试题——Java基础（持续更新）",
          "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
          "author": "Bobbi",
          "content": null,
          "readNum": 35,
          "likeNum": 2,
          "cover": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbaidulogo.bj.bcebos.com%2Feditor%2Fk2hsdqg1baxoup1bv1rckp40pmfa264n.jpeg&refer=http%3A%2F%2Fbaidulogo.bj.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633616657&t=feff303ee93d4e8fb889a989858cd511",
          "coverType": 0,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
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
        }, {
          "id": 14,
          "title": "2019Java最新面试题——Java基础（持续更新）",
          "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
          "author": "Bobbi",
          "content": null,
          "readNum": 35,
          "likeNum": 2,
          "cover": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbaidulogo.bj.bcebos.com%2Feditor%2Fk2hsdqg1baxoup1bv1rckp40pmfa264n.jpeg&refer=http%3A%2F%2Fbaidulogo.bj.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633616657&t=feff303ee93d4e8fb889a989858cd511",
          "coverType": 0,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
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
        }, {
          "id": 13,
          "title": "2019Java最新面试题——Java基础（持续更新）",
          "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
          "author": "Bobbi",
          "content": null,
          "readNum": 35,
          "likeNum": 2,
          "cover": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbaidulogo.bj.bcebos.com%2Feditor%2Fk2hsdqg1baxoup1bv1rckp40pmfa264n.jpeg&refer=http%3A%2F%2Fbaidulogo.bj.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633616657&t=feff303ee93d4e8fb889a989858cd511",
          "coverType": 0,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
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
        }, {
          "id": 12,
          "title": "2019Java最新面试题——Java基础（持续更新）",
          "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
          "author": "Bobbi",
          "content": null,
          "readNum": 35,
          "likeNum": 2,
          "cover": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbaidulogo.bj.bcebos.com%2Feditor%2Fk2hsdqg1baxoup1bv1rckp40pmfa264n.jpeg&refer=http%3A%2F%2Fbaidulogo.bj.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633616657&t=feff303ee93d4e8fb889a989858cd511",
          "coverType": 0,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
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
          "id": 11,
          "title": "2019Java最新面试题——Java基础（持续更新）",
          "description": "网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
          "author": "Bobbi",
          "content": null,
          "readNum": 35,
          "likeNum": 2,
          "cover": "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbaidulogo.bj.bcebos.com%2Feditor%2Fk2hsdqg1baxoup1bv1rckp40pmfa264n.jpeg&refer=http%3A%2F%2Fbaidulogo.bj.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1633616657&t=feff303ee93d4e8fb889a989858cd511",
          "coverType": 0,
          "createTime": 1552172978000,
          "updateTime": null,
          "recommend": null,
          "categoryId": null,
          "publish": null,
          "top": false,
          "contentFormat": null,
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
        }],
      categoryList: [{
        "id":1,
        "name":"漫谈",
        "type":0,
        "ranks":0,
        "parentId":-1,
        "parentName":null
      },
        {
          "id":2,
          "name":"本站相关",
          "type":0,
          "ranks":1,
          "parentId":1,
          "parentName":null
        },
        {
          "id":3,
          "name":"关于",
          "type":0,
          "ranks":2,
          "parentId":2,
          "parentName":null
        },
        {
          "id":9,
          "name":"后端",
          "type":0,
          "ranks":0,
          "parentId":-1,
          "parentName":null
        },
        {
          "id":11,
          "name":"Java基础",
          "type":0,
          "ranks":1,
          "parentId":9,
          "parentName":null
        },
        {
          "id":12,
          "name":"面试",
          "type":0,
          "ranks":2,
          "parentId":11,
          "parentName":null
        }],
      noMoreData: false
    }
  },

  components: {
    CommonLayout,
    HotRead,
    Profile,
    FriendLinks,
    Carousel,
    ArticlesHeader,
    ArticleCell
  },

  methods: {
    browseMore () {}
  }
}
</script>

<style>

</style>
