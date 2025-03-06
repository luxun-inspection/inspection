package com.lh.workorder.service;

import com.lh.workorder.domain.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetail> getOrderDetailByOrderId(Long orderId);

    int insertOrderDetail(OrderDetail orderDetail);

    int updateOrderDetail(OrderDetail orderDetail);

    OrderDetail getOrderDetailById(Long id);

    List<OrderDetail> getReadStatusRecord(int userType);

    List<OrderDetail> getNotReadOrderDetail(String userType,Long orderId);
}
