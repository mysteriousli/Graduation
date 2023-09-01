package com.ligy.secrity;

import com.ligy.core.path.PathConstants;
import com.ligy.exception.CustomException;
import com.ligy.fegin.userFegin.UserFeign;
import com.ligy.pojo.User;
import com.ligy.util.LoginUserUtils;
import com.ligy.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author sunyu
 */

/**
 * @Author: litong
 * @Date: 2019-09-20 11:50
 * @Description: 拦截器
 */
@Slf4j
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    /**
     * 在业务处理器处理请求之前被调用
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String path = request.getServletPath();
        if (isPre(path)){
            return true;
        }

        LoginUserUtils.remove();
        Cookie[] cookies = request.getCookies();

        Optional<Cookie> uums = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("UUMS"))
                .findFirst();

        if (!uums.isPresent()){
            throw new CustomException("用户未登录！");
        }
        UserFeign userFeign = SpringUtils.getBean(UserFeign.class);
        User user = userFeign.uums(uums.get().getValue()).getData();
        LoginUserUtils.setLoginUser(user);

        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    public boolean isPre(String url){
        for (String path : PathConstants.url) {
            if (isPathMatchPattern(url,path)){
                return true;
            }
        }
        return false;
    }

    private static boolean isPathMatchPattern(String path, String pattern) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        return pathMatcher.match(pattern, path);
    }
}
