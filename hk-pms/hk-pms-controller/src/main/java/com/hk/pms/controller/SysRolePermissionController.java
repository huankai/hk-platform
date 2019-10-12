package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 角色与权限
 *
 * @author huangkai
 * @date 2019-01-03 15:24
 */
@RestController
@RequestMapping("rolePermission")
public class SysRolePermissionController extends BaseController {

    private SysRolePermissionService rolePermissionService;

    @Autowired
    public void setRolePermissionService(SysRolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    /**
     * 更新角色权限
     *
     * @param roleId      角色id
     * @param permissions 权限id
     */
    @PostMapping(path = "update")
    public JsonResult<Void> updateUserRole(@RequestParam Long roleId, @RequestParam Long[] permissions) {
        rolePermissionService.updateRolePermission(roleId, permissions);
        return JsonResult.success();
    }

    /**
     * 权限与角色关联
     *
     * @param roleId  角色id
     * @param userIds userId
     */
    @PostMapping(path = "addUser")
    public JsonResult<Void> addRoleUser(@RequestParam Long permissionId, @RequestParam Long[] roleIds) {
        rolePermissionService.addPermissionRole(permissionId, roleIds);
        return JsonResult.success();
    }

    @DeleteMapping(path = "{id}", name = "rolePermission-delete")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        rolePermissionService.deleteById(id);
        return JsonResult.success();
    }
}
