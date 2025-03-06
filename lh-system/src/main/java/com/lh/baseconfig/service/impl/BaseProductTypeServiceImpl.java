package com.lh.baseconfig.service.impl;

import java.util.List;

import com.lh.common.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lh.baseconfig.mapper.BaseProductTypeMapper;
import com.lh.baseconfig.domain.BaseProductType;
import com.lh.baseconfig.service.IBaseProductTypeService;

/**
 * 产品类型Service业务层处理
 *
 * @author lh
 * @date 2022-10-29
 */
@Service
public class BaseProductTypeServiceImpl implements IBaseProductTypeService
{
    @Autowired
    private BaseProductTypeMapper baseProductTypeMapper;

    /**
     * 查询产品类型
     *
     * @param typeId 产品类型主键
     * @return 产品类型
     */
    @Override
    public BaseProductType selectBaseProductTypeByTypeId(Long typeId)
    {
        return baseProductTypeMapper.selectBaseProductTypeByTypeId(typeId);
    }

    /**
     * 查询产品类型列表
     *
     * @param baseProductType 产品类型
     * @return 产品类型
     */
    @Override
    public List<BaseProductType> selectBaseProductTypeList(BaseProductType baseProductType)
    {
        return baseProductTypeMapper.selectBaseProductTypeList(baseProductType);
    }

    /**
     * 新增产品类型
     *
     * @param baseProductType 产品类型
     * @return 结果
     */
    @Override
    public int insertBaseProductType(BaseProductType baseProductType)
    {
        String category=DictUtils.getDictLabel("base_product_type",baseProductType.getCategoryType());
        baseProductType.setCategoryName(category);
        return baseProductTypeMapper.insertBaseProductType(baseProductType);
    }

    /**
     * 修改产品类型
     *
     * @param baseProductType 产品类型
     * @return 结果
     */
    @Override
    public int updateBaseProductType(BaseProductType baseProductType)
    {
        String category=DictUtils.getDictLabel("base_product_type",baseProductType.getCategoryType());
        baseProductType.setCategoryName(category);
        return baseProductTypeMapper.updateBaseProductType(baseProductType);
    }

    /**
     * 批量删除产品类型
     *
     * @param typeIds 需要删除的产品类型主键
     * @return 结果
     */
    @Override
    public int deleteBaseProductTypeByTypeIds(Long[] typeIds)
    {
        return baseProductTypeMapper.deleteBaseProductTypeByTypeIds(typeIds);
    }

    /**
     * 删除产品类型信息
     *
     * @param typeId 产品类型主键
     * @return 结果
     */
    @Override
    public int deleteBaseProductTypeByTypeId(Long typeId)
    {
        return baseProductTypeMapper.deleteBaseProductTypeByTypeId(typeId);
    }
}
