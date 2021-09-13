import {socialDateFormat, mapTagColor} from '../utils/index'
import {LineBreakMode} from '../common/js/const'

// 用于映射标签颜色
export function mapTagColors(index) {
    return mapTagColor(index)
}

// 用于格式化时间的过滤器
export function socialDate(formatedDate) {
    return socialDateFormat(formatedDate)
}

// 去除html标签
export function filterHtml(richText) {
    return richText.replace(/<.+?>/g, '')
}

// 用于处理行尾省略号的过滤器
export function textLineBreak(text, maxLength, lineBreakMode) {
    if (text === undefined || text === null || text.length === 0) {
        return ''
    }
    if (lineBreakMode === null || lineBreakMode === undefined) {
        lineBreakMode = LineBreakMode.EllipsisTruncatingTail
    }
    switch (lineBreakMode) {
        case LineBreakMode.WrappingTruncatingTail: {
            return text.substr(0, maxLength)
        }

        case LineBreakMode.WrappingTruncatingHead: {
            return text.substr(-maxLength)
        }
        case LineBreakMode.EllipsisTruncatingTail: {
            return text.substr(0, maxLength) + (text.length > maxLength ? '...' : '')
        }
        case LineBreakMode.EllipsisTruncatingMiddle: {
            let resultText = text.substr(0, maxLength)
            if (text.length > maxLength) {
                return resultText.substr(0, parseInt(maxLength / 2)) + '...' + resultText.substr(parseInt(maxLength / 2))
            }
            return resultText
        }
        case LineBreakMode.EllipsisTruncatingHead: {
            return (text.length > maxLength ? '...' : '') + text.substr(-maxLength)
        }
    }
    return text
}
