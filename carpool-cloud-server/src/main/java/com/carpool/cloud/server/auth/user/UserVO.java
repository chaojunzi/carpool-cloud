package com.carpool.cloud.server.auth.user;

import lombok.Data;

/**
 * 用户参数
 * @功能：
 * @author： 薛超
 * @时间：2019年4月11日
 * @version 1.0.0
 */
@Data
public class UserVO implements IUserVO {
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 用户类型
	 */
	private Integer userType;

}
