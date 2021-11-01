import request from '../utils/httpRequest'
// 获取授权地址
export function getAuthorize(data) {
    return request({
        url: '/oauth/render',
        method: 'get',
        params: data
    })
}

// 获取用户信息
export function getInfo(data) {
    return request({
        url: '/oauth/getInfo',
        method: 'post',
        params: data
    })
}

// 获取博文列表
export function articleList(query) {
    return request({
        url: '/portal/article/list',
        method: 'get',
        params: query
    })
}

// 获取标签列表
export function tagList() {
    return request({
        url: '/portal/article/tags',
        method: 'get'
    })
}

// 获取分类列表
export function categoryList() {
    return request({
        url: '/portal/article/categories',
        method: 'get'
    })
}

// 获取轮播图列表
export function carouselList() {
    return request({
        url: '/portal/carousel/list',
        method: 'get'
    })
}

// 获取轮播图列表
export function friendList() {
    return request({
        url: '/portal/article/friends',
        method: 'get'
    })
}
