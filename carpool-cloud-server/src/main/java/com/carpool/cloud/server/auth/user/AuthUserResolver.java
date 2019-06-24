package com.carpool.cloud.server.auth.user;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.carpool.cloud.server.auth.annotation.UserTypeEnum;
import com.carpool.cloud.server.auth.cache.AuthUserCache;
import com.chao.cloud.common.annotation.ArgumentAnnotation;
import com.chao.cloud.common.base.BaseConvert;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 解析数据
 * 
 * @author 薛超 功能： 时间：2018年8月30日
 * @version 1.0
 */
@ArgumentAnnotation
@Slf4j
public class AuthUserResolver implements HandlerMethodArgumentResolver, BaseConvert, InitializingBean {

	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(IAuthUser.class);
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// 获取登录用户
		String token = getToken();
		log.info("[登录凭证：token：--->{}]", token);
		if (StrUtil.isBlank(token)) {
			exit("无效的登录凭证");
			return null;
		}
		IAuthUser user = AuthUserCache.getCurrUser(token);
		log.info("[登录用户：AuthUser：--->{}]", user);
		if (!BeanUtil.isEmpty(user)) {
			Integer type = user.getUserType();
			// 校验id和type
			if (ObjectUtil.isNull(user.getId())) {
				exit("无效的用户Id");
				return null;
			} else if (StrUtil.isBlank(user.getOpenId())) {
				exit("无效的OpenId");
				return null;
			}
			try {
				UserTypeEnum.getByType(type);
			} catch (Exception e) {
				log.info("权限校验异常: {}", ExceptionUtil.getMessage(e));
				log.error("权限校验异常: {}", e);
				exit("无效用户类型[type=" + type + "]");
			}
			return user;
		} else {
			// 退出
			exit("请进行登录");
		}
		return null;
	}

	/**
	 * 登录凭证
	 * 
	 * @return
	 */
	private String getToken() {
		return getRequest().getParameter(IAuthUser.REQUEST_PARAM_TOKEN);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		AuthUserCache.init();

	}

}