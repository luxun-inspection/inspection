package com.lh.baseconfig.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ItemDetail extends BaseEntity {
    private static final long serialVersionUID = 8828275199278018068L;

    private Long id;

    private String title;

    private String pic;

    private String content;

    private Long parentId;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    private String createBy;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private String updateBy;
}
