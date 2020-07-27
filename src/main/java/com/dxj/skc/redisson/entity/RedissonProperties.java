package com.dxj.skc.redisson.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description: 读取 redis 配置信息，封装到当前实体中
 * @Author: Sinkiang
 * @Date: 2020/7/27 10:35
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@ConfigurationProperties(prefix = "redisson.lock.server")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedissonProperties {

    /**
     * redis主机地址，ip：port，有多个用半角逗号分隔
     */
    private String address;

    /**
     * 连接类型，支持standalone-单机节点，sentinel-哨兵，cluster-集群，masterslave-主从
     */
    private String type;

    /**
     * redis 连接密码
     */
    private String password;

    /**
     * 选取那个数据库
     */
    private int database;

    public RedissonProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public RedissonProperties setDatabase(int database) {
        this.database = database;
        return this;
    }

}
