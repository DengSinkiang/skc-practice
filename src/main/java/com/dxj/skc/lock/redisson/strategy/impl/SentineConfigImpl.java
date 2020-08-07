package com.dxj.skc.lock.redisson.strategy.impl;


import com.dxj.skc.lock.redisson.enumeration.RedisConstantEnum;
import com.dxj.skc.lock.redisson.entity.RedissonProperties;
import com.dxj.skc.lock.redisson.strategy.RedissonConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;

/**
 * @Description: 哨兵集群部署 Redis 连接配置
 * @Author: Sinkiang
 * @Date: 2020/7/27 10:35
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Slf4j
public class SentineConfigImpl implements RedissonConfigService {



    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String[] addrTokens = address.split(",");
            String sentinelAliasName = addrTokens[0];
            // 设置 redis 配置文件 sentinel.conf 配置的 sentinel 别名
            config.useSentinelServers().setMasterName(sentinelAliasName);
            config.useSentinelServers().setDatabase(database);
            if (StringUtils.isNotBlank(password)) {
                config.useSentinelServers().setPassword(password);
            }
            // 设置 sentinel 节点的服务 IP 和端口
            for (int i = 1; i < addrTokens.length; i++) {
                config.useSentinelServers()
                        .addSentinelAddress(RedisConstantEnum.REDIS_CONNECTION_PREFIX.getConstantValue() + addrTokens[i]);
            }
            log.info("初始化[哨兵部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("哨兵部署 Redisson init error", e);

        }
        return config;
    }
}
