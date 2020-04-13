package com.dxj.skc.util;

import com.dxj.skc.domain.vo.Result;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/27 15:58
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public class ResultUtil<T> {

    private Result<T> result;

    public ResultUtil() {
        result = new Result<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(200);
        result.setTimestamp(LocalDateTime.now());
    }

    public Result<T> setData(T t) {
        result.setResult(t);
        result.setCode(200);
        result.setTimestamp(LocalDateTime.now());

        return result;
    }

    public Result<T> setSuccessMsg(String msg) {
        result.setSuccess(true);
        result.setMessage(msg);
        result.setCode(200);
        result.setTimestamp(LocalDateTime.now());

        return result;
    }

    public Result<T> setData(T t, String msg) {
        result.setResult(t);
        result.setCode(200);
        result.setMessage(msg);
        result.setTimestamp(LocalDateTime.now());

        return this.result;
    }

    public Result<T> setErrorMsg(String msg) {
        result.setSuccess(false);
        result.setMessage(msg);
        result.setCode(500);
        result.setTimestamp(LocalDateTime.now());

        return result;
    }

    public Result<T> setErrorMsg(int code, String msg) {
        result.setSuccess(false);
        result.setMessage(msg);
        result.setCode(code);
        result.setTimestamp(LocalDateTime.now());

        return result;
    }

    public static <T> Result<T> data(T t) {
        return new ResultUtil<T>().setData(t);
    }

    public static <T> Result<T> data(T t, String msg) {
        return new ResultUtil<T>().setData(t, msg);
    }

    public static <T> Result<T> success(String msg) {
        return new ResultUtil<T>().setSuccessMsg(msg);
    }

    public static <T> Result<T> error(String msg) {
        return new ResultUtil<T>().setErrorMsg(msg);
    }

    public static <T> Result<T> error(int code, String msg) {
        return new ResultUtil<T>().setErrorMsg(code, msg);
    }
}
