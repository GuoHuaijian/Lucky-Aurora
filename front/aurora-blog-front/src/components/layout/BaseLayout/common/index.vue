<template>
  <div>
    <BaseLayout>
      <template v-slot:header>
        <Header/>
      </template>
      <template v-slot:aside>
        <slot name="profile"></slot>
        <Recommend/>
        <slot name="aside-content"></slot>
        <TagWall/>
        <slot name="friend-links"></slot>
        <slot name="side-toc"></slot>
      </template>
      <template v-slot:main>
        <slot name="content"></slot>
      </template>
      <template v-slot:footer>
        <Footer/>
      </template>
    </BaseLayout>
  </div>
</template>

<script>
import Header from '../../../../components/layout/header'
import TagWall from '../../../../components/aside/tagwall'
import Recommend from '../../../../components/aside/recommend'
import Footer from '../../../../components/layout/footer'
import BaseLayout from '../../../../components/layout/BaseLayout'

export default {
  data() {
    return {};
  },

  components: {
    Header,
    Recommend,
    TagWall,
    Footer,
    BaseLayout
  },

  methods: {}
};
</script>

<style>

</style>
