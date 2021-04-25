package com.aurora.common.core.web.page;

import com.aurora.common.core.utils.ServletUtils;
import com.aurora.common.core.web.domain.PageDomain;

/**
 * describe: 分页支持
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
public class PageSupport {

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "current";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "size";

    /**
     * 排序字段  多个字段用","分隔
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 封装分页对象
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = new PageDomain();
        pageDomain.setCurrent(ServletUtils.getParameterToLong(PAGE_NUM));
        pageDomain.setSize(ServletUtils.getParameterToLong(PAGE_SIZE));
        pageDomain.setOrderByColumn(ServletUtils.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtils.getParameter(IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
