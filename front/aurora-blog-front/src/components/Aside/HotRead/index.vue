<template>
  <div class="hotRead">
    <panel :title="'最热阅读'">
      <div slot="content" class="content">
        <div class="top">
          <a :href="'/' + 'article' + '/' + topHotRead.articleId">
            <p class="title">{{ topHotRead.title }}</p>
            <div class="tags">
              <el-tag :type="index | mapTagColors" v-for="(tag , index)  in topHotRead.tags" :key="tag.tagId"
                      effect="dark" size="mini" style="margin-right: 4px; margin-bottom:5px; float: left;">
                {{ tag.name }}
              </el-tag>
            </div>
            <div class="img">
              <img :src="topHotRead.coverUrl" alt="">
            </div>
            <p class="desc">{{ topHotRead.description | textLineBreak(60) }}</p>
            <p class="info">
              <span class="time"><a><i class="iconfont icon-shijian"></i>{{
                  topHotRead.createTime | socialDate
                }}</a></span>
              <span class="likes"><a><i class="iconfont icon-xihuan1"></i> {{ topHotRead.likeNum }} </a></span>
              <span class="comments"><a><i class="iconfont icon-comment"></i>{{ topHotRead.commentNum }} </a></span>
              <span class="readings"><a><i
                  class="iconfont icon-ico_yueduliang"></i> {{ topHotRead.readNum }} </a></span>
            </p>
          </a>
        </div>
        <ul class="others">
          <li v-for="hotRead in hotReadList" :key="hotRead.articleId">
            <a :href="'/' + 'article' + '/' +hotRead.articleId">
              <p class="title">{{ hotRead.title }}</p>
              <p class="info">
                <span class="time"><a><i class="iconfont icon-shijian"></i>{{
                    hotRead.createTime | socialDate
                  }}</a></span>
                <span class="likes"><a><i class="iconfont icon-xihuan1"></i> {{ hotRead.likeNum }} </a></span>
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
import { articleList } from '../../../api/all';
export default {
  data() {
    return {
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 5,
        orderByColumn: 'readNum',
        isAsc: 'desc'
      },
      hotReadList: [],
      topHotRead: {}
    };
  },

  components: {},

  computed: {},

  mounted() {
    articleList(this.queryParams).then(res =>{
      this.topHotRead = res.data.data.data.shift()
      this.hotReadList = res.data.data.data;
    })
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
