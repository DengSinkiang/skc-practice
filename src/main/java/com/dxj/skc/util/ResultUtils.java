package com.dxj.skc.util;

import com.dxj.skc.enumeration.CommonEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * @Description: API 返回结果封装工具类
 * @Author: Sinkiang
 * @Date: 2020/3/27 15:58
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public class ResultUtils<T> {

    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private String message;
    /**
     * 结果
     */
    private T data;
    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    protected ResultUtils() {
    }

    protected ResultUtils(int code, String message, LocalDateTime timestamp, T data) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.data = data;

    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResultUtils<T> success(T data) {
        return new ResultUtils<T>(CommonEnum.SUCCESS.getKey(), CommonEnum.SUCCESS.getValue(), LocalDateTime.now(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> ResultUtils<T> success(T data, String message) {
        return new ResultUtils<T>(CommonEnum.SUCCESS.getKey(), message, LocalDateTime.now(), data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> ResultUtils<T> failed(int errorCode, String message) {
        return new ResultUtils<T>(errorCode, message, LocalDateTime.now(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> ResultUtils<T> failed(String message) {
        return new ResultUtils<T>(CommonEnum.FAILED.getKey(), message, LocalDateTime.now(), null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResultUtils<T> failed() {
        return failed(CommonEnum.FAILED.getKey(), CommonEnum.FAILED.getValue());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
