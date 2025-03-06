package com.lh.baseconfig.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class HomePage extends BaseEntity {
    private static final long serialVersionUID = 2148907808293174926L;

    private Long id;

    private String homeKey;

    private String homeValue;

    private String homeType;

    private String title;

    private String pic;

    private String content;

    private String status;

    private int sort;

    private String ids;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

}
