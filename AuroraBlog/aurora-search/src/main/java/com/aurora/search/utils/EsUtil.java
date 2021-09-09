package com.aurora.search.utils;

import com.aurora.search.config.RestClientConfig;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.List;

/**
 * describe: ES工具类
 *
 * @Author Guo
 * @Date 2021/9/9 16:38
 * @Version 1.0
 */
public class EsUtil {

    public static final String INDEX = "aurora_blog";

    private RestClientBuilder restClientBuilder;

    private RestHighLevelClient restHighLevelClient;

    public EsUtil() {
        RestClientConfig esConfig = new RestClientConfig();
        restClientBuilder = esConfig.restClientBuilder();
        restHighLevelClient = new RestHighLevelClient(restClientBuilder);
    }

    /**
     * 通过id获取数据 索引为aurora_blog
     *
     * @param id
     * @return
     * @throws IOException
     */
    public GetResponse get(String id) throws IOException {
        GetRequest request = new GetRequest(INDEX, id);
        return restHighLevelClient.get(request, RequestOptions.DEFAULT);
    }

    /**
     * 删除document
     *
     * @param index
     * @param id
     * @return
     * @throws IOException
     */
    public DeleteResponse delete(String index, String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(index);
        deleteRequest.id(id);
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * 创建索引，新版ES插入数据时自动创建
     *
     * @param index
     * @return
     * @throws IOException
     */
    public CreateIndexResponse createIndex(String index) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
        return restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 删除索引
     *
     * @param index
     * @return
     * @throws IOException
     */
    public AcknowledgedResponse deleteIndex(String index) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        return restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 插入json数据 索引为aurora_blog
     *
     * @param content
     * @return
     * @throws IOException
     */
    public IndexResponse insertJson(String content) throws IOException {
        IndexRequest indexRequest = new IndexRequest(INDEX);
        indexRequest.source(content, XContentType.JSON);
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 批量插入json数据 索引为aurora_blog
     *
     * @param contentList
     * @return
     * @throws IOException
     */
    public BulkResponse insertBatchJson(List<String> contentList) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        IndexRequest indexRequest;
        for (String item : contentList) {
            indexRequest = new IndexRequest(INDEX);
            indexRequest.source(item, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 查询数据 索引为aurora_blog
     *
     * @param queryBuilder
     * @return
     * @throws IOException
     */
    public SearchResponse search(QueryBuilder queryBuilder) throws IOException {
        SearchRequest request = new SearchRequest();
        request.indices(INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        request.source(searchSourceBuilder);
        return restHighLevelClient.search(request, RequestOptions.DEFAULT);

    }

    /**
     * 判断索引是否存在
     *
     * @param index
     * @return
     */
    public boolean isIndexExist(StreamInput index) throws IOException {
        GetIndexRequest request = new GetIndexRequest(index);
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }
}
