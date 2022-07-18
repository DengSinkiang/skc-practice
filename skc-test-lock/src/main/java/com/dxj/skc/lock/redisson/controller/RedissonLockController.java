package com.dxj.skc.lock.redisson.controller;

import com.dxj.skc.common.util.ResultUtils;
import com.dxj.skc.lock.redisson.RedissonLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinkiang
 * @description: 不基于注解方式锁操作
 * @date 2022/4/7 10:32
 */
@RestController
@Slf4j
public class RedissonLockController {

    final RedissonLock redissonLock;

    /**
     * 模拟这个是商品库存
     */
    public static int TOTAL = 10;

    public RedissonLockController(RedissonLock redissonLock) {
        this.redissonLock = redissonLock;
    }

    @GetMapping("lock-decrease-stock")
    public ResultUtils<String> lockDecreaseStock() throws InterruptedException {
        redissonLock.lock("lock", 10L);
        if (TOTAL > 0) {
            TOTAL--;
        }
        Thread.sleep(50);
        log.info("===lock===减完库存后,当前库存===" + TOTAL);
        //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
        String lock = "lock";
        if (redissonLock.isHeldByCurrentThread(lock)) {
            redissonLock.unlock(lock);
        }
        return ResultUtils.success("减完库存后,当前库存为:" + TOTAL);
    }

    @GetMapping("tryLock-decrease-stock")
    public ResultUtils<String> tryLockDecreaseStock() throws InterruptedException {
        String lockName = "tryLock";
        long leaseTime = 5L;
        long waitTime = 200L;
        if (redissonLock.tryLock(lockName, leaseTime, waitTime)) {
            if (TOTAL > 0) {
                TOTAL--;
            }
            Thread.sleep(50);
            redissonLock.unlock(lockName);
            log.info("====tryLock=== 减完库存后,当前库存 ===" + TOTAL);
        } else {
            log.info("[ExecutorRedisson] 获取锁失败");
        }
        return ResultUtils.success("减完库存后,当前库存为:" + TOTAL);
    }


}
