package com.hk.pms.api;

import com.hk.commons.JsonResult;
import com.hk.commons.util.ArrayUtils;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysUser;
import com.hk.pms.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class SysUserRestController extends BaseController {

    @Autowired
    private SysUserService userService;

    /**
     * @param query query
     * @return QueryPage<User>
     */
    @PostMapping(path = "list")
    public QueryPage<SysUser> findUserByPage(QueryModel<SysUser> query) {
        return userService.queryForPage(query);
    }

    /**
     * @param user user
     * @return list
     */
    @GetMapping
    public List<SysUser> findAll(SysUser user) {
        return userService.findAll(user);
    }

    /**
     * @param userId userId
     * @return userId
     */
    @GetMapping(value = "{id}", name = "api-find-userid")
    public JsonResult<SysUser> findByUserId(@PathVariable("id") Long userId) {
        return JsonResult.success(userService.findById(userId).orElse(null));
    }

    /**
     * @param account account
     * @return account
     */
    @PostMapping(path = "find_account")
    public JsonResult<SysUser> findByAccount(@RequestParam String account) {
        return JsonResult.success(userService.findByAccount(account).orElse(null));
    }

    /**
     * @param phone phone
     * @return user
     */
    @PostMapping(path = "find_phone")
    public JsonResult<SysUser> findByPhone(@RequestParam String phone) {
        return JsonResult.success(userService.findByPhone(phone).orElse(null));
    }

    /**
     * @param email email
     * @return user
     */
    @PostMapping(path = "find_email")
    public JsonResult<SysUser> findByEmail(@RequestParam String email) {
        return JsonResult.success(userService.findByEmail(email).orElse(null));
    }

    /**
     * @param users users
     */
    @PostMapping
    public Collection<SysUser> saveOrUpdate(@RequestBody SysUser[] users) {
        return userService.insertOrUpdateSelective(ArrayUtils.asArrayList(users));
    }

    /**
     * @param userIds userIds
     */
    @DeleteMapping
    public JsonResult<Void> deleteByIds(Long[] userIds) {
        userService.deleteByIds(userIds);
        return JsonResult.success();
    }

}
