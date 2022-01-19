package com.aurora.common.blog.domain.line;

import lombok.Data;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2022/1/19
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Data
public class TimelineMonth {

    /**
     * 月份
     */
    private Integer month;

    /**
     * 总数
     */
    private Integer count;

    /**
     * 时间线信息
     */
    private List<Timeline> timelines;
}
