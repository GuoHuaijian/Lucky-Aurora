import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        component: () => import('../views/index/index')
    }, {
        path: '/articles',
        name: 'articles',
        component: () => import('../views/article/list/index')
    }, {
        path: '/Article',
        name: 'article',
        component: () => import('../views/article/detail/index')
    }, {
        path: '/Archive',
        name: 'archive',
        component: () => import('../views/archive/index')
    },{
        path: '/message',
        name: 'message',
        component: () => import('../views/message/index')
    },{
        path: '/about',
        name: 'about',
        component: () => import('../views/about/index')
    }
]

const router = new VueRouter({
    routes
})

export default router
