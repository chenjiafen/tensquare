package com.itheima;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tyler.chen
 * @date 2020/4/19 20:59
 */

@RunWith (SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MqTest {

    @Autowired
    private RabbitTemplate template;


    /**
     * 发送mq，直连方式
     */
    @Test
    public void testSend(){
        template.convertAndSend ( "itcast","我要红包" );
    }


    /**
     * 发送mq，分列模式
     */
    @Test
    public void testSendFanout(){
        /**
         *参数1：指定发送路由，
         */
        template.convertAndSend ( "chuanzhi","", "分列模式走起");

    }

    /**
     * 发送mq，主题模式
     */
    @Test
    public void testSendTopic01(){
        /**
         *参数1：指定发送路由，
         */
        template.convertAndSend ( "topic_itcast","haha.fasdfa", "主题模式走起");

    }
    @Test
    public void testSendTopic02(){
        /**
         *参数1：指定发送路由，
         */
        template.convertAndSend ( "topic_itcast","fsadfa.hehe", "主题模式走起");

    }

    @Test
    public void testSendTopic03(){
        /**
         *参数1：指定发送路由，
         */
        template.convertAndSend ( "topic_itcast","haha.hehe", "主题模式走起");

    }
    @Test
    public void testSendTopic04(){
        /**
         *参数1：指定发送路由，
         */
        template.convertAndSend ( "topic_itcast","haha.abc.hehe", "主题模式走起");

    }

}
