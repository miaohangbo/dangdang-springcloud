# dangdang-springcloud
当前项目为一个依赖于springcloud搭建的平台项目。主要使用的技术点有：
1.springcloud
    1.Eureka 注册中心
    2.Feign,ribbon 远程调用
    3.Hystix的熔断机制
    4.Zuul网关
    5.Spring-cloud-config配置中心
    6.Springboot-Admin 管理中心 
    7.Zipkin 链路跟踪
2.Redis 
3.Rabbitmq 消息队列
4.Elaticsearch 搜索引擎
5.Tx-LCN 分布式事务管理


当前项目是一个框架，其中实现的功能较少，目前只有登录的验证，使用了ZuulFilter简单的登录验证，以及对各个技术点的验证。
