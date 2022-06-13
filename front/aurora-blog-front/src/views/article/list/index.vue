<template>
  <div>
    <common-layout>
      <template v-slot:content>
        <classify :category-list="categoryList" @filterByCategory="filterByCategory"/>
        <articles-header :main-title="'文章列表'" :sub-title="'Articles'"/>
        <article-cell v-for="article in articleList" :article="article" :key="article.id"/>
        <browse-more @browseMore="browseMore" :noMoreData="noMoreData" ref="browseMore"></browse-more>
      </template>
    </common-layout>
  </div>
</template>

<script>
import {categoryList,articleList } from '../../../api/all';
export default {
  data() {
    return {
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderByColumn: 'isTop,createTime',
        isAsc: 'desc',
        categoryId:'',
      },
      articleList: [],
      categoryList: [],
      noMoreData: false
    }
  },

  components: {},

  created() {
        categoryList().then(res =>{
          this.categoryList = res.data;
        }),
        this.getArticleList();
  },

  methods: {
    filterByCategory (params) {
      this.queryParams.categoryId = params
      this.articleList.length = 0
      this.queryParams.pageNum = 1
      this.queryParams.pageSize = 10;
      if (params === ''){
        this.$delete(this.queryParams,'categoryId')
      }
      this.getArticleList();
    },
    browseMore() {
      this.queryParams.pageNum++
      this.getArticleList();
    },
    getArticleList(){
      if (!this.queryParams.categoryId){
        this.$delete(this.queryParams,'categoryId')
      }
      articleList(this.queryParams).then(res => {
        if (res && res.code === 200) {
          if (res.total <= (this.queryParams.pageNum*this.queryParams.pageSize)) {
            this.noMoreData = true
          } else {
            this.noMoreData = false
          }
          this.articleList = this.articleList.concat(res.data);
        }
      }).then( () => {
        this.$refs.browseMore.stopLoading()
      }).catch(() => {
        this.$refs.browseMore.stopLoading()
      })
    }
  }
};
</script>

<style>

</style>
