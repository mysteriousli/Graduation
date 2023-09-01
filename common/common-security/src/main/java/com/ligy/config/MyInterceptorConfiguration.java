package com.ligy.config;

import com.ligy.secrity.AuthenticationInterceptor;
import org.aopalliance.intercept.Invocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author lgy
 */
@Configuration
public class MyInterceptorConfiguration implements WebMvcConfigurer {

    @Resource
    AuthenticationInterceptor authenticationInterceptor;

    /**
     * 添加自定义的拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor);
    }
}