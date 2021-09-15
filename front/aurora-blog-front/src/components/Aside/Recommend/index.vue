<template>
  <div class="recommend">
    <panel :title="'推荐阅读'">
      <div slot="content" class="content">
        <div class="top" v-if="topRecommend">
          <a :href="'/' + topRecommend.urlType + '/' + topRecommend.linkId">
            <p class="title">{{ topRecommend.title }}</p>
            <div class="tags">
              <el-tag :type="index | mapTagColors" v-for="(tag,index) in topRecommend.tagList" :key="tag.id"
                      effect="dark" size="mini" style="margin-right: 4px; margin-bottom:5px; float: left;">
                {{ tag.name }}
              </el-tag>
            </div>
            <div class="img">
              <img :src="topRecommend.cover" alt="">
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
          <li v-for="recommend in recommendList" :key="recommend.id">
            <a :href="'/' + recommend.urlType + '/' +recommend.linkId">
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

export default {
  data() {
    return {
      recommendList: [{
        "id": 1,
        "linkId": 1,
        "type": 2,
        "orderNum": 2,
        "title": "Java虚拟机01——Java内存数据区域和内存溢出异常",
        "top": null,
        "description": "Java虚拟机在执行Java程序的过程中会把它所管理的内存划分为若干个不同的数据区域。这些区域都有各自的用途，以及创建和销毁的时间，有的区域随着虚拟机进程的启动而存在，有些区域则依赖用户线程的启动和结束而建立和销毁.",
        "readNum": 12,
        "commentNum": 13,
        "likeNum": 1,
        "urlType": "bookNote",
        "cover": null,
        "createTime": 1552173065000,
        "tagList": null
      }, {
        "id": 2,
        "linkId": 1,
        "type": 2,
        "orderNum": 2,
        "title": "Java虚拟机01——Java内存数据区域和内存溢出异常",
        "top": null,
        "description": "Java虚拟机在执行Java程序的过程中会把它所管理的内存划分为若干个不同的数据区域。这些区域都有各自的用途，以及创建和销毁的时间，有的区域随着虚拟机进程的启动而存在，有些区域则依赖用户线程的启动和结束而建立和销毁.",
        "readNum": 12,
        "commentNum": 13,
        "likeNum": 1,
        "urlType": "bookNote",
        "cover": "https://img1.baidu.com/it/u=3886212450,854269223&fm=26&fmt=auto&gp=0.jpg",
        "createTime": 1552173065000,
        "tagList": null
      }, {
        "id": 3,
        "linkId": 1,
        "type": 2,
        "orderNum": 2,
        "title": "Java虚拟机01——Java内存数据区域和内存溢出异常",
        "top": null,
        "description": "Java虚拟机在执行Java程序的过程中会把它所管理的内存划分为若干个不同的数据区域。这些区域都有各自的用途，以及创建和销毁的时间，有的区域随着虚拟机进程的启动而存在，有些区域则依赖用户线程的启动和结束而建立和销毁.",
        "readNum": 12,
        "commentNum": 13,
        "likeNum": 1,
        "urlType": "bookNote",
        "cover": "https://img1.baidu.com/it/u=3886212450,854269223&fm=26&fmt=auto&gp=0.jpg",
        "createTime": 1552173065000,
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
      }],
      topRecommend: {
        "id": 6,
        "linkId": 1,
        "type": 0,
        "orderNum": 1,
        "title": "关于本站和博主",
        "top": true,
        "description": "关于本站和博主",
        "readNum": 54,
        "commentNum": 13,
        "likeNum": 1,
        "urlType": "article",
        "cover": "https://img1.baidu.com/it/u=3886212450,854269223&fm=26&fmt=auto&gp=0.jpg",
        "createTime": 1552172978000,
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
