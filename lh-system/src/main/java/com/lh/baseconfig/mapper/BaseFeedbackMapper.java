package com.lh.baseconfig.mapper;

import java.util.List;
import com.lh.baseconfig.domain.BaseFeedback;

/**
 * 用户反馈Mapper接口
 *
 * @author lh
 * @date 2022-10-28
 */
public interface BaseFeedbackMapper
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
     * 删除用户反馈
     *
     * @param id 用户反馈主键
     * @return 结果
     */
    public int deleteBaseFeedbackById(String id);

    /**
     * 批量删除用户反馈
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBaseFeedbackByIds(String[] ids);
}
