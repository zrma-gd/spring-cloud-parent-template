package com.zrma.cloud.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.zrma.cloud")
public class SystemApplication {

	public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
	
}
