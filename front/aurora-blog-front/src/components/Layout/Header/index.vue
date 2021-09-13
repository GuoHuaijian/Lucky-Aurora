<template>
  <div>
    <el-collapse-transition>
      <div v-show="show">
        <header class="header-navigation">
          <nav>
            <div class="logo">
              <a href="/">郭怀检</a>
            </div>
            <ul class="menuList">
              <li>
                <a href="/">首页</a>
              </li>
              <li>
                <router-link to="/articles">
                  文章
                </router-link>
              </li>
              <li>
                <router-link to="/archive">
                  时光轴
                </router-link>
              </li>
              <li>
                <a href="#">留言</a>
              </li>
              <li>
                <a href="#">关于</a>
              </li>
            </ul>
            <div class="search">
              <el-input
                  placeholder="请输入内容"
                  v-model="searchValue"
                  class="input-with-select"
              >
                <el-button slot="append" icon="el-icon-search"></el-button>
              </el-input>
            </div>
          </nav>
        </header>
      </div>
    </el-collapse-transition>
  </div>
</template>

<script>
export default {
  data: () => ({
    show: true,
    searchValue: "",
  }),

  mounted: function () {
    // 给页面绑定滑轮滚动事件
    // 火狐使用DOMMouseScroll绑定鼠标滚动
    document.addEventListener("DOMMouseScroll", this.scrollFunc, false);
    // 其他浏览器用mousewheel绑定
    document.onmousewheel = this.scrollFunc;
  },

  methods: {
    scrollFunc(e) {
      e = e || window.event;
      // 判断浏览器IE，谷歌滑轮事件
      if (e.wheelDelta) {
        // 当滑轮向上滚动时
        if (e.wheelDelta > 0) {
          this.show = true;
        }
        // 当滑轮向下滚动时
        if (e.wheelDelta < 0) {
          this.show = false;
        }
        // Firefox滑轮事件
      } else if (e.detail) {
        // 当滑轮向上滚动时
        if (e.detail < 0) {
          this.show = true;
        }
        // 当滑轮向下滚动时
        if (e.detail > 0) {
          this.show = false;
        }
      }
    },
  },
};
</script>

<style>
.header-navigation {
  position: fixed;
  top: 0;
  width: 100%;
  height: 60px;
  line-height: 60px;
  background: #fff;
  text-align: center;
  border-bottom: 1px solid #ddd;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.04);
  z-index: 9999;
}

.bounce-enter-active {
  transition: all 0.8s ease;
}

.bounce-leave-active {
  transform: translateY(-70px);
  opacity: 0;
}

nav {
  width: 1200px;
  margin: auto;
  position: relative;
}

.logo {
  float: left;
  font-size: 22px;
}

a {
  text-decoration: none;
  color: #444;
}

ul,
li {
  list-style: none;
}

.menuList {
  float: left;
  margin-left: 50px;
  margin-top: 0px;
}

.menuList li {
  float: left;
  width: 100px;
  text-align: center;
  display: block;
  font-size: 16px;
  margin-right: 5px;
}

.search {
  position: absolute;
  right: 0;
  margin-right: 60px;
}

.input-with-select {
  width: 200px;
}
</style>
