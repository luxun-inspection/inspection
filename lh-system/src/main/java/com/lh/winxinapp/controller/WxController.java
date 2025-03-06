package com.lh.winxinapp.controller;

import com.lh.baseconfig.domain.*;
import com.lh.baseconfig.service.*;
import com.lh.casedetail.service.ProjectCaseService;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.domain.entity.SysDictData;
import com.lh.common.core.domain.entity.SysRole;
import com.lh.common.core.domain.entity.SysUser;
import com.lh.common.core.domain.model.LoginUser;
import com.lh.common.utils.DateUtils;
import com.lh.common.utils.SMSCode;
import com.lh.common.utils.SecurityUtils;
import com.lh.common.utils.StringUtils;
import com.lh.information.service.NewsService;
import com.lh.system.service.ISysDictDataService;
import com.lh.system.service.ISysNoticeService;
import com.lh.system.service.ISysUserService;
import com.lh.workorder.domain.OrderDetail;
import com.lh.workorder.domain.WorkOrder;
import com.lh.workorder.service.OrderDetailService;
import com.lh.workorder.service.WorkOrderService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wx")
public class WxController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(WxController.class);

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ServiceItemService serviceItemService;
    @Autowired
    private ItemDetailService itemDetailService;
    @Autowired
    private ISysNoticeService sysNoticeService;
    @Autowired
    private ProjectCaseService projectCaseService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private IBaseFeedbackService baseFeedbackService;

    @Autowired
    private IWorkOrderEngineerService workOrderEngineerService;

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/getUserInfoData")
    public AjaxResult getUserInfoData() {
        LoginUser user = SecurityUtils.getLoginUser();
        UserInfo userInfo = null;
        if (user != null) {
            userInfo = userInfoService.getUserInfoById(user.getUserId());
        }
        String phonenumber=userInfo.getPhone();
        SysUser sysUser= sysUserService.selectUserByUserPhone(phonenumber);
        String authType="general";
        if(sysUser!=null){
            String postName=sysUserService.selectUserPostCodeGroup(sysUser.getUserName());
            if(StringUtils.isNotEmpty(postName)){
                if(postName.indexOf("customerSerivce")!=-1){
                    authType="serivce";
                }
                else if(postName.indexOf("engineer")!=-1){
                    authType="engineer";
                }
                else if(postName.indexOf("manager")!=-1){
                    authType="manager";
                }
            }
        }
        userInfo.setAuthType(authType);
        return AjaxResult.success(userInfo);
    }

    @ApiOperation(value = "困难接口")
    @GetMapping("/getDicAbountProlem")
    public AjaxResult getDicAbountProlem() {
        SysDictData sysDictData = new SysDictData();
        sysDictData.setDictType("problem_category");
        List<SysDictData> sysDictDataList = sysDictDataService.selectDictDataList(sysDictData);
        return AjaxResult.success(sysDictDataList);
    }

    @ApiOperation(value = "提交工单")
    @PostMapping("/submitWorkOrder")
    public AjaxResult submitWorkOrder(@RequestBody WorkOrder workOrder) {
        LoginUser user = SecurityUtils.getLoginUser();
        Long userId = user.getUser().getUserId();
        workOrder.setUserId(userId);
        workOrder.setCreateTime(new Date());
        workOrder.setStatus("1");
        workOrder.setOrderNumber(createOrderNumber());
        workOrderService.addWorkOrder(workOrder);
        return AjaxResult.success();
    }

    @ApiOperation(value = "提交工单")
    @PostMapping("/createDispatchWork")
    public AjaxResult createDispatchWork(@RequestBody WorkOrder workOrder) {
        LoginUser user = SecurityUtils.getLoginUser();
        Long userId = user.getUser().getUserId();
        workOrder.setUserId(userId);
        workOrder.setCreateTime(new Date());
        workOrder.setStatus("2");
        workOrder.setManageUserId(userId);
        workOrder.setManageUserName(user.getUsername());
        workOrder.setOrderNumber(createOrderNumber());
        workOrderService.addWorkOrder(workOrder);
        return AjaxResult.success();
    }

    public String createOrderNumber() {
        Random random = new Random();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            result.append(random.nextInt(10));
            if (i == 5) {
                //判断当前工单在数据库有没有数据
                WorkOrder workOrder = workOrderService.getWorkOrder(result.toString());
                if (workOrder == null) {
                    return result.toString();
                } else {
                    i = -1;
                    result.setLength(0);
                }
            }
        }
        return result.toString();
    }


    @ApiOperation(value = "客服分配项目经理")
    @PostMapping("/dispatchManagerUser")
    public AjaxResult dispatchManagerUser(@RequestBody WorkOrder workOrder) {
        LoginUser user = SecurityUtils.getLoginUser();
        SysUser manageruser= sysUserService.selectUserById(workOrder.getManageUserId());
        if (manageruser==null) {
            return AjaxResult.error("请选择项目经理!");
        }
        WorkOrder currentOrder = workOrderService.getWorkOrderById(workOrder.getId());
        if (!"1".equals(currentOrder.getStatus())) {
            return AjaxResult.error("工单状态异常，请联系管理员!");
        }
        SysUser kefu=sysUserService.selectSysUserByPhone(user.getUser().getPhonenumber());
        workOrder.setStatus("2");
        if(kefu!=null){
            workOrder.setServiceStaff(kefu.getUserId());
            workOrder.setServiceName(kefu.getUserName());
        }
        workOrder.setManageUserId(workOrder.getManageUserId());
        workOrder.setManageUserName(manageruser.getUserName());
        int result = workOrderService.updateWorkOrder(workOrder);
        if (result > 0) {
            String content = SMSCode.dispatchMsg + currentOrder.getOrderNumber();
            try {
                SMSCode.sunHaosendNoticeSMS(manageruser.getPhonenumber(), content);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return AjaxResult.success("工单指定工程师成功!");
        }
        return AjaxResult.success();
    }
    /**
     * @param
     * @return
     */
    @ApiOperation(value = "项目经理指定工程师")
    @PostMapping("/dispatchWorderOrder")
    public AjaxResult dispatchWorderOrder(@RequestBody WorkOrder workOrder) {
        LoginUser user = SecurityUtils.getLoginUser();
        Long userId = user.getUser().getUserId();
        if (StringUtils.isEmpty(workOrder.getEngineerId())) {
            return AjaxResult.error("请选择工程师!");
        }
        WorkOrder currentOrder = workOrderService.getWorkOrderById(workOrder.getId());
        if (!"2".equals(currentOrder.getStatus())) {
            return AjaxResult.error("工单状态异常，请联系管理员!");
        }
        workOrder.setStatus("3");
        int result = workOrderService.updateWorkOrder(workOrder);
        if (result > 0) {
            return AjaxResult.success("工单指定工程师成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "完成工单")
    @PostMapping("/finishWorderOrder")
    public AjaxResult finishWorderOrder(@RequestBody WorkOrder workOrder) {
        workOrder.setStatus("5");
        workOrder.setFinishTime(new Date());
        int result = workOrderService.updateWorkOrder(workOrder);
        if (result > 0) {
            return AjaxResult.success("工单修改成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "分配工程师")
    @PostMapping("/distributeEngineer")
    public AjaxResult distributeEngineer(@RequestBody WorkOrder workOrder) {
        workOrder.setStatus("3");
        workOrder.setDispatchStatus("1");
        String engineeId = workOrder.getEngineerId();
        if (StringUtils.isEmpty(engineeId)) {
            return AjaxResult.error("请分配工程师");
        }
        if (StringUtils.isEmpty(workOrder.getEngineerId())) {
            return AjaxResult.error("请选择工程师!");
        }
        WorkOrder currentOrder = workOrderService.getWorkOrderById(workOrder.getId());
        if (!"2".equals(currentOrder.getStatus())) {
            return AjaxResult.error("工单状态异常，请联系管理员!");
        }

        engineeId = "'" + engineeId.replaceAll(",","','") + "'";
        List<UserInfo> userList = userInfoService.getUserInfoByIds(engineeId);
        if (userList.size()==0) {
            return AjaxResult.error("选择的工程师不存在");
        }
        WorkOrder order = workOrderService.getWorkOrderById(workOrder.getId());
        workOrder.setManageDealTime(DateUtils.getNowDate());
        int result = workOrderService.updateWorkOrder(workOrder);

        if (result > 0) {
            //蒋工程师处理情况初始化到表汇总
            userList.stream().forEach(user -> {
                WorkOrderEngineer engineer=new WorkOrderEngineer();
                engineer.setOrderId(currentOrder.getId());
                engineer.setUserId(user.getId());
                engineer.setUserName(user.getNickName());
                engineer.setStatus("1");
                workOrderEngineerService.insertWorkOrderEngineer(engineer);
            });
            String content = SMSCode.dispatchMsg + order.getOrderNumber();
            userList.stream().forEach(user -> {
                try {
                    SMSCode.sunHaosendNoticeSMS(user.getPhone(), content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            return AjaxResult.success("工单更新成功!");
        }
        return AjaxResult.error("分配工程师失败");
    }

    @ApiOperation(value = "开始工单")
    @PostMapping("/startWorkOrder")
    public AjaxResult startWorkOrder(@RequestBody WorkOrder workOrder) {

        LoginUser user = SecurityUtils.getLoginUser();
        String phonenumber=user.getUser().getPhonenumber();
        SysUser sysUser= sysUserService.selectUserByUserPhone(phonenumber);

        String postName=sysUserService.selectUserPostCodeGroup(sysUser.getUserName());
        if(StringUtils.isEmpty(postName)){
            return AjaxResult.error("工程师才能处理工单!");
        }
        if(StringUtils.isNotEmpty(postName) && postName.indexOf("engineer")==-1){
            return AjaxResult.error("工程师才能处理工单!");
        }

        WorkOrderEngineer param=new WorkOrderEngineer();
        param.setOrderId(workOrder.getId());
        List<WorkOrderEngineer> engineerList=workOrderEngineerService.selectWorkOrderEngineerList(param);

        WorkOrderEngineer userEngineer= engineerList.stream().filter(engineer->engineer.getUserId().intValue()==sysUser.getUserId().intValue()).findFirst().orElse(null);

        if(userEngineer!=null && "2".equals(userEngineer.getStatus())){
            return AjaxResult.error("该工单您已经处理完毕!");
        }
        if(engineerList.size()==0){
            return AjaxResult.error("该工单未分配工程师");
        }
        userEngineer.setOrderDescribe(workOrder.getOrderDescribe());
        userEngineer.setImageUrl(workOrder.getImageUrl());
        userEngineer.setStatus("2");
        userEngineer.setDealTime(DateUtils.getNowDate());
        workOrderEngineerService.updateWorkOrderEngineer(userEngineer);

        List<WorkOrderEngineer> undealList= engineerList.stream().filter(enger->"1".equals(enger.getStatus())).collect(Collectors.toList());
        if(undealList.size()==0){
            workOrder.setStatus("4");
        }
        workOrder.setOrderDescribe(null);
        workOrderService.updateWorkOrder(workOrder);
        return AjaxResult.success("工单处理完毕!");
    }


    @ApiOperation(value = "完成工单")
    @PostMapping("/finishWorkOrder")
    public AjaxResult finishWorkOrder(@RequestBody WorkOrder workOrder) {
        workOrder.setStatus("5");
        workOrder.setFinishTime(new Date());
        workOrder.setManageConfirmTime(DateUtils.getNowDate());
        workOrder.setFinishPic(workOrder.getImageUrl());
        int result = workOrderService.updateWorkOrder(workOrder);
        if (result > 0) {
            return AjaxResult.success("工单更新成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "选择项目经理")
    @PostMapping("/getManagerUserLit")
    public AjaxResult getManagerUserLit() {
        //查询有任意咨询或是派单权限的岗位
        List<UserInfo> userInfoList = userInfoService.getManagerUserList();
        return AjaxResult.success(userInfoList);
    }

    @ApiOperation(value = "选择工程师")
    @PostMapping("/engineerLit")
    public AjaxResult engineerLit() {
        //查询有任意咨询或是派单权限的岗位
        List<Long> longList = permissionService.getPostIdsByPermissionId();
        Long[] postIds = new Long[longList.size()];
        longList.toArray(postIds);
        List<UserInfo> userInfoList = userInfoService.getEngineerList();
        return AjaxResult.success(userInfoList);
    }

    @ApiOperation(value = "获取派单记录")
    @PostMapping("/getWorkOrderRecord")
    public AjaxResult getWorkOrderRecord() {
        LoginUser user = SecurityUtils.getLoginUser();
        Long loginUserId = user.getUserId();
        List<SysRole> sysRoleList = SecurityUtils.getLoginUser().getUser().getRoles();
        UserInfo loginUserInfo = userInfoService.getUserInfoById(loginUserId);
        String loginUserType = loginUserInfo.getUserType();
        List<WorkOrder> workOrderList = null;
        if (sysRoleList.stream().filter(item -> item.getRoleName().equals("distribute")).findAny().isPresent()) {
            workOrderList = workOrderService.getAllWorkOrder();
        } else {
            workOrderList = workOrderService.getMyWorkOrder(user.getUserId());
        }
        workOrderList.stream().forEach(workOrder -> {
            String engineerId = workOrder.getEngineerId();
            if (engineerId != null && !"".equals(engineerId)) {
                //查询用户集合
                List<UserInfo> userInfoList = userInfoService.getEngineerNameList();
                workOrder.setUserInfoList(userInfoList);
            }
        });
        return AjaxResult.success(workOrderList);
    }

    @ApiOperation(value = "获取工单详情")
    @PostMapping("/getOrderDetail")
    public AjaxResult getOrderDetail(@RequestBody WorkOrder workOrder) {
        WorkOrder workOrder1 = workOrderService.getWorkOrderById(workOrder.getId());

        WorkOrderEngineer param=new WorkOrderEngineer();
        param.setOrderId(workOrder1.getId());
        List<WorkOrderEngineer> engineerList=workOrderEngineerService.selectWorkOrderEngineerList(param);

        workOrder1.setEngineerList(engineerList);

        String engineerId = workOrder1.getEngineerId();
        if (workOrder1 != null) {
            List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(workOrder1.getId());
            orderDetailList.stream().forEach(orderDetail -> {
                Long userId = orderDetail.getUserId();
                UserInfo userInfo = userInfoService.getUserInfoById(userId);
                if (userInfo != null) {
                    orderDetail.setUserInfo(userInfo);
                }
            });
            workOrder1.setOrderDetailList(orderDetailList);
            Long userId = workOrder1.getUserId();
            UserInfo userInfo = userInfoService.getUserInfoById(userId);
            if (userInfo != null) {
                workOrder1.setUserInfo(userInfo);
            }

            //获取工程师名字
            String[] engineerIds = null;
            if (engineerId != null && !"".equals(engineerId)) {
                engineerIds = engineerId.split(",");
                List<String> stringList = userInfoService.getEngineerName(engineerIds);
                String str = StringUtils.join(stringList, ",");
                workOrder1.setEngineerNameList(str);
            }

        } else {
            return AjaxResult.error("工单不存在!");
        }
        return AjaxResult.success(workOrder1);
    }

    @ApiOperation(value = "获取我的工单")
    @GetMapping("/getMyWorkOrder")
    public AjaxResult getOrderDetail() {
        LoginUser user = SecurityUtils.getLoginUser();
        Long loginUserId = user.getUserId();
        List<SysRole> sysRoleList = SecurityUtils.getLoginUser().getUser().getRoles();
        UserInfo loginUserInfo = userInfoService.getUserInfoById(loginUserId);
        String loginUserType = loginUserInfo.getUserType();
        List<WorkOrder> workOrderList = null;
        if (sysRoleList != null) {
            if (sysRoleList.stream().filter(item -> item.getRoleName().equals("distribute")).findAny().isPresent()) {
                workOrderList = workOrderService.getMyOrder(user.getUserId());
            } else {
                workOrderList = workOrderService.getMyWorkOrder(user.getUserId());
            }
        } else {
            workOrderList = workOrderService.getMyWorkOrder(user.getUserId());
        }
        workOrderList.stream().forEach(workOrder -> {
            Long workOrderId = workOrder.getId();
            //查询子订单,判断当前订单下面有没有未读的信息
            List<OrderDetail> orderDetailList = orderDetailService.getNotReadOrderDetail(loginUserType, workOrder.getId());
            if (orderDetailList.size() > 0) {
                workOrder.setReadStatus("2");
            } else {
                workOrder.setReadStatus("1");
            }
        });
        return AjaxResult.success(workOrderList);
    }

    @ApiOperation(value = "获取我的工单根据条件")
    @GetMapping("/getMyWorkOrderByStatus")
    public AjaxResult getMyWorkOrderByStatus(WorkOrder workOrder2) {
        LoginUser user = SecurityUtils.getLoginUser();
        Long loginUserId = user.getUserId();
        UserInfo loginUserInfo = userInfoService.getUserInfoById(loginUserId);
        String loginUserType = loginUserInfo.getUserType();
        String phonenumber=loginUserInfo.getPhone();
        SysUser sysUser= sysUserService.selectUserByUserPhone(phonenumber);
        boolean allflag=false;
        Long sysUserId=0L;
        if(sysUser!=null) {
            sysUserId=sysUser.getUserId();
            String postName = sysUserService.selectUserPostCodeGroup(sysUser.getUserName());
            if (StringUtils.isNotEmpty(postName)) {
                if (postName.indexOf("customerSerivce") != -1) {
                    allflag = true;
                }
            }
        }
        List<WorkOrder> workOrderList=new ArrayList<>();
        if(StringUtils.isNotEmpty(workOrder2.getStatus())){
            String userStatus=workOrder2.getStatus();
            if(userStatus.indexOf(",")>0){
                userStatus="'"+userStatus.replaceAll(",","','")+"'";
                workOrder2.setStatus(userStatus);
            }
        }
        if(allflag){
            workOrderList  = workOrderService.getMyOrderByStatus(workOrder2.getStatus());
        }
        else{
            workOrderList  = workOrderService.getMyWorkOrderByStatus(loginUserId,sysUserId, workOrder2.getStatus());
        }

        workOrderList.stream().forEach(workOrder -> {
            Long workOrderId = workOrder.getId();
            //查询子订单,判断当前订单下面有没有未读的信息
            List<OrderDetail> orderDetailList = orderDetailService.getNotReadOrderDetail(loginUserType, workOrder.getId());
            if (orderDetailList.size() > 0) {
                workOrder.setReadStatus("2");
            } else {
                workOrder.setReadStatus("1");
            }
        });
        return AjaxResult.success(workOrderList);
    }


    @ApiOperation(value = "新增工单详情")
    @PostMapping("/insertOrderDetail")
    public AjaxResult insertOrderDetail(@RequestBody OrderDetail orderDetail) {
        LoginUser user = SecurityUtils.getLoginUser();
        orderDetail.setUserId(user.getUserId());
        orderDetail.setStatus("2");
        int result = orderDetailService.insertOrderDetail(orderDetail);
        if (result > 0) {
            return AjaxResult.success("工单新增成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "工单详情修改")
    @PostMapping("/updateOrderDetail")
    public AjaxResult updateOrderDetail(@RequestBody OrderDetail orderDetail) {
        LoginUser user = SecurityUtils.getLoginUser();
        orderDetail.setUserId(user.getUserId());
        int result = orderDetailService.updateOrderDetail(orderDetail);
        if (result > 0) {
            return AjaxResult.success("工单修改成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "修改工单详情状态")
    @PostMapping("/updateReadStatus")
    public AjaxResult updateReadStatus(@RequestBody OrderDetail orderDetail) {
        Long id = orderDetail.getId();
        LoginUser user = SecurityUtils.getLoginUser();
        Long loginUserId = user.getUserId();
        UserInfo loginUserInfo = userInfoService.getUserInfoById(loginUserId);
        String loginUserType = loginUserInfo.getUserType();
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailByOrderId(id);
        orderDetailList.stream().forEach(orderDetail1 -> {
            Long userid = orderDetail1.getUserId();
            UserInfo userInfo = userInfoService.getUserInfoById(userid);
            if (userInfo != null) {
                String userType = userInfo.getUserType();
                if (!loginUserType.equals(userType)) {
                    orderDetail1.setStatus("1");
                    int result = orderDetailService.updateOrderDetail(orderDetail1);
                }
            }
        });
        return AjaxResult.success();
    }


    @ApiOperation(value = "获取派单详情")
    @GetMapping("/getdispatchOrderRecord")
    public AjaxResult getdispatchOrderRecord(WorkOrder workOrder2) {
        LoginUser user = SecurityUtils.getLoginUser();
        Long loginUserId = user.getUserId();
        List<SysRole> sysRoleList = SecurityUtils.getLoginUser().getUser().getRoles();
        UserInfo loginUserInfo = userInfoService.getUserInfoById(loginUserId);
        String loginUserType = loginUserInfo.getUserType();
        List<WorkOrder> workOrderList = null;
        System.out.println("===============================================");
        System.out.println("workOrder2.getStatus()===>" + workOrder2.getStatus());
        if (sysRoleList != null) {
            if (sysRoleList.stream().filter(item -> item.getRoleName().equals("distribute")).findAny().isPresent()) {
                System.out.println("===============================================");
                System.out.println("workOrder2.getStatus()===>" + workOrder2.getStatus());
                workOrderList = workOrderService.getAlldispatchOrder(workOrder2.getStatus());
            } else {
                //查询当前用户管理的后台用户
                String phone = loginUserInfo.getPhone();
                SysUser sysUser = sysUserService.selectSysUserByPhone(phone);
                if (sysUser != null) {
                    workOrderList = workOrderService.getMyDispatchOrder(sysUser.getUserId(), workOrder2.getStatus());
                }
            }
        } else {
            workOrderList = workOrderService.getMyDispatchOrder(user.getUserId(), workOrder2.getStatus());
        }

        workOrderList.stream().forEach(workOrder -> {
            String engineerId = workOrder.getEngineerId();
            String[] engineerIds = null;
            if (engineerId != null && !"".equals(engineerId)) {
                engineerIds = engineerId.split(",");
                //查询工程师名字
                List<String> stringList = userInfoService.getEngineerName(engineerIds);
                String str = StringUtils.join(stringList, ",");
                workOrder.setEngineerNameList(str);
            }
        });
        return AjaxResult.success(workOrderList);
    }


    @ApiOperation(value = "协议文本")
    @PostMapping(value = "/addFeddBack")
    public AjaxResult addFeddBack(@RequestBody BaseFeedback baseFeedback) {
        return toAjax(baseFeedbackService.insertBaseFeedback(baseFeedback));
    }
}
