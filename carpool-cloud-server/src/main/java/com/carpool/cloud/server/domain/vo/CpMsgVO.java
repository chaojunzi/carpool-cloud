package com.carpool.cloud.server.domain.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.Version;

import cn.hutool.core.date.DatePattern;
import lombok.Data;
@Data
public class CpMsgVO {


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
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

	 
    

}
