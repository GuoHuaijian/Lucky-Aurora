<template>
  <common-layout>
    <template v-slot:content>
      <section-title main-title="留言板" sub-title="MessageBoard"/>
      <bg-comment :comments="commentData" :comment-type=0 />
    </template>
  </common-layout>
</template>

<script>
import * as CommentData from "../../mock/commentdata";
export default {
  data() {
    return {
      commentData: [],
    };
  },

  components: {},

  computed: {},

  created() {
    this.commentData = CommentData.comment.data;
  },

  mounted() {
  },

  methods: {}
}
</script>

<style scoped>

</style>
