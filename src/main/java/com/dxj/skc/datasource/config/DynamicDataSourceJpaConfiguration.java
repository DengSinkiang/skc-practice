package com.dxj.skc.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.schema.Action;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: dengxj29231
 * @Date: 2020/8/13 12:31
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@Configuration
@EnableConfigurationProperties({JpaProperties.class})
@EnableTransactionManagement
// @EnableJpaRepositories(basePackages = {"vip.efactory"})
@Slf4j
@AllArgsConstructor
public class DynamicDataSourceJpaConfiguration {
    private final JpaProperties jpaProperties; // yml文件中的jpa配置
    private DataSourceProperties dataSourceProperties;

    /**
     * 初始化默认的数据源
     */
    @Bean
    public DataSource defaultDataSource() {
        // 先初始化默认数据源，然后其他的数据源然后再进行初始化,详见：DataSourceBeanPostProcessor类
        DruidDataSource defaultDataSource = new DruidDataSource();
        defaultDataSource.setUsername(dataSourceProperties.getUsername());
        defaultDataSource.setPassword(dataSourceProperties.getPassword());
        defaultDataSource.setUrl(dataSourceProperties.getUrl());
        defaultDataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        try {
            defaultDataSource.init();
        } catch (SQLException e) {
            log.error("默认数据源初始化异常!", e);
            log.error("系统即将退出!");
            System.exit(1);
        }
        return defaultDataSource;
    }

    /**
     * 把所有数据库都放在路由中
     * 重点是roundRobinDataSouceProxy()方法，它把所有的数据库源交给AbstractRoutingDataSource类，
     * 并由它的determineCurrentLookupKey()进行决定数据源的选择。
     *
     * @return
     */
    @Bean(name = "roundRobinDataSouceProxy")
    public AbstractRoutingDataSource roundRobinDataSouceProxy(DataSource defaultDataSource) {
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource();
        proxy.setTargetDataSources(DynamicDataSourceProvider.dataSourceMap);
        //默认库
        //proxy.setDefaultTargetDataSource(DynamicDataSourceProvider.dataSourceMap.get(DynamicDataSourceEnum.DEFAULT.name()));
        proxy.setDefaultTargetDataSource(defaultDataSource);
        return proxy;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(AbstractRoutingDataSource roundRobinDataSouceProxy) {
        Map<String, Object> hibernateProps = new LinkedHashMap<>();
        hibernateProps.putAll(this.jpaProperties.getProperties());
        hibernateProps.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        hibernateProps.put(Environment.PHYSICAL_NAMING_STRATEGY, "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy"); // 属性及column命名策略
        hibernateProps.put(Environment.HBM2DDL_AUTO, Action.UPDATE); // 自动更新表结构,仅默认数据源有效且控制台会报警告可以不用管！
        // hibernateProps.put(Environment.SHOW_SQL, true); // 显示SQL,如果需要可以打开
        // hibernateProps.put(Environment.FORMAT_SQL, true); // 格式化SQL,如果需要可以打开

        // No dataSource is set to resulting entityManagerFactoryBean
        LocalContainerEntityManagerFactoryBean result = new LocalContainerEntityManagerFactoryBean();
        result.setPackagesToScan("vip.efactory");
        result.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        result.setJpaPropertyMap(hibernateProps);
        // 这个数据源设置为代理的数据源，----这是关键性配置！！！
        result.setDataSource(roundRobinDataSouceProxy);

        return result;
    }

    @Bean
    @Primary // 注意我们自己定义的bean，最好都加此注解，防止与自动配置的重复而不知道如何选择
    public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return entityManagerFactoryBean.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary // 注意我们自己定义的bean，最好都加此注解，防止与自动配置的重复而不知道如何选择
    public PlatformTransactionManager txManager(EntityManagerFactory entityManagerFactory, AbstractRoutingDataSource roundRobinDataSouceProxy) {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        // 此处在SpringDataJpa中不使用hibernate的事务管理，否则可能导致log持久层save方法不写数据库的问题
        // HibernateTransactionManager result = new HibernateTransactionManager();
        // result.setAutodetectDataSource(false); // 不自动检测数据源
        // result.setSessionFactory(sessionFactory);
        // result.setRollbackOnCommitFailure(true);
        // return result;

        // JpaTransactionManager txManager = new JpaTransactionManager(); // 如果不想观察数据源的选择，请使用本行
        JpaTransactionManager txManager = new MyJpaTransactionManager(); // 使用自定义的子类是为了更好的观察多数据源切换
        // 这个数据源设置为代理的数据源，----这是关键性配置！！！
        txManager.setDataSource(roundRobinDataSouceProxy);
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}
