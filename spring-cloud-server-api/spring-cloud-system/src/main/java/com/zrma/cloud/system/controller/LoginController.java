package com.zrma.cloud.system.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zrma.cloud.common.core.entity.Audience;
import com.zrma.cloud.common.core.utils.IpUtil;
import com.zrma.cloud.common.core.utils.JwtHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {
	
	@Value("${admin.username: admin}")
	private String username;
	
	@Value("${admin.password: admin}")
	private String password;
	
	@Autowired
	private Audience audience;

	/**
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/hello")
    public String hello(@RequestParam("name") String name, HttpServletRequest request) {
		log.info("system server-----------hello: name {}", name);
        return "hello:" + name + "-------------ip: " + IpUtil.getLocalIpByNetcard();
    }
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
    public String lgoin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
		log.info("system server-----------login: username {}, password {}", username, password);
		if (this.username.equals(username) && this.password.equals(password)) {
			return JwtHelper.createJWT(username, audience.getName(), username, UUID.randomUUID().toString(), audience.getClientId(),
					audience.getName(), audience.getExpiresSecond(), audience.getAdminBase64Secret()) + "-------ip:" + IpUtil.getLocalIpByNetcard();
		}
		return IpUtil.getLocalIpByNetcard();
    }
}
