package com.aurora.admin.mapper;

import com.aurora.admin.domain.Article;
import com.aurora.admin.domain.vo.ArticleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail 564559079@qq.com
 * @Version 1.0
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 查询列表
     *
     * @param title
     * @return
     */
    List<ArticleVO> listArticleVo(@Param("title") String title);
}
