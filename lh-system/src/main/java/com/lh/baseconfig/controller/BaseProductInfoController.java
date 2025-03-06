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
import com.lh.baseconfig.domain.BaseProductInfo;
import com.lh.baseconfig.service.IBaseProductInfoService;
import com.lh.common.utils.poi.ExcelUtil;
import com.lh.common.core.page.TableDataInfo;

/**
 * 产品Controller
 *
 * @author lh
 * @date 2022-10-28
 */
@RestController
@RequestMapping("/baseconfig/product")
public class BaseProductInfoController extends BaseController
{
    @Autowired
    private IBaseProductInfoService baseProductInfoService;

    /**
     * 查询产品列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseProductInfo baseProductInfo)
    {
        startPage();
        List<BaseProductInfo> list = baseProductInfoService.selectBaseProductInfoList(baseProductInfo);
        return getDataTable(list);
    }

    /**
     * 导出产品列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:product:export')")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseProductInfo baseProductInfo)
    {
        List<BaseProductInfo> list = baseProductInfoService.selectBaseProductInfoList(baseProductInfo);
        ExcelUtil<BaseProductInfo> util = new ExcelUtil<BaseProductInfo>(BaseProductInfo.class);
        return util.exportExcel(list, "产品数据");
    }

    /**
     * 获取产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:product:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(baseProductInfoService.selectBaseProductInfoById(id));
    }

    /**
     * 新增产品
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:product:add')")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseProductInfo baseProductInfo)
    {
        return toAjax(baseProductInfoService.insertBaseProductInfo(baseProductInfo));
    }

    /**
     * 修改产品
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:product:edit')")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseProductInfo baseProductInfo)
    {
        return toAjax(baseProductInfoService.updateBaseProductInfo(baseProductInfo));
    }

    /**
     * 删除产品
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:product:remove')")
    @Log(title = "产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(baseProductInfoService.deleteBaseProductInfoByIds(ids));
    }
}
