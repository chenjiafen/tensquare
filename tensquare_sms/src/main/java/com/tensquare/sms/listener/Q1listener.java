package com.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author tyler.chen
 * @date 2020/4/12 0:40
 */

@Component
@RabbitListener(queues = {"q1"}) //声明从哪些队列接受消息
public class Q1listener {


    @RabbitHandler //接受到消息的处理方法
    public  void reciveMsg(String msg){
        System.out.println ("从Q1队列取出："+msg);

    }
}
