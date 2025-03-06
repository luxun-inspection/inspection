package com.lh.baseconfig.service;

import java.util.List;
import com.lh.baseconfig.domain.BaseModelConfig;

/**
 * 暖通模型Service接口
 *
 * @author lh
 * @date 2022-11-08
 */
public interface IBaseModelConfigService
{
    /**
     * 查询暖通模型
     *
     * @param id 暖通模型主键
     * @return 暖通模型
     */
    public BaseModelConfig selectBaseModelConfigById(Long id);

    /**
     * 查询暖通模型列表
     *
     * @param baseModelConfig 暖通模型
     * @return 暖通模型集合
     */
    public List<BaseModelConfig> selectBaseModelConfigList(BaseModelConfig baseModelConfig);

    /**
     * 新增暖通模型
     *
     * @param baseModelConfig 暖通模型
     * @return 结果
     */
    public int insertBaseModelConfig(BaseModelConfig baseModelConfig);

    /**
     * 修改暖通模型
     *
     * @param baseModelConfig 暖通模型
     * @return 结果
     */
    public int updateBaseModelConfig(BaseModelConfig baseModelConfig);

    /**
     * 批量删除暖通模型
     *
     * @param ids 需要删除的暖通模型主键集合
     * @return 结果
     */
    public int deleteBaseModelConfigByIds(Long[] ids);

    /**
     * 删除暖通模型信息
     *
     * @param id 暖通模型主键
     * @return 结果
     */
    public int deleteBaseModelConfigById(Long id);
}
