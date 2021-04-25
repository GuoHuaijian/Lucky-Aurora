package com.aurora.admin.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aurora.admin.mapper.BookMapper;
import com.aurora.admin.domain.Book;
import com.aurora.admin.service.BookService;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

}
