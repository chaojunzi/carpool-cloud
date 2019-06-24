package com.carpool.cloud.server.auth.annotation;

import com.chao.cloud.common.exception.BusinessException;

/**
 * 
 * @功能：用户状态
 * @author： 薛超
 * @时间： 2019年6月24日
 * @version 1.0.0
 */
public enum UserStatusEnum {

	/**
	 * 冻结
	 */
	FREEZE(0),
	/**
	 *  正常
	 */
	PASS(1);

	public Integer status;

	UserStatusEnum(Integer status) {
		this.status = status;
	}

	public static UserStatusEnum getByStatus(Integer status) {
		for (UserStatusEnum typeEnum : UserStatusEnum.values()) {
			if (typeEnum.status.equals(status)) {
				return typeEnum;
			}
		}
		throw new BusinessException("无效的用户状态");
	}
}