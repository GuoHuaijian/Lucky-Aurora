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
    articleList(this.queryParams).then(res =>{
      this.articleList = res.data.data.data;
    })
  },

  methods: {
    browseMore() {
    }
  }
}
</script>

<style>

</style>
