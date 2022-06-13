<template>
  <div class="tag-wall">
    <panel :title="'标签墙'">
      <div slot="content" class="content">
        <el-tag v-for="(tag , index) in tagList" :type="index | mapTagColors" :key="index" style="margin: 0 5px 5px 0;"
                class="dot-tag" size="small">{{ tag.name + ' [' + tag.linkNum + ']' }}
        </el-tag>
      </div>
    </panel>
  </div>
</template>

<script>
import {tagList } from '../../../api/all';
export default {
  data() {
    return {
      tagList: []
    };
  },

  components: {},

  computed: {},

  mounted() {
    tagList().then(res =>{
      this.tagList = res.data
    })
  },

  methods: {}
}
</script>

<style>
.tag-wall .content {
  padding: 5px 20px;
}
</style>
