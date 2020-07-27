package com.dxj.skc.redisson.strategy;

import com.dxj.skc.redisson.entity.RedissonProperties;
import org.redisson.config.Config;

/**
 * @Description: Redisson 配置构建接口
 * @Author: Sinkiang
 * @Date: 2020/7/27 14:45
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public interface RedissonConfigService {

    /**
     * 根据不同的 Redis 配置策略创建对应的 Config
     * @param redissonProperties
     * @return
     */
    Config createRedissonConfig(RedissonProperties redissonProperties);
}
