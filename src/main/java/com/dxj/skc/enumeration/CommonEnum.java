package com.dxj.skc.enumeration;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/30 8:48
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public enum CommonEnum {
    /**
     * 正常标志
     */
    STATUS_NORMAL(0, "正常标志"),
    /**
     * 禁用标志
     */
    STATUS_DISABLE(-1, "禁用标志"),
    /**
     * 删除标志
     */
    DEL_FLAG(1, "删除标志"),
    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 操作失败
     */
    FAILED(500, "操作失败");

    CommonEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    private int key;
    private String value;

    public int getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }


}
