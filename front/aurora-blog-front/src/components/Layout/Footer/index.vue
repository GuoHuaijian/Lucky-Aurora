<template>
  <div class="common-footer">
    <div class="copyright_center">
      <p class="copyright">
        <a href="http://www.miibeian.gov.cn/" target="_blank">{{ siteInfo.icp }}</a>
        <span>|</span>
        {{ siteInfo.copyright }}
        <span>|</span>
        <span v-html="siteInfo.copyright_desc"></span>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      siteInfo: {
        icp: '蜀ICP备19025085号-2 ',
        copyright: '版权所有 © 2018 - 2021 ',
        copyright_desc: ' 以商业目的使用本网站内容需获许可，非商业目的使用授权遵循CC BY-NC 4.0'
      }
    };
  },

  components: {},

  methods: {}
}
</script>

<style>
.common-footer {
  font-weight: 300;
  line-height: 25px;
  text-align: center;
  margin: 15px 0;
  color: #333;
  border-top: 1px solid #eee;
  background: #fff;
  overflow: hidden;
  position: relative;
  display: block;
}

@media only screen and (max-width: 768px) {
  .common-footer {
    font-size: 13px;
  }
}

@media screen and (min-width: 768px) {
  .common-footer {
    font-size: 14px;
  }
}

@media screen and (min-width: 992px) {
  .common-footer {
    font-size: 15px;
  }
}

@media screen and (min-width: 1200px) {
  .common-footer {
    font-size: 15px;
  }
}

.copyright_center {
  width: 1200px;
  margin: 0 auto;
  overflow: hidden;
  position: relative;
  display: block;
  text-align: center;
}

.common-footer .copyright {
  margin: 20px 10px 10px 10px;
  text-align: center;
  width: 1200px;
  color: #333;
}

.common-footer .copyright a {
  color: #777;
}

.common-footer .copyright a:hover {
  color: #409EFF;
}
</style>
