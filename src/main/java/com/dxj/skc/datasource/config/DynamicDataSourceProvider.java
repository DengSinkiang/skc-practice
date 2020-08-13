package com.dxj.skc.datasource.config;

import com.dxj.skc.exception.SkException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 数据源提供者，管理所有的数据源信息
 * @Author: dengxj29231
 * @Date: 2020/8/13 12:23
 * @CopyRight: 2020 hundsun all rights reserved.
 */

@Configuration
@Slf4j
public class DynamicDataSourceProvider {

    // 使用一个map来存储我们数据源Key和对应的数据源，把所有数据库都放在dataSourceMap中,
    // 注意key值要和determineCurrentLookupKey()中代码写的一致，否则切换数据源时找不到正确的数据源
    public static Map<Object, Object> dataSourceMap = new HashMap<>();

    // 根据传进来的dataSourceKey决定返回的数据源,不存在则抛出异常
    @SneakyThrows
    public static DataSource getDataSourceByKey(String dataSourceKey) {
        if (dataSourceMap.containsKey(dataSourceKey)) {
            return (DataSource) dataSourceMap.get(dataSourceKey);
        }
        throw new SkException("指定的数据源键[" + dataSourceKey + "]不存在!");
    }

    // 初始化的时候用于添加数据源的方法
    @SneakyThrows
    public static void addDataSource(String dataSourceKey, DataSource dataSource) {
        if (StringUtils.isEmpty(dataSourceKey) || dataSource == null) {
            throw new SkException("添加数据源时键[" + dataSourceKey + "]不允许为空或者数据源不允许为空!");
        }
        dataSourceMap.put(dataSourceKey, dataSource);
    }

    // 根据传进来的dataSourceKey来删除指定的数据源
    public static void removeDataSourceByKey(String dataSourceKey) {
        if (dataSourceMap.containsKey(dataSourceKey)) {
            dataSourceMap.remove(dataSourceKey);
        }
    }

}

