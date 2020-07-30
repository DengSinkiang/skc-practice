package com.dxj.skc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/7/28 16:52
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkcTest {
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Test
    public void testLoad() {
        System.out.println("hello skc");
    }
    @Test
    public void testSend() throws InterruptedException {
        String msg = "hello, Spring boot amqp";
        this.amqpTemplate.convertAndSend("spring.test.exchange","a.b", msg);
        // 等待10秒后再结束
        Thread.sleep(10000);
    }
}
