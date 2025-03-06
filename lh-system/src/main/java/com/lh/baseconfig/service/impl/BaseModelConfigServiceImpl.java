package com.lh.baseconfig.service.impl;

import java.util.List;
import com.lh.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lh.baseconfig.mapper.BaseModelConfigMapper;
import com.lh.baseconfig.domain.BaseModelConfig;
import com.lh.baseconfig.service.IBaseModelConfigService;

/**
 * 暖通模型Service业务层处理
 *
 * @author lh
 * @date 2022-11-08
 */
@Service
public class BaseModelConfigServiceImpl implements IBaseModelConfigService
{
    @Autowired
    private BaseModelConfigMapper baseModelConfigMapper;

    /**
     * 查询暖通模型
     *
     * @param id 暖通模型主键
     * @return 暖通模型
     */
    @Override
    public BaseModelConfig selectBaseModelConfigById(Long id)
    {
        return baseModelConfigMapper.selectBaseModelConfigById(id);
    }

    /**
     * 查询暖通模型列表
     *
     * @param baseModelConfig 暖通模型
     * @return 暖通模型
     */
    @Override
    public List<BaseModelConfig> selectBaseModelConfigList(BaseModelConfig baseModelConfig)
    {
        return baseModelConfigMapper.selectBaseModelConfigList(baseModelConfig);
    }

    /**
     * 新增暖通模型
     *
     * @param baseModelConfig 暖通模型
     * @return 结果
     */
    @Override
    public int insertBaseModelConfig(BaseModelConfig baseModelConfig)
    {
        baseModelConfig.setCreateTime(DateUtils.getNowDate());
        return baseModelConfigMapper.insertBaseModelConfig(baseModelConfig);
    }

    /**
     * 修改暖通模型
     *
     * @param baseModelConfig 暖通模型
     * @return 结果
     */
    @Override
    public int updateBaseModelConfig(BaseModelConfig baseModelConfig)
    {
        return baseModelConfigMapper.updateBaseModelConfig(baseModelConfig);
    }

    /**
     * 批量删除暖通模型
     *
     * @param ids 需要删除的暖通模型主键
     * @return 结果
     */
    @Override
    public int deleteBaseModelConfigByIds(Long[] ids)
    {
        return baseModelConfigMapper.deleteBaseModelConfigByIds(ids);
    }

    /**
     * 删除暖通模型信息
     *
     * @param id 暖通模型主键
     * @return 结果
     */
    @Override
    public int deleteBaseModelConfigById(Long id)
    {
        return baseModelConfigMapper.deleteBaseModelConfigById(id);
    }
}
