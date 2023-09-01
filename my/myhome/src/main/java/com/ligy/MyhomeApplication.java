package com.ligy;

import com.ligy.annotation.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @Author lgy
 */
@SpringCloudApplication
@EnableDiscoveryClient
@EnableRyFeignClients
public class MyhomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyhomeApplication.class, args);
    }

}