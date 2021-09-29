package com.aurora.search.service;

import java.io.IOException;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/9/8
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
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
    void deleteIndex(String articleId) throws IOException;
}
