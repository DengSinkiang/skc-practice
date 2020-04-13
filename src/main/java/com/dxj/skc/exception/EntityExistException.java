package com.dxj.skc.exception;

import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/31 18:28
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Object o, String field, String val) {
        super(EntityExistException.generateMessage(o.getClass().getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " existed";
    }
}
