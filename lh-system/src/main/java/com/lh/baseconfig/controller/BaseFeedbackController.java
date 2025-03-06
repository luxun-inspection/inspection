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
import com.lh.baseconfig.domain.BaseFeedback;
import com.lh.baseconfig.service.IBaseFeedbackService;
import com.lh.common.utils.poi.ExcelUtil;
import com.lh.common.core.page.TableDataInfo;

/**
 * 用户反馈Controller
 *
 * @author lh
 * @date 2022-10-28
 */
@RestController
@RequestMapping("/baseconfig/feedback")
public class BaseFeedbackController extends BaseController
{
    @Autowired
    private IBaseFeedbackService baseFeedbackService;

    /**
     * 查询用户反馈列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseFeedback baseFeedback)
    {
        startPage();
        List<BaseFeedback> list = baseFeedbackService.selectBaseFeedbackList(baseFeedback);
        return getDataTable(list);
    }

    /**
     * 导出用户反馈列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:feedback:export')")
    @Log(title = "用户反馈", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseFeedback baseFeedback)
    {
        List<BaseFeedback> list = baseFeedbackService.selectBaseFeedbackList(baseFeedback);
        ExcelUtil<BaseFeedback> util = new ExcelUtil<BaseFeedback>(BaseFeedback.class);
        return util.exportExcel(list, "用户反馈数据");
    }

    /**
     * 获取用户反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:feedback:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseFeedbackService.selectBaseFeedbackById(id));
    }

    /**
     * 新增用户反馈
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:feedback:add')")
    @Log(title = "用户反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseFeedback baseFeedback)
    {
        return toAjax(baseFeedbackService.insertBaseFeedback(baseFeedback));
    }

    /**
     * 修改用户反馈
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:feedback:edit')")
    @Log(title = "用户反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseFeedback baseFeedback)
    {
        return toAjax(baseFeedbackService.updateBaseFeedback(baseFeedback));
    }

    /**
     * 删除用户反馈
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:feedback:remove')")
    @Log(title = "用户反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseFeedbackService.deleteBaseFeedbackByIds(ids));
    }
}
