package com.carpool.cloud.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.carpool.cloud.server.ai.TAiNlpService;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("chao.cloud.im.tai")
public class TAiConfig {

	private String appId;
	private String appKey;
	private String session; // 唯一标识

	@Bean
	public TAiNlpService tAiNlpService(TAiConfig config) {
		TAiNlpService robotService = new TAiNlpService();
		robotService.setConfig(config);
		return robotService;
	}

}