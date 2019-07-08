package com.carpool.cloud.server.ai;

/**
 * 
 * @功能： @author： 薛超 @时间： 2019年7月3日
 * 
 * @version 1.0.0
 */
public interface AiConstant {

	/**
	 * 腾讯响应成功返回码
	 */
	Integer TAI_SUCCESS = 0;
	/**
	 * 错误返回值
	 */
	String ERROR_RESULT = "抱歉，解析失败";
	/**
	 * 时间
	 */
	String NLP_NE_TIME = "TIME";
	/**
	 * 地址
	 */
	String NLP_NE_LOC = "LOC";
	/**
	 * 车找人
	 */
	String FIND_PEOPLE_REGEX = "(车找人|找人|车寻人|寻人)";
	/**
	 * 人找车
	 */
	String FIND_CAR_REGEX = "(人找车|找车|人寻车|寻车)";
	/**
	 * 展示的目标类型
	 */
	String FIND_PEOPLE = "车找人";
	String FIND_CAR = "人找车";
}