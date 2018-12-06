package com.hk.pms.controller;

import com.hk.core.query.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.commons.JsonResult;
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
    public JsonResult<QueryPage<SysPermission>> userPage(@RequestBody QueryModel<SysPermission> query) {
        QueryPage<SysPermission> page = permissionService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult<SysPermission> get(@RequestParam String id) {
        return JsonResult.success(permissionService.findById(id).orElse(null));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> delete(@RequestParam String id) {
        permissionService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysPermission permission) {
        permissionService.insertOrUpdate(permission);
        return JsonResult.success();
    }
}
