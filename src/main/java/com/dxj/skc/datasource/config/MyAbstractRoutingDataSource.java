package com.dxj.skc.datasource.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;


/**
 * @Description: 抽象数据源的路由的子类
 * @Author: dengxj29231
 * @Date: 2020/8/13 12:28
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@Getter
@Setter
@AllArgsConstructor
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {

    /**
     * 重写父类的功能，如果没有查询key使用默认，
     * 如果有查询key，但是没有对应的数据源，直接抛异常不允许使用默认的数据源，如果使用,用户会误以为是自己的查询key对应的数据源！！！
     * 因为父类的属性私有，目前重写不了，变通的方式是检查DynamicDataSourceProvider,这里没有就认为没有
     */
    @Override
    protected DataSource determineTargetDataSource() {
        Object lookupKey = determineCurrentLookupKey();
        if (!StringUtils.isEmpty(lookupKey)) {
            // 检查持有者是否含有，没有就抛异常
            DataSource dataSource = DynamicDataSourceProvider.getDataSourceByKey((String) lookupKey);
            if (dataSource == null) {
                throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
            }
        }
        return super.determineTargetDataSource();
    }

    /**
     * 这是AbstractRoutingDataSource类中的一个抽象方法，
     * 而它的返回值是你所要用的数据源dataSource的key值，有了这个key值，
     * targetDataSources就从中取出对应的DataSource，如果找不到，就用配置默认的数据源。
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
