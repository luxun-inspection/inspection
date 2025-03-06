package com.lh.baseconfig.service;

import java.util.List;
import com.lh.baseconfig.domain.BaseFeedback;

/**
 * 用户反馈Service接口
 *
 * @author lh
 * @date 2022-10-28
 */
public interface IBaseFeedbackService
{
    /**
     * 查询用户反馈
     *
     * @param id 用户反馈主键
     * @return 用户反馈
     */
    public BaseFeedback selectBaseFeedbackById(String id);

    /**
     * 查询用户反馈列表
     *
     * @param baseFeedback 用户反馈
     * @return 用户反馈集合
     */
    public List<BaseFeedback> selectBaseFeedbackList(BaseFeedback baseFeedback);

    /**
     * 新增用户反馈
     *
     * @param baseFeedback 用户反馈
     * @return 结果
     */
    public int insertBaseFeedback(BaseFeedback baseFeedback);

    /**
     * 修改用户反馈
     *
     * @param baseFeedback 用户反馈
     * @return 结果
     */
    public int updateBaseFeedback(BaseFeedback baseFeedback);

    /**
     * 批量删除用户反馈
     *
     * @param ids 需要删除的用户反馈主键集合
     * @return 结果
     */
    public int deleteBaseFeedbackByIds(String[] ids);

    /**
     * 删除用户反馈信息
     *
     * @param id 用户反馈主键
     * @return 结果
     */
    public int deleteBaseFeedbackById(String id);
}
