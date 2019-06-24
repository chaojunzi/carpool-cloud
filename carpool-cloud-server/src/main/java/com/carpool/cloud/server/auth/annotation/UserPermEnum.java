package com.carpool.cloud.server.auth.annotation;

import java.math.BigInteger;

import com.chao.cloud.common.util.RightsUtil;

/**
 * 用户权限
 * 
 * @author 薛超 功能： 时间：2019年3月21日
 * @version 1.0
 */
public enum UserPermEnum {
	/**
	 * 冻结用户-（不可访问）
	 */
	PERM_F(-1),
	/**
	 * 游客 （必须进行转型-只有查看功能）
	 */
	PERM_V(0),
	/**
	 * 乘客
	 */
	PERM_P(1),
	/**
	 * 车主
	 */
	PERM_C(2),
	/**
	 * 管理员
	 */
	PERM_A(3);

	public Integer perm;

	UserPermEnum(Integer perm) {
		this.perm = perm;
	}

	/**
	 * 全部属性
	 */
	private final static UserPermEnum[] All = UserPermEnum.values();

	public Integer getPerm() {
		return perm;
	}

	/**
	 * 获取组合的编码
	 * 
	 * @return
	 */
	public static BigInteger getSumRights() {
		UserPermEnum[] values = All;
		return getSumRights(values);
	}

	/**
	 * 获取指定组合的编码
	 * 
	 * @return
	 */
	public static BigInteger getSumRights(UserPermEnum[] values) {
		int[] sum_right = new int[values.length];
		for (int i = 0; i < values.length; i++) {
			UserPermEnum oplogEnum = values[i];
			sum_right[i] = oplogEnum.getPerm();
		}
		return RightsUtil.sumRights(sum_right);
	}

	/**
	 * 校验权限
	 * 
	 * @param type
	 * @param status
	 * @return
	 */
	public static UserPermEnum getByTypeAndStatus(Integer type, Integer status) {
		UserStatusEnum userStatus = UserStatusEnum.getByStatus(status);
		if (userStatus == UserStatusEnum.FREEZE) {
			return PERM_F;// 已被冻结
		}
		// 用户类型
		UserTypeEnum userType = UserTypeEnum.getByType(type);
		switch (userType) {
		case VISITOR:// 游客
			return PERM_V;
		case PASSENGER://乘客
			return PERM_P;
		case CAROWNERS:// 车主
			return PERM_C;
		case ADMIN:// 管理员
			return PERM_A;
		default:
			break;
		}
		return null;
	}
}