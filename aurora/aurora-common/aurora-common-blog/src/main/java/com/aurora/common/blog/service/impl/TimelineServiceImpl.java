package com.aurora.common.blog.service.impl;

import com.aurora.common.blog.domain.line.Timeline;
import com.aurora.common.blog.domain.line.TimelineMonth;
import com.aurora.common.blog.domain.line.TimelineYear;
import com.aurora.common.blog.mapper.TimelineMapper;
import com.aurora.common.blog.service.TimelineService;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2022/1/19
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class TimelineServiceImpl implements TimelineService {

    @Resource
    private TimelineMapper timelineMapper;

    /**
     * 获取时间信息
     *
     * @return
     */
    @Override
    public List<TimelineYear> listTimeLine() {
        List<TimelineYear> timelineYears = timelineMapper.listTimelineYear();
        genTimelineMonth(timelineYears);
        return timelineYears;
    }

    /**
     * 按月获取时间线信息
     *
     * @param timelineYears
     * @return
     */
    private List<TimelineYear> genTimelineMonth(List<TimelineYear> timelineYears) {
        for (TimelineYear timeline : timelineYears) {
            List<TimelineMonth> timelineMonthList = Lists.newArrayList();
            for (int i = Calendar.DECEMBER + 1; i > 0; i--) {
                List<Timeline> postList = timelineMapper.listTimeline(timeline.getYear(), i);
                if (CollectionUtils.isEmpty(postList)) {
                    continue;
                }
                TimelineMonth month = new TimelineMonth();
                month.setCount(postList.size());
                month.setMonth(i);
                month.setTimelines(postList);
                timelineMonthList.add(month);
            }
            timeline.setMonths(timelineMonthList);
        }
        return timelineYears;
    }
}
