package com.ligy.annotation;

import org.springframework.cloud.openfeign.EnableFeignClients;

import java.lang.annotation.*;

/**
 * @Author lgy
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableFeignClients
public @interface EnableRyFeignClients {
    /**
     * value.
     * @date: 2020/12/7
     */
    String[] value() default {};
    /**
     * basePackages. 扫描存在@FeginClients的类，自动装配时使用
     * @date: 2020/12/7
     */
    String[] basePackages() default {"com.ligy"};
    /**
     * basePackageClasses.
     * @date: 2020/12/7
     */
    Class<?>[] basePackageClasses() default {};
    /**
     * defaultConfiguration.
     * @date: 2020/12/7
     */
    Class<?>[] defaultConfiguration() default {};
    /**
     * clients.
     * @date: 2020/12/7
     */
    Class<?>[] clients() default {};
}
