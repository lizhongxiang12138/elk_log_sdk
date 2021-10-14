package com.lzx.elk.log.sdk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestLogController {

    @GetMapping("/logPrint")
    public String logPrint(){
        log.info("打印一条日志呀呀！！");
        log.info("哈哈哈哈哈！！");
        return "打印一条日志呀呀";
    }

}
