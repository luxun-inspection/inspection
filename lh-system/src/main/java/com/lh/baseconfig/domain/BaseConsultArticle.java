package com.lh.baseconfig.domain;

import com.lh.common.annotation.Excel;
import com.lh.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 咨询介绍对象 base_consult_article
 *
 * @author lh
 * @date 2022-10-28
 */
public class BaseConsultArticle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 标题1系统设计2系统故障3系统节能4智能系统5建立模型6管线综合7施工方案8运维模型 */
    @Excel(name = "标题1系统设计2系统故障3系统节能4智能系统5建立模型6管线综合7施工方案8运维模型")
    private String title;

    /** 封面图片 */
    @Excel(name = "封面图片")
    private String coverImg;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setCoverImg(String coverImg)
    {
        this.coverImg = coverImg;
    }

    public String getCoverImg()
    {
        return coverImg;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("coverImg", getCoverImg())
            .append("content", getContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
