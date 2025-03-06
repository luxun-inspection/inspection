package com.lh.baseconfig.domain;

import java.math.BigDecimal;
import com.lh.common.annotation.Excel;
import com.lh.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 产品对象 base_product_info
 *
 * @author lh
 * @date 2022-10-28
 */
public class BaseProductInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 产品类型 */
    @Excel(name = "产品类型")
    private Long typeId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品分类 */
    @Excel(name = "产品分类")
    private String typeName;

    /** 产品图片 */
    @Excel(name = "产品图片")
    private String imgUrl;

    /** 产品描述 */
    @Excel(name = "产品描述")
    private String detail;

    /** 是否展示Y是N否 */
    @Excel(name = "是否展示Y是N否")
    private String showFlag;

    /** 价格 */
    @Excel(name = "价格")
    private BigDecimal price;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId()
    {
        return typeId;
    }
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getProductName()
    {
        return productName;
    }
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }
    public void setImgUrl(String imgUrl)
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl()
    {
        return imgUrl;
    }
    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    public String getDetail()
    {
        return detail;
    }
    public void setShowFlag(String showFlag)
    {
        this.showFlag = showFlag;
    }

    public String getShowFlag()
    {
        return showFlag;
    }
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("typeId", getTypeId())
                .append("productName", getProductName())
                .append("typeName", getTypeName())
                .append("imgUrl", getImgUrl())
                .append("detail", getDetail())
                .append("showFlag", getShowFlag())
                .append("remark", getRemark())
                .append("price", getPrice())
                .append("createTime", getCreateTime())
                .append("createBy", getCreateBy())
                .append("updateTime", getUpdateTime())
                .append("updateBy", getUpdateBy())
                .toString();
    }
}
