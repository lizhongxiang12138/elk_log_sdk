package com.lzx.elk.log.sdk.filter;

import brave.Tracer;
import com.lzx.elk.log.sdk.util.HttpUtils;
import com.lzx.elk.log.sdk.util.MutableHttpServletRequest;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;


/**
 * 描述 ：RequestId过滤器，主要用来生成 request_id
 * lizhongxiang
 * 2021/9/29
 */
@Component
@WebFilter
@Order(-999)
public class RequestIdFilter implements Filter {

    @Autowired
    private Tracer tracer;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
        String requestId = request.getHeader("X-B3-TraceId");
        requestId = request.getHeader("traceId");
        requestId = StringUtils.isNotBlank(requestId)?requestId:UUID.randomUUID().toString();
        String s = this.tracer.currentSpan().context().traceIdString();
        mutableRequest.putHeader("request_id", s);
//        mutableRequest.putHeader("X─B3─TraceId", requestId);
        HttpUtils.setTrace(mutableRequest);

        chain.doFilter(mutableRequest,servletResponse);

        HttpUtils.removeTrace();
    }
}
