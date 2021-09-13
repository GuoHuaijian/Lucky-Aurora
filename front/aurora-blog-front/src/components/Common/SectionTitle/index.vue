<template>
  <div class="section-title">
    <div class="title">
      <span class="square"></span>
      <span class="main-title">{{ mainTitle }}</span>
      <span class="vertical-line"></span>
      <span class="sub-title">{{ subTitle }}</span>
      <span class="view-more"
      ><a :href="tipHref" v-if="tipText">{{ tipText }}<i class="el-icon-caret-right"></i></a
      ></span>
    </div>
    <div class="menu">
      <slot name="menu"></slot>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    // eslint-disable-next-line vue/require-prop-type-constructor
    mainTitle: {
      type: String
    },
    // eslint-disable-next-line vue/require-prop-type-constructor
    subTitle: {
      type: String
    },
    // eslint-disable-next-line vue/require-prop-type-constructor
    tipText: {
      type: String
    },
    // eslint-disable-next-line vue/require-prop-type-constructor
    tipHref: {
      type: String
    }
  },


  components: {},

  computed: {},

  mounted() {
  },

  methods: {},
};
</script>

<style>
.section-title {
  display: flex;
  justify-content: space-between;
  padding: 20px 0;
  height: 90px;
  line-height: 50px;
  text-align: left;
  box-sizing: border-box;
@media only screen and (max-width: 768 px);
  padding: 10px 0;
  height: 70px;
}

.title .square {
  display: inline-block;
  height: 30px;
  width: 30px;
  background: #000000;
  vertical-align: middle;
}

.main-title {
  display: inline-block;
  vertical-align: middle;
  font-size: 22px;
  padding: 0 10px;
}

.vertical-line {
  display: inline-block;
  height: 20px;
  width: 1px;
  background-color: #333;
  vertical-align: middle;
}

.sub-title {
  display: inline-block;
  vertical-align: middle;
  font-size: 18px;
  padding-left: 10px;
}

.view-more {
  display: inline-block;
  vertical-align: middle;
  font-size: 14px;
  padding-left: 10px;
}

.view-more a {
  color: #409eff;
  font-weight: 500;
  cursor: pointer;
}

.view-more a:hover {
  text-decoration: underline;
}

/*.menu {*/
/*  height: 100%;*/
/*  line-height: inherit;*/
/*  float: right;*/
/*  @media only screen and (max-width: 768px);*/
/*  display: none;*/
/*}*/
</style>
