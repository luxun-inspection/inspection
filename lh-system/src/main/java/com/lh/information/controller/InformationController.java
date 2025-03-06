package com.lh.information.controller;

import com.lh.baseconfig.domain.HomePage;
import com.lh.baseconfig.service.HomePageService;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.page.TableDataInfo;
import com.lh.information.domain.News;
import com.lh.information.service.NewsService;
import com.lh.system.domain.SysNotice;
import com.lh.system.service.ISysNoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ht/information")
public class InformationController extends BaseController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private ISysNoticeService sysNoticeService;
    @Autowired
    private HomePageService homePageService;


    @ApiOperation(value = "资讯列表")
    @GetMapping("/list")
    public TableDataInfo list(News news) {
        startPage();
        List<News> newsList = newsService.getNewsList(news);
        return getDataTable(newsList);
    }

    @ApiOperation(value = "新增资讯")
    @PostMapping("/insertNews")
    public AjaxResult insertNews(@RequestBody News news) {
        int maxSort = newsService.getMaxSort();
        news.setCreateTime(new Date());
        news.setStatus("1");
        news.setSort(maxSort + 1);
        int result = newsService.insertNews(news);
        if (result > 0) {
            return AjaxResult.success("更新成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "更新资讯")
    @PostMapping("/updateNews")
    public AjaxResult updateNews(@RequestBody News news) {
        int result = newsService.updateNews(news);
        if (result > 0) {
            return AjaxResult.success("更新成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除资讯")
    @PostMapping("/deleteNewsById")
    public AjaxResult deleteNewsById(@RequestBody News news) {
        int result = newsService.deleteNewsById(news.getId());
        if (result > 0) {
            return AjaxResult.success("删除成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "调整资讯位置")
    @PostMapping("/updateNewsLocation")
    public AjaxResult updateNewsLocation(@RequestBody News news) {
        String ids = news.getIds();
        if (ids != null && !"".equals(ids)) {
            String[] idList = ids.split(",");
            for (int i = 0; i < idList.length; i++) {
                String id = idList[i];
                News news1 = newsService.getNewsById(Long.valueOf(id));
                news1.setSort(i + 1);
                newsService.updateNews(news1);
            }
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "进入资讯详情")
    @GetMapping("/getNewsDetail")
    public AjaxResult getNewsDetail(Long id) {
        News news = newsService.getNewsById(id);
        if (news == null) {
            return AjaxResult.error("该新闻不存在!");
        }
        return AjaxResult.success(news);
    }

    @ApiOperation(value = "获取系统公告")
    @GetMapping("/getSysNotice")
    public TableDataInfo getSysNotice() {
        SysNotice sysNotice = new SysNotice();
        sysNotice.setNoticeType("2");
        List<SysNotice> sysNoticeList = sysNoticeService.selectNoticeList(sysNotice);
        return getDataTable(sysNoticeList);
    }

    @ApiOperation(value = "获取系统公告")
    @GetMapping("/getSysNoticeById")
    public AjaxResult getSysNoticeById(Long id) {
        SysNotice sysNotice = sysNoticeService.selectNoticeById(id);
        return AjaxResult.success(sysNotice);
    }

    @ApiOperation(value = "新增公告")
    @PostMapping("/insertNotice")
    public AjaxResult insertNotice(@RequestBody SysNotice sysNotice) {
        sysNotice.setNoticeType("2");
        int result = sysNoticeService.insertNotice(sysNotice);
        if (result > 0) {
            return AjaxResult.success("新增公告成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "修改公告")
    @PostMapping("/updateNotice")
    public AjaxResult updateNotice(@RequestBody SysNotice sysNotice) {
        int result = sysNoticeService.updateNotice(sysNotice);
        if (result > 0) {
            return AjaxResult.success("公告修改成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "删除公告")
    @PostMapping("/deleteNotice")
    public AjaxResult deleteNotice(@RequestBody HomePage homePage) {
        int result = sysNoticeService.deleteNoticeById(homePage.getId());
        if (result > 0) {
            return AjaxResult.success("公告删除成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "修改公告排列位置")
    @PostMapping("/updateSysNoticePosition")
    public AjaxResult updateSysNoticePosition(@RequestBody SysNotice sysNotice) {
        String ids = sysNotice.getIds();
        if (ids != null && !"".equals(ids)) {
            String[] idList = ids.split(",");
            for (int i = 0; i < idList.length; i++) {
                String id = idList[i];
                SysNotice sysNotice1 = sysNoticeService.selectNoticeById(Long.valueOf(id));
                sysNotice1.setSort(i + 1);
                sysNoticeService.updateNotice(sysNotice1);
            }
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "修改公司简介")
    @PostMapping("/updateCompanyIntroduction")
    public AjaxResult updateCompanyIntroduction(@RequestBody HomePage homePage) {
        homePage.setId(3l);
        int result = homePageService.updateHomePage(homePage);
        if (result > 0) {
            return AjaxResult.success("公司简介修改成功!");
        }
        return AjaxResult.success();
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


}
