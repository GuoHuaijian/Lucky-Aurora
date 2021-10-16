package com.aurora.admin.service;

import com.aurora.admin.domain.BlogTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
public interface BlogTagService extends IService<BlogTag> {

    /**
     * 分页查询
     *
     * @param tag
     * @return
     */
    List<BlogTag> list(BlogTag tag);


    /**
     * 删除标签
     *
     * @param tagIds
     * @return
     */
    boolean deleteTag(List<Integer> tagIds);

}
