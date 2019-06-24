package com.carpool.cloud.server.auth.user;

/**
 * 用户参数
 * @功能：
 * @author： 薛超
 * @时间：2019年4月11日
 * @version 1.0.0
 */
public interface IUserVO {
	/**
	 * 获取用户id
	 * @return
	 */
	Integer getUserId();

	/**
	 * 赋值用户id
	 * @param userId
	 */
	void setUserId(Integer userId);

	/**
	 * 获取用户类型
	 * @return
	 */
	public Integer getUserType();

	/**
	 * 赋值
	 * @param userType
	 */
	void setUserType(Integer userType);

}
