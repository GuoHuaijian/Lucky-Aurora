<template>
<div>
    <el-carousel :interval="5000" arrow="hover">
        <el-carousel-item v-for="item in items" :key="item">
            <img :src="item" class="carousel-img" />
        </el-carousel-item>
    </el-carousel>
</div>
</template>

<script>
export default {
    data() {
        return {
            items: [
                'https://img0.baidu.com/it/u=3934513038,3120860854&fm=26&fmt=auto&gp=0.jpg',
                'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0330%252Fe8365c0ej00qqqo760020c000hs012hc.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1622124124&t=c03ee2f168bbe96637f8ea8c67ff2b7a',
                'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fnimg.ws.126.net%2F%3Furl%3Dhttp%253A%252F%252Fdingyue.ws.126.net%252F2021%252F0330%252F61da4b5ej00qqqo76002ic000hs012hc.jpg%26thumbnail%3D650x2147483647%26quality%3D80%26type%3Djpg&refer=http%3A%2F%2Fnimg.ws.126.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1622124151&t=164dc7146c9313ebfea7b2c9f529f218'
            ],
        };
    },

    components: {},

    methods: {}
}
</script>

<style>
.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}

.carousel-img {
    max-width: 100%;
    max-height: 100%;
}
</style>
