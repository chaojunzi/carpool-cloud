package com.carpool.cloud.server.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baidu.aip.nlp.AipNlp;
import com.carpool.cloud.server.ai.AipNlpService;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties("chao.cloud.im.bai")
public class BAiConfig {

	private String appId;
	private String apiKey;
	private String secretKey; // 唯一标识

	@Bean
	public AipNlpService AipUnit(BAiConfig config) {
		AipNlp aipNlp = new AipNlp(config.appId, config.apiKey, config.secretKey);
		AipNlpService aipUnit = new AipNlpService();
		aipUnit.setAipNlp(aipNlp);
		return aipUnit;
	}

}