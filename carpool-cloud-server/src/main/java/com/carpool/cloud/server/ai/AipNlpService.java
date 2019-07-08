package com.carpool.cloud.server.ai;

import java.util.List;
import java.util.stream.Collectors;

import com.baidu.aip.nlp.AipNlp;
import com.carpool.cloud.server.ai.BAiResp.Item;
import com.chao.cloud.common.exception.BusinessException;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONUtil;

/**
 * https://ai.baidu.com/docs#/NLP-Basic-API/76c78712
 * 
 * @功能：百度：自然语言处理 @author： 薛超 @时间： 2019年7月5日
 * @version 1.0.0
 */
public class AipNlpService {

	private AipNlp aipNlp;

	public void setAipNlp(AipNlp aipNlp) {
		this.aipNlp = aipNlp;
	}

	/**
	 * 文本解析
	 * 
	 * @param content
	 */
	public BAiResp lexerContent(String content) {
		content = content.replace("：", ":")//
				.replace(".", ":")//
		;
		BAiResp resp = JSONUtil.toBean(aipNlp.lexerCustom(content, null).toString(), BAiResp.class);
		if (BeanUtil.isEmpty(resp) || CollUtil.isEmpty(resp.getItems())) {
			throw new BusinessException("AI无法解析");
		}
		List<Item> items = resp.getItems();
		// 解析时间
		String time = items.stream().filter(i -> AiConstant.NLP_NE_TIME.equals(i.getNe())).map(Item::getItem)
				.collect(Collectors.joining("->"));
		resp.setTime(time);
		// 解析地址
		List<String> ways = items.stream().filter(i -> AiConstant.NLP_NE_LOC.equals(i.getNe())).map(Item::getItem)
				.collect(Collectors.toList());
		resp.setWay(CollUtil.join(ways, "->"));
		resp.setStart(CollUtil.getFirst(ways));
		resp.setEnd(CollUtil.getLast(ways));
		// 获取手机号
		String mobile = ReUtil.findAll(PatternPool.MOBILE, content, 0).stream().collect(Collectors.joining(","));
		resp.setMobile(mobile);
		// 解析类型
		if (ReUtil.contains(AiConstant.FIND_PEOPLE_REGEX, content)) {// 车找人
			resp.setType(AiConstant.FIND_PEOPLE);
		} else if (ReUtil.contains(AiConstant.FIND_CAR_REGEX, content)) {// 人找车
			resp.setType(AiConstant.FIND_CAR);
		} else {
			throw new BusinessException("无法解析人找车还是车找人");
		}
		resp.setItems(null);
		return resp;
	};

	public static void main(String[] args) {
		String content = "人找车，周一早六点左右，玫瑰大街出发。到立水桥。电话15201284366";
		AipNlp aipNlp = new AipNlp("16725432", "xz02TND1B4a5cVwmWc3Y1EfT", "5nieFQo3g0LxVHOdk5ABcQfAx7826iqs");
		AipNlpService nlpService = new AipNlpService();
		nlpService.setAipNlp(aipNlp);
		BAiResp resp = nlpService.lexerContent(content);
		Console.log(JSONUtil.toJsonPrettyStr(resp));
	}

}