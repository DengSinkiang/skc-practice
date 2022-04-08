package com.dxj.skc.lock.redisson;

import com.dxj.skc.lock.redisson.entity.RedissonProperties;
import com.dxj.skc.lock.redisson.enumeration.RedisDeployTypeEnum;
import com.dxj.skc.lock.redisson.strategy.RedissonConfigService;
import com.dxj.skc.lock.redisson.strategy.impl.ClusterConfigImpl;
import com.dxj.skc.lock.redisson.strategy.impl.MasterslaveConfigImpl;
import com.dxj.skc.lock.redisson.strategy.impl.SentinelConfigImpl;
import com.dxj.skc.lock.redisson.strategy.impl.StandaloneConfigImpl;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.config.Config;

/**
 * @description: Redisson 核心配置，用于提供初始化的 redisson 实例
 * @author: sinkiang
 * @date: 2022/4/7 10:32
 */
@Slf4j
public class RedissonManager {


    private Redisson redisson = null;

    public RedissonManager() {
    }

    public RedissonManager(RedissonProperties redissonProperties) {
        try {
            // 通过不同部署方式获得不同 config 实体
            Config config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
            redisson = (Redisson) Redisson.create(config);
        } catch (Exception e) {
            log.error("Redisson init error", e);
            throw new IllegalArgumentException("please input correct configurations," +
                    "connectionType must in standalone/sentinel/cluster/masterslave");
        }
    }

    public Redisson getRedisson() {
        return redisson;
    }

    /**
     * Redisson 连接方式配置工厂
     * 双重检查锁
     */
    static class RedissonConfigFactory {

        private RedissonConfigFactory() {
        }

        private static volatile RedissonConfigFactory factory = null;

        public static RedissonConfigFactory getInstance() {
            if (factory == null) {
                synchronized (Object.class) {
                    if (factory == null) {
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }


        /**
         * 根据连接类型获取对应连接方式的配置,基于策略模式
         *
         * @param redissonProperties redis 连接信息
         * @return Config
         */
        Config createConfig(RedissonProperties redissonProperties) {
            Preconditions.checkNotNull(redissonProperties);
            Preconditions.checkNotNull(redissonProperties.getAddress(), "redisson.lock.server.address cannot be NULL!");
            Preconditions.checkNotNull(redissonProperties.getType(), "redisson.lock.server.password cannot be NULL");
            String connectionType = redissonProperties.getType();
            // 声明配置上下文
            RedissonConfigService redissonConfigService;
            if (connectionType.equals(RedisDeployTypeEnum.STANDALONE.getConnectionType())) {
                redissonConfigService = new StandaloneConfigImpl();
            } else if (connectionType.equals(RedisDeployTypeEnum.SENTINEL.getConnectionType())) {
                redissonConfigService = new SentinelConfigImpl();
            } else if (connectionType.equals(RedisDeployTypeEnum.CLUSTER.getConnectionType())) {
                redissonConfigService = new ClusterConfigImpl();
            } else if (connectionType.equals(RedisDeployTypeEnum.MASTERSLAVE.getConnectionType())) {
                redissonConfigService = new MasterslaveConfigImpl();
            } else {
                throw new IllegalArgumentException("创建Redisson连接Config失败！当前连接方式:" + connectionType);
            }
            return redissonConfigService.createRedissonConfig(redissonProperties);
        }
    }

}
