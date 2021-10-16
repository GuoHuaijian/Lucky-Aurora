package com.aurora.admin.service.impl;

import com.aurora.admin.domain.BlogArticleTag;
import com.aurora.admin.domain.BlogTag;
import com.aurora.admin.mapper.BlogTagMapper;
import com.aurora.admin.service.BlogArticleTagService;
import com.aurora.admin.service.BlogTagService;
import com.aurora.common.core.exception.ServiceException;
import com.aurora.common.core.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class BlogTagServiceImpl extends ServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {

    @Autowired
    private BlogArticleTagService articleTagService;

    /**
     * 分页条件查询
     *
     * @param tag
     * @return
     */
    @Override
    public List<BlogTag> list(BlogTag tag) {
        QueryWrapper<BlogTag> wrapper = new QueryWrapper<>(tag);
        if (StringUtil.isNotNull(tag.getBeginTime())) {
            wrapper.lambda().le(BlogTag::getCreateTime, tag.getBeginTime());
        }
        if (StringUtil.isNotNull(tag.getEndTime())) {
            wrapper.lambda().ge(BlogTag::getCreateTime, tag.getEndTime());
        }
        return list(wrapper);
    }

    /**
     * 删除标签
     *
     * @param tagIds
     * @return
     */
    @Override
    public boolean deleteTag(List<Integer> tagIds) {
        int count = articleTagService.count(new LambdaQueryWrapper<BlogArticleTag>().in(BlogArticleTag::getTagId,
                tagIds));
        if (count >= 1) {
            throw new ServiceException("删除标签失败,该标签下有关联博文");
        }
        return removeByIds(tagIds);
    }
}
