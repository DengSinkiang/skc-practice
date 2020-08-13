package com.dxj.skc.datasource.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/8/13 12:19
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DynamicDataSource {

    /**
     * groupName or specific database name or spring SPEL name.
     *
     * @return the database you want to switch
     */
    String value() default "default";
}
