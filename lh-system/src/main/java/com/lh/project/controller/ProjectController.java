package com.lh.project.controller;


import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.page.TableDataInfo;
import com.lh.project.domain.ProjectDetail;
import com.lh.project.domain.ProjectInfo;
import com.lh.project.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * PC 项目管理
 */
@RestController
@RequestMapping("/ht/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    /**
     * 获取所有项目
     *
     * @param projectInfo
     * @return
     */
    @ApiOperation(value = "获取所有项目")
    @GetMapping("/getProjectList")
    public TableDataInfo getProjectServiceList(ProjectInfo projectInfo) {
        startPage();
        List<ProjectInfo> projectList = projectService.getProjectList(projectInfo);
        return getDataTable(projectList);
    }

    /**
     * 详情
     *
     * @param projectDetail
     * @return
     */
    @ApiOperation(value = "详情")
    @GetMapping("/getProjectInfoById")
    public AjaxResult getProjectInfoById(@RequestBody ProjectDetail projectDetail) {
        List<ProjectDetail> projectDetailList = projectService.getProjectDetailList(projectDetail);
        return AjaxResult.success(projectDetailList);
    }

    /**
     * 添加项目
     *
     * @param projectInfo
     * @return
     */
    @ApiOperation(value = "添加项目")
    @PostMapping("/add")
    public AjaxResult insertProject(@RequestBody ProjectInfo projectInfo) {
        projectInfo.setCreateTime(new Date());
        projectInfo.setIsDel("1");
        int result = projectService.insertProjectInfo(projectInfo);
        if (result > 0) {
            return AjaxResult.success("项目添加成功!");
        }
        return AjaxResult.success();
    }

    /**
     * 修改项目
     *
     * @param projectInfo
     * @return
     */
    @ApiOperation(value = "修改项目")
    @PostMapping("/update")
    public AjaxResult updateProject(@RequestBody ProjectInfo projectInfo) {
        int result = projectService.updateProjectInfo(projectInfo);
        if (result > 0) {
            return AjaxResult.success("项目修改成功!");
        }
        return AjaxResult.success();
    }

    /**
     * 删除项目
     *
     * @param projectInfo
     * @return
     */
    @ApiOperation(value = "删除项目")
    @PostMapping("/delete")
    public AjaxResult deleteProjectById(@RequestBody ProjectInfo projectInfo) {
        int result = projectService.deleteProjectInfo(projectInfo.getId());
        if (result > 0) {
            return AjaxResult.success("项目删除成功!");
        }
        return AjaxResult.success();
    }

    /**
     * 获取所有项目明细
     *
     * @param projectDetail
     * @return
     */
    @ApiOperation(value = "获取所有项目明细")
    @GetMapping("/getProjectDetailList")
    public TableDataInfo getProjectDetailList(ProjectDetail projectDetail) {
        startPage();
        List<ProjectDetail> projectDetailList = projectService.getProjectDetailList(projectDetail);
        return getDataTable(projectDetailList);
    }

    /**
     * 根据项目ID获取所有项目明细
     *
     * @param projectDetail
     * @return
     */
    @ApiOperation(value = "根据项目ID获取所有项目明细")
    @GetMapping("/getProjectDetailListById")
    public TableDataInfo getProjectDetailListByIdOrProject(ProjectDetail projectDetail) {
        startPage();
        List<ProjectDetail> projectDetailList = projectService.getProjectDetailListByIdOrProject(projectDetail);
        return getDataTable(projectDetailList);
    }

    /**
     * 明细详情
     *
     * @param projectDetail
     * @return
     */
    @ApiOperation(value = "明细详情")
    @PostMapping("/getProjectDetailById")
    public AjaxResult getProjectDetailById(@RequestBody ProjectDetail projectDetail) {
        ProjectDetail projectDetail1 = projectService.getProjectDetailById(projectDetail.getId());
        return AjaxResult.success(projectDetail1);
    }

    /**
     * 添加项目明细
     *
     * @param projectDetail
     * @return
     */
    @ApiOperation(value = "添加项目明细")
    @PostMapping("/addDetail")
    public AjaxResult insertProjectDetail(@RequestBody ProjectDetail projectDetail) {
        projectDetail.setCreateTime(new Date());
        int result = projectService.insertProjectDetail(projectDetail);
        if (result > 0) {
            return AjaxResult.success("项目明细添加成功!");
        }
        return AjaxResult.success();
    }

    /**
     * 修改项目明细
     *
     * @param projectDetail
     * @return
     */
    @ApiOperation(value = "修改项目明细")
    @PostMapping("/updateDetail")
    public AjaxResult updateProjectDetail(@RequestBody ProjectDetail projectDetail) {
        int result = projectService.updateProjectDetail(projectDetail);
        if (result > 0) {
            return AjaxResult.success("项目明细修改成功!");
        }
        return AjaxResult.success();
    }

    /**
     * 根据Id删除项目明细
     *
     * @param projectDetail
     * @return
     */
    @ApiOperation(value = "删除项目明细")
    @PostMapping("/deleteDetail")
    public AjaxResult deleteProjectDetailById(@RequestBody ProjectDetail projectDetail) {
        int result = projectService.deleteProjectDetail(projectDetail.getId());
        if (result > 0) {
            return AjaxResult.success("项目明细删除成功!");
        }
        return AjaxResult.success();
    }

}
