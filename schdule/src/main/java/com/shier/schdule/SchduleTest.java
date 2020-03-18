package com.shier.schdule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SchduleTest {
    private static final Logger logger = LoggerFactory.getLogger(SchduleTest.class);

    private ExecutorService testExecutor = Executors.newFixedThreadPool(20); // 线程池

    public void startTset() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            testExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        logger.info("这是测试=========================", finalI);
                    } catch (Exception e) {
                        logger.info("====>>>====testException: {}", e.getMessage());
                    }
                }
            });
        }
    }
}
