package com.shier.kafka.common;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

public class TestProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("metadata.broker.list", "47.98.229.152:9092");
        props.setProperty("serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "1");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        try {
            int i = 1;
            while (i < 1000) {
                producer.send(new KeyedMessage<String, String>("mykafka", "test-kafka" + i));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.close();
    }
}

