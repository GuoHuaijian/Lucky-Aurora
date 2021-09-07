package com.aurora.common.core.web.domain;

import cn.hutool.core.util.StrUtil;
import com.aurora.common.core.utils.CamelCaseUtil;
import lombok.Data;

/**
 * describe: 分页参数
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Data
public class PageDomain {

    /**
     * 每页显示条数，默认 10
     */
    private long size;

    /**
     * 当前页
     */
    private long current;

    /**
     * 排序字段 多个字段用","分隔
     */
    private String orderByColumn;

    /**
     * 排序的方向 "desc" 或者 "asc"
     */
    private String isAsc;

    /**
     * 组合排序sql字符串
     *
     * @return
     */
    public String getOrderBy() {
        if (StrUtil.isEmpty(orderByColumn)) {
            return "";
        }
        return CamelCaseUtil.toCapitalizeCamelCase(orderByColumn) + " " + isAsc;
    }
}
