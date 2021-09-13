<template>
  <div>
    <CommonLayout>
      <template v-slot:content>
        <SectionTitle main-title="存档时光" sub-title="Timeline"/>
        <div class="timeline-content">
                <div v-for="year in timelineList" :key="year.year" >
                  <div v-if="year.months.length > 0">
                  <archive-list-time-title :date="year.year" :count="year.count"></archive-list-time-title>
                  <div v-for="month in year.months" :key="month.month">
                    <div v-if="month.posts.length > 0">
                    <archive-list-time-title :date="month.month + '月'" :count="month.count" :dateType="'month'"></archive-list-time-title>
                    <archive-list-cell v-for="post in month.posts" :post="post"
                                       :key="post.title"></archive-list-cell>
                    </div>
                  </div>
                  </div>
                </div>
        </div>
      </template>
    </CommonLayout>
  </div>
</template>

<script>
import CommonLayout from '../../components/layout/BaseLayout/common'
import SectionTitle from '../../components/common/sectiontitle'
import ArchiveListTimeTitle from '../../components/archive/archivelisttimetitle'
import ArchiveListCell from '../../components/archive/archivelistcell'

export default {
  data() {
    return {
      timelineList: [
        {
          "year":2019,
          "count":5,
          "months":[
            {
              "month":3,
              "count":5,
              "posts":[
                {
                  "id":1,
                  "title":"Java虚拟机01——Java内存数据区域和内存溢出异常",
                  "description":"Java虚拟机在执行Java程序的过程中会把它所管理的内存划分为若干个不同的数据区域。这些区域都有各自的用途，以及创建和销毁的时间，有的区域随着虚拟机进程的启动而存在，有些区域则依赖用户线程的启动和结束而建立和销毁.",
                  "postType":"bookNote",
                  "createTime":1552173065000
                },
                {
                  "id":1,
                  "title":"关于本站和博主",
                  "description":"关于本站和博主",
                  "postType":"article",
                  "createTime":1552172978000
                },
                {
                  "id":3,
                  "title":"2019Java最新面试题——Java基础（持续更新）",
                  "description":"网上找的面试题大多比较老旧，很多还是Java6以前的。现在都更新到Java11了，面试题也应该与时俱进。本文搜罗各大厂的Java面试题，力求题目从简到难，分类明确，答案详细！也借此来巩固自己的知识。",
                  "postType":"article",
                  "createTime":1552172978000
                },
                {
                  "id":4,
                  "title":"用最简单的话告诉你什么是ElasticSearch",
                  "description":"Elasticsearch 是一个分布式可扩展的实时搜索和分析引擎,一个建立在全文搜索引擎 Apache Lucene(TM) 基础上的搜索引擎.当然 Elasticsearch 并不仅仅是 Lucene 那么简单，下面就介绍ElasticSearch为什么是分布式的，可扩展，高性能，高可用。\n\n\n作者：fallinjava\n链接：https://juejin.im/post/5c790b4b51882545194f84f0\n来源：掘金\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。",
                  "postType":"article",
                  "createTime":1552172978000
                },
                {
                  "id":5,
                  "title":"性能分析利器《Arthas》总结",
                  "description":"Arthas 是Alibaba开源的Java诊断工具，为什么要介绍这个工具呢？先来看看你是否都遇到这样的场景：当你线上项目出了问题，但是一打开日志发现，有些地方忘记打了日志，于是你马上补上日志，然后重新上线。当你的项目某个接口执行速度较慢，为了排查问题，于是你四处加上每个方法运行时间。当你发现某个类有冲突，好像在线上运行的结果和你预期的不符合，手动把线上编译出的class文件下载下来然后反编译，看看究竟class内容是什么。",
                  "postType":"article",
                  "createTime":1552172978000
                }
              ]
            }
          ]
        }
      ]
    };
  },

  components: {
    CommonLayout,
    SectionTitle,
    'archive-list-time-title': ArchiveListTimeTitle,
    'archive-list-cell': ArchiveListCell
  },
  created () {
    this.listTimeline()
  },
  methods: {
    listTimeline () {
      this.$http({
        url: this.$http.adornUrl('/timeline'),
        method: 'get',
        params: this.$http.adornParams()
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.timelineList = data.timelineList
        }
      })
    }
  }
};
</script>

<style>

</style>
