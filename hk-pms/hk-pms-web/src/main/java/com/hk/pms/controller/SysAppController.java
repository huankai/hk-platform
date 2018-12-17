package com.hk.pms.controller;

import com.hk.core.query.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.commons.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysApp;
import com.hk.pms.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author kevin
 * @date 2018-07-26 10:55
 */
@RestController
@RequestMapping("app")
public class SysAppController extends BaseController {

    private SysAppService appService;

    @Autowired
    public SysAppController(SysAppService appService) {
        this.appService = appService;
    }

    @PostMapping("/list")
    public JsonResult<QueryPage<SysApp>> userPage(@RequestBody QueryModel<SysApp> query) {
        QueryPage<SysApp> page = appService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping("{id}")
    public JsonResult<SysApp> get(@PathVariable String id) {
        return JsonResult.success(appService.getById(id));
    }

    @DeleteMapping("{id}")
    public JsonResult<Void> delete(@PathVariable String id) {
        appService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping("/disabled")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> disabled(@RequestParam String id) {
        appService.disable(id);
        return JsonResult.success();
    }

    @PostMapping("/enabled")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> enabled(@RequestParam String id) {
        appService.enable(id);
        return JsonResult.success();
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysApp app) {
        appService.insertOrUpdate(app);
        return JsonResult.success();
    }
}
