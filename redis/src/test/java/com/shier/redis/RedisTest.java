package com.shier.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Resource
    private RedisUtil redisUtil;

    @Test
    public void redisTest() {
        redisUtil.set("123", "hello world shier 1234");
        System.out.println("进入了方法");
        String string = redisUtil.get("123").toString();
        System.out.println("string===========:" + string);
    }

    /*public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        RedisUtil redisUtil = (RedisUtil) context.getBean("redisUtil");
        redisUtil.set("123", "hello world shier spring");
        System.out.println("进入了方法");
        String string = redisUtil.get("123").toString();
        System.out.println("string===========:" + string);

    }*/

}
