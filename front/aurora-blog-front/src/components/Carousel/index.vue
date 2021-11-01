<template>
  <div>
    <el-carousel :interval="5000" arrow="hover">
      <el-carousel-item v-for="item in items" :key="item.carouselId">
        <a :href="item.url">
        <img :src="item.imgUrl" class="carousel-img" :alt="item.name"/>
        </a>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>

<script>
export default {
  props: {
    items: {
      Type: Object
    }
  },
  data() {
    return {};
  },

  components: {},

  methods: {}
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

.carousel-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
