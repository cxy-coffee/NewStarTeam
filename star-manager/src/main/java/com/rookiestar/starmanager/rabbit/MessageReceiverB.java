package com.rookiestar.starmanager.rabbit;

import com.rookiestar.starmanager.config.RabbitConfig;
import com.rookiestar.starmanager.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * RabbitMQ MessageReceiverB
 *
 * @author 曹向阳
 * @date 2021/7/17
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_B)
public class MessageReceiverB {
    @Autowired
    private EmailService emailService;

    @RabbitHandler
    public void process(Map<String,String> contentMap) {
        String to = contentMap.get("to");
        String subject = contentMap.get("subject");
        String content = contentMap.get("content");
        emailService.sendSimpleEmail(to,subject,content);
    }
}
