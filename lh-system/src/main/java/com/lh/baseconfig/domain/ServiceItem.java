package com.lh.baseconfig.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ServiceItem extends BaseEntity {
    private static final long serialVersionUID = 3105715200852280711L;

    private Long id;

    /**
     * 项目名称
     */
    private String itemName;

    /**
     * 英文名称
     */
    private String englishName;

    /**
     * 层级(1.最高级别)
     */
    private int level;

    /**
     * 上级ID
     */
    private Long parentId;

    /**
     * 项目照片
     */
    private String pic;

    private String createBy;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    private String updateBy;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private List<ServiceItem> serviceItemList;

    private ItemDetail itemDetail;

}
