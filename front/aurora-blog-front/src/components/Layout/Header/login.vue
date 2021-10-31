<template>
  <div>
    <div class="login_form">
      <div class="login" v-if="isLogin == true">
        <el-avatar :src="url" ></el-avatar>
        <el-button type="text"><span v-model="name">{{name}}</span></el-button>
      </div>
      <div class="login" v-else>
        <el-avatar src=""></el-avatar>
        <el-button type="text" @click="hello()">未登录</el-button>
      </div>
    </div>

    <!--    功能需要登录，且用户未登录时弹出 :visible.sync="open"  -->
    <el-dialog title="登录" :visible.sync="open" width="300px" append-to-body>
      <el-form :model="form" :rules="rules" ref="ruleForm" label-width="0px">
        <el-form-item prop="mobileNumber">
          <el-input placeholder="输入用户名称" v-model="form.mobileNumber">
            <template slot="prepend">账号</template>
          </el-input>
        </el-form-item>
        <el-form-item prop="passWord">
          <el-input placeholder="输入密码" v-model="form.passWord" type="password">
            <template slot="prepend">密码</template>
          </el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="login('ruleForm')">登录</el-button>
            <el-button @click="test()">取 消</el-button>
        </el-form-item>
        <el-form-item>
          <span>第三方登录:</span>
          <a @click="test('gitee')">
            <img src="https://cdn.jsdelivr.net/gh/justauth/justauth-oauth-logo@1.11/gitee.png"/>
          </a>
          <a @click="test('github')">
            <img src="https://cdn.jsdelivr.net/gh/justauth/justauth-oauth-logo@1.11/github.png"/>
          </a>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { getAuthorize,getInfo } from '../../../api/all';
export default {
  data() {
    return {
      url:null,
      name:null,
      authUrl: null,
      platform:{
      source: null,
      code: null,
      state: null,
      },
      isLogin: false,
      open: false,
      form: {
        mobileNumber: '',
        passWord: '',
        type: 0
      },
      rules: {
        mobileNumber: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {trigger: 'blur'}
        ],
        passWord: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ]
      }
    };
  },

  components: {},

  computed: {},

  mounted() {
    const code = localStorage.getItem("authCode");
    if (code) {
      this.isLogin = true;
    }
    const url = localStorage.getItem("url")
    const name = localStorage.getItem("name")
    this.url = url;
    this.name = name
  },

  methods: {
    hello() {
      this.open = true
    },
    test:function(source) {
      this.platform.source = source;
      getAuthorize(this.platform).then(res =>{
         this.authUrl = res.data.data.authorizeUrl;
        // 更新状态码与登录平台名称
        this.platform.state = res.data.data.state;

        // 打开授权窗口
        window.open(
            this.authUrl,
            "_blank",
            "toolbar=no,width=800, height=600"
        );
        this.authUrl = '';
        // 开始监听localStorage,获取授权码
        window.addEventListener("storage", this.getAuthCode);
      })
    },
    getAuthCode: function () {
      // 获取授权码
      const code = localStorage.getItem("authCode");
      this.platform.code = code
      //localStorage.removeItem("authCode");
      // 移除localStorage监听
      this.removeStorageListener();
      if (code) {
        // 调用登录函数
        this.open = false
        this.authLogin();
        return;
      }
      throw this.platform + "授权码获取失败";
    },
    authLogin() {
      getInfo(this.platform).then(res =>{
        this.url = res.data.data.avatar
        this.name = res.data.data.nickname
        localStorage.setItem("url",this.url)
        localStorage.setItem("name",this.name)
        location.reload();
      })
    },
    removeStorageListener: function () {
      // 移除localStorage监听
      window.removeEventListener("storage", this.getAuthCode);
    }
  }
}
</script>

<style scoped>
.login {
  height: 60px;
  margin: 0 auto;
  text-align: center;
}

.el-avatar {
  margin-top: 10px;
}

.login-name {
  height: 60px;
  line-height: 60px;
}
</style>
