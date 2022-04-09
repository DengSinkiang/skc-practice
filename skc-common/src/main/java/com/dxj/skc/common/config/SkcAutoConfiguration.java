package com.dxj.skc.common.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
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
