package com.lh.workorder.mapper;

import com.lh.workorder.domain.OrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetailMapper {

    List<OrderDetail> getOrderDetailByOrderId(Long orderId);

    int insertOrderDetail(OrderDetail orderDetail);

    int updateOrderDetail(OrderDetail orderDetail);

    OrderDetail getOrderDetailById(Long id);

    List<OrderDetail> getReadStatusRecord(int userType);

    List<OrderDetail> getNotReadOrderDetail(@Param("userType") String userType,@Param("orderId") Long orderId);

}
