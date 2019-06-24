package com.carpool.cloud.server.domain.vo;

import lombok.Data;

/**
 * @功能：
 * @author： 超君子
 * @时间：2019-06-24
 * @version 1.0.0
 */
@Data
public class UserVO {

	/**
	 * unionId
	 */
	private String unionId;

	/**
	 * open_id
	 */
	private String openId;

	/**
	 * 真实名字
	 */
	private String realName;

	/**
	 * 昵称
	 */
	private String userName;

	/**
	 * 手机号
	 */
	private String mobile;

}
