package com.lh.workorder.service;

import com.lh.workorder.domain.WorkOrder;

import java.util.List;

public interface WorkOrderService {

     void addWorkOrder(WorkOrder workOrder);

    int updateWorkOrder(WorkOrder workOrder);

    List<WorkOrder> getWorkOrderRecord();

    WorkOrder getWorkOrderById(Long id);

    /**
     * 查看自己的工单
     */
    List<WorkOrder>  getMyWorkOrder(Long userId);

    /**
     * 获取派单记录
     */
    List<WorkOrder> getdispatchOrderRecord(Long serviceStaff);

    WorkOrder getWorkOrder(String orderNumber);

    /**
     * 客服获取所有的工单
     */
    List<WorkOrder> getAllWorkOrder();

    /**
     * 获取客户咨询的工单
     */
    List<WorkOrder> getCustomerWorkOrder(Long userId);

    /**
     * 查看自己的工单
     */
    List<WorkOrder> getMyOrder(Long userId);

    /**
     * 查看自己的派单
     */
    List<WorkOrder> getAlldispatchOrder(String status);

    /**
     * 查看自己的派单
     */
    List<WorkOrder> getMyDispatchOrder(Long userId,String status);

    /**
     * 获取用户订单数量
     */
    int getOrderNumberByUserId(Long userId);

    List<WorkOrder> getWorkOrderByParms(WorkOrder workOrder);

    List<WorkOrder> getdispatchWorderOrderByParms(WorkOrder workOrder);

    /**
     * 根据状态获取工单
     */
    List<WorkOrder> getMyOrderByStatus(String status);

    /**
     *
     */
    List<WorkOrder> getMyWorkOrderByStatus(Long userId,Long sysId,String status);



}
