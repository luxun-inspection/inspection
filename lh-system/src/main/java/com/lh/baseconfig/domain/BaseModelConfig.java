package com.lh.baseconfig.domain;

import com.lh.common.annotation.Excel;
import com.lh.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 暖通模型对象 base_model_config
 *
 * @author lh
 * @date 2022-11-08
 */
public class BaseModelConfig extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 模型名称 */
    @Excel(name = "模型名称")
    private String modelName;

    /** 图片名称 */
    @Excel(name = "图片名称")
    private String imgUrl;

    /** 是否展示 */
    @Excel(name = "是否展示")
    private String showFlag;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sortNo;

    private String desc1;
    private String desc2;

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public String getDesc2() {
        return desc2;
    }

    public void setDesc2(String desc2) {
        this.desc2 = desc2;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setModelName(String modelName)
    {
        this.modelName = modelName;
    }

    public String getModelName()
    {
        return modelName;
    }
    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }
    public void setShowFlag(String showFlag)
    {
        this.showFlag = showFlag;
    }

    public String getShowFlag()
    {
        return showFlag;
    }
    public void setSortNo(Integer sortNo)
    {
        this.sortNo = sortNo;
    }

    public Integer getSortNo()
    {
        return sortNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("modelName", getModelName())
            .append("imgUrl", getImgUrl())
            .append("showFlag", getShowFlag())
            .append("sortNo", getSortNo())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
