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
import com.lh.baseconfig.domain.BaseConsultArticle;
import com.lh.baseconfig.service.IBaseConsultArticleService;
import com.lh.common.utils.poi.ExcelUtil;
import com.lh.common.core.page.TableDataInfo;

/**
 * 咨询介绍Controller
 *
 * @author lh
 * @date 2022-10-28
 */
@RestController
@RequestMapping("/baseconfig/article")
public class BaseConsultArticleController extends BaseController
{
    @Autowired
    private IBaseConsultArticleService baseConsultArticleService;

    /**
     * 查询咨询介绍列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:article:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseConsultArticle baseConsultArticle)
    {
        startPage();
        List<BaseConsultArticle> list = baseConsultArticleService.selectBaseConsultArticleList(baseConsultArticle);
        return getDataTable(list);
    }

    /**
     * 导出咨询介绍列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:article:export')")
    @Log(title = "咨询介绍", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseConsultArticle baseConsultArticle)
    {
        List<BaseConsultArticle> list = baseConsultArticleService.selectBaseConsultArticleList(baseConsultArticle);
        ExcelUtil<BaseConsultArticle> util = new ExcelUtil<BaseConsultArticle>(BaseConsultArticle.class);
        return util.exportExcel(list, "咨询介绍数据");
    }

    /**
     * 获取咨询介绍详细信息
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:article:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(baseConsultArticleService.selectBaseConsultArticleById(id));
    }

    /**
     * 新增咨询介绍
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:article:add')")
    @Log(title = "咨询介绍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseConsultArticle baseConsultArticle)
    {
        return toAjax(baseConsultArticleService.insertBaseConsultArticle(baseConsultArticle));
    }

    /**
     * 修改咨询介绍
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:article:edit')")
    @Log(title = "咨询介绍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseConsultArticle baseConsultArticle)
    {
        return toAjax(baseConsultArticleService.updateBaseConsultArticle(baseConsultArticle));
    }

    /**
     * 删除咨询介绍
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:article:remove')")
    @Log(title = "咨询介绍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(baseConsultArticleService.deleteBaseConsultArticleByIds(ids));
    }
}
