package com.lh.baseconfig.service.impl;

import java.util.List;

import com.lh.baseconfig.domain.BaseProductType;
import com.lh.baseconfig.mapper.BaseProductTypeMapper;
import com.lh.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lh.baseconfig.mapper.BaseProductInfoMapper;
import com.lh.baseconfig.domain.BaseProductInfo;
import com.lh.baseconfig.service.IBaseProductInfoService;

/**
 * 产品Service业务层处理
 *
 * @author lh
 * @date 2022-10-28
 */
@Service
public class BaseProductInfoServiceImpl implements IBaseProductInfoService
{
    @Autowired
    private BaseProductInfoMapper baseProductInfoMapper;

    @Autowired
    private BaseProductTypeMapper baseProductTypeMapper;
    /**
     * 查询产品
     *
     * @param id 产品主键
     * @return 产品
     */
    @Override
    public BaseProductInfo selectBaseProductInfoById(Long id)
    {
        return baseProductInfoMapper.selectBaseProductInfoById(id);
    }

    /**
     * 查询产品列表
     *
     * @param baseProductInfo 产品
     * @return 产品
     */
    @Override
    public List<BaseProductInfo> selectBaseProductInfoList(BaseProductInfo baseProductInfo)
    {
        return baseProductInfoMapper.selectBaseProductInfoList(baseProductInfo);
    }

    /**
     * 新增产品
     *
     * @param baseProductInfo 产品
     * @return 结果
     */
    @Override
    public int insertBaseProductInfo(BaseProductInfo baseProductInfo)
    {
        BaseProductType type= baseProductTypeMapper.selectBaseProductTypeByTypeId(baseProductInfo.getTypeId());
        if(type!=null){
            baseProductInfo.setTypeName(type.getTypeName());
        }
        baseProductInfo.setCreateTime(DateUtils.getNowDate());
        return baseProductInfoMapper.insertBaseProductInfo(baseProductInfo);
    }

    /**
     * 修改产品
     *
     * @param baseProductInfo 产品
     * @return 结果
     */
    @Override
    public int updateBaseProductInfo(BaseProductInfo baseProductInfo)
    {
        BaseProductType type= baseProductTypeMapper.selectBaseProductTypeByTypeId(baseProductInfo.getTypeId());
        if(type!=null){
            baseProductInfo.setTypeName(type.getTypeName());
        }
        baseProductInfo.setUpdateTime(DateUtils.getNowDate());
        return baseProductInfoMapper.updateBaseProductInfo(baseProductInfo);
    }

    /**
     * 批量删除产品
     *
     * @param ids 需要删除的产品主键
     * @return 结果
     */
    @Override
    public int deleteBaseProductInfoByIds(Long[] ids)
    {
        return baseProductInfoMapper.deleteBaseProductInfoByIds(ids);
    }

    /**
     * 删除产品信息
     *
     * @param id 产品主键
     * @return 结果
     */
    @Override
    public int deleteBaseProductInfoById(Long id)
    {
        return baseProductInfoMapper.deleteBaseProductInfoById(id);
    }
}
