package com.dxj.skc.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @Author: Sinkiang
 * @Date: 2020/8/6 16:16
 * @CopyRight: 2020 sk-admin all rights reserved.
 */
@Configuration
public class ElasticsearchConfiguration {
    @PostConstruct
    public void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
