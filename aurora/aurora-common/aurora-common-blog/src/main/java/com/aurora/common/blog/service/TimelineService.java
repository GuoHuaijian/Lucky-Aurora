package com.aurora.common.blog.service;

import com.aurora.common.blog.domain.line.TimelineYear;

import java.util.List;

/**
 * describe: 时间线
 *
 * @Author Guo Huaijian
 * @Date 2022/1/19
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface TimelineService {

    /**
     * 获取时间线信息
     *
     * @return
     */
    List<TimelineYear> listTimeLine();
}
