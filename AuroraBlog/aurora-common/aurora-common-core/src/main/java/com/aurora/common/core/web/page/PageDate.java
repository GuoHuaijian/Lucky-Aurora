package com.aurora.common.core.web.page;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * describe: 分页返回数据
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail 564559079@qq.com
 * @Version 1.0
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
