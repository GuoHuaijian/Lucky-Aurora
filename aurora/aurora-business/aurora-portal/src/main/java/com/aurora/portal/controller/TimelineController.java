package com.aurora.portal.controller;

import com.aurora.common.blog.domain.line.TimelineYear;
import com.aurora.common.blog.service.TimelineService;
import com.aurora.common.core.web.domain.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * describe:时间线
 *
 * @author Guo Huaijian
 * @date 2022/1/19
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/portal")
public class TimelineController {

    private final TimelineService timelineService;

    /**
     * 获取时间线信息
     *
     * @return
     */
    @GetMapping("timeline")
    public Result listTimeline() {
        List<TimelineYear> timelineList = timelineService.listTimeLine();
        return Result.success(timelineList);
    }
}
