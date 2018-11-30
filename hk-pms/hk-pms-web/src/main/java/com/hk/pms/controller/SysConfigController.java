package com.hk.pms.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.commons.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysConfig;
import com.hk.pms.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统配置
 *
 * @author: kevin
 * @date: 2018-09-20 20:05
 */
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController {

    private final SysConfigService configService;

    @Autowired
    public SysConfigController(SysConfigService configService) {
        this.configService = configService;
    }

    @PostMapping("/list")
    public JsonResult<QueryPage<SysConfig>> userPage(@RequestBody QueryModel<SysConfig> query) {
        QueryPage<SysConfig> page = configService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping("{id}")
    public JsonResult<SysConfig> get(@PathVariable String id) {
        return JsonResult.success(configService.findById(id).orElse(null));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> delete(@PathVariable String id) {
        configService.deleteById(id);
        return JsonResult.success();
    }


    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysConfig config) {
        configService.insertOrUpdate(config);
        return JsonResult.success();
    }


}
