package com.dxj.skc.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description: 异常工具
 * @Author: Sinkiang
 * @Date: 2020/3/27 16:14
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

}
