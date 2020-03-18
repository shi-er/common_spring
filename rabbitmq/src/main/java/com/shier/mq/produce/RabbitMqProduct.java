package com.shier.mq.produce;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class RabbitMqProduct {
    @Resource
    private AmqpTemplate amqpTemplate;

    public void send(Map<String, Object> bodyMap) {
        amqpTemplate.convertAndSend("hello", bodyMap.toString());
    }
}