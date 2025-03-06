package com.lh.baseconfig.domain;

import com.lh.common.annotation.Excel;
import com.lh.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 产品类型对象 base_product_type
 *
 * @author lh
 * @date 2022-10-29
 */
public class BaseProductType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类型ID */
    private Long typeId;

    /** 类型名称 */
    @Excel(name = "类型名称")
    private String typeName;

    /** 软件类型 */
    @Excel(name = "软件类型")
    private String categoryType;

    /** 软件类型名称 */
    @Excel(name = "软件类型名称")
    private String categoryName;

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId()
    {
        return typeId;
    }
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }
    public void setCategoryType(String categoryType)
    {
        this.categoryType = categoryType;
    }

    public String getCategoryType()
    {
        return categoryType;
    }
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("typeId", getTypeId())
            .append("typeName", getTypeName())
            .append("categoryType", getCategoryType())
            .append("categoryName", getCategoryName())
            .toString();
    }
}
