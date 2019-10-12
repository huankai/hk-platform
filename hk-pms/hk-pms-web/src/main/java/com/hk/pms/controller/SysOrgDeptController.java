package com.hk.pms.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysOrgDept;
import com.hk.pms.service.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
 * @date: 2018-08-29 16:26
 */
@RestController
@RequestMapping("/dept")
public class SysOrgDeptController extends BaseController {

    private SysOrgDeptService orgDeptService;

    @Autowired
    public SysOrgDeptController(SysOrgDeptService orgDeptService) {
        this.orgDeptService = orgDeptService;
    }

    @PostMapping("/list")
    public JsonResult<QueryPage<SysOrgDept>> userPage(@RequestBody QueryModel<SysOrgDept> query) {
        QueryPage<SysOrgDept> page = orgDeptService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult<SysOrgDept> get(@RequestParam String id) {
        return JsonResult.success(orgDeptService.findById(id).orElse(null));
    }

    @DeleteMapping
    public JsonResult<Void> delete(@RequestParam String id) {
        orgDeptService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysOrgDept dept) {
        orgDeptService.insertOrUpdate(dept);
        return JsonResult.success();
    }
}
