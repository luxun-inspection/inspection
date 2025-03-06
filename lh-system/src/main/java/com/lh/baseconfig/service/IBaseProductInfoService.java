package com.lh.baseconfig.service;

import java.util.List;
import com.lh.baseconfig.domain.BaseProductInfo;

/**
 * 产品Service接口
 *
 * @author lh
 * @date 2022-10-28
 */
public interface IBaseProductInfoService
{
    /**
     * 查询产品
     *
     * @param id 产品主键
     * @return 产品
     */
    public BaseProductInfo selectBaseProductInfoById(Long id);

    /**
     * 查询产品列表
     *
     * @param baseProductInfo 产品
     * @return 产品集合
     */
    public List<BaseProductInfo> selectBaseProductInfoList(BaseProductInfo baseProductInfo);

    /**
     * 新增产品
     *
     * @param baseProductInfo 产品
     * @return 结果
     */
    public int insertBaseProductInfo(BaseProductInfo baseProductInfo);

    /**
     * 修改产品
     *
     * @param baseProductInfo 产品
     * @return 结果
     */
    public int updateBaseProductInfo(BaseProductInfo baseProductInfo);

    /**
     * 批量删除产品
     *
     * @param ids 需要删除的产品主键集合
     * @return 结果
     */
    public int deleteBaseProductInfoByIds(Long[] ids);

    /**
     * 删除产品信息
     *
     * @param id 产品主键
     * @return 结果
     */
    public int deleteBaseProductInfoById(Long id);
}
