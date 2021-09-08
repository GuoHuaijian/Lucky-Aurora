package com.aurora.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aurora.search.domain.Article;
import com.aurora.search.service.EsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * describe:
 *
 * @Author Guo
 * @Date 2021/9/8 14:27
 * @Version 1.0
 */
@Slf4j
@Service
public class EsServiceImpl implements EsService {

    @Resource
    private ElasticsearchRestTemplate restTemplate;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 批量添加索引
     *
     * @param object
     */
    @Override
    public void addIndex(Object object) {
        String str = JSONObject.toJSONString(object);
        Article article = JSON.parseObject(str, Article.class);
        restTemplate.save(article);
    }

    /**
     * 更新索引
     *
     * @param object
     */
    @Override
    public void updateIndex(Object object) throws IOException {
        Map map = objectToMap(object);
        UpdateByQueryRequest request = new UpdateByQueryRequest("aurora_blog");
        // 设置查询条件，第一个参数是字段名，第二个参数是字段的值
        request.setQuery(new TermQueryBuilder("articleId", map.get("articleId")));
        // 批次大小
        request.setBatchSize(1000);
        // 设置更新字段和字段值
        request.setScript(
                new Script(mapToScriptStr(map)));
        // 并行
        request.setSlices(2);
        // 使用滚动参数来控制“搜索上下文”存活的时间
        request.setScroll(TimeValue.timeValueMinutes(10));
        // 超时
        request.setTimeout(TimeValue.timeValueMinutes(2));
        // 刷新索引
        request.setRefresh(true);
        BulkByScrollResponse response = restHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);
        log.info("索引更新：" + response.getUpdated());
    }

    /**
     * 删除索引
     *
     * @param articleId
     * @throws IOException
     */
    @Override
    public void deleteIndex(String articleId) throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest("aurora_blog");
        request.setQuery(new TermQueryBuilder("articleId", articleId));
        restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
    }

    public static String mapToScriptStr(Map<String, String> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String str = String.format("ctx._source['%s']='%s';", entry.getKey(), entry.getValue());
            builder.append(str);
        }
        String scriptStr = builder.toString().substring(0, builder.length() - 1);
        return scriptStr;
    }

    public static Map objectToMap(Object object) {
        String string = JSONObject.toJSONString(object);
        JSONObject jsonObject = JSON.parseObject(string);
        Map map = JSONObject.toJavaObject(jsonObject, Map.class);
        return map;
    }
}
