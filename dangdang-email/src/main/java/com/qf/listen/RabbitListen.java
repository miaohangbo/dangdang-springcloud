package com.qf.listen;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by 54110 on 2020/6/9.
 */
@Component
public class RabbitListen {

    @RabbitListener(queues = "qf-1911-2")
    public void message(String str){
        System.out.println("第二台服务器========"+str);

    }
}
