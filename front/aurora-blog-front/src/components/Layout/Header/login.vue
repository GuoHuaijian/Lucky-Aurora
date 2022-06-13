<template>
  <div>
    <div class="login_form">
      <div class="login" v-if="isLogin == true">
        <el-dropdown @command="handleCommand">
        <span class="el-dropdown-link">
          <el-avatar :src="url"></el-avatar>
        </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item icon="el-icon-user" command="user">个人中心</el-dropdown-item>
            <el-dropdown-item icon="el-icon-switch-button" command="logout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
      <div class="login" v-else>
        <el-button type="text" @click="loginDialog()">登录</el-button>
        <el-button type="text" @click="loginDialog()">注册</el-button>
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
        <el-form-item class="btns">
          <el-button type="primary" @click="login('ruleForm')" style="width: 260px">登录</el-button>
        </el-form-item>
        <el-form-item class="three-login">
          <span>第三方登录: </span>
          <div class="login-box">
            <a @click="login('gitee')" class="login-icon">
              <img src="https://cdn.jsdelivr.net/gh/justauth/justauth-oauth-logo@1.11/gitee.png"/>
            </a>
            <a @click="login('github')" class="login-icon">
              <img src="https://cdn.jsdelivr.net/gh/justauth/justauth-oauth-logo@1.11/github.png"/>
            </a>
          </div>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {getAuthorize, getInfo} from '../../../api/all';

export default {
  data() {
    return {
      url: null,
      name: null,
      authUrl: null,
      platform: {
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
    loginDialog() {
      this.open = true
    },
    handleCommand(command){
      if (command === 'logout'){
        localStorage.removeItem("authCode");
        location.reload();
      }
      if (command === 'user'){
        alert("还未实现")
      }
    },
    login(source) {
      this.platform.source = source;
      getAuthorize(this.platform).then(res => {
        this.authUrl = res.data.authorizeUrl;
        // 更新状态码与登录平台名称
        this.platform.state = res.data.state;

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
    getAuthCode() {
      // 获取授权码
      const code = localStorage.getItem("authCode");
      this.platform.code = code
      //localStorage.removeItem("authCode");
      // 移除localStorage监听
      this.removeStorageListener();
      if (code) {
        // 调用登录函数
        this.authLogin();
        return;
      }
      throw this.platform + "授权码获取失败";
    },
    authLogin() {
      getInfo(this.platform).then(res => {
        this.url = res.data.avatar
        this.name = res.data.nickname
        let userId = res.data.userId
        localStorage.setItem("url", this.url)
        localStorage.setItem("name", this.name)
        localStorage.setItem("userId",userId)
        this.open = false
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

.btns {
  display: flex;
  justify-content: left;
  align-items: center;
}
.three-login{
  width: 260px;
  height: 40px;
  display: flex;
  justify-content: left;
  align-items: center;
}
::v-deep .el-form-item__content {
  display: flex;
  line-height: 40px;
  position: relative;
  font-size: 14px;
}
::v-deep .el-button+.el-button{
  margin-left: 30px;
}
.login-box{
  display: flex;
  justify-content: center;
align-items: center;
}
.login-icon{
  margin-left: 8px;
  height: 40px;
}
</style>
