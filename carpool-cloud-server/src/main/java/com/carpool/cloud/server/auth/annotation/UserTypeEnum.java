package com.carpool.cloud.server.auth.annotation;

import com.chao.cloud.common.exception.BusinessException;

/**
 * 用户类型
 * 
 * @author xuechao
 *
 */
public enum UserTypeEnum {
	PASSENGER(1), // 乘客
	CAROWNERS(2), // 车主
	ADMIN(3), // 管理员
	VISITOR(0); // 游客
	Integer type;

	UserTypeEnum(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	/**
	 * int 值获取用户类型
	 * @param type
	 * @return
	 */
	public static UserTypeEnum getByType(Integer type) {
		for (UserTypeEnum typeEnum : UserTypeEnum.values()) {
			if (typeEnum.type.equals(type)) {
				return typeEnum;
			}
		}
		throw new BusinessException("错误的用户类型:" + type);
	}

}