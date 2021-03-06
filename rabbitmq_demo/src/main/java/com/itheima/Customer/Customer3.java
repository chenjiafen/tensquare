package com.itheima.Customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tyler.chen
 * @date 2020/4/19 21:05
 * 编写消息消费者类
 */

@Component
@RabbitListener(queues = {"itheima"})//从哪个队列接受消息
public class Customer3 {

    //String message和你发送的消息类型保持一致
    @RabbitHandler
    public void showMessage(String message){
        System.out.println("itheima接收到消息："+message);
    }

}
