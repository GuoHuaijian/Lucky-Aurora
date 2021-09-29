package com.aurora.common.core.web.page;

import com.aurora.common.core.utils.ServletUtil;
import com.aurora.common.core.web.domain.PageDomain;

import java.util.Optional;

/**
 * describe: 分页支持
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
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
        Long current = Optional.ofNullable(ServletUtil.getParameterToLong(PAGE_NUM)).orElse(1L);
        Long size = Optional.ofNullable(ServletUtil.getParameterToLong(PAGE_SIZE)).orElse(10L);
        pageDomain.setCurrent(current);
        pageDomain.setSize(size);
        pageDomain.setOrderByColumn(ServletUtil.getParameter(ORDER_BY_COLUMN));
        pageDomain.setIsAsc(ServletUtil.getParameter(IS_ASC));
        return pageDomain;
    }

    public static PageDomain buildPageRequest() {
        return getPageDomain();
    }
}
