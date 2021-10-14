package com.lzx.elk.log.sdk.filter;

import com.lzx.elk.log.sdk.util.HttpUtils;
import com.lzx.elk.log.sdk.util.MutableHttpServletRequest;
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
 * @author lizhongxiang
 * @date 2021/9/29
 */
@Component
@WebFilter
@Order(-999)
public class RequestIdFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(request);
        String requestId = UUID.randomUUID().toString();
        mutableRequest.putHeader("request_id", requestId);
        HttpUtils.setTrace(mutableRequest);

        chain.doFilter(servletRequest,servletResponse);

        HttpUtils.removeTrace();
    }
}
