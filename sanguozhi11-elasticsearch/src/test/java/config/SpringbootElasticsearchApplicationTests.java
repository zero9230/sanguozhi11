package config;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
public class SpringbootElasticsearchApplicationTests {
    @Autowired
    public RestHighLevelClient restHighLevelClient;

//    @Test
//    public void testCreateIndex() throws IOException {
//        CreateIndexRequest request = new CreateIndexRequest("liuyou_index");
//        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
//        System.out.println(response.isAcknowledged());// 查看是否创建成功
//        System.out.println(response);// 查看返回对象
//        restHighLevelClient.close();
//    }

    @Test
    public void mytest(){
        System.out.println("hello");
    }

}
