<template>
  <div>
    <common-layout>
      <div slot="profile">
        <profile/>
      </div>
      <div slot="aside-content">
        <hot-read/>
      </div>
      <div slot="friend-links">
        <friend-links/>
      </div>
      <template v-slot:content>
        <bg-carousel :items="items"/>
        <articles-header :main-title="'文章'" :sub-title="'Articles'"/>
        <article-cell v-for="article in articleList" :article="article" :key="article.articleId"/>
        <browse-more @browseMore="browseMore" :noMoreData="noMoreData" ref="browseMore"/>
      </template>
    </common-layout>
  </div>
</template>

<script>
import { carouselList,categoryList,articleList } from '../../api/all';
export default {
  data() {
    return {
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderByColumn: 'isTop,createTime',
        isAsc: 'desc'
      },
      // 轮播图
      items: [],
      // 文章
      articleList: [],
      // 分类
      categoryList: [],
      noMoreData: false
    }
  },

  created() {
    carouselList().then(res =>{
      this.items = res.data.data;
    }),
    categoryList().then(res =>{

      this.categoryList = res.data.data;
    }),
    this.getArticleList()
  },

  methods: {
    browseMore() {
      this.queryParams.pageNum++
      this.getArticleList()
    },
    getArticleList(){
      articleList(this.queryParams).then(res => {
        if (res && res.data.code === 200) {
          if (res.data.data.total <= (this.queryParams.pageNum*this.queryParams.pageSize)) {
            this.noMoreData = true
          } else {
            this.noMoreData = false
          }
          this.articleList = this.articleList.concat(res.data.data.data);
        }
      }).then( () => {
        this.$refs.browseMore.stopLoading()
      }).catch(() => {
        this.$refs.browseMore.stopLoading()
      })
    }
  }
}
</script>

<style>

</style>
