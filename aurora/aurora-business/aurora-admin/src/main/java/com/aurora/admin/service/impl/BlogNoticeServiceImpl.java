package com.aurora.admin.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aurora.admin.mapper.BlogNoticeMapper;
import com.aurora.admin.domain.BlogNotice;
import com.aurora.admin.service.BlogNoticeService;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/16
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@Service
public class BlogNoticeServiceImpl extends ServiceImpl<BlogNoticeMapper, BlogNotice> implements BlogNoticeService {

}
