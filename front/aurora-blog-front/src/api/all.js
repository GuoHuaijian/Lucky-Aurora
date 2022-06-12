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

// 获取文章详情
export function getArticle(id) {
    return request({
        url: '/portal/article/'+id,
        method: 'get',
    })
}


// 获取标签列表
export function tagList() {
    return request({
        url: '/portal/tag/tags',
        method: 'get'
    })
}

// 获取分类列表
export function categoryList() {
    return request({
        url: '/portal/category/categories',
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
        url: '/portal/friend/friends',
        method: 'get'
    })
}

// 获取时间线列表
export function TimelineList() {
    return request({
        url: '/portal/timeline',
        method: 'get'
    })
}

// 添加评论
export function addComment(data) {
    return request({
        url: '/portal/comment/add',
        method: 'post',
        data: data
    })
}

// 查询评论列表
export function getComments(ownerId) {
    return request({
        url: '/portal/comment/' + ownerId,
        method: 'get'
    })
}
