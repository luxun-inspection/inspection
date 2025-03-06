package com.lh.baseconfig.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.annotation.Excel;
import com.lh.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 工程师处理工单情况对象 work_order_engineer
 *
 * @author lh
 * @date 2022-12-08
 */
public class WorkOrderEngineer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private String id;

    /** 工单ID */
    @Excel(name = "工单ID")
    private Long orderId;

    /** 工程师ID */
    @Excel(name = "工程师ID")
    private Long userId;

    /** 工程师 */
    @Excel(name = "工程师")
    private String userName;

    /** 工程师描述问题 */
    @Excel(name = "工程师描述问题")
    private String orderDescribe;

    /** 处理图片 */
    @Excel(name = "处理图片")
    private String imageUrl;

    /** 处理状态 1 待处理 2已处理 */
    @Excel(name = "处理状态 1 待处理 2已处理")
    private String status;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date dealTime;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public Long getOrderId()
    {
        return orderId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
    public void setOrderDescribe(String orderDescribe)
    {
        this.orderDescribe = orderDescribe;
    }

    public String getOrderDescribe()
    {
        return orderDescribe;
    }
    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setDealTime(Date dealTime)
    {
        this.dealTime = dealTime;
    }

    public Date getDealTime()
    {
        return dealTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("orderDescribe", getOrderDescribe())
            .append("imageUrl", getImageUrl())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("dealTime", getDealTime())
            .toString();
    }
}
