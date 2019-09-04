package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysApp;
import com.hk.pms.service.SysAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author kevin
 * @date 2018-07-26 10:55
 */
@RestController
@RequestMapping("app")
@RequiredArgsConstructor
public class SysAppController extends BaseController {

    private final SysAppService appService;

    @PostMapping(path = "list")
    public JsonResult<QueryPage<SysApp>> userPage(@RequestBody QueryModel<SysApp> query) {
        return JsonResult.success(appService.queryForPage(query));
    }

    @GetMapping(path = "{id}", name = "app-get")
    public JsonResult<SysApp> get(@PathVariable Long id) {
        return JsonResult.success(appService.getOne(id));
    }

    @PostMapping(path = "delete")
    public JsonResult<Void> delete(@RequestParam Long id) {
        appService.markDelete(id);
        return JsonResult.success();
    }

    @PostMapping(path = "recovery")
    public JsonResult<Void> recovery(@RequestParam Long id) {
        appService.markRecovery(id);
        return JsonResult.success();
    }

    @PostMapping(path = "disabled")
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> disabled(@RequestParam Long id) {
        appService.disable(id);
        return JsonResult.success();
    }

    @PostMapping(path = "enabled")
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> enabled(@RequestParam Long id) {
        appService.enable(id);
        return JsonResult.success();
    }

    @PostMapping
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysApp app) {
        appService.insertOrUpdateSelective(app);
        return JsonResult.success();
    }
}
