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
public class TimelineYear {

    /**
     * 年份
     */
    private Integer year;

    /**
     * 总数
     */
    private Integer count;

    /**
     * 时间线信息按月分组
     */
    private List<TimelineMonth> months;
}
