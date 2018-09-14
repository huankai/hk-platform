package com.hk.pms.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysPermission;
import com.hk.pms.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 权限管理
 *
 * @author: kevin
 * @date: 2018-08-29 16:16
 */
@RestController
@RequestMapping("/permissions")
public class SysPermissionController extends BaseController {

    private SysPermissionService permissionService;

    @Autowired
    public SysPermissionController(SysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/list")
    public JsonResult userPage(@RequestBody QueryModel<SysPermission> query) {
        QueryPage<SysPermission> page = permissionService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult get(@RequestParam String id) {
        return JsonResult.success(permissionService.getOne(id));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult delete(@RequestParam String id) {
        permissionService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult saveOrUpdate(@Validated @RequestBody SysPermission permission) {
        permissionService.insertOrUpdate(permission);
        return JsonResult.success();
    }
}
