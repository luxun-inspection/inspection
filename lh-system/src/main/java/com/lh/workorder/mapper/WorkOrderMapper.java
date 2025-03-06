package com.lh.workorder.mapper;

import com.lh.workorder.domain.WorkOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkOrderMapper {

    int addWorkOrder(WorkOrder workOrder);

    int updateWorkOrder(WorkOrder workOrder);

    List<WorkOrder>  getWorkOrderRecord( );

    WorkOrder getWorkOrderById(Long id);

    List<WorkOrder> getMyWorkOrder(Long userId);

    List<WorkOrder> getdispatchOrderRecord(Long serviceStaff);

    WorkOrder getWorkOrder(String orderNumber);

    List<WorkOrder> getAllWorkOrder();

    List<WorkOrder> getCustomerWorkOrder(Long userId);

    List<WorkOrder> getMyOrder(Long userId);

    List<WorkOrder> getAlldispatchOrder(@Param("status") String status);

    List<WorkOrder> getMyDispatchOrder(@Param("userId") Long userId,@Param("status")String status);

    int getOrderNumberByUserId(Long userId);

    List<WorkOrder> getWorkOrderByParms(WorkOrder workOrder);

    List<WorkOrder> getdispatchWorderOrderByParms(WorkOrder workOrder);

    List<WorkOrder> getMyOrderByStatus(String status);

    List<WorkOrder> getMyWorkOrderByStatus(@Param("userId")Long userId,@Param("sysId")Long sysId,@Param("status") String status);
}
