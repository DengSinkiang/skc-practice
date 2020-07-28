package com.dxj.skc.limit.controller;

import com.dxj.skc.domain.vo.Result;
import com.dxj.skc.limit.annotation.Limit;
import com.dxj.skc.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author /
 * @date
 * 接口限流测试类
 */
@RestController
@RequestMapping("/limit")
@Api(tags = "限流测试")
public class LimitController {

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    /**
     * 测试限流注解，下面配置说明该接口 60秒内最多只能访问 10次，保存到redis的键名为 limit_test，
     */
    @ApiOperation("测试")
    @GetMapping("/test")
    @Limit(key = "test", period = 60, count = 10, name = "testLimit", prefix = "limit")
    public Result<Integer> test() {

        return new ResultUtil<Integer>().setData(ATOMIC_INTEGER.incrementAndGet());
    }
}
