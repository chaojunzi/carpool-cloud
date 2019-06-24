package com.carpool.cloud.server.dal.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import org.springframework.format.annotation.DateTimeFormat;
import cn.hutool.core.date.DatePattern;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @功能：
 * @author： 超君子
 * @时间：2019-06-24
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CpUser implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * unionId
     */
    private String unionId;

    /**
     * open_id
     */
    private String openId;

    /**
     * 真实名字
     */
    private String realName;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 用户状态0.冻结1.正常
     */
    private Boolean status;

    /**
     * 逻辑删除  1 删除
     */
    @TableLogic
    private Integer deleted;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private Date createTime;


}
