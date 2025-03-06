package com.lh.baseconfig.controller;

import com.lh.baseconfig.domain.ItemDetail;
import com.lh.baseconfig.service.ItemDetailService;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ht/itemDetail")
public class ItemDetailController extends BaseController {

    @Autowired
    private ItemDetailService itemDetailService;



    @ApiOperation(value ="获取服务项目内容")
    @GetMapping("/getItemDetail")
    public AjaxResult getItemDetail(ItemDetail itemDetail){
        ItemDetail itemDetail1 =  itemDetailService.getItemDetailByParentId(itemDetail.getParentId());
        return AjaxResult.success(itemDetail1);
    }

    @ApiOperation(value = "updateItemDetail")
    @PostMapping("/updateItemDetail")
    public AjaxResult updateItemDetail(@RequestBody ItemDetail itemDetail){
        itemDetailService.updateItemDetail(itemDetail);
        return AjaxResult.success();
    }
}
