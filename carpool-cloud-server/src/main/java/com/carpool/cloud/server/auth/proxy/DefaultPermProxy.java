package com.carpool.cloud.server.auth.proxy;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import com.carpool.cloud.server.auth.annotation.Permission;
import com.carpool.cloud.server.auth.annotation.UserPermEnum;
import com.carpool.cloud.server.auth.user.IAuthUser;
import com.carpool.cloud.server.auth.user.IUserVO;
import com.chao.cloud.common.base.BaseProxy;
import com.chao.cloud.common.base.BaseWirter;
import com.chao.cloud.common.constants.ResultCodeEnum;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 权限拦截
 * 
 * @author xuecaho
 *
 */
@Aspect
@Slf4j
public class DefaultPermProxy implements BaseProxy, BaseWirter {

	private String retMsg = "权限不足";
	private String freezeRetMsg = "冻结";
	private String retCode = ResultCodeEnum.CODE_403.code();

	/**
	 * 操作拦截
	 * 
	 * @param pdj
	 * @return
	 * @throws Throwable 
	 */
	@Around("@annotation(com.carpool.cloud.server.auth.annotation.Permission)")
	public Object around(ProceedingJoinPoint pdj) throws Throwable {
		Object obj = null;
		// 权限校验
		boolean check = checkPerm(pdj);
		if (check) {
			obj = pdj.proceed();
		}
		return obj;
	}

	/**
	 * 权限校验
	 * 
	 * @param pdj
	 * @return
	 */
	private boolean checkPerm(ProceedingJoinPoint pdj) {
		try {
			Signature signature = pdj.getSignature();
			MethodSignature methodSignature = (MethodSignature) signature;
			Method method = methodSignature.getMethod();
			Permission permission = method.getAnnotation(Permission.class);
			Object[] args = pdj.getArgs();
			if (ObjectUtil.isNotNull(permission)) {
				retMsg = permission.retMsg();
				retCode = permission.retCode().code();
				// 获取当前用户
				IAuthUser user = this.getParamFirst(IAuthUser.class, args);
				log.info("[当前用户：]" + user);
				UserPermEnum[] perm = permission.hasPerm();
				// 该方法具有的权限
				BigInteger rights = UserPermEnum.getSumRights(perm);
				// 当前用户的权限
				Integer userType = user.getUserType();
				UserPermEnum user_rights = UserPermEnum.getByTypeAndStatus(userType, user.getStatus());
				log.info("[interface:perm={},val={}]", Arrays.toString(perm), rights);
				log.info("[user:perm={},val={}]", user_rights, user_rights.getPerm());
				// 判断用户是否被冻结
				if (user_rights == UserPermEnum.PERM_F) {// 冻结状态
					this.wirteJsonObject(ResultCodeEnum.CODE_400.code(), this.freezeRetMsg);
					return false;
				}
				// 校验
				if (rights.testBit(user_rights.getPerm())) {
					// 匹配参数
					IUserVO vo = this.getParamFirst(IUserVO.class, args);
					if (ObjectUtil.isNotNull(vo)) {
						vo.setUserId(user.getId());
						// 转为字节值
						vo.setUserType(userType);
					}
					return true;
				}
			} else {
				return true;
			}
		} catch (Exception e) {
			log.error("权限校验异常：{}", e);
		}
		this.wirteJsonObject(retCode, retMsg);
		return false;
	}

}
