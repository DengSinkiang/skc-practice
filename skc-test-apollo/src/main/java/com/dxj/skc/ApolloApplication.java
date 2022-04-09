package com.dxj.skc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sinkiang
 * @date 2022/4/8 12:27
 */
@SpringBootApplication
public class ApolloApplication {
    public static void main(String[] args) {
        System.setProperty("apollo.config-service", "http://localhost:7000");
        SpringApplication.run(ApolloApplication.class, args);
    }

}