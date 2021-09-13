<template>
  <div class="archive-list-cell">
    <div class="info" >
      <el-tag type="info" size="mini">{{ post.createTime | socialDate }}</el-tag>
      <div class="base-info">
        <a class="title" :href="'/' + post.postType + '/' + post.id">
          {{post.title}}
        </a>
        <a class="see-desc" @click.stop="seeDesc" :class="{show: showDesc}">查看简介&nbsp;&nbsp;<i class="el-icon-caret-bottom"></i></a>
        <p class="desc" :class="{show: showDesc}">{{post.description}}</p>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  props: {
    post: {
      Type: Object
    }
  },
  data () {
    return {
      showDesc: false
    }
  },
  methods: {
    seeDesc () {
      this.showDesc = !this.showDesc
    }
  }
}
</script>

<style>
.archive-list-cell {
  position: relative;
  padding: 5px 0;
}
.archive-list-cell::after {
  content: '';
  position: absolute;
  min-height: 25px;
  top: 0;
  bottom: 0;
  left: 17px;
  width: 4px;
  background: #409EFF;
}
.archive-list-cell .info {
  display: flex;
  font-size: 17px;
  line-height: 25px;
  margin-left: 45px;
  cursor: pointer;
}
@media only screen and (max-width: 720px) {
  .archive-list-cell .info {
    font-size: 13px;
  }
}
.archive-list-cell .info .time-tag {
  flex-shrink: 0;
}
.archive-list-cell .info .base-info {
  margin-left: 0;
  text-align: left;
}
.archive-list-cell .info .base-info a.title {

  margin-left: 5px;
  font-weight: 300;
  color: #333;
  cursor: pointer;
}
.archive-list-cell .info .base-info a.title:hover {
  color: #409EFF;
}
.archive-list-cell .info .base-info a.see-desc {
  color: #444;
  font-size: 13px;
  margin-left: 5px;
  font-weight: 100;
  cursor: pointer;
}
.archive-list-cell .info .base-info a.see-desc > i {
  transition: All 0.4s ease-in-out;
  transform: rotateX(180deg);
}
.archive-list-cell .info .base-info a.see-desc:hover,
.archive-list-cell .info .base-info a.see-desc.show {
  color: #409EFF;
}
.archive-list-cell .info .base-info a.see-desc.show > i {
  transform: rotateX(0deg);
}
.archive-list-cell .info .base-info .desc {
  font-size: 14px;
  font-weight: 300;
  margin-left: 5px;
  color: #444;
  overflow: hidden;
  max-height: 0;
  transition: All 0.4s ease-in-out;
}
.archive-list-cell .info .base-info .desc.show {
  max-height: 250px;
}
</style>
