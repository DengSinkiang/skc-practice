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
    STATUS_NORMAL(0),
    /**
     * 禁用标志
     */
    STATUS_DISABLE(-1),
    /**
     * 删除标志
     */
    DEL_FLAG(1);

    CommonEnum(int status) {
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return status;
    }


}
