package com.dxj.skc.datasource.config;

/**
 * @Description: 初始化数据表中定义的数据源
 * @Author: dengxj29231
 * @Date: 2020/8/13 12:32
 * @CopyRight: 2020 hundsun all rights reserved.
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.dxj.skc.datasource.domain.GenDataSourceConf;
import com.dxj.skc.datasource.enumeration.DynamicDataSourceEnum;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
@Component
@Slf4j
public class DataSourceBeanPostProcessor {
    // 自动配置创建的druid数据源
    private DruidDataSource defaultDataSource;
    private GenDataSourceConfService genDataSourceConfService;

    private AbstractRoutingDataSource roundRobinDataSouceProxy;


    @PostConstruct
    public void init() {
        // 先将默认的数据源也放在容器里
        DynamicDataSourceProvider.addDataSource(DynamicDataSourceEnum.DEFAULT.name(), defaultDataSource);
        log.info("动态数据源初始化开始...");
        // 放入数据源集合中
        List<GenDataSourceConf> dataSourceConfs = genDataSourceConfService.findAll();
        // 初始化所有租户的数据源
        if (dataSourceConfs != null && dataSourceConfs.size() > 0) {
            dataSourceConfs.forEach(dataSourceConf -> {
                try {
                    DruidDataSource newDataSource = defaultDataSource.cloneDruidDataSource();  // 克隆已有的数据源进行修改
                    // 设定新的数据源的重要参数
                    newDataSource.setUsername(dataSourceConf.getUserName());
                    newDataSource.setPassword(dataSourceConf.getPwd());
                    newDataSource.setUrl(dataSourceConf.getJdbcUrl());
                    // newDataSource.setDriverClassName(dataSourceConf.getDriverClassName());  // 其实也可以默认
                    newDataSource.init(); // 初始化数据源
                    DynamicDataSourceProvider.addDataSource(dataSourceConf.getName(), newDataSource);  // 放入数据源集合中
                    log.info("数据源{}初始化完成！", dataSourceConf.getName());
                } catch (SQLException throwables) {
                    log.error("数据源{}初始化失败！异常内容:{}", dataSourceConf.getName(), throwables.getMessage());
                    throwables.printStackTrace();
                }
            });
            // 再次设定路由的数据源,否则用key查不到就会走默认数据源，因为初次是空集合。
            roundRobinDataSouceProxy.setTargetDataSources(DynamicDataSourceProvider.dataSourceMap);
            roundRobinDataSouceProxy.afterPropertiesSet();  // 重新处理加入的数据源集合，如果不调用此方法就算加入了也不会被处理，等于没有加入!!
        }
        log.info("动态数据源初始化结束");
    }

    /**
     * 在数据源管理界面如果动态增加新的数据源,可以注入当前对象，调用此方法！
     *
     * @param dataSourceConf
     */
    public void addNewDataSource(GenDataSourceConf dataSourceConf) {
        try {
            // 克隆已有的数据源进行修改
            DruidDataSource newDataSource = defaultDataSource.cloneDruidDataSource();
            // 设定新的数据源的重要参数
            newDataSource.setUsername(dataSourceConf.getUserName());
            newDataSource.setPassword(dataSourceConf.getPwd());
            newDataSource.setUrl(dataSourceConf.getJdbcUrl());
            // newDataSource.setDriverClassName(dataSourceConf.getDriverClassName());  // 其实也可以默认
            // 初始化数据源
            newDataSource.init();
            // 放入数据源集合中
            DynamicDataSourceProvider.addDataSource(dataSourceConf.getName(), newDataSource);
            // 刷新所有的数据源
            roundRobinDataSouceProxy.setTargetDataSources(DynamicDataSourceProvider.dataSourceMap);
            roundRobinDataSouceProxy.afterPropertiesSet();
            log.info("数据源{}初始化完成！", dataSourceConf.getName());
        } catch (SQLException e) {
            log.error("数据源{}初始化失败！异常内容:{}", dataSourceConf.getName(), e.getMessage());
            e.printStackTrace();
        }
    }
}
