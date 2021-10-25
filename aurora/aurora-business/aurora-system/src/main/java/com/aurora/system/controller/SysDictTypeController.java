package com.aurora.system.controller;

import com.aurora.common.core.utils.poi.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.constant.SystemConstants;
import com.aurora.system.domain.SysDictType;
import com.aurora.system.service.SysDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * describe:
 *
 * @Author Guo Huaijian
 * @Date 2021/10/10
 * @E-mail guohuaijian9527@gmail.com
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends AbstractController {
    @Autowired
    private SysDictTypeService dictTypeService;

    @PreAuthorize("hasAuthority('system:dict:list')")
    @GetMapping("/list")
    public Result list(SysDictType dictType) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return Result.success(getPageDate(list));
    }

    @Log(value = "字典类型", LogType = LogType.EXPORT)
    @PreAuthorize("hasAuthority('system:dict:export')")
    @GetMapping("/export")
    public void export(SysDictType dictType) throws IOException {
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<>(SysDictType.class);
        util.exportExcel(list, "字典类型");
    }

    /**
     * 查询字典类型详细
     */
    @PreAuthorize("hasAuthority('system:dict:query')")
    @GetMapping(value = "/{dictId}")
    public Result getInfo(@PathVariable Long dictId) {
        return Result.success(dictTypeService.selectDictTypeById(dictId));
    }

    /**
     * 新增字典类型
     */
    @PreAuthorize("hasAuthority('system:dict:add')")
    @Log(value = "字典类型", LogType = LogType.INSERT)
    @PostMapping
    public Result add(@Validated @RequestBody SysDictType dict) {
        if (SystemConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return Result.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(SecurityUtil.getUsername());
        return toResult(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize("hasAuthority('system:dict:edit')")
    @Log(value = "字典类型", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysDictType dict) {
        if (SystemConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return Result.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(SecurityUtil.getUsername());
        return toResult(dictTypeService.updateDictType(dict));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("hasAuthority('system:dict:remove')")
    @Log(value = "字典类型", LogType = LogType.DELETE)
    @DeleteMapping("/{dictIds}")
    public Result remove(@PathVariable Long[] dictIds) {
        dictTypeService.deleteDictTypeByIds(dictIds);
        return Result.success();
    }

    /**
     * 刷新字典缓存
     */
    @PreAuthorize("hasAuthority('system:dict:remove')")
    @Log(value = "字典类型", LogType = LogType.CLEAN)
    @DeleteMapping("/refreshCache")
    public Result refreshCache() {
        dictTypeService.resetDictCache();
        return Result.success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionSelect")
    public Result optionSelect() {
        List<SysDictType> dictTypes = dictTypeService.selectDictTypeAll();
        return Result.success(dictTypes);
    }
}
