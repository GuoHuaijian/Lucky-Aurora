package com.aurora.admin.controller;

import com.aurora.admin.domain.BlogArticle;
import com.aurora.admin.service.BlogArticleService;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.core.web.page.PageDate;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.common.rocketmq.constant.ConsumerGroupConstant;
import com.aurora.common.rocketmq.constant.TagConstant;
import com.aurora.common.rocketmq.constant.TopicConstant;
import com.aurora.common.rocketmq.producer.RocketMqProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/1/3
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin/article")
public class ArticleController extends AbstractController {

    @Resource
    private BlogArticleService articleService;

    @Resource
    private RocketMqProducer sendService;

    /**
     * 获取文章列表
     *
     * @param title
     * @return
     */
    @Log(value = "文章列表", LogType = LogType.OTHER)
    @GetMapping("/list")
    @PreAuthorize("hasRole('admin')")
    public Result listArticle(String title) {
        startPage();
        PageDate pageDate = getPageDate(articleService.getArticleList(title));
        return Result.success(pageDate);
    }

    @GetMapping("/info/{articleId}")
    @PreAuthorize("hasPermission('article:list')")
    public Result info(@PathVariable Integer articleId) {

        return Result.success();
    }

    @Log(value = "测试", LogType = LogType.OTHER)
    @PostMapping("/test/{id}")
    @PreAuthorize("hasRole('admin')")
    public String test(@PathVariable Integer id) {
        int i = 10 / 0;
        String s = String.valueOf(id);
        return s;
    }

    @Log(value = "添加文章", LogType = LogType.OTHER)
    @PostMapping("/test")
    @PreAuthorize("hasRole('admin')")
    public String add() throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException, MQClientException {
        BlogArticle article = new BlogArticle();
        article.setArticleId(1);
        article.setTitle("文章");
        article.setDescription("文章");
        article.setAuthor("文章");
        article.setContent("添加文章");
        article.setContentFormat("添加文章");
        article.setReadNum(0);
        article.setCommentNum(0);
        article.setLikeNum(0);
        article.setCoverType(0);
        article.setCover("添加文章");
        article.setIsRecommend(false);
        article.setCategoryId("添加文章");
        article.setPublish((byte) 0);
        article.setIsTop(false);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        sendService.send(article, TopicConstant.ARTICLE_ADD_TOPIC_NAME, ConsumerGroupConstant.ARTICLE_GROUP_ID,
                TagConstant.ARTICLE_ADD);
        return "成功";
    }

}
