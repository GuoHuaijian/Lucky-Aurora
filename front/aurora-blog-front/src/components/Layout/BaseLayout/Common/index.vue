<template>
  <div>
    <base-layout>
      <template v-slot:header>
        <bg-header/>
      </template>
      <template v-slot:aside>
        <slot name="profile"></slot>
        <recommend/>
        <slot name="aside-content"></slot>
        <tag-wall/>
        <slot name="friend-links"></slot>
        <slot name="side-toc"></slot>
      </template>
      <template v-slot:main>
        <slot name="content"></slot>
      </template>
      <template v-slot:footer>
        <bg-footer/>
      </template>
    </base-layout>
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

</style>
