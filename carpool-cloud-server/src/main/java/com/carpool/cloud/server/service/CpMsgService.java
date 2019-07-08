package com.carpool.cloud.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.carpool.cloud.server.ai.BAiResp;
import com.carpool.cloud.server.dal.entity.CpMsg;

/**
 * @功能： @author： 超君子 @时间：2019-06-28
 * 
 * @version 1.0.0
 */
public interface CpMsgService extends IService<CpMsg> {

	BAiResp addContent(String content);

}
