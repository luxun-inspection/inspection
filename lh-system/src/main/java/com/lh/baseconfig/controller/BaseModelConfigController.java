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
import com.lh.baseconfig.domain.BaseModelConfig;
import com.lh.baseconfig.service.IBaseModelConfigService;
import com.lh.common.utils.poi.ExcelUtil;
import com.lh.common.core.page.TableDataInfo;

/**
 * 暖通模型Controller
 *
 * @author lh
 * @date 2022-11-08
 */
@RestController
@RequestMapping("/baseconfig/modelconfig")
public class BaseModelConfigController extends BaseController
{
    @Autowired
    private IBaseModelConfigService baseModelConfigService;

    /**
     * 查询暖通模型列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:modelconfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseModelConfig baseModelConfig)
    {
        startPage();
        List<BaseModelConfig> list = baseModelConfigService.selectBaseModelConfigList(baseModelConfig);
        return getDataTable(list);
    }

    /**
     * 导出暖通模型列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:modelconfig:export')")
    @Log(title = "暖通模型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseModelConfig baseModelConfig)
    {
        List<BaseModelConfig> list = baseModelConfigService.selectBaseModelConfigList(baseModelConfig);
        ExcelUtil<BaseModelConfig> util = new ExcelUtil<BaseModelConfig>(BaseModelConfig.class);
        return util.exportExcel(list, "暖通模型数据");
    }

    /**
     * 获取暖通模型详细信息
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:modelconfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(baseModelConfigService.selectBaseModelConfigById(id));
    }

    /**
     * 新增暖通模型
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:modelconfig:add')")
    @Log(title = "暖通模型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseModelConfig baseModelConfig)
    {
        return toAjax(baseModelConfigService.insertBaseModelConfig(baseModelConfig));
    }

    /**
     * 修改暖通模型
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:modelconfig:edit')")
    @Log(title = "暖通模型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseModelConfig baseModelConfig)
    {
        return toAjax(baseModelConfigService.updateBaseModelConfig(baseModelConfig));
    }

    /**
     * 删除暖通模型
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:modelconfig:remove')")
    @Log(title = "暖通模型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(baseModelConfigService.deleteBaseModelConfigByIds(ids));
    }
}
