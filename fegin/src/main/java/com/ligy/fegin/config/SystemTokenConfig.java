package com.ligy.fegin.config;/*
 * Copyright 2020, Zetyun DEP All rights reserved.
 */


//import com.zetyun.dep.common.core.utils.ServletUtils;
//import com.zetyun.dep.common.core.utils.StringUtils;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
public class SystemTokenConfig implements RequestInterceptor {

    private static final ThreadLocal<List<String>> TOKEN = new ThreadLocal<>();

    public static void remove() {
        TOKEN.remove();
    }

    public static void setToken(List<String> tokens) {
        TOKEN.set(tokens);
    }

    public static List<String> getToken() {
        return TOKEN.get();
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        final String HEADER_UUMS = "Cookie";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            List<String> tokens = TOKEN.get();
            for (String token : tokens) {
                requestTemplate.header(HEADER_UUMS, token);
            }
        } else {
            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            String token = null;
            Cookie[] cookies = httpServletRequest.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    try {
                        token = cookie.getName() + "=" + cookie.getValue();
                    } catch (Exception e) {
                        log.error("get cookies raise  UnsupportedEncodingException and the message is {}",
                                e.getMessage());
                    }
                    requestTemplate.header(HEADER_UUMS, token);
                }
            }
        }
    }
}
