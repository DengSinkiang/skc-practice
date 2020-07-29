package com.dxj.skc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @Description: 统一异常处理
 * @Author: Sinkiang
 * @Date: 2020/3/31 18:28
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Getter
public class SkException extends RuntimeException {

    private Integer status = 500;

    public SkException(String msg) {
        super(msg);
    }

    public SkException(HttpStatus status, String msg){
        super(msg);
        this.status = status.value();
    }
}

