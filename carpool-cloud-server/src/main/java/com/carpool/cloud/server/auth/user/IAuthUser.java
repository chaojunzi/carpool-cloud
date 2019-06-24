package com.carpool.cloud.server.auth.user;

import java.io.Serializable;

/**
 * 用户接口
 * 
 * @author 薛超 功能： 时间：2018年8月30日
 * @version 1.0
 */
public interface IAuthUser extends Serializable {

	String USER_LOGIN_TOKEN = "CARPOOL_USER_LOGIN_TOKEN:";
	String REQUEST_PARAM_TOKEN = "token";

	/**
	 * id
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * : 1正常2冻结      
	 * 
	 * @return
	 */
	Integer getStatus();

	/**
	 * 用户类型
	 * 1.买 
	 * 2.卖
	 * 0.游客
	 * @return
	 */
	Integer getUserType();

	/**
	 * openId
	 * 
	 * @return
	 */
	String getOpenId();

	/**
	 * 服务号全局id
	 */
	String getUnionId();

	/**
	 * 用户名
	 * 
	 * @return
	 */
	String getUserName();

	/**
	 * 手机
	 * 
	 * @return
	 */
	String getMobile();

	/**
	 * 头像
	 * 
	 * @return
	 */
	String getHeadImg();

	/**
	 * 登录凭证
	 * 
	 * @return
	 */
	String getToken();
}
