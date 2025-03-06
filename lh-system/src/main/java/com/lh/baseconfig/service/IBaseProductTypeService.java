package com.lh.baseconfig.service;

import java.util.List;
import com.lh.baseconfig.domain.BaseProductType;

/**
 * 产品类型Service接口
 *
 * @author lh
 * @date 2022-10-29
 */
public interface IBaseProductTypeService
{
    /**
     * 查询产品类型
     *
     * @param typeId 产品类型主键
     * @return 产品类型
     */
    public BaseProductType selectBaseProductTypeByTypeId(Long typeId);

    /**
     * 查询产品类型列表
     *
     * @param baseProductType 产品类型
     * @return 产品类型集合
     */
    public List<BaseProductType> selectBaseProductTypeList(BaseProductType baseProductType);

    /**
     * 新增产品类型
     *
     * @param baseProductType 产品类型
     * @return 结果
     */
    public int insertBaseProductType(BaseProductType baseProductType);

    /**
     * 修改产品类型
     *
     * @param baseProductType 产品类型
     * @return 结果
     */
    public int updateBaseProductType(BaseProductType baseProductType);

    /**
     * 批量删除产品类型
     *
     * @param typeIds 需要删除的产品类型主键集合
     * @return 结果
     */
    public int deleteBaseProductTypeByTypeIds(Long[] typeIds);

    /**
     * 删除产品类型信息
     *
     * @param typeId 产品类型主键
     * @return 结果
     */
    public int deleteBaseProductTypeByTypeId(Long typeId);
}
