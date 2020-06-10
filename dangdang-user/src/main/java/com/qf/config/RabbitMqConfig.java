package com.qf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 54110 on 2020/6/9.
 */
@Configuration
public class RabbitMqConfig {

    @Bean(name = "queue1")
    public Queue queue(){
        return new Queue("qf-1911-1");
    }

    @Bean(name = "queue2")
    public Queue queue2(){
        return new Queue("qf-1911-2");
    }

    @Bean(name = "email")
    public Queue email(){
        return new Queue("email");
    }

    //direct =>工作队列，发布订阅，routing模式
   /* @Bean
    public Exchange exchange(){
        return new DirectExchange("1911");
    }*/
    @Bean(name = "topicExchange")
    public TopicExchange exchange(){
        return new TopicExchange("topic-1911");
    }


    @Bean
    Binding binginExchangeToque(@Qualifier("topicExchange") TopicExchange exchange, @Qualifier("queue1")Queue queue){

        return BindingBuilder.bind(queue).to(exchange).with("1911.#.name"); //*代表一个词，#代表 0个或多个
    }

    @Bean
    Binding binginExchangeToque2(@Qualifier("topicExchange")TopicExchange exchange, @Qualifier("queue2")Queue queue){

        return BindingBuilder.bind(queue).to(exchange).with("qita.#");
    }

    @Bean
    Binding binginExchangeToque3(@Qualifier("topicExchange")TopicExchange exchange, @Qualifier("email")Queue queue){

        return BindingBuilder.bind(queue).to(exchange).with("email.#");
    }
}
