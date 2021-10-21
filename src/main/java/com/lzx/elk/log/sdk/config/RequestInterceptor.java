package com.lzx.elk.log.sdk.config;

import brave.Tracer;
import brave.propagation.TraceContext;
import com.lzx.elk.log.sdk.util.SpringUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class RequestInterceptor implements ClientHttpRequestInterceptor {
    public static final String TOKEN_KEY = "TencentTokenKey";

    public RequestInterceptor() {
    }

    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        long timeStamp = System.currentTimeMillis() / 1000L;
        headers.add("t", timeStamp + "");
        Tracer tracer = SpringUtil.getBean(Tracer.class);
        TraceContext context = tracer.currentSpan().context();
        headers.add("X-B3-TraceId",String.valueOf(context.traceIdString()));
        headers.add("X-B3-SpanId", String.valueOf(context.spanIdString()));
        headers.add("X-B3-ParentSpanId", String.valueOf(context.parentIdString()));
        headers.add("X-B3-Sampled", context.sampled()?"1":"0");

        return execution.execute(request,body);
    }

}
