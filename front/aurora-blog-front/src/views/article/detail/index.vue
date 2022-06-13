<template>
  <div>
    <common-layout>
      <div slot="content">
        <div class="article-page-header">
          <p class="title">{{ article.title }}</p>
          <div class="tags">
            <el-tag size="small" effect="dark" :type="index | mapTagColors" v-for="(tag , index) in article.tagList"
                    :key="tag.id">
              {{ tag.name }}
            </el-tag>
          </div>
          <el-row>
            <el-col :xs="24" :sm="10" :md="10" :lg="10" style="padding-left: 0;padding-right: 0;">
              <p class="info"><span class="author"><a><i class="iconfont icon-zuozhe"></i>{{
                  article.author
                }}</a></span><span
                  class="publish-time"> <a><i class="iconfont icon-shijian"></i>{{
                  article.createTime | socialDate
                }}</a></span></p>
            </el-col>
            <el-col :xs="24" :sm="14" :md="14" :lg="14" style="padding-left: 0;padding-right: 0;">
              <p class="operate_info">
                <span class="comments"><a><i class="iconfont icon-comment"></i> {{ article.readNum }} 评论</a></span>
                <span class="readings"><a><i class="iconfont icon-ico_yueduliang"></i> {{
                    article.readNum
                  }} 阅读</a></span>
                <span class="likes"><a @click="likePost(article)"><i
                    class="iconfont icon-xihuan1"></i> {{ article.likeNum }} 喜欢</a></span>
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
          <div class="detail-footer"> 以上内容添加于 {{ article.createTime | socialDate }} &nbsp;&nbsp;&nbsp; 更新于 {{
              article.updateTime | socialDate
            }}
          </div>
        </div>
        <bg-comment :comments="commentData" :comment-type=1 :article-id="article.articleId"></bg-comment>
      </div>
      <iv-affix :offset-top="60" slot="side-toc">
        <side-toc/>
      </iv-affix>
    </common-layout>
  </div>
</template>

<script>
import TOC from '../../../common/js/MarkdownToc'
// TOC滚动监听
import TocScrollSpy from '../../../common/js/TocScrollSpy'
import {getArticle, getComments} from '../../../api/all';

export default {
  data() {
    return {
      commentData: [],
      article: {},
    };
  },

  components: {},

  computed: {},

  created() {
  },

  mounted() {
    this.getArticle()
    this.comments()
    this.addCodeLineNumber()
    this.refreshDiectory()
  },

  methods: {
    likePost(post) {
      this.$http({
        url: this.$http.adornUrl('/Article/like/' + post.id),
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
    addCodeLineNumber() {
      // 添加行号
      let blocks = this.$refs.article.querySelectorAll('pre code')
      blocks.forEach((block) => {
        window.hljs.highlightBlock(block)
        // 去前后空格并添加行号
        block.innerHTML = '<ul><li>' + block.innerHTML.replace(/(^\s*)|(\s*$)/g, '').replace(/\n/g, '\n</li><li>') + '\n</li></ul>'
      })
    },
    getArticle() {
      let articleId = this.$route.params.id
      getArticle(articleId).then(res => {
        if (res && res.code === 200) {
          this.article = res.data
          // 更新目录、高亮代码
          this.$nextTick(function () {
            //this.addCodeLineNumber()
            this.refreshDiectory()
          })
        }
      })
    },
    comments() {
      getComments(this.$route.params.id).then(res => {
        this.commentData = res.data
      })
    },
    refreshDiectory() {
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
    //   new TOC('Article-main-page', {
    //     'level': 5,
    //     'top': 200,
    //     'class': 'list',
    //     'targetId': 'sidebar-toc'
    //   })
    //   new TocScrollSpy('Article-main-page', 'sidebar-toc', {
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
  margin-bottom: 10px;
}

.article-page-header .tags span {
  margin-right: 5px;
}

.article-page-header .title {
  font-size: 27px;
  line-height: 33px;
  font-weight: 500;
  color: #333;
  margin-bottom: 15px;
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

.detail-footer {
  text-align: right;
  font-size: 12px;
  color: #444;
  border-bottom: 2px dashed #eee;
  padding-bottom: 10px;
  margin: 15px 0;
}
</style>
