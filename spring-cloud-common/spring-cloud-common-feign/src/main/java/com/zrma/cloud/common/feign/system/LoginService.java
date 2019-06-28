package com.zrma.cloud.common.feign.system;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value ="admin-nacos-server")
public interface LoginService {

	/**
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value ="/hello",method= RequestMethod.GET)
    String hello(@RequestParam("name") String name);
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("/login")
    public String lgoin(@RequestParam("username") String username, @RequestParam("password") String password);
}
