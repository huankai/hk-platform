package com.hk.pms.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
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
    public JsonResult userPage(@RequestBody QueryModel<SysOrg> query) {
        QueryPage<SysOrg> page = orgService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult get(@RequestParam String id) {
        return JsonResult.success(orgService.getOne(id));
    }

    @DeleteMapping
    public JsonResult delete(@RequestParam String id) {
        orgService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping
    public JsonResult saveOrUpdate(@Validated @RequestBody SysOrg org) {
        orgService.insertOrUpdate(org);
        return JsonResult.success();
    }
}
