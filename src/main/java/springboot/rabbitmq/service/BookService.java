package springboot.rabbitmq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import springboot.rabbitmq.bean.Book;

@Service
public class BookService {
    @RabbitListener(queues = "张章2")
    public void receive(Book book) {
        System.out.println("收到消息"+book.getBookName()+"And"+book.getAuthor());
    }


    @RabbitListener(queues = "张章1")
    public void receive01(Message message) {
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }
}
