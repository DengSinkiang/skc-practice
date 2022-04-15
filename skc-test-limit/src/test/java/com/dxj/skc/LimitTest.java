package com.dxj.skc;

import com.dxj.skc.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author sinkiang
 * @date 2022/4/15 11:34
 */
@SpringBootTest(classes = LimitApplication.class)
public class LimitTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedisTemplate(){
        redisService.set("test", "123456");
        System.out.println(redisService.get("test"));
    }
}
