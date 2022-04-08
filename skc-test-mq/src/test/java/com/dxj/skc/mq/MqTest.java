package com.dxj.skc.mq;

import com.dxj.skc.MqApplication;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author sinkiang
 * @date 2022/4/8 16:27
 */
@SpringBootTest(classes = MqApplication.class)
public class MqTest {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange","a.b", msg);
        // 等待10秒后再结束
        Thread.sleep(10000);
    }
}
