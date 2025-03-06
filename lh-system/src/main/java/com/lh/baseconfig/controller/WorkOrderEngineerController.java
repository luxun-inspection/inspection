package com.lh.baseconfig.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lh.common.annotation.Log;
import com.lh.common.core.controller.BaseController;
import com.lh.common.core.domain.AjaxResult;
import com.lh.common.enums.BusinessType;
import com.lh.baseconfig.domain.WorkOrderEngineer;
import com.lh.baseconfig.service.IWorkOrderEngineerService;
import com.lh.common.utils.poi.ExcelUtil;
import com.lh.common.core.page.TableDataInfo;

/**
 * 工程师处理工单情况Controller
 *
 * @author lh
 * @date 2022-12-08
 */
@RestController
@RequestMapping("/baseconfig/engineer")
public class WorkOrderEngineerController extends BaseController
{
    @Autowired
    private IWorkOrderEngineerService workOrderEngineerService;

    /**
     * 查询工程师处理工单情况列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:engineer:list')")
    @GetMapping("/list")
    public TableDataInfo list(WorkOrderEngineer workOrderEngineer)
    {
        startPage();
        List<WorkOrderEngineer> list = workOrderEngineerService.selectWorkOrderEngineerList(workOrderEngineer);
        return getDataTable(list);
    }

    /**
     * 导出工程师处理工单情况列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:engineer:export')")
    @Log(title = "工程师处理工单情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WorkOrderEngineer workOrderEngineer)
    {
        List<WorkOrderEngineer> list = workOrderEngineerService.selectWorkOrderEngineerList(workOrderEngineer);
        ExcelUtil<WorkOrderEngineer> util = new ExcelUtil<WorkOrderEngineer>(WorkOrderEngineer.class);
        return util.exportExcel(list, "工程师处理工单情况数据");
    }

    /**
     * 获取工程师处理工单情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:engineer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(workOrderEngineerService.selectWorkOrderEngineerById(id));
    }

    /**
     * 新增工程师处理工单情况
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:engineer:add')")
    @Log(title = "工程师处理工单情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WorkOrderEngineer workOrderEngineer)
    {
        return toAjax(workOrderEngineerService.insertWorkOrderEngineer(workOrderEngineer));
    }

    /**
     * 修改工程师处理工单情况
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:engineer:edit')")
    @Log(title = "工程师处理工单情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WorkOrderEngineer workOrderEngineer)
    {
        return toAjax(workOrderEngineerService.updateWorkOrderEngineer(workOrderEngineer));
    }

    /**
     * 删除工程师处理工单情况
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:engineer:remove')")
    @Log(title = "工程师处理工单情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(workOrderEngineerService.deleteWorkOrderEngineerByIds(ids));
    }
}
