package com.dxj.skc.lock.redisson.enumeration;

/**
 * @author sinkiang
 * @description: Redis连接方式包含:
 * * standalone-单节点部署方式
 * * sentinel-哨兵部署方式
 * * cluster-集群方式
 * * masterslave-主从部署方式
 * @date 2022/4/7 10:32
 */
public enum RedisDeployTypeEnum {

    /**
     * 单节点部署方式
     */
    STANDALONE("standalone", "单节点部署方式"),
    SENTINEL("sentinel", "哨兵部署方式"),
    CLUSTER("cluster", "集群方式"),
    MASTERSLAVE("masterslave", "主从部署方式");

    private final String connectionType;
    private final String connectionDesc;

    RedisDeployTypeEnum(String connectionType, String connectionDesc) {
        this.connectionType = connectionType;
        this.connectionDesc = connectionDesc;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getConnectionDesc() {
        return connectionDesc;
    }
}
