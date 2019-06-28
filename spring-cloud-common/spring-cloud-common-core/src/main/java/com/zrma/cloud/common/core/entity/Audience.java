package com.zrma.cloud.common.core.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "token.audience")
@Component
public class Audience {

	private String clientId;
	
	private String AdminBase64Secret;
	
	private String WebBase64Secret;
	
	private String name;
	
	private int expiresSecond;
}
