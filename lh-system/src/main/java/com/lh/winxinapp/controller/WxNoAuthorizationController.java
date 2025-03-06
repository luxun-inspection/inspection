package com.lh.winxinapp.controller;

import com.lh.baseconfig.domain.*;
import com.lh.baseconfig.service.*;
import com.lh.casedetail.domain.ProjectCase;
import com.lh.casedetail.service.ProjectCaseService;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.domain.entity.SysDictData;
import com.lh.common.core.page.TableDataInfo;
import com.lh.common.utils.DictUtils;
import com.lh.common.utils.StringUtils;
import com.lh.heating.algorithm.*;
import com.lh.heating.beans.MinMaxTuple;
import com.lh.information.domain.News;
import com.lh.information.service.NewsService;
import com.lh.system.domain.SysNotice;
import com.lh.system.service.ISysNoticeService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/noAuthorization")
public class WxNoAuthorizationController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(WxNoAuthorizationController.class);
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
    private HomePageService homePageService;

    @Autowired
    private IBaseProductInfoService baseProductInfoService;

    @Autowired
    private IBaseProductTypeService baseProductTypeService;

    @Autowired
    private IBaseConsultArticleService baseConsultArticleService;

    @Autowired
    private IBaseTextContentService baseTextContentService;

    @Autowired
    private IBaseModelConfigService baseModelConfigService;

    @ApiOperation(value = "获取功能模块")
    @GetMapping("/getServiceItem")
    public AjaxResult getServiceItem() {
        List<ServiceItem> serviceItems = serviceItemService.getServiceItem();
        serviceItems.stream().forEach(serviceItem -> {
                    Long id = serviceItem.getId();
                    List<ServiceItem> serviceItemList = serviceItemService.getServiceItemListByParentId(id);
                    serviceItem.setServiceItemList(serviceItemList);
                }
        );
        return AjaxResult.success(serviceItems);
    }

    @ApiOperation(value = "获取功能详情")
    @PostMapping("/getItemDetail")
    public AjaxResult getItemDetail(@RequestBody ItemDetail itemDetail) {
        List<ServiceItem> serviceItemList = serviceItemService.getServiceItemListByParentId(itemDetail.getId());
        serviceItemList.stream().forEach(serviceItem -> {
            Long serviceId = serviceItem.getId();
            ItemDetail itemDetail1 = itemDetailService.getItemDetailByParentId(serviceId);
            serviceItem.setItemDetail(itemDetail1);
        });
        return AjaxResult.success(serviceItemList);
    }


    @ApiOperation(value = "获取系统公告")
    @GetMapping("/getSysNotice")
    public AjaxResult getSysNotice() {
        SysNotice sysNotice = new SysNotice();
        sysNotice.setNoticeType("2");
        List<SysNotice> sysNoticeList = sysNoticeService.selectNoticeList(sysNotice);
        if (sysNoticeList.size() == 0) {
            return AjaxResult.error("暂时没有公告!");
        }
        return AjaxResult.success(sysNoticeList);
    }

    @ApiOperation(value = "获取公告详情")
    @GetMapping("/getSysNoticeById")
    public AjaxResult getSysNoticeById(Long noticeId) {
        SysNotice sysNotice = sysNoticeService.selectNoticeById(noticeId);
        if (sysNotice == null) {
            return AjaxResult.error("该公告已删除!");
        }
        return AjaxResult.success(sysNotice);
    }

    @ApiOperation(value = "公司介绍")
    @GetMapping("/getCompanyIntroduction")
    public AjaxResult getCompanyIntroduction() {
        HomePage homePage = homePageService.getCompanyIntroduction();
        if (homePage == null) {
            return AjaxResult.error("暂时没有公司介绍!");
        }
        return AjaxResult.success(homePage);
    }

    @ApiOperation(value = "项目案例列表")
    @GetMapping("/getProjectCaseList")
    public AjaxResult getProjectCaseList(ProjectCase projectCase) {
        List<ProjectCase> projectCaseList = projectCaseService.getProjectCaseList(projectCase);
        return AjaxResult.success(projectCaseList);
    }

    @ApiOperation(value = "案例详情")
    @PostMapping("/getCaseDetail")
    public AjaxResult getCaseDetail(@RequestBody ProjectCase projectCase) {
        ProjectCase projectCase1 = projectCaseService.getCaseDetailById(projectCase.getId());
        if (projectCase1 == null) {
            return AjaxResult.error("没有该案例!");
        }
        ProjectCase n = new ProjectCase();
        n.setId(projectCase.getId());
        n.setReadNum(projectCase1.getReadNum() + 1);
        projectCaseService.updateProjectCase(n);
        return AjaxResult.success(projectCase1);
    }

    @ApiOperation(value = "获取新闻列表")
    @GetMapping("/getNewsList")
    public AjaxResult getNewsList(News news) {
        List<News> newsList = newsService.getNewsList(news);
        return AjaxResult.success(newsList);
    }

    @ApiOperation(value = "获取新闻详情")
    @PostMapping("/getNewsDetail")
    public AjaxResult getNewsDetail(@RequestBody News news) {
        News news1 = newsService.getNewsById(news.getId());
        if (news1 == null) {
            return AjaxResult.error("暂时没有新闻!");
        }
        News n = new News();
        n.setId(news.getId());
        n.setReadNum(news1.getReadNum() + 1);
        newsService.updateNews(n);
        return AjaxResult.success(news1);
    }

    @ApiOperation(value = "获取首页banner")
    @GetMapping("/getHomePageBanner")
    public AjaxResult getHomePageBanner() {
        List<HomePage> homePage = homePageService.getHomePageBanner();
        return AjaxResult.success(homePage);
    }

    @ApiOperation(value = "获取轮播图详情")
    @GetMapping("/getHomePageById")
    public AjaxResult getHomePageById(Long id) {
        HomePage homePage = homePageService.getHomePageById(id);
        if (homePage == null) {
            return AjaxResult.error("该轮播图已被删除!");
        }
        return AjaxResult.success(homePage);
    }


    @ApiOperation(value = "获取产品类型")
    @GetMapping("/getAllProductType")
    public AjaxResult getAllProductType() {
        List<SysDictData> dataList = DictUtils.getDictCache("base_product_type");
        List<BaseProductType> list = baseProductTypeService.selectBaseProductTypeList(new BaseProductType());
        List<Map<String, Object>> result = new ArrayList<>();
        dataList.stream().forEach(dict -> {
            Map data = new HashMap();
            data.put("label", StringUtils.isNotEmpty(dict.getDictLabel()) ? dict.getDictLabel() : "");
            data.put("value", StringUtils.isNotEmpty(dict.getDictValue()) ? dict.getDictValue() : "");
            List<BaseProductType> typeList = list.stream().filter(type -> type.getCategoryType().equals(dict.getDictValue())).collect(Collectors.toList());
            data.put("options", typeList);
            result.add(data);
        });
        return AjaxResult.success(result);
    }

    @ApiOperation(value = "获取产品列表")
    @GetMapping("/getProductData")
    public TableDataInfo getProductData(BaseProductInfo productInfo) {
        startPage();
        List<BaseProductInfo> productList = baseProductInfoService.selectBaseProductInfoList(productInfo);
        return getDataTable(productList);
    }

    @ApiOperation(value = "获取产品详情")
    @GetMapping("/getProductDetail")
    public AjaxResult getProductDetail(@RequestParam(name = "id", required = true) Long id) {
        BaseProductInfo detail = baseProductInfoService.selectBaseProductInfoById(id);
        if (detail == null) {
            return AjaxResult.error("产品不存在");
        }
        return AjaxResult.success(detail);
    }

    @ApiOperation(value = "技术咨询")
    @GetMapping(value = "/getArticleDetail")
    public AjaxResult getArticleDetail(@RequestParam(name = "id", required = true) Long id) {
        return AjaxResult.success(baseConsultArticleService.selectBaseConsultArticleById(id));
    }

    @ApiOperation(value = "协议文本")
    @GetMapping(value = "/getTextContent")
    public AjaxResult getTextContent(@RequestParam(name = "id", required = true) Long id) {
        return AjaxResult.success(baseTextContentService.selectBaseTextContentById(id));
    }

    @ApiOperation(value = "计算模型")
    @GetMapping(value = "/getModels")
    public AjaxResult getModels() {
        BaseModelConfig config = new BaseModelConfig();
        config.setShowFlag("Y");
        List<BaseModelConfig> configList = baseModelConfigService.selectBaseModelConfigList(config);
        return AjaxResult.success(configList);
    }

    @ApiOperation(value = "计算模型详情")
    @GetMapping(value = "/getModelsDetail")
    public AjaxResult getModelsDetail(@RequestParam(name = "id", required = true) Long id) {
        BaseModelConfig detail = baseModelConfigService.selectBaseModelConfigById(id);
        return AjaxResult.success(detail);
    }

    @ApiOperation(value = "获取基础数据")
    @GetMapping(value = "/getBaseData")
    public AjaxResult getBaseData() {
        List<SysDictData> data = DictUtils.getDictCache("base_build_type");
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "暖通计算器")
    @GetMapping(value = "/getQ1HeatingCalculate")
    public AjaxResult getQ1HeatingCalculate(@RequestParam(name = "buildingType", required = true) int buildingType,
                                            @RequestParam(name = "buildingArea", required = true) double buildingArea,
                                            @RequestParam(name = "save", required = true) int save
    ) {

        Q1HeatingLoadCalculate hlc = new Q1HeatingLoadCalculate();
        double flow = hlc.heatingLoadCalculate(buildingType, buildingArea, save);
        Map data = new HashMap();
        data.put("value", flow);
        double[] ary = Q1HeatingLoadCalculate.HeadingLoadIndicatorHyperParam.get(buildingType);
        data.put("areaMeasure", ary[save]);
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "热负荷计算")
    @GetMapping(value = "/getQ2TotalHeatingFlow")
    public AjaxResult getQ2TotalHeatingFlow(@RequestParam(name = "heatingLoad", required = true) double heatingLoad,
                                            @RequestParam(name = "inputWater", required = true) Double inputWater,
                                            @RequestParam(name = "outputWater", required = true) Double outputWater
    ) {
        if (inputWater == null || inputWater.doubleValue() <= 0) {
            return AjaxResult.error("请正确填写供水温度");
        }

        if (outputWater == null || outputWater.doubleValue() <= 0) {
            return AjaxResult.error("请正确填写回水温度");
        }
        Q2TotalHeatingFlow q2 = new Q2TotalHeatingFlow();
        double flow = 0.0;
        try {
            flow = q2.totalHeatingFlowCalculate(heatingLoad, inputWater, outputWater);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        Map data = new HashMap();
        data.put("value", flow);
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "系统主管管径")
    @GetMapping(value = "/getQ3MainPipeDiameterCalcate")
    public AjaxResult getQ2TotalHeatingFlow(@RequestParam(name = "totalHeatingFlow", required = true) Double totalHeatingFlow) {
        if (totalHeatingFlow == null || totalHeatingFlow.doubleValue() <= 0) {
            return AjaxResult.error("请正确填写供热系统总流量");
        }

        Q3MainPipeDiameterCalculate q3 = new Q3MainPipeDiameterCalculate();
        List<MinMaxTuple> result = new ArrayList<>();
        try {
            result = q3.calculate(totalHeatingFlow);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(result);
    }

    @ApiOperation(value = "循环水泵选型")
    @GetMapping(value = "/getQ4PumpSelection")
    public AjaxResult getQ2TotalHeatingFlow(@RequestParam(name = "type", required = true) Integer type,
                                            @RequestParam(name = "totalHeatingFlow", required = true) Double totalHeatingFlow) {
        if (type == null) {
            return AjaxResult.error("请选择循环水泵类型");
        }
        if (totalHeatingFlow == null || totalHeatingFlow.doubleValue() <= 0) {
            return AjaxResult.error("请正确填写供热系统总流量");
        }

        Q4PumpSelection q4 = new Q4PumpSelection();
        double liftValue = q4.calLift(type);
        double result = q4.calFlow(totalHeatingFlow);
        Map data = new HashMap();
        data.put("value", result);
        data.put("liftValue", liftValue);
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "热源设备选型")
    @GetMapping(value = "/getQ5BoilerSelection")
    public AjaxResult getQ5BoilerSelection(@RequestParam(name = "type", required = true) Integer type,
                                           @RequestParam(name = "totalHeating", required = true) Double totalHeating,
                                           @RequestParam(name = "boilerCnt", required = false) Integer boilerCnt,
                                           @RequestParam(name = "FirstInputT", required = false) Double FirstInputT,
                                           @RequestParam(name = "FirstOutputT", required = false) Double FirstOutputT,
                                           @RequestParam(name = "SecondInputT", required = false) Double SecondInputT,
                                           @RequestParam(name = "SecondOutputT", required = false) Double SecondOutputT,
                                           @RequestParam(name = "exchangerHeating", required = false) Integer exchangerHeating) {
        if (type == null) {
            return AjaxResult.error("请选择循环水泵类型");
        }
        if (totalHeating == null || totalHeating.doubleValue() <= 0) {
            return AjaxResult.error("请正确填写热负荷值");
        }
        Q5BoilerSelection q5 = new Q5BoilerSelection();
        double result = 0.00;
        if (type != null && type.intValue() == 1) {
            List<Double> resultList = q5.Q51CalBoilerCnt(totalHeating.doubleValue(), boilerCnt);
            return AjaxResult.success(resultList);
        } else if (type != null && type.intValue() == 2) {
            result = q5.Q52calArea(totalHeating, FirstInputT, FirstOutputT, SecondInputT, SecondOutputT);
            System.out.println(2222);
        } else if (type != null && type.intValue() == 3) {
            List<Integer> cntList = q5.Q53calExchangerCnt(totalHeating, exchangerHeating.intValue());
            return AjaxResult.success(cntList);
        } else {
            return AjaxResult.error("参数错误");
        }
        Map data = new HashMap();
        data.put("value", result);
        return AjaxResult.success(data);
    }

    @ApiOperation(value = "供热机房建筑面积")
    @GetMapping(value = "/getQ6HeatingAreaCal")
    public AjaxResult getQ6HeatingAreaCal(@RequestParam(name = "type", required = true) int type,
                                          @RequestParam(name = "buildingType", required = false) Integer buildingType,
                                          @RequestParam(name = "area", required = true) Double area) {
        if (area == null || area.doubleValue() <= 0) {
            return AjaxResult.error("请正确填写建筑面积");
        }
        Q6HeatingAreaCal q6 = new Q6HeatingAreaCal();
        double result = 0.00;
        if (type == 1) {
            result = q6.calBiloer(area);
        } else if (type == 2) {
            result = q6.calExchange(area);
        } else {
            return AjaxResult.error("参数错误");
        }
        Map data = new HashMap();
        data.put("value", result);
        return AjaxResult.success(data);
    }


}
