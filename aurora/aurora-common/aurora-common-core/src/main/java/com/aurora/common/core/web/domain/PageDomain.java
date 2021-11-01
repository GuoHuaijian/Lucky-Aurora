package com.aurora.common.core.web.domain;

import cn.hutool.core.util.StrUtil;
import com.aurora.common.core.utils.CamelCaseUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * describe: 分页参数
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
public class PageDomain {

    /**
     * 降序
     */
    private static final String DESC = "desc";

    /**
     * 升序
     */
    private static final String ASC = "asc";
    /**
     * 每页显示条数，默认 10
     */
    private long pageSize;

    /**
     * 页码
     */
    private long pageNum;

    /**
     * 排序字段 多个字段用","分隔
     */
    private String orderByColumn;

    /**
     * 排序的方向 "desc" 或者 "asc"
     */
    private String isAsc = ASC;

    /**
     * 组合排序sql字符串
     *
     * @return
     */
    public String getOrderBy() {
        if (StrUtil.isEmpty(orderByColumn)) {
            return "";
        }
        if (isAsc.equalsIgnoreCase(DESC)) {
            List<String> descStr = Lists.newArrayList();
            List<String> columns = Arrays.asList(CamelCaseUtil.toUnderlineName(orderByColumn).split(","));
            columns.forEach(column -> descStr.add(StrUtil.format(column + " {}", DESC)));
            return StringUtils.join(descStr, ",");
        }
        return CamelCaseUtil.toUnderlineName(orderByColumn) + " " + isAsc;
    }
}
