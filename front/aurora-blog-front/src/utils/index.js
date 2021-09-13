/**
 * [Datestr 时间戳转字符串格式]
 */
export function socialDateFormat(date) {
    // 获取js 时间戳
    var time = new Date().getTime()
    // 去掉 js 时间戳后三位
    time = parseInt((time - date) / 1000)
    // 存储转换值
    var s
    if (time < 60 * 10) { // 十分钟内
        return '刚刚'
    } else if ((time < 60 * 60) && (time >= 60 * 10)) {
        // 超过十分钟少于1小时
        s = Math.floor(time / 60)
        return s + '分钟前'
    } else if ((time < 60 * 60 * 24) && (time >= 60 * 60)) {
        // 超过1小时少于24小时
        s = Math.floor(time / 60 / 60)
        return s + '小时前'
    } else if ((time < 60 * 60 * 24 * 3) && (time >= 60 * 60 * 24)) {
        // 超过1天少于3天内
        s = Math.floor(time / 60 / 60 / 24)
        return s + '天前'
    } else {
        // 超过3天
        date = new Date(parseInt(date))
        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
    }
}

/**
 *  映射tag颜色
 */
export function mapTagColor(index) {
    switch (index % 4) {
        case 0:
            return ''
        case 1:
            return 'success'
        case 2:
            return 'danger'
        case 3:
            return 'warning'
    }
}

/**
 * 树形数据转换
 * @param {*} data
 * @param {*} id
 * @param {*} pid
 */
export function treeDataTranslate(data, id = 'id', pid = 'parentId') {
    var res = []
    var temp = {}
    for (var i = 0; i < data.length; i++) {
        temp[data[i][id]] = data[i]
    }
    for (var k = 0; k < data.length; k++) {
        if (temp[data[k][pid]] && data[k][id] !== data[k][pid]) {
            if (!temp[data[k][pid]]['children']) {
                temp[data[k][pid]]['children'] = []
            }
            if (!temp[data[k][pid]]['_level']) {
                temp[data[k][pid]]['_level'] = 1
            }
            data[k]['_level'] = temp[data[k][pid]]._level + 1
            temp[data[k][pid]]['children'].push(data[k])
        } else {
            res.push(data[k])
        }
    }
    return res
}
