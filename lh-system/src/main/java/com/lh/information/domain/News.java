package com.lh.information.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class News extends BaseEntity {
    private static final long serialVersionUID = -3789927884194426791L;
    private Long id;

    private String title;

    private String content;

    private String pic;

    private String status;

    private String ids;

    private int sort;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    private String createBy;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private String updateBy;

    private int readNum;

    private String orderBy;
}
