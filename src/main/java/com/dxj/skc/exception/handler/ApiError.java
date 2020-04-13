package com.dxj.skc.exception.handler;

import com.dxj.skc.domain.vo.Result;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/31 18:28
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Data
class ApiError {

    private int code = HttpStatus.BAD_REQUEST.value();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public static ApiError error(String message) {
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    public static ApiError error(int code, String message) {
        ApiError apiError = new ApiError();
        apiError.setCode(code);
        apiError.setMessage(message);
        return apiError;
    }
}
