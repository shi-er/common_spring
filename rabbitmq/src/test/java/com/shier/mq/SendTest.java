package com.shier.mq;

import com.shier.mq.produce.RabbitMqProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendTest {
    @Resource
    private RabbitMqProduct rabbitMqProduct;

    @Test
    public void send() {
        List<String> submobileList = new ArrayList<String>();
        submobileList.add("1");
        submobileList.add("2");
        submobileList.add("3");
        submobileList.add("4");
        submobileList.add("5");
        submobileList.add("6");
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("batchNo", "welecme to fula");
        bodyMap.put("item", submobileList);
        rabbitMqProduct.send(bodyMap);
    }
}
