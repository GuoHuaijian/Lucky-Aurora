<template>
  <div class="profile">
    <div class="about">
      <img class="background" src="../../../assets/img/background.jpg" alt=""/>
      <img class="avatar" src="../../../assets/img/avatar.jpg" alt=""/>
      <p class="name">郭怀检</p>
      <p class="desc">Write the code, Change the World</p>
      <ul class="social">
        <li>
          <a href="https://github.com/guohuaijian" target="_blank"
          ><img src="../../../assets/img/GitHub.png" alt=""
          /></a>
          <a href="https://github.com/guohuaijian" target="_blank"
          ><img src="../../../assets/img/weixin.jpeg" alt=""
          /></a>
          <a href="https://guohuaijian9527@gmail.com" target="_blank"
          ><img src="../../../assets/img/mail.jpeg" alt=""
          /></a>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },

  components: {},

  methods: {},
};
</script>

<style>
.profile {
  height: 300px;
  margin-top: 0;
  background-color: aliceblue;
}

.about {
  position: relative;
  text-align: center;
  border: 1px solid #eee;
  height: 300px;
  padding-bottom: 0px;
}

.about img.background {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  height: 100px;
  z-index: 1;
}

.about img.avatar {
  position: relative;
  margin: 50px auto 15px;
  width: 100px;
  height: 100px;
  border: 5px solid #e8e8e8;
  border-radius: 50%;
  z-index: 9;
}

.name {
  font-size: 22px;
  color: #333333ff;
  line-height: 30px;
  font-weight: 700;
  margin-top: 0px;
  margin-bottom: 0px;
}

.desc {
  font-size: 15px;
  color: #878d99ff;
  line-height: 30px;
  font-weight: 100;
  margin-bottom: 0px;
}

.social {
  text-align: center;
  padding: 0 20px;
  margin-top: 0px;
  margin-bottom: 0px;
}

.social li {
  padding: 8px;
}

.social li a {
  display: inline-block;
  width: 44px;
  height: 44px;
  margin: 0 4px;
}

.social li a img {
  width: 100%;
}
</style>
