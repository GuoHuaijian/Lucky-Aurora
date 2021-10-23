import request from '@/utils/request'

// 查询文章列表
export function listArticle(query) {
  return request({
    url: '/admin/article/list',
    method: 'get',
    params: query
  })
}

// 查询文章详细
export function getArticle(articleId) {
  return request({
    url: '/admin/article/' + articleId,
    method: 'get'
  })
}

// 新增文章
export function addArticle(data) {
  return request({
    url: '/admin/article',
    method: 'post',
    data: data
  })
}

// 修改文章
export function updateArticle(data) {
  return request({
    url: '/admin/article',
    method: 'put',
    data: data
  })
}

// 删除文章
export function delArticle(articleId) {
  return request({
    url: '/admin/article/' + articleId,
    method: 'delete'
  })
}

// 导出文章
export function exportArticle(query) {
  return request({
    url: '/admin/article/export',
    method: 'get',
    params: query
  })
}

export function uploadFile(data) {
  return request({
    url: '/file/upload',
    method: 'post',
    data: data
  })
}
