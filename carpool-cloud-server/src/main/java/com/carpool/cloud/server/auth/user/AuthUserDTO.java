package com.carpool.cloud.server.auth.user;

import lombok.Data;

/**
 * 用户接口
 * @功能：
 * @author： 薛超
 * @时间：2019年3月21日
 * @version 1.0.0
 */
@Data
public class AuthUserDTO implements IAuthUser {

    private Integer id;
    private String openId;
    private String unionId;
    private String userName;
    private Integer status;
    private String mobile;
    private String headImg;
    private String token; 
    private Integer userType;
	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer getStatus() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer getUserType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getOpenId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUnionId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getMobile() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getHeadImg() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    

}
