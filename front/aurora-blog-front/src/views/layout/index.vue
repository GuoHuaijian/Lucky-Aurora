<template>
<div>
    <Header class="header" />
    <div class="type-area">
        <Aside class="aside" />
        <Main class="main" />
        <Footer class="footer" />
    </div>
</div>
</template>

<script>
import Header from '../layout/header/index.vue'
import Aside from '../layout/aside/index.vue'
import Main from '../layout/main/index.vue'
import Footer from '../layout/footer/index.vue'
export default {
    name: "Layout",
    data() {
        return {};
    },

    components: {
        Header,
        Aside,
        Main,
        Footer
    },

    methods: {}
};
</script>

<style>
.type-area {
    width: 1000px;
    margin: 0 auto;
}

.aside {
    width: 30%;
    float: right;
    overflow: hidden;
    position: relative;
}

.main {
    width: 65%;
    float: left;
}

.footer {
    position: absolute;
    margin: 0 auto;
    bottom: 10px;;
}
</style>
