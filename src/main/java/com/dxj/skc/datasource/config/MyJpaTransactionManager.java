package com.dxj.skc.datasource.config;

/**
 * @Description: 这个类其实完全可以不存在，主要是为了打日志方便观察动态数据源的使用!
 * 如果不需要观察，直接使用JpaTransactionManager即可!
 * @Author: dengxj29231
 * @Date: 2020/8/13 12:22
 * @CopyRight: 2020 hundsun all rights reserved.
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;

@Slf4j
public class MyJpaTransactionManager extends JpaTransactionManager {

    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        log.debug("jpa-transaction:begin-----now dataSource use is [" + DataSourceContextHolder.getDataSource() + "]");
        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCommit(DefaultTransactionStatus status) {
        log.debug("jpa-transaction:commit-----now dataSource use is [" + DataSourceContextHolder.getDataSource() + "]");
        super.doCommit(status);
    }
}

