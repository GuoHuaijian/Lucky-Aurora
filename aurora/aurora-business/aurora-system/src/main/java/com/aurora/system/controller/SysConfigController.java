package com.aurora.system.controller;

import com.aurora.common.config.annotation.RepeatSubmit;
import com.aurora.common.core.utils.ExcelUtil;
import com.aurora.common.core.web.controller.AbstractController;
import com.aurora.common.core.web.domain.Result;
import com.aurora.common.log.annotation.Log;
import com.aurora.common.log.enums.LogType;
import com.aurora.common.security.utils.SecurityUtil;
import com.aurora.system.constant.SystemConstants;
import com.aurora.system.domain.SysConfig;
import com.aurora.system.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/config")
public class SysConfigController extends AbstractController {

    @Autowired
    private SysConfigService configService;

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("@hasAuthority('system:config:list')")
    @GetMapping("/list")
    public Result list(SysConfig config) {
        startPage();
        List<SysConfig> list = configService.selectConfigList(config);
        return Result.success(getPageDate(list));
    }

    /**
     * 参数管理
     *
     * @param config
     * @return
     */
    @Log(value = "参数管理", LogType = LogType.EXPORT)
    @PreAuthorize("@hasAuthority('system:config:export')")
    @GetMapping("/export")
    public Result export(SysConfig config) {
        List<SysConfig> list = configService.selectConfigList(config);
        ExcelUtil<SysConfig> util = new ExcelUtil<SysConfig>(SysConfig.class);
        return util.exportExcel(list, "参数数据");
    }

    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("@hasAuthority('system:config:query')")
    @GetMapping(value = "/{configId}")
    public Result getInfo(@PathVariable Long configId) {
        return Result.success(configService.selectConfigById(configId));
    }

    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public Result getConfigKey(@PathVariable String configKey) {
        return Result.success(configService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("@hasAuthority('system:config:add')")
    @Log(value = "参数管理", LogType = LogType.INSERT)
    @PostMapping
    @RepeatSubmit
    public Result add(@Validated @RequestBody SysConfig config) {
        if (SystemConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return Result.error("新增参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setCreateBy(SecurityUtil.getUsername());
        return toResult(configService.insertConfig(config));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@hasAuthority('system:config:edit')")
    @Log(value = "参数管理", LogType = LogType.UPDATE)
    @PutMapping
    public Result edit(@Validated @RequestBody SysConfig config) {
        if (SystemConstants.NOT_UNIQUE.equals(configService.checkConfigKeyUnique(config))) {
            return Result.error("修改参数'" + config.getConfigName() + "'失败，参数键名已存在");
        }
        config.setUpdateBy(SecurityUtil.getUsername());
        return toResult(configService.updateConfig(config));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@hasAuthority('system:config:remove')")
    @Log(value = "参数管理", LogType = LogType.DELETE)
    @DeleteMapping("/{configIds}")
    public Result remove(@PathVariable Long[] configIds) {
        configService.deleteConfigByIds(configIds);
        return Result.success();
    }

    /**
     * 刷新参数缓存
     */
    @PreAuthorize("@hasAuthority('system:config:remove')")
    @Log(value = "参数管理", LogType = LogType.CLEAN)
    @DeleteMapping("/refreshCache")
    public Result refreshCache() {
        configService.resetConfigCache();
        return Result.success();
    }
}
