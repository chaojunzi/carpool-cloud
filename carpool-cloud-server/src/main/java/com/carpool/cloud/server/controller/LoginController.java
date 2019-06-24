package com.carpool.cloud.server.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.carpool.cloud.server.auth.annotation.UserTypeEnum;
import com.carpool.cloud.server.auth.cache.AuthUserCache;
import com.carpool.cloud.server.auth.user.AuthUserDTO;
import com.carpool.cloud.server.auth.user.IAuthUser;
import com.carpool.cloud.server.dal.entity.CpUser;
import com.carpool.cloud.server.domain.vo.UserAuthVO;
import com.carpool.cloud.server.service.CpUserService;
import com.chao.cloud.common.entity.Response;
import com.chao.cloud.common.entity.ResponseResult;
import com.chao.cloud.common.exception.BusinessException;
import com.chao.cloud.common.extra.wx.WxAppUserInfoApi;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @功能：微信授权登录
 * @author： 薛超
 * @时间： 2019年6月24日
 * @version 1.0.0
 */
@RestController
@Validated
@Slf4j
public class LoginController extends BaseController {

	@Autowired
	private CpUserService cpUserService;

	@Autowired
	private WxAppUserInfoApi wxAppUserInfoApi;

	/**
	 * code 登录
	 * @param code
	 * @return
	 */
	@RequestMapping("/code")
	public Response<IAuthUser> login(@NotBlank(message = "不能为空") String code) {
		// 获取openId
		WxMaJscode2SessionResult info = wxAppUserInfoApi.getSessionInfo(code);
		log.info("[登录用户:info={}]", JSONUtil.toJsonPrettyStr(info));
		LambdaQueryWrapper<CpUser> queryWrapper = Wrappers.<CpUser>lambdaQuery().eq(CpUser::getOpenId,
				info.getOpenid());
		AuthUserDTO dto = BeanUtil.toBean(cpUserService.getOne(queryWrapper), AuthUserDTO.class);
		// 存入缓存
		AuthUserCache.cache(dto);
		return ResponseResult.getResponseResult(dto);
	}

	/**
	 * 微信授权登录
	 */
	@RequestMapping("/auth")
	public Response<IAuthUser> auth(@Valid UserAuthVO vo, @NotNull Integer userType) {
		UserTypeEnum.getByType(userType);
		// 获取对应的api
		WxMaUserInfo userInfo = wxAppUserInfoApi.getUserInfo(vo.getEncryptedData(), vo.getIv(), vo.getCode());
		log.info("[授权用户:userInfo={}]", JSONUtil.toJsonPrettyStr(userInfo));
		// 新增用户
		CpUser cpUser = new CpUser();
		cpUser.setUnionId(userInfo.getUnionId());
		cpUser.setOpenId(userInfo.getOpenId());
		cpUser.setHeadImg(userInfo.getAvatarUrl());
		cpUser.setUserName(userInfo.getNickName());
		cpUser.setUserType(userType);
		boolean save = cpUserService.save(cpUser);
		if (!save) {
			throw new BusinessException("授权失败");
		}
		// 存入缓存
		AuthUserDTO dto = BeanUtil.toBean(cpUser, AuthUserDTO.class);
		// 存入缓存
		AuthUserCache.cache(dto);
		return ResponseResult.getResponseResult(dto);
	}

}