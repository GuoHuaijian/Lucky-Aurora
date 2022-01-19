package com.aurora.common.blog.mapper;

import com.aurora.common.blog.domain.line.Timeline;
import com.aurora.common.blog.domain.line.TimelineYear;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2022/1/19
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Mapper
public interface TimelineMapper {

    /**
     * 根据年份和月份获取时间线信息
     *
     * @param year
     * @param month
     * @return
     */
    List<Timeline> listTimeline(@Param("year") Integer year, @Param("month") Integer month);

    /**
     * 获取年份和数量
     *
     * @return
     */
    List<TimelineYear> listTimelineYear();
}
