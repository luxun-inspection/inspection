package com.lh.baseconfig.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfo extends BaseEntity {
    private static final long serialVersionUID = -2294449225354835139L;

    private Long id;

    /**
     * 关联的后台用户
     */
    private Long userId;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * openId
     */
    private  String openId;

    /**
     * 微信头像
     */
    private String avatar;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 电话
     */
    private String phone;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private String sessionKey;

    private String encryptedData;

    private String iv;


    /**
     * 工程师名字()
     */
    private String engineerName;

    /**
     * 查询用户的工单数量
     */
    int orderNumber;

    private String userName;
    private String address;
    private String authStatus;

    private int hotAmount;

    private String authType;

}
