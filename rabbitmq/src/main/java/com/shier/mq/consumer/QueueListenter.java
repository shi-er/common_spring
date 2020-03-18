package com.shier.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class QueueListenter implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(QueueListenter.class);

    @Override
    public void onMessage(Message msg) {
        try {
            System.out.println(new String(msg.getBody(), "UTF-8"));
            String str = new String(msg.getBody(), "UTF-8");
            logger.info("************************************" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}