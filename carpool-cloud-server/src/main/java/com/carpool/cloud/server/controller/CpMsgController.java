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
import com.carpool.cloud.server.ai.BAiResp;
import com.carpool.cloud.server.dal.entity.CpMsg;
import com.carpool.cloud.server.service.CpMsgService;
import com.chao.cloud.common.entity.Response;
import com.chao.cloud.common.entity.ResponseResult;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;

/**
 * @功能： @author： David @时间：2019-06-28
 * 
 * @version 1.0.0
 */
@RequestMapping("/cp/msg")
@RestController
@Validated
public class CpMsgController {

	@Autowired
	private CpMsgService cpMsgService;

	@RequestMapping("/list")
	public Response<IPage<CpMsg>> list(Page<CpMsg> page //
	) { // 分页
		LambdaQueryWrapper<CpMsg> queryWrapper = Wrappers.lambdaQuery();
		return ResponseResult.getResponseResult(cpMsgService.page(page, queryWrapper));
	}

	@RequestMapping("/list/search")
	public Response<IPage<CpMsg>> list(Page<CpMsg> page, CpMsg cpMsg) { // 分页
		LambdaQueryWrapper<CpMsg> queryWrapper = Wrappers.lambdaQuery();
		queryWrapper.like(CpMsg::getContent, cpMsg.getContent());
		IPage<CpMsg> searchInfo = cpMsgService.page(page, queryWrapper);
		return ResponseResult.getResponseResult(searchInfo);
	}

	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public Response<String> save(CpMsg cpMsg) {
		BAiResp resp = cpMsgService.addContent(cpMsg.getContent());
		// 发布的类型||车找人||人找车
		cpMsg.setType(resp.getType());
		// 出发时间
		cpMsg.setTime(resp.getTime());
		// 行程总路线
		cpMsg.setWay(resp.getWay());
		// 起始地点
		cpMsg.setStartAddr(resp.getStart());
		// 终点
		cpMsg.setEndAddr(resp.getEnd());
		// 联系人电话
		cpMsg.setMobile(resp.getMobile());
		// 发布时间
		cpMsg.setCreateTime(new DateTime());
		// 状态 1：已发布 2：已过期
		cpMsg.setStatus(1);
		boolean result = cpMsgService.save(cpMsg);
		return result ? ResponseResult.ok() : ResponseResult.error();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public Response<String> update(CpMsg cpMsg) {
		boolean result = cpMsgService.updateById(cpMsg);
		return result ? ResponseResult.ok() : ResponseResult.error();
	}

	/**
	 * 删除
	 */
	@RequestMapping("/remove")
	public Response<String> remove(@NotNull(message = "id 不能为空") Long id) {
		boolean result = cpMsgService.removeById(id);
		return result ? ResponseResult.ok() : ResponseResult.error();
	}

	/**
	 * 批量删除
	 */
	@RequestMapping("/batchRemove")
	public Response<String> batchRemove(
			@NotNull(message = "不能为空") @Size(min = 1, message = "请至少选择一个") @RequestParam("ids[]") Long[] ids) {
		boolean result = cpMsgService.removeByIds(CollUtil.toList(ids));
		return result ? ResponseResult.ok() : ResponseResult.error();
	}

}