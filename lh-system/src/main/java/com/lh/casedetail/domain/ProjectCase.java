package com.lh.casedetail.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectCase extends BaseEntity {
    private static final long serialVersionUID = 2201339983265758840L;

    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 子标题
     */
    private String subTitle;

    /**
     * 图片
     */
    private String pic;

    /**
     * 案例内容
     */
    private String content;

    /**
     * 排序
     */
    private int sort;

    /**
     * 状态
     */
    private String status;

    private String ids;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    private String createBy;

    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private String updateBy;

    private int readNum;

    private String orderBy;

}
