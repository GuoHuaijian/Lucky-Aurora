<template>
  <div class="container" style="padding: 0 0">
    <header class="header">
      <slot name="header"></slot>
    </header>
    <div class="type-area">
      <aside class="aside">
        <slot name="aside"></slot>
      </aside>
      <main class="main">
        <slot name="main"></slot>
      </main>
    </div>
    <footer class="footer">
      <slot name="footer"></slot>
    </footer>
  </div>
</template>

<script>

export default {
  data() {
    return {};
  },

  components: {},

  methods: {}
};
</script>

<style>
.type-area {
  width: 1200px;
  margin: 0 auto;
  padding-top: 10px;
  display: block;
  overflow: hidden;
  position: relative;
}

.aside {
  width: 25%;
  float: right;
  margin-right: 50px;
  overflow: hidden;
  position: relative;
}

.main {
  width: 70%;
  float: left;
}

.footer {
  margin: 0 auto;
  width: 100%;
  display: block;
}
</style>
