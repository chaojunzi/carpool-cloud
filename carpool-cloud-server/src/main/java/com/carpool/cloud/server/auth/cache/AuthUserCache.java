package com.carpool.cloud.server.auth.cache;

import com.carpool.cloud.server.auth.user.AuthUserDTO;
import com.carpool.cloud.server.auth.user.IAuthUser;
import com.chao.cloud.common.exception.BusinessException;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;

public interface AuthUserCache {

	long DELAY = 1000 * 10 * 60;// 10分钟
	TimedCache<String, AuthUserDTO> CACHE_USER = CacheUtil.newTimedCache(2 * 3600 * 1000);// 两小时

	/**
	 * 获取当前在线用户
	 * @param token
	 * @return
	 */
	static AuthUserDTO getCurrUser(String token) {
		AuthUserDTO dto = CACHE_USER.get(IAuthUser.USER_LOGIN_TOKEN + token);
		if (BeanUtil.isEmpty(dto)) {
			throw new BusinessException("请进行登录");
		}
		return dto;
	}

	/**
	 * 获取当前在线用户
	 * @param token
	 * @return
	 */
	static AuthUserDTO getCurrUserByOpenId(String openId) {
		return getCurrUser(DigestUtil.md5Hex(openId));
	}

	static void cache(AuthUserDTO user) {
		// 加密
		String token = DigestUtil.md5Hex(user.getOpenId());
		user.setToken(token);
		CACHE_USER.put(IAuthUser.USER_LOGIN_TOKEN + token, user);
	}

	static void clear(String token) {
		CACHE_USER.remove(token);
	}

	static void init() {
		CACHE_USER.schedulePrune(DELAY);
	}
}
