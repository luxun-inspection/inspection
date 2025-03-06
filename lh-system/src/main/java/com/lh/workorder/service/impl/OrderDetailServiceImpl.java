package com.lh.workorder.service.impl;

import com.lh.workorder.domain.OrderDetail;
import com.lh.workorder.mapper.OrderDetailMapper;
import com.lh.workorder.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

   @Autowired
   private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetail> getOrderDetailByOrderId(Long orderId) {
        return orderDetailMapper.getOrderDetailByOrderId(orderId);
    }

    @Override
    public int insertOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.insertOrderDetail(orderDetail);
    }

    @Override
    public int updateOrderDetail(OrderDetail orderDetail) {
        return orderDetailMapper.updateOrderDetail(orderDetail);
    }

    @Override
    public OrderDetail getOrderDetailById(Long id) {
        return orderDetailMapper.getOrderDetailById(id);
    }

    @Override
    public List<OrderDetail> getReadStatusRecord(int userType) {
        return orderDetailMapper.getReadStatusRecord(userType);
    }

    @Override
    public List<OrderDetail> getNotReadOrderDetail(String userType,Long orderId) {
        return orderDetailMapper.getNotReadOrderDetail(userType,orderId);
    }
}
