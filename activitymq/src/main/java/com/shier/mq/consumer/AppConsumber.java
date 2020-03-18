package com.shier.mq.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppConsumber {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/consumer.xml");
    }
}