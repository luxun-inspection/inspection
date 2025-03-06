package com.lh.weixinweb.controller;

import com.lh.common.config.HuaweiObsfaceService;
import com.lh.common.core.domain.AjaxResult;
import com.lh.weixinweb.controller.req.CreateQRCodeRequest;
import com.lh.weixinweb.controller.req.WxApiQueryRequest;
import com.lh.weixinweb.service.WxApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("/wx/api")
public class WxApiController {
    @Autowired
    private WxApiService wxApiService;

    @ApiOperation(value = "getAccessToken")
    @GetMapping("/getAccessToken")
    public AjaxResult getAccessToken() {
        return AjaxResult.success(wxApiService.getAccessToken());
    }

    @ApiOperation(value = "获取用户访问小程序周留存")
    @PostMapping("/getWeeklyRetain")
    public AjaxResult getWeeklyRetain(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getWeeklyRetain(request));
    }

    @ApiOperation(value = "获取用户访问小程序月留存")
    @PostMapping("/getMonthlyRetain")
    public AjaxResult getMonthlyRetain(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getMonthlyRetain(request));
    }

    /**
     * 获取用户访问小程序日留存
     *
     * @param request begin_date 20230602
     *                end_date 20230602
     * @return
     */
    @ApiOperation(value = "获取用户访问小程序日留存")
    @PostMapping("/getDailyRetain")
    public AjaxResult getDailyRetain(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getDailyRetain(request));
    }

    @ApiOperation(value = "获取用户访问小程序数据月趋势")
    @PostMapping("/getMonthlyVisitTrend")
    public AjaxResult getMonthlyVisitTrend(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getMonthlyVisitTrend(request));
    }

    @ApiOperation(value = "获取用户访问小程序数据日趋势")
    @PostMapping("/getDailyVisitTrend")
    public AjaxResult getDailyVisitTrend(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getDailyVisitTrend(request));
    }

    @ApiOperation(value = "获取用户访问小程序数据周趋势")
    @PostMapping("/getWeeklyVisitTrend")
    public AjaxResult getWeeklyVisitTrend(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getWeeklyVisitTrend(request));
    }

    @ApiOperation(value = "获取用户访问小程序数据概况")
    @PostMapping("/getDailySummary")
    public AjaxResult getDailySummary(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getDailySummary(request));
    }

    @ApiOperation(value = "获取访问页面数据")
    @PostMapping("/getVisitPage")
    public AjaxResult getVisitPage(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getVisitPage(request));
    }


    @ApiOperation(value = "获取小程序用户画像分布")
    @PostMapping("/getUserPortrait")
    public AjaxResult getUserPortrait(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getUserPortrait(request));
    }

    @ApiOperation(value = "获取用户小程序访问分布数据")
    @PostMapping("/getVisitDistribution")
    public AjaxResult getVisitDistribution(@RequestBody WxApiQueryRequest request) {
        return AjaxResult.success(wxApiService.getVisitDistribution(request));
    }

    @Autowired
    private HuaweiObsfaceService huaweiObsfaceService;
    @ApiOperation(value = "获取小程序二维码")
    @PostMapping("/createQRCode")
    public Object createQRCode(@RequestBody CreateQRCodeRequest request) throws Exception {
        byte[] bytes = wxApiService.createQRCode(request);
        InputStream input = new ByteArrayInputStream(bytes);
        String upload = huaweiObsfaceService.upload(input, "123.jpg");
        return upload;
    }

}
