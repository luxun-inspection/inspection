package com.lh.baseconfig.mapper;

import java.util.List;
import com.lh.baseconfig.domain.WorkOrderEngineer;

/**
 * 工程师处理工单情况Mapper接口
 *
 * @author lh
 * @date 2022-12-08
 */
public interface WorkOrderEngineerMapper
{
    /**
     * 查询工程师处理工单情况
     *
     * @param id 工程师处理工单情况主键
     * @return 工程师处理工单情况
     */
    public WorkOrderEngineer selectWorkOrderEngineerById(String id);

    /**
     * 查询工程师处理工单情况列表
     *
     * @param workOrderEngineer 工程师处理工单情况
     * @return 工程师处理工单情况集合
     */
    public List<WorkOrderEngineer> selectWorkOrderEngineerList(WorkOrderEngineer workOrderEngineer);

    /**
     * 新增工程师处理工单情况
     *
     * @param workOrderEngineer 工程师处理工单情况
     * @return 结果
     */
    public int insertWorkOrderEngineer(WorkOrderEngineer workOrderEngineer);

    /**
     * 修改工程师处理工单情况
     *
     * @param workOrderEngineer 工程师处理工单情况
     * @return 结果
     */
    public int updateWorkOrderEngineer(WorkOrderEngineer workOrderEngineer);

    /**
     * 删除工程师处理工单情况
     *
     * @param id 工程师处理工单情况主键
     * @return 结果
     */
    public int deleteWorkOrderEngineerById(String id);

    /**
     * 批量删除工程师处理工单情况
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWorkOrderEngineerByIds(String[] ids);
}
