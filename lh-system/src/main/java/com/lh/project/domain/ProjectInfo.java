package com.lh.project.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectInfo extends BaseEntity  {

    private static final long serialVersionUID =2201339983265758840L;

    private Long id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目简介
     */
    private String introduction;

    /**
     * 项目封面
     */
    private String projectPic;

    /**
     * 是否删除
     */
    private String isDel;

    /**
     * 是否显示
     */
    private String isShow;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String endTime;




}
