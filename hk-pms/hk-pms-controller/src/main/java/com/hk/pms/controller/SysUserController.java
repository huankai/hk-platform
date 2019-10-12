package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.commons.util.BeanUtils;
import com.hk.commons.util.StringUtils;
import com.hk.commons.util.TextValueItem;
import com.hk.core.jdbc.query.ConditionQueryModel;
import com.hk.core.page.QueryPage;
import com.hk.platform.commons.enums.UserStateEnum;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysUser;
import com.hk.pms.enums.UserTypeEnum;
import com.hk.pms.service.SysOrgService;
import com.hk.pms.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author kevin
 * @date 2018-08-28 17:24
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class SysUserController extends BaseController {

    private final SysUserService userService;

    private final SysOrgService orgService;

    @PostMapping(path = "list")
    public JsonResult<QueryPage<SysUser>> userPage(@RequestBody ConditionQueryModel query) {
        QueryPage<SysUser> page = userService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping(path = "{id}", name = "user-get")
    public JsonResult<Map<String, Object>> get(@PathVariable Long id) {
        SysUser sysUser = userService.getOne(id);
        Map<String, Object> data = BeanUtils.beanToMapIgnoreEntityProperties(sysUser, "password");
        data.put("orgName", orgService.getOne(sysUser.getOrgId()).getOrgName());
        return JsonResult.success(data);
    }

    @PostMapping(path = "deleted")
    public JsonResult<Void> delete(@RequestParam Long id) {
        userService.markDeleted(id);
        return JsonResult.success();
    }

    @PostMapping(path = "disabled")
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> disabled(@RequestParam Long id) {
        userService.disable(id);
        return JsonResult.success();
    }

    @PostMapping(path = "enabled")
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> enabled(@RequestParam Long id) {
        userService.enable(id);
        return JsonResult.success();
    }

    @GetMapping("status")
    public JsonResult<List<TextValueItem>> getUserStatus() {
        return JsonResult.success(UserStateEnum.LIST);
    }

    @GetMapping("usertypes")
    public JsonResult<List<TextValueItem>> getUserTypes() {
        return JsonResult.success(UserTypeEnum.LIST);
    }

    @PostMapping
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysUser user) {
        userService.insertOrUpdateSelective(user);
        return JsonResult.success();
    }

    @PostMapping(path = "reset_pwd")
    public JsonResult<Void> resetPassword(@RequestParam Long id, @RequestParam String password, @RequestParam String confirm) {
        if (StringUtils.isBlank(password)) {
            return JsonResult.badRequest("密码不能为空");
        }
        if (StringUtils.notEquals(password, confirm)) {
            return JsonResult.badRequest("两次输入密码不一致");
        }
        userService.resetPassword(id, password);
        return JsonResult.success("重设密码成功");
    }

    /**
     * 重设密码
     *
     * @param oldPassword  原密码
     * @param newPassword  新密码
     * @param newPassword2 新密码2
     * @return jsonResult
     */
    @PostMapping(path = "reset_password")
    public JsonResult<Void> resetPassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String newPassword2) {
        if (StringUtils.notEquals(newPassword, newPassword2)) {
            return JsonResult.badRequest("两次输入密码不一致");
        }
        userService.resetPassword(getPrincipal().getUserId(), oldPassword, newPassword);
        return JsonResult.success("重设密码成功");
    }

}
