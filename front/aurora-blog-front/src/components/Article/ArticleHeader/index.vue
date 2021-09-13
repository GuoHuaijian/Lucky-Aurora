<template>
  <section-title :main-title="mainTitle" :sub-title="subTitle" tip-text="View More" tip-href="/articles">
    <title-menu-filter @filterByMenu="filterByMenu"  slot="menu" :menu-filter-list="menuFilterList"></title-menu-filter>
  </section-title>
</template>

<script>
export default {
  props: {
    mainTitle: {
      type: String
    },
    subTitle: {
      type: String
    }
  },
  data() {
    return {
      menuFilterList: [
        {
          name: "最新",
          type: "latest",
          active: true,
        },
        {
          name: "点赞最多",
          type: "favorite",
          active: false,
        },
        {
          name: "推荐",
          type: "recommend",
          active: false,
        },
      ],
    };
  },
  components: {},

  computed: {},

  mounted() {
  },
  created: function () {},
  methods: {
    filterByMenu (params) {
      this.$emit('filterByMenu', params)
    }
  }
}
</script>

<style>

</style>
