package org.spring.cloud.zuul.route.config;

import org.spring.cloud.zuul.route.NewZuulRouteLocator;
import org.spring.cloud.zuul.route.listener.ZuulRefreshListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NewZuulConfig {

	@Autowired
	private ZuulProperties zuulProperties;
	
	@Autowired
	private DispatcherServletPath dispatcherServletPath;
	
	@Bean
	public NewZuulRouteLocator routeLocator() {
		NewZuulRouteLocator routeLocator = new NewZuulRouteLocator(
				this.dispatcherServletPath.getPrefix(), this.zuulProperties);
		return routeLocator;
	}
	
	@Bean
    public ApplicationListener<ApplicationEvent> zuulRefreshRoutesListener() {
        return new ZuulRefreshListener();
    }
}
