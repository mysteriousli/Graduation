package com.ligy.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lgy
 */
public class WebUtils {
    /**
     * 获取token
     * @return
     */
    public static List<String> getToken(){
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        List<String> tokens = new ArrayList<>();
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            String token = cookie.getName() + "=" + cookie.getValue();
            tokens.add(token);
        }
        return tokens;
    }
}
