package com.lzx.elk.log.sdk.appender;

import ch.qos.logback.classic.spi.LoggingEvent;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SdkLoggingEvent extends LoggingEvent {

    private String threadName;

}
