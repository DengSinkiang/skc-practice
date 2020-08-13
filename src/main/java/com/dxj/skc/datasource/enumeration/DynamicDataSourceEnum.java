package com.dxj.skc.datasource.enumeration;

/**
 * @Description: 动态数据源枚举
 * @Author: dengxj29231
 * @Date: 2020/8/13 12:20
 * @CopyRight: 2020 hundsun all rights reserved.
 */
public enum DynamicDataSourceEnum {
    /**
     * 默认
     */
    DEFAULT("default");

    DynamicDataSourceEnum(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

