package springboot.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RabbitAutoConfiguration
 * 1.自动配置连接工厂ConnectionFactory
 * 2.RabbitProperties：封装了RabbitMQ的配置
 * 3.RabbitTemplate:给RabbitMQ发送和接收消息
 * 4.Amqpadmin：RabbitMq系统管理功能组件
 *  AmqpAdmin:创建和删除  Queue，Exchange，Binding(绑定规则)
 *
 * 5.@EnableRabbit + @RabbitListener 监听信息队列的内容
 */
@EnableRabbit
@SpringBootApplication
public class RabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

}
