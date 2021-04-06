package com.ricemarch.cms.pms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author ricemarch
 * @since 2021-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysReqLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 请求来源 order
     */
    private String source;

    /**
     * companyId
     */
    private Long companyId;

    /**
     * institution_id
     */
    private Long institutionId;

    /**
     * cell_id
     */
    private Long cellId;

    /**
     * openId
     */
    private String openId;

    /**
     * user_id
     */
    private Long userId;

    /**
     * username
     */
    private String username;

    /**
     * 用户token
     */
    private String token;

    /**
     * 请求url
     */
    @TableField("reqUrl")
    private String requrl;

    /**
     * 请求uri
     */
    @TableField("reqUri")
    private String requri;

    /**
     * 接口耗时ms
     */
    private Long time;

    /**
     * 请求方式post get
     */
    private String method;

    /**
     * 请求参数
     */
    @TableField("reqParams")
    private String reqparams;

    /**
     * 返回参数
     */
    @TableField("resParams")
    private String resparams;

    /**
     * 创建时间
     */
    @TableField("createTime")
    private LocalDateTime createtime;

    /**
     * requestId -> phone
     */
    @TableField("requestId")
    private String requestid;

    public SysReqLog() {
    }

    public SysReqLog(String requrl, String requri, Long time, String method, LocalDateTime createtime) {
        this.requrl = requrl;
        this.requri = requri;
        this.time = time;
        this.method = method;
        this.createtime = createtime;
    }
}
