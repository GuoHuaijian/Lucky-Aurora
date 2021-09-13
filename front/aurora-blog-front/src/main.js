import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'iview/dist/styles/iview.css';
import '../src/assets/aliicon/iconfont.css'
import '../src/assets/aliicon/iconfont'
import * as formatFilter from '../src/utils/filter'
import '../src/utils/GlobalComponents'

Vue.config.productionTip = false

Object.keys(formatFilter).forEach(key => {
    Vue.filter(key, formatFilter[key])
})

// iView UI 组件引入
import {
    Affix
} from 'iview'

// 国际化设置
Vue.use(ElementUI)
Vue.component('iv-affix', Affix)

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
