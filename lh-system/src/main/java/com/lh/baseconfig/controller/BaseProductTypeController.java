package com.lh.baseconfig.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lh.common.core.domain.entity.SysDictData;
import com.lh.common.utils.DictUtils;
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
import com.lh.baseconfig.domain.BaseProductType;
import com.lh.baseconfig.service.IBaseProductTypeService;
import com.lh.common.utils.poi.ExcelUtil;
import com.lh.common.core.page.TableDataInfo;

/**
 * 产品类型Controller
 *
 * @author lh
 * @date 2022-10-29
 */
@RestController
@RequestMapping("/baseconfig/producttype")
public class BaseProductTypeController extends BaseController
{
    @Autowired
    private IBaseProductTypeService baseProductTypeService;

    /**
     * 查询产品类型列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:producttype:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseProductType baseProductType)
    {
        startPage();
        List<BaseProductType> list = baseProductTypeService.selectBaseProductTypeList(baseProductType);
        return getDataTable(list);
    }

    @GetMapping("/getAllProductType")
    public AjaxResult getAllProductType()
    {
        List<SysDictData>  dataList= DictUtils.getDictCache("base_product_type");

        List<BaseProductType> list = baseProductTypeService.selectBaseProductTypeList(new BaseProductType());
        List<Map<String,Object>> result=new ArrayList<>();
        dataList.stream().forEach(dict->{
            Map data=new HashMap();
            data.put("label",dict.getDictLabel());
            data.put("value",dict.getDictValue());
            List<BaseProductType> typeList =list.stream().filter(type->type.getCategoryType().equals(dict.getDictValue())).collect(Collectors.toList());
            data.put("options",typeList);
            result.add(data);
        });
        return AjaxResult.success(result);
    }

    /**
     * 导出产品类型列表
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:producttype:export')")
    @Log(title = "产品类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseProductType baseProductType)
    {
        List<BaseProductType> list = baseProductTypeService.selectBaseProductTypeList(baseProductType);
        ExcelUtil<BaseProductType> util = new ExcelUtil<BaseProductType>(BaseProductType.class);
        return util.exportExcel(list, "产品类型数据");
    }

    /**
     * 获取产品类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:producttype:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return AjaxResult.success(baseProductTypeService.selectBaseProductTypeByTypeId(typeId));
    }

    /**
     * 新增产品类型
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:producttype:add')")
    @Log(title = "产品类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseProductType baseProductType)
    {
        return toAjax(baseProductTypeService.insertBaseProductType(baseProductType));
    }

    /**
     * 修改产品类型
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:producttype:edit')")
    @Log(title = "产品类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseProductType baseProductType)
    {
        return toAjax(baseProductTypeService.updateBaseProductType(baseProductType));
    }

    /**
     * 删除产品类型
     */
    @PreAuthorize("@ss.hasPermi('baseconfig:producttype:remove')")
    @Log(title = "产品类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(baseProductTypeService.deleteBaseProductTypeByTypeIds(typeIds));
    }
}
