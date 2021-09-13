<template>
  <div class="classify-bar" v-if="categorys !== undefined">
    <p class="level level-one">
      <span class="title">分类：</span>
      <span class="class">
        <a class="active" data-level="1" @click="choseLevel($event)">全部</a>
        <a class="name" :id="'id' + category.id" :data-level="category.ranks"
           @click="choseLevel($event)"
           v-for="category in this.categorys" :key="category.id">{{ category.name }}</a>
      </span>
    </p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      props: {
        categorys: Array
      },
    }
  },

  components: {},

  computed: {},

  mounted() {
  },

  methods: {
    choseLevel (event) {
      // 更新样式
      let pNode = event.target.parentNode
      let activeNode = pNode.querySelector('.active')
      if (activeNode) {
        activeNode.classList.remove('active')
      }
      event.target.classList.add('active')
    },
  }
}
</script>

<style scoped>
.classify-bar .level {
  display: flex;
  padding: 10px 0;
  font-size: 15px;
  line-height: 22px;
  border-bottom: 1px solid #eee;
}
.classify-bar .level .title {
  display: inline-block;
  flex: 0 0 60px;
  width: 60px;
  font-size: 17px;
  line-height: 22px;
  padding: 4px 0;
  font-weight: 700;
  color: #333;
}
.classify-bar .level .class a {
  display: inline-block;
  margin-right: 4px;
  padding: 3px 8px;
  margin-bottom: 2px;
  font-weight: 300;
  border-radius: 4px;
  color: #777;
}
.classify-bar .level .class a.name:hover {
  color: #409EFF;
}
.classify-bar .level .class a.active {
  color: #fff;
  background: #555;
}
</style>
