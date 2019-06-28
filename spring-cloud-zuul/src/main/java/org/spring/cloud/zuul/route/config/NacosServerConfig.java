package org.spring.cloud.zuul.route.config;

import java.util.Properties;

import org.apache.catalina.Executor;
import org.spring.cloud.zuul.route.NewZuulRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

@Configuration
public class NacosServerConfig {
	
	@Value("${spring.cloud.nacos.config.server-addr:127.0.0.1:8848}")
	private String server_addr;
	
    @Value("${spring.cloud.nacos.config.group: DEFAULT_GROUP}")
	private String group;
    
    @Value("${spring.cloud.nacos.config.service}")
	private String service;
	
    @Value("${spring.cloud.nacos.config.namespace}")
	private String namespace;
    
    @Value("${spring.cloud.nacos.config.encode: utf-8}")
    private String encode;
	
	@Autowired
    NewZuulRouteLocator routeLocator;
	
    @Autowired
    ApplicationEventPublisher publisher;

	@Bean
    public ConfigService configService(){
        Properties properties = new Properties();
		properties.put(PropertyKeyConst.SERVER_ADDR, server_addr);
		properties.put(PropertyKeyConst.NAMESPACE, namespace);
		properties.put(PropertyKeyConst.ENCODE, encode);
        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            configService.addListener(service, group, new Listener() {
                @Override
                public Executor getExecutor() {
                    //可以发送监听消息到某个MQ
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("Nacos更新了！");
                    //切忌！！！不需要自己去刷新
                    RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
                    publisher.publishEvent(routesRefreshedEvent);
                }
            });

            return configService;
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return null;
    }
}
