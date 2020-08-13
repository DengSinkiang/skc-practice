package com.dxj.skc.datasource.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: 本地线程，数据源上下文切换
 * @Author: dengxj29231
 * @Date: 2020/8/13 12:25
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@Slf4j
public class DataSourceContextHolder {
    //线程本地环境
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return LOCAL;
    }

    /**
     * 设定使用的数据源标识
     */
    public static void setDataSource(String dsId) {
        LOCAL.set(dsId);
        log.debug("设定使用的数据源标识为:" + dsId);
    }

    /**
     * 获取使用的数据源标识
     *
     * @return
     */
    public static String getDataSource() {
        log.debug("获取使用的数据源标识为:" + LOCAL.get());
        return LOCAL.get();
    }

    /**
     * 清除使用的数据源标识
     */
    public static void clear() {
        log.debug("删除使用的数据源标识为:" + LOCAL.get());
        LOCAL.remove();
    }
}

