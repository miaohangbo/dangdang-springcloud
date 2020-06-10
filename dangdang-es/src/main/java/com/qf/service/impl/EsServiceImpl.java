package com.qf.service.impl;

import com.qf.service.EsService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 54110 on 2020/6/8.
 */
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    RestHighLevelClient restClient;

    @Override
    public String search(String value) throws IOException {
        //创建搜索客户端
        SearchRequest searchRequest = new SearchRequest("qf-1911");
        //设置type
        searchRequest.types("doc");
        //构建搜索源对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置搜索的类型 ==》matchaAllquery ==》查询全部
        //searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //termquery =>不再将对关键字分词，直接来进行查询
        //searchSourceBuilder.query(QueryBuilders.termQuery("name","辣少"));
        //termsquery ==>根据id精准查询
        //searchSourceBuilder.query(QueryBuilders.termsQuery("_id","123321"));
        //matchQuery ==>先将关键字分词 中华 与辣少
        //searchSourceBuilder.query(QueryBuilders.matchQuery("name","千锋辣少").operator(Operator.AND));
       //multiMatchQuery ==>设置关键字匹配多个fileid
        //searchSourceBuilder.query(QueryBuilders.multiMatchQuery("辣少陕西","name","address").field("address",10));
        //设置boolquery ==>将多个搜索条件组个起来
        //TermsQueryBuilder termQuery = QueryBuilders.termsQuery("_id", "123321");
        MultiMatchQueryBuilder multiQuery = QueryBuilders.multiMatchQuery(value, "name", "address").field("address", 10);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
       // boolQueryBuilder.must(termQuery);
        boolQueryBuilder.must(multiQuery);

        searchSourceBuilder.query(boolQueryBuilder);
        //设置高亮展示
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font style='color:red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        searchSourceBuilder.highlighter(highlightBuilder);
        //设置分页
        int page = 1;
        int size = 10;

        int from = (page -1)*size;
        searchSourceBuilder.from(from);
        searchSourceBuilder.size(size);
        //将搜索源对象设置到搜索客户端,拿到
        searchRequest.source(searchSourceBuilder);
        //使用es客户端进行搜索,拿到搜索的返回值
        SearchResponse search = restClient.search(searchRequest);
        //解析返回值
        SearchHits hits = search.getHits();
        SearchHit[] hits1 = hits.getHits();
        List list =new ArrayList<>();
        for (SearchHit hit:hits1){
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            System.out.println("当前搜索出的数据ID为======>"+hit.getId()+"=========="+hit.docId());

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (highlightFields!=null){
                HighlightField name = highlightFields.get("name");
                if (name!=null){
                    Text[] fragments = name.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text txt:fragments){
                        stringBuffer.append(txt);
                    }
                    sourceAsMap.put("name",stringBuffer.toString());
                }
            }
            list.add(sourceAsMap);
        }
        return list.toString();
    }
}
