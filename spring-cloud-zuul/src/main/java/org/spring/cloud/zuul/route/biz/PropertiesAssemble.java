package org.spring.cloud.zuul.route.biz;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.spring.cloud.zuul.route.entity.ZuulRouteEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

@Component
public class PropertiesAssemble{
	
    @Value("${spring.cloud.nacos.config.service}")
	private String service;
	
    @Value("${spring.cloud.nacos.config.group: DEFAULT_GROUP}")
	private String group;
    
    @Value("${spring.cloud.nacos.config.timeout: 5000}")
    private Long timeout;
    
    @Autowired
    private ConfigService configService;

	

	public Map<String, ZuulRoute> getProperties() {
		Map<String, ZuulRoute> routes = new LinkedHashMap<>();
		List<ZuulRouteEntity> results = listenerNacos(service, group);
		for (ZuulRouteEntity result : results) {
			if (StringUtils.isBlank(result.getPath())
					/*|| org.apache.commons.lang3.StringUtils.isBlank(result.getUrl())*/) {
				continue;
			}
			ZuulRoute zuulRoute = new ZuulRoute();
			try {
				BeanUtils.copyProperties(result, zuulRoute);
			} catch (Exception e) {
			}
			routes.put(zuulRoute.getPath(), zuulRoute);
		}
		return routes;
	}

	private List<ZuulRouteEntity> listenerNacos (String dataId, String group) {
		try {
			String content = configService.getConfig(dataId, group, timeout);
			System.out.println("从Nacos返回的配置：" + content);
			return JSONObject.parseArray(content, ZuulRouteEntity.class);
		} catch (NacosException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}