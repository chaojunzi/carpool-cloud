package com.carpool.cloud.server.ai;

import org.springframework.beans.factory.InitializingBean;

import com.carpool.cloud.server.config.TAiConfig;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import cn.xsshome.taip.nlp.TAipNlp;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @功能：自然语言分析 @author： 薛超 @时间： 2019年7月3日
 * @version 1.0.0
 */
@Slf4j
public class TAiNlpService implements InitializingBean {

	private TAiConfig config;
	private TAipNlp client;

	public void setConfig(TAiConfig config) {
		this.config = config;
	}

	public String text(String text) {
		try {
			String result = client.nlpTextchat(config.getSession(), text);// 基础闲聊
			// 解析
			TAiResp tAiResp = JSONUtil.toBean(result, TAiResp.class);
			if (AiConstant.TAI_SUCCESS.equals(tAiResp.getRet())) {
				return tAiResp.getData().getAnswer();
			}
			log.info("[腾讯闲聊解析失败={}]", result);
		} catch (Exception e) {
			log.error("{}", e);
		}
		return AiConstant.ERROR_RESULT;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(config);
		client = new TAipNlp(config.getAppId(), config.getAppKey());
	}

	public static void main(String[] args) throws Exception {
		String text = "车找人：今晚6：10 软件园二期（近二小东门），后厂村路，亮甲店-永澄南路，北清路，六环，涿州北，国富，东兴北街，"//
				+ "范阳路沿线，终点甲秀路南口 联系电话：18531290330";

		TAipNlp client = new TAipNlp("2118014633", "LirwLm1HMBGXDThB");
		String json = client.nlpWordner(text);
		Console.log(JSONUtil.toJsonPrettyStr(json));
	}

}