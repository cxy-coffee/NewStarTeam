package com.rookiestar.starmanager.rabbit;

import com.rookiestar.starmanager.config.RabbitConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * RabbitMQ MessageProducer
 *
 * @author 曹向阳
 * @date 2021/7/17
 */
@Component
public class MessageProducer implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendEmailCode(Map<String,String> contentMap) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTING_KEY_A, contentMap, correlationId);
    }

    public void sendNotice(Map<String,String> contentMap) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTING_KEY_B, contentMap, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
    }
}
