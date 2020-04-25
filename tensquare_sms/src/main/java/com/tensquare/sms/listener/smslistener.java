package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.util.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tyler.chen
 * @date 2020/4/12 0:40
 */

@Component
@RabbitListener(queues = {"sms"}) //声明从哪些队列接受消息
public class smslistener {

    @Value("${aliyun.sms.template}")//模板编号
    private String templateCode;

    @Value("${aliyun.sms.sign}")
    private String sign;//签名

    @RabbitHandler //接受到消息的处理方法
    public void reciveMsg(Map<String, String> map) {
        System.out.println ( "手机号：" + map.get ( "phoneNumber" ) );
        System.out.println ( "验证码：" + map.get ( "checkCode" ) );
//        try {
//
//            //调用工具发送短信
//            SmsUtil.sendSms ( map.get ( "phoneNumber" ), templateCode, sign, "{\"number\":\"" + map.get ( "checkCode" ) + "\"}" );
//        } catch (ClientException e) {
//            e.printStackTrace ();
//        }


    }
}
