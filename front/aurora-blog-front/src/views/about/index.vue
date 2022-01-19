<template>
  <common-layout>
    <template v-slot:content>
      <section-title main-title="关于我" sub-title="AboutMe"/>
            <div class="about-content" style="padding:0;">
              <ul class="myblog">
                <div class="intro">
                  <h5>博客简介 | Blog：</h5>
                  <div class="intro_detail">
                    <div>
                      程&nbsp;序：基于Dubbo开发的个人博客程序。 <span class="wb_url"><a
                        href="https://github.com/guohuaijian"
                        target="_blank">GitHub</a></span><br>
                      域&nbsp;名：www.guohuaijian.com，创建于2018<span class="wb_url"><a
                        href="https://wanwang.aliyun.com/"
                        target="_blank">注册域名</a></span><br>
                      服务器：阿里云轻量级应用服务器(2G)<span class="wb_url"><a
                        href="https://promotion.aliyun.com/ntms/yunparter/invite.html?userCode=3viaxu0k"
                        target="_blank">购买空间</a></span><br>
                      备案号：蜀ICP备19025085号-2<br>
                    </div>
                  </div>
                </div>
              </ul>
              <p class="ab_dubai">
              <div class="intro">
                <h5>个人简介 | Resume：</h5>
                <div class="intro_detail">
                  <div>
                    人物：Autumn<br>
                    性别：男<br>
                    年龄：96后<br>
                    身高：1.70m<br>
                    体重：55kg<br>
                    现居：中国·四川·成都<br>
                    职业：Java开发工程师<br>
                    1、大学专业网络工程，大一大二期间每天玩游戏对编程一窍不通。直到大三有了就业压力开始疯狂泡图书馆，一年时间从一个小白变成菜鸟。<br>
                    2、大三一年每天起早贪黑从Java基础一直学到了微服务，大数据以及一点前端知识。事实证明只要努力大三还不算太晚<br>
                    3、大三暑假离开学校跟随老师外出企业实训。<br>
                    4、大三暑假结束学校安排的实训后，通过校招进入四川某科技公司担任Java开发实习生。<br>
                    5、大四暑假结束实习放弃公司提供的转正机会回校答辩，答辩完成后跟随同学去往北京求职进入北京某企业担任Java开发工程师一职正式开始苦逼的北漂生活，2022年申请调回成都分公司。<br>
                  </div>
                </div>
                <h5>技术方向 | Technology：</h5>
                <div class="intro_detail">
                  <div>
                    1、熟悉Java语法，多线程、集合等基础框架。<br>
                    2、对JVM原理有初步的理解，包括内存模型、垃圾回收机制。<br>
                    3、熟悉Redis的集群搭建，备份策略，了解高并发下缓存穿透缓存雪崩解决方案。<br>
                    4、熟悉Kafka消息中间件的集群搭建，利用MQ缓冲流量、削峰填谷。<br>
                    5、熟悉ElasticSearch的使用，实现数据的存储、搜索、分析。<br>
                    6、熟练使用主流的关系型数据库，如MySQL、Oracle，以及国产数据库人大金仓、达梦。<br>
                    7、熟悉Netty、Nginx、Zookeeper等中间件的使用，熟悉Linux常用命令。<br>
                    8、熟练掌Spring、Spring MVC、MyBatis、SpringBoot、SpringCIoud、Dubbo等主流框架。<br>
                    9、熟悉jQuery、vue等常用的前端技术，了解React的基本使用。<br>
                    10、熟练使用Eclipse/lDEA等开发工具，Maven构建项目工程以及使用SVN /Git实施版本控制。<br>
                  </div>
                </div>
                <h5 id="project">项目经验 | Project：</h5>
                <div class="intro_detail">
                  <div>
                    1、个人博客系统（正如您所看到的）。<br>
                    2、某医院智能电子病案系统。<br>
                    3、电子智慧档案系统。<br>
                    4、某集团办公网数字化档案系统（支撑其集团旗下二十多家分公司使用）。<br/>
                    4、参与共建某省云密码平台（实现全省公文系统文件加解密）。<br>
                    5、其他(遗忘，是对过去的缅怀)。<br>
                  </div>
                </div>
                <h5>兴趣爱好 | Hobby：</h5>
                <div class="intro_detail">
                  <div>
                    1、向往自由梦想着以后经济情况允许的情况实现自驾318看看离天堂最近的地方。<br>
                    2、喜欢看别人的代码，经常会上GitHub拉一些代码看看，看到写的好就会模仿着写，不得说和大神的差距真是遥不可及。<br>
                    3、喜欢旅游，大学期间到现在去看了祖国的好几处大好河山，未来将会看更多的山。<br>
                    4、喜欢读书，但是属于那种好读书不求甚解，不太爱深究各种原理，只喜欢写自己想写的代码。<br>
                  </div>
                </div>
              </div>
            </div>
    </template>
  </common-layout>
</template>

<script>
export default {
  data() {
    return {};
  },

  components: {},

  computed: {},

  mounted() {
  },

  methods: {}
}
</script>

<style scoped>
.about-content {
  line-height: 25px;
  margin-bottom: 30px;
  padding: 20px;
}
.intro {
  text-align: left;
  float: left;
  font-size: 14px;
}
.intro h5 {
  width: 60%;
  font-weight: bold;
  margin-top: 10px;
  float: left;
}
.intro hr {
  border: 0.5px dashed #ccc;
}
.intro_detail div {
  margin: 10px 0;
  float: left;
  padding: 10px 20px 10px 20px;
  color: #555;
  border-left: 4px solid #2d8cf0;
}
.intro_detail .wb_url {
  margin: 0 10px;
}
.intro_detail .wb_url a {
  color: #2d8cf0;;
}
.intro span {
  color: #ff3366;
}
.ab_box h3 {
  margin: 10px 0 10px;
  font-size: 18px;
}
.ab_box p {
  color: #666;
  margin-bottom: 5px
}
.avatar_pic img {
  width: 100%;
  border-radius: 50%;
}
.ab_dubai {
  margin-bottom: 15px;
  display: inline-block;
}
.ly_button {
  float: right;
  width: 100px;
  background: #000;
  text-align: center;
  border-radius: 3px;
  line-height: 30px
}
.ly_button a {
  color: #FFF
}
</style>
