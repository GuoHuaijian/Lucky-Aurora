<template>
  <common-layout>
    <template v-slot:content>
      <section-title main-title="留言板" sub-title="MessageBoard"/>
      <bg-comment :comments="commentData" :comment-type=0 />
    </template>
  </common-layout>
</template>

<script>
import {getComments} from '../../api/all';
export default {
  data() {
    return {
      commentData: [],
    };
  },

  components: {},

  computed: {},

  created() {
  },

  mounted() {
    this.comments()
  },

  methods: {
    comments() {
      getComments(0).then(res => {
        this.commentData = res.data
      })
    },
  }
}
</script>

<style scoped>

</style>
