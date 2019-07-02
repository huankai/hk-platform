package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huangkai
 * @date 2019-01-03 14:39
 */
@RestController
@RequestMapping("userRole")
public class UserRoleController extends BaseController {

    private SysUserRoleService userRoleService;

    @Autowired
    public void setUserRoleService(SysUserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    /**
     * 更新用户角色
     *
     * @param userId  userId
     * @param roleIds 角色id
     */
    @PostMapping(path = "update")
    public JsonResult<Void> updateUserRole(@RequestParam Long userId, @RequestParam Long[] roleIds) {
        userRoleService.updateUserRole(userId, roleIds);
        return JsonResult.success();
    }

    /**
     * 角色添加用户
     *
     * @param roleId  角色id
     * @param userIds userId
     */
    @PostMapping(path = "addUser")
    public JsonResult<Void> addRoleUser(@RequestParam Long roleId, @RequestParam Long[] userIds) {
        userRoleService.addRoleUser(roleId, userIds);
        return JsonResult.success();
    }

    @DeleteMapping(path = "{id}", name = "userRole-delete")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        userRoleService.deleteById(id);
        return JsonResult.success();
    }

}
