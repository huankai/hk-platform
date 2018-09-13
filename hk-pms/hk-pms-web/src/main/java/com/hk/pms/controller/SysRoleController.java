package com.hk.pms.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysRole;
import com.hk.pms.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
 * @date 2018-08-29 16:20
 */
@RestController
@RequestMapping("/roles")
public class SysRoleController extends BaseController {

    private SysRoleService roleService;

    @Autowired
    public SysRoleController(SysRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/list")
    public JsonResult userPage(@RequestBody QueryModel<SysRole> query) {
        QueryPage<SysRole> page = roleService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult get(@RequestParam String id) {
        return JsonResult.success(roleService.getOne(id));
    }

    @DeleteMapping
    public JsonResult delete(@RequestParam String id) {
        roleService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping("/disabled")
    @PreAuthorize("hasRole('admin')")
    public JsonResult disabled(@RequestParam String id) {
        roleService.disable(id);
        return JsonResult.success();
    }

    @PostMapping("/enabled")
    @PreAuthorize("hasRole('admin')")
    public JsonResult enabled(@RequestParam String id) {
        roleService.enable(id);
        return JsonResult.success();
    }

    @PostMapping
    public JsonResult saveOrUpdate(@Validated @RequestBody SysRole role) {
        roleService.insertOrUpdate(role);
        return JsonResult.success();
    }
}