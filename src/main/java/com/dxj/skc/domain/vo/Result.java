package com.dxj.skc.domain.vo;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description: 前后端交互数据标准
 * @Author: Sinkiang
 * @Date: 2020/3/27 15:55
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息
     */
    private String message;

    /**
     * 返回代码
     */
    private int code;

    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    /**
     * 结果对象
     */
    private T data;
}
