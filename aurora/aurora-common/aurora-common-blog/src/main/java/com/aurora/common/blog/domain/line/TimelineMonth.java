package com.aurora.common.blog.domain.line;

import lombok.Data;

import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2022/1/19
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
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
