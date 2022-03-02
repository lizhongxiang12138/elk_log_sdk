package com.lzx.elk.log.sdk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class ElkLogSdkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElkLogSdkApplication.class, args);
    }

    @KafkaListener(topics = "sqm")
    public void listen(String in) {
        log.info(in);
    }


    @KafkaListener(groupId = "do_action",topics = "sqm")
    public void doActionListener(String in) {
        log.info(in);
    }

}
