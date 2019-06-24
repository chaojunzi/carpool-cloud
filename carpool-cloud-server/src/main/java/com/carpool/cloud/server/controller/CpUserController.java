package com.carpool.cloud.server.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carpool.cloud.server.dal.entity.CpUser;
import com.carpool.cloud.server.domain.dto.UserDTO;
import com.carpool.cloud.server.domain.vo.UserVO;
import com.carpool.cloud.server.service.CpUserService;
import com.chao.cloud.common.entity.Response;
import com.chao.cloud.common.entity.ResponseResult;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

/**
 * @功能：
 * @author： 超君子
 * @时间：2019-06-24
 * @version 1.0.0
 */
@RequestMapping("/cp/user")
@RestController
@Validated
public class CpUserController {

	@Autowired
	private CpUserService cpUserService;

	@RequestMapping("/list")
	public Response<IPage<CpUser>> list(Page<CpUser> page, //
			UserVO vo) { // 分页
		LambdaQueryWrapper<CpUser> queryWrapper = Wrappers.lambdaQuery();
		if (StrUtil.isNotBlank(vo.getOpenId())) {
			queryWrapper.like(CpUser::getOpenId, vo.getOpenId());
		}
		if (StrUtil.isNotBlank(vo.getMobile())) {
			queryWrapper.like(CpUser::getMobile, vo.getMobile());
		}
		return ResponseResult.getResponseResult(cpUserService.page(page, queryWrapper));
	}

	@RequestMapping("/info")
	public Response<UserDTO> info(@NotNull(message = "id 不能为空") Integer id) {
		return ResponseResult.getResponseResult(BeanUtil.toBean(cpUserService.getById(id), UserDTO.class));
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public Response<String> save(CpUser cpUser) {
		boolean result = cpUserService.save(cpUser);
		return result ? ResponseResult.ok() : ResponseResult.error();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public Response<String> update(CpUser cpUser) {
		boolean result = cpUserService.updateById(cpUser);
		return result ? ResponseResult.ok() : ResponseResult.error();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/remove")
	public Response<String> remove(@NotNull(message = "id 不能为空") Integer id) {
		boolean result = cpUserService.removeById(id);
		return result ? ResponseResult.ok() : ResponseResult.error();
	}

	/**
	 * 批量删除
	 */
	@RequestMapping("/batchRemove")
	public Response<String> batchRemove(
			@NotNull(message = "不能为空") @Size(min = 1, message = "请至少选择一个") @RequestParam("ids[]") Integer[] ids) {
		boolean result = cpUserService.removeByIds(CollUtil.toList(ids));
		return result ? ResponseResult.ok() : ResponseResult.error();
	}

}