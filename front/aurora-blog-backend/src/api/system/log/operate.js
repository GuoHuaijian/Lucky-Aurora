import request from '@/utils/request'

// 查询操作日志列表
export function list(query) {
  return request({
    url: '/system/operateLog/list',
    method: 'get',
    params: query
  })
}

// 删除操作日志
export function delOperateLog(operateId) {
  return request({
    url: '/system/operateLog/' + operateId,
    method: 'delete'
  })
}

// 清空操作日志
export function cleanOperateLog() {
  return request({
    url: '/system/operateLog/clean',
    method: 'delete'
  })
}

// 导出操作日志
export function exportOperateLog(query) {
  return request({
    url: '/system/operateLog/export',
    method: 'get',
    params: query
  })
}
