package com.carpool.cloud.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carpool.cloud.server.ai.AipNlpService;
import com.carpool.cloud.server.ai.BAiResp;
import com.carpool.cloud.server.dal.entity.CpMsg;
import com.carpool.cloud.server.dal.mapper.CpMsgMapper;
import com.carpool.cloud.server.service.CpMsgService;

/**
 * @功能： @author： 超君子 @时间：2019-06-28
 * 
 * @version 1.0.0
 */
@Service
public class CpMsgServiceImpl extends ServiceImpl<CpMsgMapper, CpMsg> implements CpMsgService {

	@Autowired
	private AipNlpService aipNlpService;

	@Override
	public BAiResp addContent(String content) {
		BAiResp resp = aipNlpService.lexerContent(content);
		return resp;
	}

}
