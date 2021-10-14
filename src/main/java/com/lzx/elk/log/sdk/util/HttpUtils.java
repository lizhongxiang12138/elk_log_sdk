package com.lzx.elk.log.sdk.util;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    private static final String UNKNOWN = "UNKNOWN";
    private static final InheritableThreadLocal<Map<String, String>> threadLocal = new InheritableThreadLocal<>();

    public static void setTrace(HttpServletRequest request) {
        String requestId = request.getHeader("request_id");
        String requestURI = request.getRequestURI();

        Map<String, String> map = new HashMap<>();
        if(StringUtils.isNotBlank(requestURI)) {
            try {
                String[] split = requestURI.substring(1).split("/");
                map.put("targetService", split[0]);
            } catch (Exception e) {}
        }
        map.put("request_id", requestId);
        HttpUtils.threadLocal.set(map);
    }

    public static void removeTrace() {
        HttpUtils.threadLocal.remove();
    }

    public static String getRequestId() {
        return get("request_id");
    }

    private static String get(String key) {
        String result = "UNKNOWN";
        Map<String, String> map = HttpUtils.threadLocal.get();
        if(map != null) {
            String get = map.get(key);
            if(StringUtils.isNotBlank(get)) {
                result = get;
            }
        }
        return result;
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = "";

        try {
            //X-Forwarded-For：Squid 服务代理
            String ipAddresses = request.getHeader("X-Forwarded-For");
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //Proxy-Client-IP：apache 服务代理
                ipAddresses = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //WL-Proxy-Client-IP：weblogic 服务代理
                ipAddresses = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //HTTP_CLIENT_IP：有些代理服务器
                ipAddresses = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                //X-Real-IP：nginx服务代理
                ipAddresses = request.getHeader("X-Real-IP");
            }

            //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
            if (ipAddresses != null && ipAddresses.length() != 0) {
                ip = ipAddresses.split(",")[0];
            }

            //还是不能获取到，最后再通过request.getRemoteAddr();获取
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            ip = "127.0.0.1";
        }

        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

}
