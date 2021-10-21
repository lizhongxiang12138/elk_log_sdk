package com.lzx.elk.log.sdk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sdk/test/")
@Slf4j
public class SDKTestLogController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/logPrint")
    public String logPrint(HttpServletRequest request){
        log.info("打印一条日志呀呀！！");
        log.info("哈哈哈哈哈！！");
        String forObject = restTemplate.getForObject("http://localhost:8081/test/logPrint", String.class);
        forObject = restTemplate.getForObject("http://localhost:8082/test/logPrint", String.class);
        return forObject;
    }

}
