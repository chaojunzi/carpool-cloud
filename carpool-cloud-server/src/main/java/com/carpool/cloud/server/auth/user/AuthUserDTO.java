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

}
