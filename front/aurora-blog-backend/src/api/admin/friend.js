import request from '@/utils/request'

// 查询友链列表
export function listFriend(query) {
  return request({
    url: '/admin/friend/list',
    method: 'get',
    params: query
  })
}

// 查询友链详细
export function getFriend(friendId) {
  return request({
    url: '/admin/friend/' + friendId,
    method: 'get'
  })
}

// 新增友链
export function addFriend(data) {
  return request({
    url: '/admin/friend',
    method: 'post',
    data: data
  })
}

// 修改友链
export function updateFriend(data) {
  return request({
    url: '/admin/friend',
    method: 'put',
    data: data
  })
}

// 删除友链
export function delFriend(friendId) {
  return request({
    url: '/admin/friend/' + friendId,
    method: 'delete'
  })
}

// 导出友链
export function exportFriend(query) {
  return request({
    url: '/admin/friend/export',
    method: 'get',
    params: query
  })
}
