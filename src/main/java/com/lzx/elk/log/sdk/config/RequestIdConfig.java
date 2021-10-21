package com.lzx.elk.log.sdk.config;

import brave.Tracer;
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.lzx.elk.log.sdk.util.HttpUtils;
import com.lzx.elk.log.sdk.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestIdConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        String requestId = HttpUtils.getRequestId();
        return requestId;
    }
}
