package com.dxj.skc.lock.redisson.controller;

import com.dxj.skc.common.util.ResultUtils;
import com.dxj.skc.lock.redisson.RedissonLock;
import com.dxj.skc.lock.redisson.annotation.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinkiang
 * @description: 基于注解的方式加锁
 * @date 2022/4/7 10:32
 */
@RestController
@Slf4j
public class AnnotatinLockController {

    final RedissonLock redissonLock;

    /**
     * 模拟这个是商品库存
     */
    public static int TOTAL = 10;

    public AnnotatinLockController(RedissonLock redissonLock) {
        this.redissonLock = redissonLock;
    }

    @GetMapping("annotation-lock-decrease-stock")
    @DistributedLock(value = "goods", leaseTime = 5)
    public ResultUtils<String> lockDecreaseStock() {
        if (TOTAL > 0) {
            TOTAL--;
        }
        log.info("===注解模式=== 减完库存后,当前库存===" + TOTAL);
        return ResultUtils.success("减完库存后,当前库存为:" + TOTAL);
    }
}
