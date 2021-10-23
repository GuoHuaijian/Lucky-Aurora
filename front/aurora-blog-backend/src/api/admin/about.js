import request from '@/utils/request'

// 查询关于我列表
export function listAbout(query) {
  return request({
    url: '/admin/about/list',
    method: 'get',
    params: query
  })
}

// 查询关于我详细
export function getAbout(aboutId) {
  return request({
    url: '/admin/about/' + aboutId,
    method: 'get'
  })
}

// 新增关于我
export function addAbout(data) {
  return request({
    url: '/admin/about',
    method: 'post',
    data: data
  })
}

// 修改关于我
export function updateAbout(data) {
  return request({
    url: '/admin/about',
    method: 'put',
    data: data
  })
}

// 删除关于我
export function delAbout(aboutId) {
  return request({
    url: '/admin/about/' + aboutId,
    method: 'delete'
  })
}

// 导出关于我
export function exportAbout(query) {
  return request({
    url: '/admin/about/export',
    method: 'get',
    params: query
  })
}
