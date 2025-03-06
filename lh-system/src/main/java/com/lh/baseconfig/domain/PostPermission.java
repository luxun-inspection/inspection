package com.lh.baseconfig.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lh.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class PostPermission extends BaseEntity {
    private static final long serialVersionUID = 1146622071024396142L;

    private Long id;

    private Long postId;

    private Long permissionId;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;

    private String createBy;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date updateTime;

    private String updateBy;
}
