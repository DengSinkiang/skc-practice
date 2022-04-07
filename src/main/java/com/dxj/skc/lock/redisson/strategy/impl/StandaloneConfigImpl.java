package com.dxj.skc.lock.redisson.strategy.impl;

import com.dxj.skc.lock.redisson.enumeration.RedisConstantEnum;
import com.dxj.skc.lock.redisson.entity.RedissonProperties;
import com.dxj.skc.lock.redisson.strategy.RedissonConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;

/**
 * @description: 单机部署 Redisson 配置
 * @author: sinkiang
 * @date: 2020/7/27 10:35
 */
@Slf4j
public class StandaloneConfigImpl implements RedissonConfigService {

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String redisAddr = RedisConstantEnum.REDIS_CONNECTION_PREFIX.getConstantValue() + address;
            config.useSingleServer().setAddress(redisAddr);
            config.useSingleServer().setDatabase(database);
            // 密码可以为空
            if (StringUtils.isNotBlank(password)) {
                config.useSingleServer().setPassword(password);
            }
            log.info("初始化[单机部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("单机部署 Redisson init error", e);
        }
        return config;
    }
}
