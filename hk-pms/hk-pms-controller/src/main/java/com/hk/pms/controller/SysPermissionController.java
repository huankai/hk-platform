package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
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
 * @author kevin
 * @date 2018-08-29 16:16
 */
@RestController
@RequestMapping("permissions")
public class SysPermissionController extends BaseController {

    private SysPermissionService permissionService;

    @Autowired
    public SysPermissionController(SysPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping(path = "list")
    public JsonResult<QueryPage<SysPermission>> userPage(@RequestBody QueryModel<SysPermission> query) {
        return JsonResult.success(permissionService.queryForPage(query));
    }

    @GetMapping
    public JsonResult<SysPermission> get(@RequestParam Long id) {
        return JsonResult.success(permissionService.getOne(id));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> delete(@RequestParam Long id) {
        permissionService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysPermission permission) {
        permissionService.insertOrUpdateSelective(permission);
        return JsonResult.success();
    }
}
