package com.zrma.cloud.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.zrma.cloud")
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.zrma.cloud")
public class NacosFeignApplication {

	public static void main(String[] args) {
        SpringApplication.run(NacosFeignApplication.class, args);
    }

}
