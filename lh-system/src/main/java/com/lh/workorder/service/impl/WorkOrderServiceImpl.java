package com.lh.workorder.service.impl;

import com.lh.workorder.domain.WorkOrder;
import com.lh.workorder.mapper.OrderDetailMapper;
import com.lh.workorder.mapper.WorkOrderMapper;
import com.lh.workorder.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {

    @Autowired
    private WorkOrderMapper workOrderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    @Transactional
    public void addWorkOrder(WorkOrder workOrder) {
        //新增工单详情记录
        workOrderMapper.addWorkOrder(workOrder);
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrderId(workOrder.getId());
//        orderDetail.setContent(workOrder.getProblemDescribe());
//        orderDetail.setUserId(workOrder.getUserId());
//        orderDetail.setCreateTime(new DateTime());
//        orderDetailMapper.insertOrderDetail(orderDetail);
    }

    @Override
    public int updateWorkOrder(WorkOrder workOrder) {
        return workOrderMapper.updateWorkOrder(workOrder);
    }

    @Override
    public List<WorkOrder> getWorkOrderRecord() {
        return workOrderMapper.getWorkOrderRecord();
    }

    @Override
    public WorkOrder getWorkOrderById(Long id) {
        return workOrderMapper.getWorkOrderById(id);
    }

    @Override
    public List<WorkOrder> getMyWorkOrder(Long userId) {
        return workOrderMapper.getMyWorkOrder(userId);
    }

    @Override
    public List<WorkOrder> getdispatchOrderRecord(Long serviceStaff) {
        return workOrderMapper.getdispatchOrderRecord(serviceStaff);
    }

    @Override
    public WorkOrder getWorkOrder(String orderNumber) {
        return workOrderMapper.getWorkOrder(orderNumber);
    }

    @Override
    public List<WorkOrder> getAllWorkOrder() {
        return workOrderMapper.getAllWorkOrder();
    }

    @Override
    public List<WorkOrder> getCustomerWorkOrder(Long userId) {
        return workOrderMapper.getCustomerWorkOrder(userId);
    }

    @Override
    public List<WorkOrder> getMyOrder(Long userId) {
        return workOrderMapper.getMyOrder(userId);
    }

    @Override
    public List<WorkOrder> getAlldispatchOrder(String status) {
        return workOrderMapper.getAlldispatchOrder(status);
    }

    @Override
    public List<WorkOrder> getMyDispatchOrder(Long userId,String status) {
        return workOrderMapper.getMyDispatchOrder(userId,status);
    }

    @Override
    public int getOrderNumberByUserId(Long userId) {
        return workOrderMapper.getOrderNumberByUserId(userId);
    }

    @Override
    public List<WorkOrder> getWorkOrderByParms(WorkOrder workOrder) {
        return workOrderMapper.getWorkOrderByParms(workOrder);
    }

    @Override
    public List<WorkOrder> getdispatchWorderOrderByParms(WorkOrder workOrder) {
        return workOrderMapper.getdispatchWorderOrderByParms(workOrder);
    }

    @Override
    public List<WorkOrder> getMyOrderByStatus(String status) {
        return workOrderMapper.getMyOrderByStatus(status);
    }

    @Override
    public List<WorkOrder> getMyWorkOrderByStatus(Long userId,Long sysId, String status) {
        return workOrderMapper.getMyWorkOrderByStatus(userId,sysId,status);
    }


}
