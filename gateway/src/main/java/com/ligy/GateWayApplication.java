package com.ligy;

import com.ligy.annotation.EnableRyFeignClients;
import com.ligy.handler.ExceptionHandlingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @Author lgy
 */
@SpringCloudApplication
@EnableDiscoveryClient
@EnableRyFeignClients
@EnableWebFlux
public class GateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}