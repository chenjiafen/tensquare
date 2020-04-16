package com.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tyler.chen
 * @date 2020/4/12 0:40
 */

@Component
@RabbitListener(queues = {"sms"}) //声明从哪些队列接受消息
public class smslistener {


    @RabbitHandler //接受到消息的处理方法
    public  void reciveMsg(Map<String,String> map){
        System.out.println ("手机号："+map.get ( "phoneNumber" ));
        System.out.println ("验证码："+map.get ( "checkCode" ));

    }
}
