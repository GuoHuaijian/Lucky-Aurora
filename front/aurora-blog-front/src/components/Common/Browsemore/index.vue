<template>
  <div class="browse-more" :class="{loading: loading}">
    <a @click="browseMore">
      <p class="text" v-show="!loading">{{ tipStr }}</p>
      <div class="spinner" v-show="loading">
        <div class="line1"></div>
        <div class="line2"></div>
        <div class="line3"></div>
        <div class="line4"></div>
        <div class="line5"></div>
      </div>
    </a>
  </div>
</template>

<script>
export default {
  props: {
    tipText: {
      default: '浏览更多'
    },
    noMoreData: {
      default: false
    }
  },
  data() {
    return {
      loading: false
    }
  },
  computed: {
    tipStr: function () {
      let isNoMore = this.noMoreData
      return isNoMore ? '暂无更多' : '浏览更多'
    }
  },
  methods: {
    browseMore() {
      if (this.noMoreData) {
        this.loading = false
        return
      }
      this.loading = true
      this.$emit('browseMore')
      this.loading = false
    },
    stopLoading(noMoreData) {
      this.loading = false
      this.noMore = noMoreData
    }
  }
}
</script>

<style>
.browse-more {
  width: 110px;
  padding: 5px;
  margin: 10px auto;
  border: 1px solid #409EFF;
  border-radius: 4px;
}

.browse-more.loading {
  border: none;
}

.browse-more a {
  display: block;
  position: relative;
  width: 100px;
  height: 30px;
  line-height: 30px;
}

.browse-more a:hover {
  cursor: pointer;
}

.browse-more a .text {
  margin: 0 auto;
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  text-align: center;
  font-size: 18px;
  color: #409EFF;
}

.browse-more a .spinner {
  width: 100px;
  height: 30px;
  margin: 0 auto;
  text-align: center;
}

.browse-more a .spinner > div {
  display: inline-block;
  width: 6px;
  height: 100%;
  background: #409EFF;
  animation: strechdelay 1.2s infinite ease-in-out;
}

.browse-more a .spinner .line2 {
  animation-delay: -1.1s;
}

.browse-more a .spinner .line3 {
  animation-delay: -1s;
}

.browse-more a .spinner .line4 {
  animation-delay: -0.9s;
}

.browse-more a .spinner .line5 {
  animation-delay: -0.8s;
}

@-moz-keyframes strechdelay {
  0%, 40%, 100% {
    -webkit-transform: scaleY(0.4);
  }
  20% {
    -webkit-transform: scaleY(1);
  }
}

@-webkit-keyframes strechdelay {
  0%, 40%, 100% {
    -webkit-transform: scaleY(0.4);
  }
  20% {
    -webkit-transform: scaleY(1);
  }
}

@-o-keyframes strechdelay {
  0%, 40%, 100% {
    -webkit-transform: scaleY(0.4);
  }
  20% {
    -webkit-transform: scaleY(1);
  }
}

@keyframes strechdelay {
  0%, 40%, 100% {
    -webkit-transform: scaleY(0.4);
  }
  20% {
    -webkit-transform: scaleY(1);
  }
}
</style>
