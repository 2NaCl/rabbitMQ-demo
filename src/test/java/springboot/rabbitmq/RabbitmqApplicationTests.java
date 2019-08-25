package springboot.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.rabbitmq.bean.Book;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    public void createExchange(){
        /**
         * 创建DirectExchange
         */
//        amqpAdmin.declareExchange(new DirectExchange("amqp.exchange"));
//        System.out.println("创建完成");

        /**
         * 创建Queue
         */
//        amqpAdmin.declareQueue(new Queue("amqp.queue", true));

        /**
         * 创建绑定关系
         */
        amqpAdmin.declareBinding(new Binding("amqp.queue",Binding.DestinationType.QUEUE,"amqp.exchange","amqp.zz",null));
    }

    /**
     * 1.单播(点对点)
     */
    @Test
    public void contextLoads() {
        //message要自己定义消息体内容和消息头
        //rabbitTemplate.send(exchange,routeKey,message)

        //object 默认当成消息体，只需要传入要发送的对象，自动序列化给rabbitMQ
        //rabbitTemplate.convertAndSend(exchange,routeKey,object)
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "第一个消息");
        map.put("data", Arrays.asList("HelloWorld", 123, true));

        rabbitTemplate.convertAndSend("exchange.direct","张章2",new Book("阿章前传","阿章"));
    }

    @Test
    public void receive(){
        Object receiveAndConvert = rabbitTemplate.receiveAndConvert("张章2");
        System.out.println(receiveAndConvert.getClass());
        System.out.println(receiveAndConvert);
    }

    @Test
    public void sentAllMsg() {
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("阿章后传","阿章"));
    }
}
