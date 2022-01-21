package com.lzx.elk.log.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ElkLogSdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElkLogSdkApplication.class, args);
    }

}
