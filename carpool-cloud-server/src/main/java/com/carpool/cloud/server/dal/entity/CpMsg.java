package com.carpool.cloud.server.dal.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;

import cn.hutool.core.date.DatePattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @功能： @author： 超君子 @时间：2019-06-28
 * 
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CpMsg implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 消息内容
	 */
	private String content;

	/**
	 * 消息类型（1:车寻人，车找人，找人2：人找车，人寻车
	 */
	private String type;

	/**
	 * 消息状态（1:已发布2：已撤销）
	 */
	private Integer status;

	@DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
	private Date createTime;

	/**
	 * 发布人
	 */
	private Integer creator;

	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
	private Date modifyTime;

	/**
	 * 版本号
	 */
	@Version
	private Integer version;

	/**
	 * 排序
	 */
	private String sort;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 时间
	 */
	private String time;

	/**
	 * 路线
	 */
	private String way;

	/**
	 * 开始地址
	 */
	private String startAddr;

	/**
	 * 结束地址
	 */
	private String endAddr;

	/**
	 * 电话
	 */
	private String mobile;

}
