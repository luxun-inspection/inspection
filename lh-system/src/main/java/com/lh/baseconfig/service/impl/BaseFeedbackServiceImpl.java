package com.lh.baseconfig.service.impl;

import java.util.List;
import com.lh.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lh.baseconfig.mapper.BaseFeedbackMapper;
import com.lh.baseconfig.domain.BaseFeedback;
import com.lh.baseconfig.service.IBaseFeedbackService;

/**
 * 用户反馈Service业务层处理
 *
 * @author lh
 * @date 2022-10-28
 */
@Service
public class BaseFeedbackServiceImpl implements IBaseFeedbackService
{
    @Autowired
    private BaseFeedbackMapper baseFeedbackMapper;

    /**
     * 查询用户反馈
     *
     * @param id 用户反馈主键
     * @return 用户反馈
     */
    @Override
    public BaseFeedback selectBaseFeedbackById(String id)
    {
        return baseFeedbackMapper.selectBaseFeedbackById(id);
    }

    /**
     * 查询用户反馈列表
     *
     * @param baseFeedback 用户反馈
     * @return 用户反馈
     */
    @Override
    public List<BaseFeedback> selectBaseFeedbackList(BaseFeedback baseFeedback)
    {
        return baseFeedbackMapper.selectBaseFeedbackList(baseFeedback);
    }

    /**
     * 新增用户反馈
     *
     * @param baseFeedback 用户反馈
     * @return 结果
     */
    @Override
    public int insertBaseFeedback(BaseFeedback baseFeedback)
    {
        baseFeedback.setCreateTime(DateUtils.getNowDate());
        return baseFeedbackMapper.insertBaseFeedback(baseFeedback);
    }

    /**
     * 修改用户反馈
     *
     * @param baseFeedback 用户反馈
     * @return 结果
     */
    @Override
    public int updateBaseFeedback(BaseFeedback baseFeedback)
    {
        return baseFeedbackMapper.updateBaseFeedback(baseFeedback);
    }

    /**
     * 批量删除用户反馈
     *
     * @param ids 需要删除的用户反馈主键
     * @return 结果
     */
    @Override
    public int deleteBaseFeedbackByIds(String[] ids)
    {
        return baseFeedbackMapper.deleteBaseFeedbackByIds(ids);
    }

    /**
     * 删除用户反馈信息
     *
     * @param id 用户反馈主键
     * @return 结果
     */
    @Override
    public int deleteBaseFeedbackById(String id)
    {
        return baseFeedbackMapper.deleteBaseFeedbackById(id);
    }
}
