package com.qf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 54110 on 2020/6/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQSendTest {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
            //for (int i = 0;i<=10;i++){
                //三个参数，第一个参数：交换机的名称，如果未定义交换机可以为空。
                //第二个参数：我们的定义的规则，如果是工作队列模式，放置的是队列的名称
                //第三个参数：发送的消息的内容
                Map map = new HashMap<>();
                map.put("username","王昊");
                map.put("email","1416499603@qq.com");
                map.put("message","你好么？王昊，还记得我么？那年一起giao的日子");
                rabbitTemplate.convertAndSend("topic-1911","email",map);
            //}

    }
}
