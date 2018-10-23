package com.hk.pms.controller;

import com.hk.commons.util.StringUtils;
import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysUser;
import com.hk.pms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
 * @date: 2018-08-28 17:24
 */
@RestController
@RequestMapping("/users")
public class SysUserController extends BaseController {

    private SysUserService userService;

    @Autowired
    public SysUserController(SysUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/list")
    public JsonResult<QueryPage<SysUser>> userPage(@RequestBody QueryModel<SysUser> query) {
        QueryPage<SysUser> page = userService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult<SysUser> get(@RequestParam String id) {
        return JsonResult.success(userService.findById(id).orElse(null));
    }

    @DeleteMapping
    public JsonResult<Void> delete(@RequestParam String id) {
        userService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping("/disabled")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> disabled(@RequestParam String id) {
        userService.disable(id);
        return JsonResult.success();
    }

    @PostMapping("/enabled")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> enabled(@RequestParam String id) {
        userService.enable(id);
        return JsonResult.success();
    }

    @PostMapping
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysUser user) {
        userService.insertOrUpdate(user);
        return JsonResult.success();
    }

    /**
     * 重设密码
     *
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @param newPassword 新密码2
     * @return jsonResult
     */
    @PostMapping("reset_password")
    public JsonResult<Void> resetPassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String newPassword2) {
        if (StringUtils.notEquals(newPassword, newPassword2)) {
            return JsonResult.badRequest("两次输入密码不一致");
        }
        userService.resetPassword(getPrincipal().getUserId(), oldPassword, newPassword);
        return JsonResult.success("重设密码成功");
    }

}
