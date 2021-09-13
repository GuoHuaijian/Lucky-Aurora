<template>
  <div class="panel">
    <h4>{{ title }}</h4>
    <slot name="content"></slot>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      default: ''
    }
  }
}
</script>

<style>
.panel {
  position: relative;
  background: #fff;
  border: 1px solid #eee;
  margin-top: 10px;
}

.panel h4 {
  font-size: 18px;
  padding: 13px 20px 13px;
  line-height: 18px;
  text-align: left;
  border-left: 5px solid #409eff;
  margin: 0 auto;
}

</style>
