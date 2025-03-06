package com.lh.workorder.controller;

import com.lh.baseconfig.domain.UserInfo;
import com.lh.baseconfig.service.UserInfoService;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.domain.entity.SysDictData;
import com.lh.common.core.page.TableDataInfo;
import com.lh.common.utils.StringUtils;
import com.lh.system.service.ISysDictDataService;
import com.lh.workorder.domain.OrderDetail;
import com.lh.workorder.domain.WorkOrder;
import com.lh.workorder.service.OrderDetailService;
import com.lh.workorder.service.WorkOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/ht/workOrder")
public class WorkOrderController extends BaseController {

    @Autowired
    private WorkOrderService workOrderService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @ApiOperation(value = "获取工单记录")
    @GetMapping("/getWorkOrderRecordList")
    public TableDataInfo getWorkOrderRecordList(WorkOrder workOrder) {
        startPage();
        List<WorkOrder> list = workOrderService.getWorkOrderByParms(workOrder);
        list.stream().forEach(workOrder1 -> {
            Long userId = workOrder1.getUserId();
            UserInfo userInfo = userInfoService.getUserInfoById(userId);
            workOrder1.setUserInfo(userInfo);

            String engineerId = workOrder1.getEngineerId();
            String [] engineerIds =null;
            if(engineerId!=null&&!"".equals(engineerId)){
                engineerIds = engineerId.split(",");
                //查询工程师名字
                List<String> stringList = userInfoService.getEngineerName(engineerIds);
                String str = StringUtils.join(stringList, ",");
                workOrder1.setEngineerNameList(str);
            }
        });


        return getDataTable(list);
    }


    @ApiOperation(value = "获取工单详情")
    @GetMapping("/getWorkOrderDetail")
    public AjaxResult getWorkOrderDetail(WorkOrder workOrder){
        WorkOrder workOrder1 = workOrderService.getWorkOrderById(workOrder.getId());
        String engineerId = workOrder1.getEngineerId();
        String [] engineerIds =null;
        if(engineerId!=null&&!"".equals(engineerId)){
            engineerIds = engineerId.split(",");
            //查询工程师名字
            List<String> stringList = userInfoService.getEngineerName(engineerIds);
            String str = StringUtils.join(stringList, ",");
            workOrder1.setEngineerNameList(str);
        }
        Long userid = workOrder1.getUserId();
        UserInfo userInfo = userInfoService.getUserInfoById(userid);
        workOrder1.setUserInfo(userInfo);
        List<OrderDetail> orderDetail = orderDetailService.getOrderDetailByOrderId(workOrder.getId());
        Iterator<OrderDetail> it =orderDetail.iterator();
        orderDetail.stream().forEach(orderDetail1 -> {
            Long userId = orderDetail1.getUserId();
            UserInfo userInfo1 = userInfoService.getUserInfoById(userId);
            orderDetail1.setUserInfo(userInfo1);
        });
        workOrder1.setOrderDetailList(orderDetail);
        return AjaxResult.success(workOrder1);
    }


    @ApiOperation(value = "获取派单记录")
    @GetMapping("/getdispatchWorkOrder")
    public TableDataInfo getdispatchWorderOrder(WorkOrder workOrder) {
        startPage();
        List<WorkOrder> list = workOrderService.getdispatchWorderOrderByParms(workOrder);
        list.stream().forEach(workOrder1 -> {
            Long userId = workOrder1.getUserId();
            UserInfo userInfo = userInfoService.getUserInfoById(userId);
            workOrder1.setUserInfo(userInfo);

            String engineerId = workOrder1.getEngineerId();
            String [] engineerIds =null;
            if(engineerId!=null&&!"".equals(engineerId)){
                engineerIds = engineerId.split(",");
                //查询工程师名字
                List<String> stringList = userInfoService.getEngineerName(engineerIds);
                String str = StringUtils.join(stringList, ",");
                workOrder1.setEngineerNameList(str);
            }
        });


        return getDataTable(list);
    }




    @ApiOperation(value = "困难接口")
    @GetMapping("/getDicAbountProlem")
    public AjaxResult getDicAbountProlem(){
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictType("problem_category");
        List<SysDictData> sysDictDataList = sysDictDataService.selectDictDataList(sysDictData);
        return AjaxResult.success(sysDictDataList);
    }

}
