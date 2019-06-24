package com.carpool.cloud.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carpool.cloud.server.auth.proxy.DefaultPermProxy;
import com.carpool.cloud.server.auth.user.AuthUserResolver;

@Configuration
public class UserPermConfig {

	/**
	 * 用户权限管理
	 * 
	 * @return
	 */
	@Bean
	public AuthUserResolver authUserResolver() {
		return new AuthUserResolver();
	}

	/** 
	 * 权限
	 */
	@Bean
	public DefaultPermProxy defaultPermProxy() {
		return new DefaultPermProxy();
	}

}
