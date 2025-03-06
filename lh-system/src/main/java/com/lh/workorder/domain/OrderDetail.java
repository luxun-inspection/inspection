package com.lh.workorder.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.baseconfig.domain.UserInfo;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDetail extends BaseEntity {

    private Long id;

    /**
     * 订单Id
     */
    private Long orderId;

    /**
     * 内容
     */
    private String content;

    /**
     * 状态(是否可读1.已读2.未读)
     */
    private String status;

    /**
     * userId
     */
    private Long userId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private UserInfo userInfo;
}
