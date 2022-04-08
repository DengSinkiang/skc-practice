package com.dxj.skc.lock.redisson.strategy;

import com.dxj.skc.lock.redisson.entity.RedissonProperties;
import org.redisson.config.Config;

/**
 * @author sinkiang
 * @description: Redisson 配置构建接口
 * @date 2022/4/7 10:32
 */
public interface RedissonConfigService {

    /**
     * 根据不同的 Redis 配置策略创建对应的 Config
     *
     * @param redissonProperties
     * @return
     */
    Config createRedissonConfig(RedissonProperties redissonProperties);
}
