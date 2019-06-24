package com.carpool.cloud.server.domain.vo;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 
 * @功能：微信授权参数
 * @author： 薛超
 * @时间： 2019年6月24日
 * @version 1.0.0
 */
@Data
public class UserAuthVO {

	@NotBlank(message = "数据不能为空")
	private String encryptedData;

	@NotBlank(message = "加密向量不能为空")
	private String iv;

	@NotBlank(message = "code不能为空")
	private String code;

}
