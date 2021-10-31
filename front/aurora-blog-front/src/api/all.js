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
