package com.qf;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by 54110 on 2020/6/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEs {

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    //创建索引库
    @Test
    public void testCreatIndex() throws IOException {

        //获取创建索引库的客户端
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("qf-1911");
        //设置副本数，设置分片数
        createIndexRequest.settings(Settings.builder().put("number_of_shards",1)
                                                        .put("number_of_replicas",0));

        //设置当前索引库的映射
        createIndexRequest.mapping("doc","{\n" +
                "\t\"properties\":{\n" +
                "\t\t\"name\":{\n" +
                "\t\t\t\"type\":\"text\",\n" +
                "\t\t\t\"analyzer\":\"ik_max_word\",\n" +
                "\t\t\t\"search_analyzer\":\"ik_smart\"\n" +
                "\t\t},\n" +
                "\t\t\"address\":{\n" +
                "\t\t\t\"type\":\"text\",\n" +
                "\t\t\t\"analyzer\":\"ik_max_word\",\n" +
                "\t\t\t\"search_analyzer\":\"ik_smart\"\n" +
                "\t\t},\n" +
                "\t\t\"idcard\":{\n" +
                "\t\t\t\"type\":\"keyword\"\n" +
                "\t\t},\n" +
                "\t\t\"pic\":{\n" +
                "\t\t\t\"type\":\"text\",\n" +
                "\t\t\t\"index\":false\n" +
                "\t\t},\n" +
                "\t\t\"age\":{\n" +
                "\t\t\t\"type\":\"integer\"\n" +
                "\t\t},\n" +
                "\t\t\"createdate\":{\n" +
                "\t\t\t\"type\":\"date\",\n" +
                "\t\t\t\"format\":\"yyyy-MM-dd HH:mm:ss || yyyy-MM-dd\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}", XContentType.JSON);

        //创建索引映射客户端
        IndicesClient indices =  restHighLevelClient.indices();
        //创建索引库以及映射
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest);
        //获取返回值
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("当前 创建的返回结果是=================》"+acknowledged);
    }

    @Test
    public void addDoc() throws IOException {

        //我们首先要准备数据
        Map map  = new HashMap<>();

        map.put("name","千锋");
        map.put("age",10);
        map.put("address","陕西西安南窑头国际中心");
        map.put("idcard","11111111111");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        map.put("createdate",simpleDateFormat.format(new Date()));
        //创建索引请求对象
        IndexRequest doc = new IndexRequest("qf-1911", "doc");
        //设置当前数据的id
        doc.id("123321");
        //设置添加的数据
        doc.source(map);
        //使用highlevel区请求添加
        IndexResponse index = restHighLevelClient.index(doc);
        //解析返回
        //index.getId()
        DocWriteResponse.Result result = index.getResult();
        System.out.println("添加数据后的返回为==============>"+result);
    }

    //查询文档
    @Test
    public void selectDoc() throws IOException {
        GetRequest getRequest = new GetRequest("qf-1911", "doc", "123321");

        GetResponse documentFields = restHighLevelClient.get(getRequest);

        boolean exists = documentFields.isExists();

        if (exists){
            Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            System.out.println(sourceAsMap);
        }
    }
    //修改文档
    @Test
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("qf-1911", "doc", "Ox2mknIBrUmy77WycH42");

        Map map = new HashMap<>();

        map.put("name","张三");
        map.put("address","南窑头国际城1号楼601");
        map.put("pic","http://localhost:8779/pic");

         updateRequest.doc(map);

        UpdateResponse update = restHighLevelClient.update(updateRequest);

        System.out.println("返回的为：==============>"+update.getResult());
    }

    //删除文档
    @Test
    public void delDoc() throws IOException {

        DeleteRequest deleteRequest = new DeleteRequest("qf-1911", "doc", "Ox2mknIBrUmy77WycH42");

        DeleteResponse delete = restHighLevelClient.delete(deleteRequest);

        System.out.println("删除的结果是==================>"+delete.getResult());
    }
}
