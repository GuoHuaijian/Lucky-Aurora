<template>
  <div class="recommend">
    <panel :title="'推荐阅读'">
      <div slot="content" class="content">
        <div class="top" v-if="topRecommend">
          <a :href="'/' + 'article' + '/' + topRecommend.articleId">
            <p class="title">{{ topRecommend.title }}</p>
            <div class="tags">
              <el-tag :type="index | mapTagColors" v-for="(tag,index) in topRecommend.tags" :key="tag.tagId"
                      effect="dark" size="mini" style="margin-right: 4px; margin-bottom:5px; float: left;">
                {{ tag.name }}
              </el-tag>
            </div>
            <div class="img">
              <img :src="topRecommend.coverUrl" alt="">
            </div>
            <p class="desc">{{ topRecommend.description | textLineBreak(60) }}</p>
            <p class="info">
              <span class="time"><a><i class="iconfont icon-shijian"></i>{{ topRecommend.createTime | socialDate }}</a></span>
              <span class="likes"><a><i class="iconfont icon-xihuan1"></i> {{ topRecommend.likeNum }} </a></span>
              <span class="comments"><a><i class="iconfont icon-comment"></i> {{ topRecommend.commentNum }} </a></span>
              <span class="readings"><a><i
                  class="iconfont icon-ico_yueduliang"></i> {{ topRecommend.readNum }} </a></span>
            </p>
          </a>
        </div>
        <ul class="others">
          <li v-for="recommend in recommendList" :key="recommend.articleId">
            <a :href="'/' + 'article' + '/' +recommend.articleId">
              <p class="title">{{ recommend.title }}</p>
              <p class="info">
                <span class="time"><a><i class="iconfont icon-shijian"></i>{{
                    recommend.createTime | socialDate
                  }}</a></span>
                <span class="likes"><a><i class="iconfont icon-xihuan1"></i> {{ recommend.likeNum }} </a></span>
                <span class="comments"><a><i class="iconfont icon-comment"></i> {{ recommend.commentNum }} </a></span>
                <span class="readings"><a><i
                    class="iconfont icon-ico_yueduliang"></i> {{ recommend.readNum }} </a></span>
              </p>
            </a>
          </li>
        </ul>
      </div>
    </panel>
  </div>
</template>

<script>
import { articleList } from '../../../api/all';
export default {
  data() {
    return {
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        orderByColumn: 'isRecommend',
        isAsc: 'desc'
      },
      recommendList: [],
      topRecommend: {}
    };
  },

  components: {},

  computed: {},

  mounted() {
    articleList(this.queryParams).then(res =>{
      this.topRecommend = res.data.data.data.shift()
      this.recommendList = res.data.data.data;
    })
  },

  methods: {}
}
</script>

<style>
.recommend {
  margin-top: 10px;
}

.recommend .others {
  padding-left: 0px;
}

.recommend .content {
  padding: 5px 20px;
}

.recommend .top a,
.recommend .others a {
  display: block;
  overflow: hidden;
}

.recommend .top a .tags,
.recommend .others a .tags {
  margin-bottom: 10px;
}

.recommend .top a .title,
.recommend .others a .title {
  text-align: justify;
  color: #696969;
  font-size: 16px;
  line-height: 23px;
  margin-top: 0px;
  margin-bottom: 5px;
}

.recommend .top a .info,
.recommend .others a .info {
  height: 100%;
  overflow: hidden;
  margin: 5px 0 0px;
}

.recommend .top a .info span,
.recommend .others a .info span {
  float: left;
  font-size: 13px;
  line-height: 18px;
  font-weight: 100;
  color: #878D99;
}

.recommend .top a .info span + span,
.recommend .others a .info span + span {
  float: right;
  margin-left: 10px;
}

.recommend .top a .info a,
.recommend .others a .info a {
  display: inline-block;
  color: #777;
  cursor: pointer;
}

.recommend .top a .info a:hover,
.recommend .others a .info a:hover {
  color: #409EFF;
  text-decoration: none;
}

.recommend .top a .img,
.recommend .others a .img {
  padding-bottom: 40%;
  width: 100%;
  height: 0;
  margin: 5px 0;
  overflow: hidden;
}

.recommend .top a .img img,
.recommend .others a .img img {
  width: 100%;
  transition: All 0.4s ease-in-out;
  transform: scale(1);
  zoom: 1;
}

.recommend .top a .desc,
.recommend .others a .desc {
  text-align: justify;
  color: #878D99;
  font-size: 13px;
  line-height: 20px;
  margin: 5px 0 0;
}

.recommend .top a:hover .title,
.recommend .others a:hover .title {
  color: #409EFF;
}

.recommend .top a:hover img,
.recommend .others a:hover img {
  transition: All 0.4s ease-in-out;
  transform: scale(1.05);
  zoom: 1.05;
}

.recommend .others li {
  list-style-type: none;
  margin-top: 10px;
  padding-top: 10px;
  border-top: 1px solid #eee;
}
</style>
