package com.tensquare;

import com.tensquare.sms.SmsApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tyler.chen
 * @date 2020/4/12 0:07
 */

@RunWith (SpringRunner.class)
@SpringBootTest(classes = SmsApplication.class)
public class RabbitTest {
    @Autowired
    private RabbitTemplate template;


    //直连方式发送消息
    @Test
    public void fun1(){
        template.convertAndSend ( "q1","Hello word" );

    }

    //分列方式发送消息
    @Test
    public void fun2(){
        //参数1：指定消息发送的路由 ，参数2：
        template.convertAndSend("q1&q2","","hello everyBody!");

    }


    //主题模式发送消息,只想给q1发
    @Test
    public void fun3(){
        //参数1：指定消息发送的路由 ，参数2：
        template.convertAndSend("topic_ex","ooo.hehe","hello everyBody!");

    }

    //主题模式发送消息,只想给q2发
    @Test
    public void fun4(){
        //参数1：指定消息发送的路由 ，参数2：
        template.convertAndSend("topic_ex","haha.ttt","hello everyBody!");

    }


}
