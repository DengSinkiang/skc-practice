package com.dxj.skc.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/3/27 16:24
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Configuration
@Slf4j
@Getter
@ComponentScans(value = {@ComponentScan("com.dxj.skc.*")})
public class SkcAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("init skc start...");
    }

}
