package com.aurora.common.core.web.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * describe: Entity基类
 *
 * @author Guo Huaijian
 * @date 2021/10/16
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
public class BaseEntity {

    /**
     * 搜索值
     */
    @TableField(exist = false)
    private String searchValue;

    /**
     * 开始时间
     */
    @TableField(exist = false)
    private Date beginTime;

    /**
     * 结束时间
     */
    @TableField(exist = false)
    private Date endTime;

    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params;
}
