package com.lh.casedetail.controller;

import com.lh.casedetail.domain.ProjectCase;
import com.lh.casedetail.service.ProjectCaseService;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.core.page.TableDataInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ht/projectCase")
public class ProjectCaseController extends BaseController {

    @Autowired
    private ProjectCaseService projectCaseService;


    @ApiOperation(value = "获取所有案例")
    @GetMapping("/getProjectCase")
    public TableDataInfo getServiceItem(ProjectCase projectCae){
        startPage();
        List<ProjectCase> projectCaseList = projectCaseService.getAllProjectCase(projectCae);
        return getDataTable(projectCaseList);
    }

    @ApiOperation(value = "添加案例")
    @PostMapping("/insertProjectCase")
    public AjaxResult insertProjectCase(@RequestBody ProjectCase projectCase){
        int maxsort = projectCaseService.getMaxSort();
        projectCase.setCreateTime(new Date());
        projectCase.setSort(maxsort+1);
        int result = projectCaseService.insertProjectCase(projectCase);
        if(result>0){
            return AjaxResult.success("案例添加成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "调整资讯位置")
    @PostMapping("/updateCaseLocation")
    public AjaxResult updateCaseLocation(@RequestBody ProjectCase projectCase){
        String ids = projectCase.getIds();
        if(ids!=null&&!"".equals(ids)){
            String [] idList = ids.split(",");
            for(int i=0;i<idList.length;i++){
                String id = idList[i];
                ProjectCase projectCase1 = projectCaseService.getCaseDetailById(Long.valueOf(id));
                projectCase1.setSort(i+1);
                projectCaseService.updateProjectCase(projectCase1);
            }
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "修改案例")
    @PostMapping("/updateProjectCase")
    public AjaxResult updateProjectCase(@RequestBody ProjectCase projectCase){
        int result = projectCaseService.updateProjectCase(projectCase);
        if(result>0){
            return AjaxResult.success("案例修改成功!");
        }
        return AjaxResult.success();
    }

    @ApiOperation(value = "详情")
    @GetMapping("/getCaseDetailById")
    public AjaxResult getCaseDetailById(ProjectCase projectCase){
        ProjectCase projectCase1 = projectCaseService.getCaseDetailById(projectCase.getId());
        return AjaxResult.success(projectCase1);
    }

    @ApiOperation(value = "详情")
    @PostMapping("/deleteProjectCaseById")
    public AjaxResult deleteProjectCaseById(@RequestBody ProjectCase projectCase){
        int result = projectCaseService.deleteProjectCase(projectCase.getId());
        if(result>0){
            return AjaxResult.success("案例删除成功!");
        }
        return AjaxResult.success();
    }





}
