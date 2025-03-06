package com.lh.baseconfig.service.impl;

import java.util.List;
import com.lh.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lh.baseconfig.mapper.BaseConsultArticleMapper;
import com.lh.baseconfig.domain.BaseConsultArticle;
import com.lh.baseconfig.service.IBaseConsultArticleService;

/**
 * 咨询介绍Service业务层处理
 *
 * @author lh
 * @date 2022-10-28
 */
@Service
public class BaseConsultArticleServiceImpl implements IBaseConsultArticleService
{
    @Autowired
    private BaseConsultArticleMapper baseConsultArticleMapper;

    /**
     * 查询咨询介绍
     *
     * @param id 咨询介绍主键
     * @return 咨询介绍
     */
    @Override
    public BaseConsultArticle selectBaseConsultArticleById(Long id)
    {
        return baseConsultArticleMapper.selectBaseConsultArticleById(id);
    }

    /**
     * 查询咨询介绍列表
     *
     * @param baseConsultArticle 咨询介绍
     * @return 咨询介绍
     */
    @Override
    public List<BaseConsultArticle> selectBaseConsultArticleList(BaseConsultArticle baseConsultArticle)
    {
        return baseConsultArticleMapper.selectBaseConsultArticleList(baseConsultArticle);
    }

    /**
     * 新增咨询介绍
     *
     * @param baseConsultArticle 咨询介绍
     * @return 结果
     */
    @Override
    public int insertBaseConsultArticle(BaseConsultArticle baseConsultArticle)
    {
        baseConsultArticle.setCreateTime(DateUtils.getNowDate());
        return baseConsultArticleMapper.insertBaseConsultArticle(baseConsultArticle);
    }

    /**
     * 修改咨询介绍
     *
     * @param baseConsultArticle 咨询介绍
     * @return 结果
     */
    @Override
    public int updateBaseConsultArticle(BaseConsultArticle baseConsultArticle)
    {
        baseConsultArticle.setUpdateTime(DateUtils.getNowDate());
        return baseConsultArticleMapper.updateBaseConsultArticle(baseConsultArticle);
    }

    /**
     * 批量删除咨询介绍
     *
     * @param ids 需要删除的咨询介绍主键
     * @return 结果
     */
    @Override
    public int deleteBaseConsultArticleByIds(Long[] ids)
    {
        return baseConsultArticleMapper.deleteBaseConsultArticleByIds(ids);
    }

    /**
     * 删除咨询介绍信息
     *
     * @param id 咨询介绍主键
     * @return 结果
     */
    @Override
    public int deleteBaseConsultArticleById(Long id)
    {
        return baseConsultArticleMapper.deleteBaseConsultArticleById(id);
    }
}
