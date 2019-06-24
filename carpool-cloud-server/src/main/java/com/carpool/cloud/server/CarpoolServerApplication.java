package com.carpool.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import com.chao.cloud.common.config.exception.EnableGlobalException;
import com.chao.cloud.common.config.web.EnableWeb;
import com.chao.cloud.common.extra.mybatis.annotation.EnableMybatisPlus;
import com.chao.cloud.common.extra.wx.annotation.EnableWxMaSingleton;

/**
 * 
 * @功能：拼车小程序
 * @author： 薛超
 * @时间： 2019年6月24日
 * @version 1.0.0
 */
@SpringBootApplication
@EnableCaching // 缓存
@EnableWeb // web
@EnableGlobalException // 全局异常处理
@EnableMybatisPlus // 数据库插件-乐观锁
@EnableWxMaSingleton //微信小程序
public class CarpoolServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarpoolServerApplication.class, args);
	}

}
