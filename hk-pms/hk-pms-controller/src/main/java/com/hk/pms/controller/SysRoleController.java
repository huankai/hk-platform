package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysRole;
import com.hk.pms.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author kevin
 * @date 2018-08-29 16:20
 */
@RestController
@RequestMapping("roles")
public class SysRoleController extends BaseController {

    private SysRoleService roleService;

    @Autowired
    public SysRoleController(SysRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping(path = "list")
    public JsonResult<QueryPage<SysRole>> page(@RequestBody QueryModel<SysRole> query) {
        return JsonResult.success(roleService.queryForPage(query));
    }

    @GetMapping(path = "{id}", name = "role-get")
    public JsonResult<SysRole> get(@PathVariable String id) {
        return JsonResult.success(roleService.getById(id));
    }

    @DeleteMapping(path = "{id}", name = "role-delete")
    public JsonResult<Void> delete(@PathVariable String id) {
        roleService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping(path = "disabled")
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> disabled(@RequestParam String id) {
        roleService.disable(id);
        return JsonResult.success();
    }

    @PostMapping(path = "enabled")
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> enabled(@RequestParam String id) {
        roleService.enable(id);
        return JsonResult.success();
    }

    @PostMapping
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysRole role) {
        roleService.insertOrUpdateSelective(role);
        return JsonResult.success();
    }
}
