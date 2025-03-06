package com.lh.baseconfig.controller;

import com.lh.baseconfig.domain.ServiceItem;
import com.lh.baseconfig.service.ServiceItemService;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ht/serviceTtem")
public class ServiceItemController extends BaseController {

    @Autowired
    private ServiceItemService serviceItemService;

    @ApiOperation(value = "获取服务项目")
    @GetMapping("/getServiceItem")
    public AjaxResult getServiceItem() {
        List<ServiceItem> serviceItemList = serviceItemService.getServiceItem();
        serviceItemList.stream().forEach(serviceItem -> {
            Long id = serviceItem.getId();
            List<ServiceItem> serviceItemList1 = serviceItemService.getServiceItemListByParentId(id);
            serviceItem.setServiceItemList(serviceItemList1);
        });
        return AjaxResult.success(serviceItemList);
    }

    @ApiOperation(value = "修改项目内容")
    @PostMapping("/updateServiceItem")
    public AjaxResult updateServiceItem(@RequestBody ServiceItem serviceItem) {
        int result = serviceItemService.updateServiceItem(serviceItem);
        if (result > 0) {
            return AjaxResult.success("信息更新成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除项目内容")
    @PostMapping("/deleteServiceItem")
    public AjaxResult deleteServiceItem(@RequestBody ServiceItem serviceItem) {
        int result = serviceItemService.deleteServiceItem(serviceItem);
        if (result > 0) {
            return AjaxResult.success("信息删除成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "新增项目内容")
    @PostMapping("/insertServiceItem")
    public AjaxResult insertServiceItem(@RequestBody ServiceItem serviceItem) {
        int result = serviceItemService.insertServiceItem(serviceItem);
        if (result > 0) {
            return AjaxResult.success("信息新增成功!");
        }
        return AjaxResult.success();
    }


}
