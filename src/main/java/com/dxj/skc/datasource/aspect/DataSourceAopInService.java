package com.dxj.skc.datasource.aspect;

import com.dxj.skc.datasource.annotation.DynamicDataSource;
import com.dxj.skc.datasource.config.DataSourceContextHolder;
import com.dxj.skc.datasource.enumeration.DynamicDataSourceEnum;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @Description: 在service层决定数据源
 * * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行
 * * 方法名或者类名上加上动态数据源@DynamicDataSource注解就可以使用对应的数据库！！
 * @Author: dengxj29231
 * @Date: 2020/8/13 12:27
 * @CopyRight: 2020 hundsun all rights reserved.
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Component
public class DataSourceAopInService implements PriorityOrdered {

    private static final String LAST_PREFIX = "#last";

    /**
     * 设定使用的数据源
     *
     * @param dds
     */
    @Before("@annotation(dds)")
    public void setDynamicDataSource(JoinPoint point, DynamicDataSource dds) {
        String key = dds.value();
        // 说明是使用方法的最后一个参数作为数据源的key
        if (!StringUtils.isEmpty(key) && key.startsWith(LAST_PREFIX)) {
            Object[] arguments = point.getArgs();
            // 参数值为空及空串时，使用默认的数据源!
            String dynamicKey = (arguments[arguments.length - 1] == null || StringUtils.isEmpty(String.valueOf(arguments[arguments.length - 1]))) ? DynamicDataSourceEnum.DEFAULT.name() : String.valueOf(arguments[arguments.length - 1]);
            DataSourceContextHolder.setDataSource(dynamicKey);
            return;
        }
        DataSourceContextHolder.setDataSource(dds.value());
    }

    @Override
    public int getOrder() {
        /**
         * 值越小，越优先执行
         * 要优于事务的执行
         * 在启动类中加上了@EnableTransactionManagement(order = 10)
         */
        return 1;
    }

}
