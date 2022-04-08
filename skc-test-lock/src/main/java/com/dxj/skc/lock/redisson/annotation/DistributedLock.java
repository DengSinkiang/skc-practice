package com.dxj.skc.lock.redisson.annotation;

import java.lang.annotation.*;

/**
 * @author sinkiang
 * @description: 基于注解的分布式式锁
 * @date 2022/4/7 10:32
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributedLock {

    /**
     * 锁的名称
     */
    String value() default "redisson";

    /**
     * 锁的有效时间
     */
    int leaseTime() default 10;
}


