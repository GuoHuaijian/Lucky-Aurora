<template>
  <div>
    <div class="article-cell">
      <a>
        <el-row type="flex">
          <el-col
              :xs="24"
              :sm="24"
              :md="textSpan(article.coverType)"
              :lg="textSpan(article.coverType)"
              :order="textOrderType(article.coverType)"
              style="padding-left: 0; padding-right: 0"
          >
            <div class="text-wrapper">
              <h4 class="title">
                <a :href="'/article/' + article.articleId">{{ article.title }}</a>
                <span class="special" v-if="article.isTop === true" title="置顶">置顶</span>
              </h4>
              <div class="tags">
                <el-tag
                    v-for="(tag, index) in article.tags"
                    :type="index | mapTagColors"
                    :key="tag.tagId"
                    effect="plain" size="small"
                >{{ tag.name }}
                </el-tag
                >
              </div>
              <p class="desc">
                {{
                  article.description | filterHtml | textLineBreak(70)
                }}<a :href="'/Article/' + article.articleId">
                查看更多
                <i class="el-icon-caret-right"></i>
              </a>
              </p>
              <p class="operate_info">
              <span class="publish-time"
              ><a><i class="iconfont icon-shijian"></i> {{ article.createTime | socialDate }}</a></span
              >
                <span class="readings"
                ><a
                ><i class="iconfont icon-ico_yueduliang"></i> {{ article.readNum }} 阅读</a
                ></span
                >
                <span class="readings"><a
                ><i class="iconfont icon-comment"></i> {{ article.commentNum }} 评论</a
                ></span>
                <span class="likes"
                ><a @click="likePost(article)"
                ><i class="iconfont icon-xihuan1"></i>
                  {{ article.likeNum }} 喜欢</a></span>
              </p>
            </div>
          </el-col>
          <el-col
              :xs="6"
              :sm="6"
              :md="imgSpan(article.coverType)"
              :lg="imgSpan(article.coverType)"
              :order="imgOrderType(article.coverType)"
              style="padding-left: 0px; padding-right: 0px"
          >
            <div class="img-wrapper" :class="themeClass(article.coverType)">
              <img :src="article.coverUrl" alt=""/>
            </div>
          </el-col>
        </el-row>
      </a>
    </div>
  </div>
</template>

<script>

const ARTICLE_TYPE_BIG_IMAGE = 1
const ARTICLE_TYPE_NO_IMAGE = 2

export default {
  props: {
    article: {
      Type: Object
    },
  },
  data() {
    return {
      noMoreData: false,
    };
  },

  components: {},

  computed: {
    textOrderType() {
      return function (coverType) {
        return coverType === ARTICLE_TYPE_BIG_IMAGE ? 2 : 1
      }
    },
    imgOrderType() {
      return function (coverType) {
        return coverType === ARTICLE_TYPE_BIG_IMAGE ? 1 : 2
      }
    },
    textSpan() {
      return function (coverType) {
        if (coverType === ARTICLE_TYPE_BIG_IMAGE) {
          return 24
        } else if (coverType === ARTICLE_TYPE_NO_IMAGE) {
          return 24
        } else {
          return 17
        }
      }
    },
    imgSpan() {
      return function (coverType) {
        if (coverType === ARTICLE_TYPE_BIG_IMAGE) {
          return 24
        } else if (coverType === ARTICLE_TYPE_NO_IMAGE) {
          return 0
        } else {
          return 7
        }
      }
    },
    themeClass() {
      return function (coverType) {
        if (coverType === ARTICLE_TYPE_BIG_IMAGE) {
          return 'big-image'
        } else {
          return ''
        }
      }
    }
  },

  mounted() {
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
    }
  },
};
</script>

<style>
.article-cell {
  margin-bottom: 15px;
}

.article-cell > a {
  display: block;
  cursor: default;
  border: 1px solid #eee;
}

.article-cell > a:hover {
  border: 1px solid #ddd;
  box-shadow: 2px 2px 3px #eee;
}

.article-cell > a .text-wrapper {
  padding: 20px 20px 0 20px;
  text-align: left;
}

@media only screen and (max-width: 768px) {
  .article-cell > a .text-wrapper {
    padding: 15px 15px 0 15px;
  }
}

.article-cell > a .text-wrapper .title {
  font-size: 23px;
  font-weight: normal;
  line-height: 27px;
  margin: 0px;
}

.article-cell > a .text-wrapper .title span.special {
  border-radius: 4px;
  font-size: 12px;
  font-weight: 100;
  padding: 3px 5px;
  margin-left: 1px;
  vertical-align: top;
  color: #fff;
  background: #EB9E05;
  cursor: pointer;
}

.article-cell > a .text-wrapper .title a {
  color: #333;
  cursor: pointer;
}

.article-cell > a .text-wrapper .title a:hover {
  color: #409EFF;
  text-decoration: none;
}

.article-cell > a .text-wrapper .info {
  margin-top: 10px;
  font-size: 14px;
  line-height: 18px;
  font-weight: 200;
}

.article-cell > a .text-wrapper .info a {
  color: #777;
  cursor: pointer;
}

.article-cell > a .text-wrapper .info a:hover {
  color: #409EFF;
  text-decoration: underline;
}

.article-cell > a .text-wrapper .info .publish-time {
  margin-left: 20px;
}

.article-cell > a .text-wrapper .line {
  width: 50px;
  margin-top: 30px;
  position: relative;
}

.article-cell > a .text-wrapper .line::after {
  display: block;
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  border-top: 1px solid rgba(7, 17, 27, 0.4);
  content: '';
  margin-bottom: 15px;
}

.article-cell > a .text-wrapper .tags {
  /*cursor: pointer;*/
  margin: 8px 0;
}

.article-cell > a .text-wrapper .tags .el-tag {
  margin: 2px 4px 2px 0;
}

.article-cell > a .text-wrapper .desc {
  color: #666;
  font-size: 14px;
  line-height: 20px;
  font-weight: 200;
}

.article-cell > a .text-wrapper .desc a {
  color: #409EFF;
  font-weight: 500;
  cursor: pointer;
}

.article-cell > a .text-wrapper .desc a:hover {
  text-decoration: underline;
}

.article-cell > a .text-wrapper .operate_info {
  font-size: 14px;
  margin: 15px 0 20px;
}

.article-cell > a .text-wrapper .operate_info span {
  margin-right: 8px;
}

.article-cell > a .text-wrapper .operate_info span + span {
  margin-left: 8px;
}

.article-cell > a .text-wrapper .operate_info span a {
  cursor: pointer;
  color: #909399;
}

.article-cell > a .text-wrapper .operate_info span a:hover {
  color: #409EFF;
  text-decoration: none;
}

.article-cell > a .img-wrapper {
  padding-bottom: 85%;
  width: 100%;
  height: 0;
  overflow: hidden;
}

.article-cell > a .img-wrapper.big-image {
  padding-bottom: 26%;
}

.article-cell > a .img-wrapper img {
  width: 100%;
}
</style>
