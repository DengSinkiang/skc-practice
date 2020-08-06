package com.dxj.skc.limit.annotation;

import com.dxj.skc.limit.aspect.LimitTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/27 15:58
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    // 资源名称，用于描述接口功能
    String name() default "";

    // 资源 key
    String key() default "";

    // key prefix
    String prefix() default "";

    // 时间的，单位秒
    int period();

    // 限制访问次数
    int count();

    // 限制类型
    LimitTypeEnum limitType() default LimitTypeEnum.CUSTOMER;

}
