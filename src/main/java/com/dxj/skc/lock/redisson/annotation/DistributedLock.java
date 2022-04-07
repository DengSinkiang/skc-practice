package com.dxj.skc.lock.redisson.annotation;

import java.lang.annotation.*;

/**
 * @description: 基于注解的分布式式锁
 * @author sinkiang
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


