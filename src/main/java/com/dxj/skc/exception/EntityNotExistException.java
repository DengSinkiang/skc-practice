package com.dxj.skc.exception;

import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/31 18:28
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public class EntityNotExistException extends RuntimeException {

    public EntityNotExistException(Object o, String field, String val) {
        super(EntityNotExistException.generateMessage(o.getClass().getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " "+ val + " does not exist";
    }
}
