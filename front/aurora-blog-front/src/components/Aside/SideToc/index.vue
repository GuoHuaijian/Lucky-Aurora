<template>
  <div class="side-toc" ref="list">
    <panel :title="'目录'" id="side-toc">
    </panel>
  </div>
</template>

<script>
export default {
  methods: {
    recalcActive () {
      // 先计算list相对于页面的顶部距离
      var listScrollTop = this.$refs.list.getBoundingClientRect().top + document.documentElement.scrollTop
      // 再计算active相对于页面的顶部距离
      var activeAnode = this.$refs.list.querySelector('.active')
      if (activeAnode === null) {
        return
      }
      var activeANodeScrollTop = activeAnode.getBoundingClientRect().top + document.documentElement.scrollTop
      var activeIndicator = this.$refs.list.querySelector('.active-indicator')
      activeIndicator.style.top = activeANodeScrollTop - listScrollTop + 'px'
      activeIndicator.style.height = activeAnode.clientHeight + 'px'
    },
    beActive (event) {
      var activeANode = this.$refs.list.querySelector('.active')
      if (activeANode !== null) {
        activeANode.classList.remove('active')
      }
      event.target.classList.add('active')
      this.$nextTick(() => {
        this.recalcActive()
      })
    }
  },
  mounted: function () {
    this.recalcActive()
  },
  updated: function () {
    this.recalcActive()
  },
  components: {}
}
</script>

<style >
.side-toc {
  position: relative;
  background: #fff;
}
.side-toc h4 {
  font-size: 18px;
  padding: 13px 13px 0;
  line-height: 18px;
  text-align: left;
}
.side-toc .list {
  position: relative;
  padding: 0 25px 15px;
  margin-top: 10px;
}
.side-toc .list .active-indicator {
  position: absolute;
  left: -1px;
  right: 0;
  background-color: #f9f9f9;
  border-left: 2px solid #409EFF;
  z-index: 1;
  transition: all 0.2s ease-out;
}
.side-toc .list ul {
  position: relative;
  padding-left: 1em;
  z-index: 2;
}
.side-toc .list ul > li {
  list-style-type: square;
  line-height: 2.2em;
  text-align: left;
}
.side-toc .list ul > li > a {
  cursor: pointer;
  display: block;
  height: 100%;
  width: 100%;
  margin: 0;
  padding: 0;
  font-size: 16px;
  color: #363636;
  text-decoration: none;
}
.side-toc .list ul > li > a.active {
  font-weight: 800;
  color: #409EFF;
  text-decoration: none;
}
.side-toc .list ul > li > ul > li > a {
  font-size: 16px;
  color: #828282;
  text-decoration: none;
}
</style>
