package com.aurora.admin.service.impl;

import com.aurora.admin.domain.Book;
import com.aurora.admin.mapper.BookMapper;
import com.aurora.admin.service.BookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
