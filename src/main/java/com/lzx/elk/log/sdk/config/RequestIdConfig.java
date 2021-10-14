package com.lzx.elk.log.sdk.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.lzx.elk.log.sdk.util.HttpUtils;

public class RequestIdConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        String requestId = HttpUtils.getRequestId();
        return requestId;
    }
}
