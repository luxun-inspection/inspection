package com.lh.baseconfig.mapper;

import java.util.List;
import com.lh.baseconfig.domain.BaseProductInfo;

/**
 * 产品Mapper接口
 *
 * @author lh
 * @date 2022-10-28
 */
public interface BaseProductInfoMapper
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
     * 删除产品
     *
     * @param id 产品主键
     * @return 结果
     */
    public int deleteBaseProductInfoById(Long id);

    /**
     * 批量删除产品
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBaseProductInfoByIds(Long[] ids);
}
