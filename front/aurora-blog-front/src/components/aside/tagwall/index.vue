<template>
  <div class="tag-wall">
    <Panel :title="'标签墙'">
      <div slot="content" class="content">
        <el-tag v-for="(tag , index) in tagList" :type="index | mapTagColors" :key="index" style="margin: 0 5px 5px 0;"
                class="dot-tag" size="small">{{ tag.name + ' [' + tag.linkNum + ']' }}
        </el-tag>
      </div>
    </Panel>
  </div>
</template>

<script>
import Panel from '../../common/panel'

export default {
  data() {
    return {
      tagList: [{
        "id": null,
        "name": "本站相关",
        "type": null,
        "linkNum": "1"
      },
        {
          "id": null,
          "name": "关于",
          "type": null,
          "linkNum": "1"
        },
        {
          "id": null,
          "name": "Java",
          "type": null,
          "linkNum": "4"
        },
        {
          "id": null,
          "name": "JVM",
          "type": null,
          "linkNum": "2"
        },
        {
          "id": null,
          "name": "面试",
          "type": null,
          "linkNum": "1"
        },
        {
          "id": null,
          "name": "ElasticSearch",
          "type": null,
          "linkNum": "1"
        },
        {
          "id": null,
          "name": "Arthas",
          "type": null,
          "linkNum": "1"
        }]
    };
  },

  components: {
    Panel
  },

  computed: {},

  mounted() {
  },

  methods: {}
}
</script>

<style>
.tag-wall .content {
  padding: 5px 20px;
}
</style>
