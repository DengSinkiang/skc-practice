package com.dxj.skc.redisson.strategy.impl;

import com.dxj.skc.redisson.enumeration.GlobalConstantEnum;
import com.dxj.skc.redisson.entity.RedissonProperties;
import com.dxj.skc.redisson.strategy.RedissonConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 主从部署 Redisson 配置
 * 连接方式: 主节点,子节点,子节点
 * 格式为: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
 * @Author: Sinkiang
 * @Date: 2020/7/27 10:35
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Slf4j
public class MasterslaveConfigImpl implements RedissonConfigService {

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String[] addrTokens = address.split(",");
            String masterNodeAddr = addrTokens[0];
            // 设置主节点ip
            config.useMasterSlaveServers().setMasterAddress(masterNodeAddr);
            if (StringUtils.isNotBlank(password)) {
                config.useMasterSlaveServers().setPassword(password);
            }
            config.useMasterSlaveServers().setDatabase(database);
            // 设置从节点，移除第一个节点，默认第一个为主节点
            List<String> slaveList = new ArrayList<>();
            for (String addrToken : addrTokens) {
                slaveList.add(GlobalConstantEnum.REDIS_CONNECTION_PREFIX.getConstantValue() + addrToken);
            }
            slaveList.remove(0);
            String[] strings = new String[slaveList.size()];
            config.useMasterSlaveServers().addSlaveAddress(slaveList.toArray(strings));
            log.info("初始化[主从部署]方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("主从部署 Redisson init error", e);
            e.printStackTrace();
        }
        return config;
    }

}
