package com.dxj.skc.apollo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@Slf4j
@RestController
public class ApolloController {

    @Value("${test.value}")
    String testValue;

    @GetMapping("/test")
    public String test() {
        return "打印配置中心 testValue 值" + testValue;
    }
}
