<!--  -->
<template>
<div class="profile">
    <div class="avatar">
        <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
    </div>
    <div class="contact">
        <div class="contact-title">
            <h3>联系我</h3>
        </div>
        <div class="contact-icon">
            <i class="el-icon-user-solid"></i>
            <i class="el-icon-user"></i>
            <i class="el-icon-phone"></i>
        </div>
    </div>
</div>
</template>

<script>
export default {
    data() {
        return {};
    },

    components: {},

    methods: {}
}
</script>

<style>
.profile {
    height: 300px;
    margin-top: 0;
    background-color: aliceblue;
}

.avatar {
    padding-top: 20px;
    margin: 0 auto;
    position: relative;
}

.el-avatar {
    height: 100px;
    width: 100px;
}

.contact {
    height: 150px;
}

.contact-title {
    width: 100%;
    height: 50px;
}

.contact-title h3 {
    float: left;
}

.contact-icon {
    display: block;
    position: relative;
    margin-bottom: 50px;
    margin: 0 auto;
}
</style>
