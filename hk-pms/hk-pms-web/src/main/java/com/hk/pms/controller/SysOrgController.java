package com.hk.pms.controller;

import com.hk.core.query.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.commons.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author kevin
 * @date 2018-08-29 16:22
 */
@RestController
@RequestMapping("/org")
public class SysOrgController extends BaseController {

    private SysOrgService orgService;

    @Autowired
    public SysOrgController(SysOrgService orgService) {
        this.orgService = orgService;
    }

    @PostMapping("/list")
    public JsonResult<QueryPage<SysOrg>> userPage(@RequestBody QueryModel<SysOrg> query) {
        QueryPage<SysOrg> page = orgService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult<SysOrg> get(@RequestParam String id) {
        return JsonResult.success(orgService.findById(id).orElse(null));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('org_admin')")
    public JsonResult<Void> delete(@RequestParam String id) {
        orgService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping
    @PreAuthorize("hasRole('org_admin')")
    public JsonResult<Void> update(@Validated @RequestBody SysOrg org) {
        orgService.updateById(org);
        return JsonResult.success();
    }
}
