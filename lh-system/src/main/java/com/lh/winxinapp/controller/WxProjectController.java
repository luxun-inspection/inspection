package com.lh.winxinapp.controller;

import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.page.TableDataInfo;
import com.lh.project.domain.ProjectDetail;
import com.lh.project.domain.ProjectInfo;
import com.lh.project.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 小程序端项目管理
 */
@RestController
@RequestMapping("/api/noAuthorization")
public class WxProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    /**
     * 获取所有项目
     *
     * @param projectInfo
     * @return
     */
    @ApiOperation(value = "获取所有项目根据isShow=0返回")
    @GetMapping("/getProjectList")
    public TableDataInfo getProjectListByIsShow(@Validated ProjectInfo projectInfo) {
        startPage();
        List<ProjectInfo> projectList = projectService.getProjectListByIsShow(projectInfo);
        return getDataTable(projectList);
    }

    /**
     * 项目详情
     *
     * @param
     * @return
     */
    @ApiOperation(value = "项目详情")
    @GetMapping("/getProjectInfoById")
    public AjaxResult getProjectInfoById(@Validated ProjectDetail projectDetail) {
        List<ProjectDetail> projectDetailList = projectService.getProjectDetailListByIdOrProject(projectDetail);
        return AjaxResult.success(projectDetailList);
    }


}
