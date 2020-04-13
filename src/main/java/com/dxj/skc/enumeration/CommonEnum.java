package com.dxj.skc.enumeration;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/30 8:48
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public enum CommonEnum {
    STATUS_NORMAL(0),
    STATUS_DISABLE(-1),
    DEL_FLAG(1);

    private CommonEnum(int status) {
        this.status = status;
    }

    private int status;

    public int getStatus() {
        return status;
    }


}
