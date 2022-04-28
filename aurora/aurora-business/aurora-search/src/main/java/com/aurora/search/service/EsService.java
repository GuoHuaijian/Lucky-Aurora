package com.aurora.search.service;

import java.io.IOException;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/9/8
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
public interface EsService {

    /**
     * 批量添加索引
     *
     * @param object
     */
    void addIndex(String object);

    /**
     * 更新索引
     *
     * @param object
     * @throws IOException
     */
    void updateIndex(Object object) throws IOException;

    /**
     * 删除索引
     *
     * @param articleId
     * @throws IOException
     */
    void deleteIndex(Integer articleId) throws IOException;
}
