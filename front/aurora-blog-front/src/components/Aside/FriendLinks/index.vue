<template>
  <div class="friend-links">
    <panel :title="'友情链接'">
      <ul class="link-list" slot="content">
        <li v-for="link in linkList" :key="link.friendId">
          <a :href="link.url" target="_blank">
            <img :src="link.icon" alt="">
            <div class="right">
              <p class="title">{{ link.name }}</p>
              <p class="link">{{ link.url }}</p>
            </div>
          </a>
        </li>
      </ul>
    </panel>
  </div>
</template>

<script>
import { friendList } from '../../../api/all';
export default {
  data() {
    return {
      linkList: []
    };
  },

  components: {},

  computed: {},

  mounted() {
    friendList().then(res =>{
      this.linkList = res.data
    })
  },

  methods: {}
}
</script>

<style>
.friend-links {
  position: relative;
}

.friend-links ul.link-list {
  padding: 15px 0;
  margin: 0 auto;
}

.friend-links ul.link-list li {
  padding: 2px 25px;
}

.friend-links ul.link-list li a {
  display: flex;
  transform: translate(0, 0);
  transition: all 0.2s ease-in;
}

.friend-links ul.link-list li a:hover {
  transform: translate(30px, 0);
  transition: all 0.2s ease-in-out;
}

.friend-links ul.link-list li a:hover .link {
  color: #2c3e50;
}

.friend-links ul.link-list li a img {
  flex: 0 0 50px;
  width: 50px;
  height: 50px;
  margin: 5px 0;
}

.friend-links ul.link-list li a .right {
  display: flex;
  flex-direction: column;
  padding-left: 12px;
  height: 60px;
  line-height: 60px;
}

.friend-links ul.link-list li a .right p {
  flex: 1;
  text-align: left;
}

.friend-links ul.link-list li a .right p.title {
  height: 35px;
  line-height: 45px;
  font-size: 17px;
  color: #409EFF;
  margin: 0 auto;
}

.friend-links ul.link-list li a .right p.link {
  height: 15px;
  line-height: 15px;
  font-weight: 100;
  margin: 0 auto;
}
</style>
