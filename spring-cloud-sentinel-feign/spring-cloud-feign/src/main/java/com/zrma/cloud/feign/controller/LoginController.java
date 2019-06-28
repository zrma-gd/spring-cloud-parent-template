package com.zrma.cloud.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zrma.cloud.common.feign.system.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	/**
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
		log.info("feign--hello: name {}", name);
        return loginService.hello(name);
    }
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
    public String lgoin(@RequestParam("username") String username, @RequestParam("password") String password) {
		log.info("feign--login: username {}, password", username, password);
		return loginService.lgoin(username, password);
    }
}
