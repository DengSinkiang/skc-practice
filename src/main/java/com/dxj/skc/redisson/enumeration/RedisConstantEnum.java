package com.dxj.skc.redisson.enumeration;

/**
 * @Description: 全局常量枚举 用来拼接完整的 URL
 * @Author: Sinkiang
 * @Date: 2020/7/27 10:35
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public enum RedisConstantEnum {

    /**
     * xxx
     */
    REDIS_CONNECTION_PREFIX("redis://", "Redis地址配置前缀");

    private final String constantValue;
    private final String constantDesc;

    RedisConstantEnum(String constantValue, String constantDesc) {
        this.constantValue = constantValue;
        this.constantDesc = constantDesc;
    }

    public String getConstantValue() {
        return constantValue;
    }

    public String getConstantDesc() {
        return constantDesc;
    }
}
