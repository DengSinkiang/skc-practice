package com.dxj.skc.lock.redisson.enumeration;

/**
 * @author sinkiang
 * @description: 全局常量枚举 用来拼接完整的 URL
 * @date 2022/4/7 10:32
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
