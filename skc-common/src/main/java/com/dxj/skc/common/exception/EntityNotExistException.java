package com.dxj.skc.common.exception;

import org.springframework.util.StringUtils;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
public class EntityNotExistException extends RuntimeException {

    public EntityNotExistException(Object o, String field, String val) {
        super(EntityNotExistException.generateMessage(o.getClass().getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " " + val + " does not exist";
    }
}
