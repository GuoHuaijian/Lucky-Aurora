import request from '@/utils/request'

// 查询访问日志列表
export function list(query) {
  return request({
    url: '/system/visitLog/list',
    method: 'get',
    params: query
  })
}

// 删除访问日志
export function delVisitLog(visitId) {
  return request({
    url: '/system/visitLog/' + visitId,
    method: 'delete'
  })
}

// 清空访问日志
export function cleanVisitLog() {
  return request({
    url: '/system/visitLog/clean',
    method: 'delete'
  })
}

// 导出访问日志
export function exportVisitLog(query) {
  return request({
    url: '/system/visitLog/export',
    method: 'get',
    params: query
  })
}
