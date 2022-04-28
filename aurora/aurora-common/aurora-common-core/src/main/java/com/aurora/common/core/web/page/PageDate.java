package com.aurora.common.core.web.page;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * describe: 分页返回数据
 *
 * @author Guo Huaijian
 * @date 2021/1/3
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@Data
public class PageDate<T> {

    /**
     * 查询数据列表
     */
    private List<T> data = Collections.emptyList();

    /**
     * 总数
     */
    private long total = 0;
}
