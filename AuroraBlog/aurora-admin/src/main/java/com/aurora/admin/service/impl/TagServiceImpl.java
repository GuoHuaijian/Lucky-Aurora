package com.aurora.admin.service.impl;

import com.aurora.admin.domain.Tag;
import com.aurora.admin.mapper.TagMapper;
import com.aurora.admin.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    /**
     * 根据关联Id获取列表
     *
     * @param linkId
     * @param type
     * @return
     */
    @Override
    public List<Tag> listByLinkId(Integer linkId, Integer type) {
        return this.baseMapper.listByLinkId(linkId, type);
    }
}
