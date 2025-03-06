package com.lh.baseconfig.mapper;

import java.util.List;
import com.lh.baseconfig.domain.BaseConsultArticle;

/**
 * 咨询介绍Mapper接口
 *
 * @author lh
 * @date 2022-10-28
 */
public interface BaseConsultArticleMapper
{
    /**
     * 查询咨询介绍
     *
     * @param id 咨询介绍主键
     * @return 咨询介绍
     */
    public BaseConsultArticle selectBaseConsultArticleById(Long id);

    /**
     * 查询咨询介绍列表
     *
     * @param baseConsultArticle 咨询介绍
     * @return 咨询介绍集合
     */
    public List<BaseConsultArticle> selectBaseConsultArticleList(BaseConsultArticle baseConsultArticle);

    /**
     * 新增咨询介绍
     *
     * @param baseConsultArticle 咨询介绍
     * @return 结果
     */
    public int insertBaseConsultArticle(BaseConsultArticle baseConsultArticle);

    /**
     * 修改咨询介绍
     *
     * @param baseConsultArticle 咨询介绍
     * @return 结果
     */
    public int updateBaseConsultArticle(BaseConsultArticle baseConsultArticle);

    /**
     * 删除咨询介绍
     *
     * @param id 咨询介绍主键
     * @return 结果
     */
    public int deleteBaseConsultArticleById(Long id);

    /**
     * 批量删除咨询介绍
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBaseConsultArticleByIds(Long[] ids);
}
