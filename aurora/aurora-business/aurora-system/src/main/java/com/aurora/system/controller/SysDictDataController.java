package com.aurora.system.controller;

import com.aurora.common.core.utils.StringUtil;
import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.domain.SysDictData;
import com.aurora.system.service.SysDictDataService;
import com.aurora.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * describe:
 *
 * @author Guo Huaijian
 * @date 2021/10/10
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends AbstractController {

    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictTypeService dictTypeService;

    @PreAuthorize("hasAuthority('system:dict:list')")
    @GetMapping("/list")
    public Result list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getPageResult(list);
    }

    @Log(value = "字典数据", LogType = LogType.EXPORT)
    @PreAuthorize("hasAuthority('system:dict:export')")
    @GetMapping("/export")
    public void export(SysDictData dictData) throws IOException {
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<>(SysDictData.class);
        util.exportExcel(list, "字典数据");
    }

    /**
     * 查询字典数据详细
     */
    @PreAuthorize("hasAuthority('system:dict:query')")
    @GetMapping(value = "/{dictCode}")
    public Result getInfo(@PathVariable Long dictCode) {
        return Result.success(dictDataService.selectDictDataById(dictCode));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public Result dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtil.isNull(data)) {
            data = new ArrayList<>();
        }
        return Result.success(data);
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("hasAuthority('system:dict:add')")
    @Log(value = "字典数据", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysDictData dict) {
        dict.setCreateBy(SecurityUtil.getUsername());
        return toResult(dictDataService.insertDictData(dict));
    }

    /**
     * 修改保存字典类型
     */
    @PreAuthorize("hasAuthority('system:dict:edit')")
    @Log(value = "字典数据", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysDictData dict) {
        dict.setUpdateBy(SecurityUtil.getUsername());
        return toResult(dictDataService.updateDictData(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("hasAuthority('system:dict:remove')")
    @Log(value = "字典类型", LogType = LogType.DELETE)
    @DeleteMapping("/{dictCodes}")
    public Result remove(@PathVariable Long[] dictCodes) {
        dictDataService.deleteDictDataByIds(dictCodes);
        return Result.success();
    }
}
