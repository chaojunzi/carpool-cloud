package com.carpool.cloud.server.domain.dto;

import lombok.Data;

/**
 * @功能：
 * @author： 超君子
 * @时间：2019-06-24
 * @version 1.0.0
 */
@Data
public class UserDTO {

	/**
	 * ID
	 */
	private Integer id;

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
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 昵称
	 */
	private String userName;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 头像
	 */
	private String headImg;

	/**
	 * 用户状态0.冻结1.正常
	 */
	private Boolean status;

}
