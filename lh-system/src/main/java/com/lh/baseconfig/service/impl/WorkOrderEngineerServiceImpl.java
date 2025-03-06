package com.lh.baseconfig.service.impl;

import java.util.List;
import com.lh.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lh.baseconfig.mapper.WorkOrderEngineerMapper;
import com.lh.baseconfig.domain.WorkOrderEngineer;
import com.lh.baseconfig.service.IWorkOrderEngineerService;

/**
 * 工程师处理工单情况Service业务层处理
 *
 * @author lh
 * @date 2022-12-08
 */
@Service
public class WorkOrderEngineerServiceImpl implements IWorkOrderEngineerService
{
    @Autowired
    private WorkOrderEngineerMapper workOrderEngineerMapper;

    /**
     * 查询工程师处理工单情况
     *
     * @param id 工程师处理工单情况主键
     * @return 工程师处理工单情况
     */
    @Override
    public WorkOrderEngineer selectWorkOrderEngineerById(String id)
    {
        return workOrderEngineerMapper.selectWorkOrderEngineerById(id);
    }

    /**
     * 查询工程师处理工单情况列表
     *
     * @param workOrderEngineer 工程师处理工单情况
     * @return 工程师处理工单情况
     */
    @Override
    public List<WorkOrderEngineer> selectWorkOrderEngineerList(WorkOrderEngineer workOrderEngineer)
    {
        return workOrderEngineerMapper.selectWorkOrderEngineerList(workOrderEngineer);
    }

    /**
     * 新增工程师处理工单情况
     *
     * @param workOrderEngineer 工程师处理工单情况
     * @return 结果
     */
    @Override
    public int insertWorkOrderEngineer(WorkOrderEngineer workOrderEngineer)
    {
        workOrderEngineer.setCreateTime(DateUtils.getNowDate());
        return workOrderEngineerMapper.insertWorkOrderEngineer(workOrderEngineer);
    }

    /**
     * 修改工程师处理工单情况
     *
     * @param workOrderEngineer 工程师处理工单情况
     * @return 结果
     */
    @Override
    public int updateWorkOrderEngineer(WorkOrderEngineer workOrderEngineer)
    {
        return workOrderEngineerMapper.updateWorkOrderEngineer(workOrderEngineer);
    }

    /**
     * 批量删除工程师处理工单情况
     *
     * @param ids 需要删除的工程师处理工单情况主键
     * @return 结果
     */
    @Override
    public int deleteWorkOrderEngineerByIds(String[] ids)
    {
        return workOrderEngineerMapper.deleteWorkOrderEngineerByIds(ids);
    }

    /**
     * 删除工程师处理工单情况信息
     *
     * @param id 工程师处理工单情况主键
     * @return 结果
     */
    @Override
    public int deleteWorkOrderEngineerById(String id)
    {
        return workOrderEngineerMapper.deleteWorkOrderEngineerById(id);
    }
}
