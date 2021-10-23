package com.aurora.common.core.web.controller;

import cn.hutool.core.date.DateUtil;
import com.aurora.common.core.utils.SqlUtil;
import com.aurora.common.core.web.domain.PageDomain;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.core.web.page.PageDate;
import com.aurora.common.core.web.page.PageSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * describe: 通用controller处理
 *
 * @Author Guo Huaijian
 * @Date 2020/12/31
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public abstract class AbstractController {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtil.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = PageSupport.buildPageRequest();
        Long pageNum = pageDomain.getPageNum();
        Long pageSize = pageDomain.getPageSize();
        if (StringUtils.hasLength(String.valueOf(pageNum)) && StringUtils.hasLength(String.valueOf(pageSize))) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            // 紧跟着的第一个select方法会被分页 后面的不会被分页，除非再次调用PageHelper.startPage
            PageHelper.startPage(pageNum.intValue(), pageSize.intValue(), orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected PageDate getPageDate(List<?> list) {
        PageDate date = new PageDate();
        date.setData(list);
        date.setTotal(new PageInfo(list).getTotal());
        return date;
    }

    /**
     * 响应返回结果
     *
     * @param result
     * @return 操作结果
     */
    protected Result toResult(boolean result) {
        if (result) {
            return Result.success();
        }
        return Result.error();
    }
}
