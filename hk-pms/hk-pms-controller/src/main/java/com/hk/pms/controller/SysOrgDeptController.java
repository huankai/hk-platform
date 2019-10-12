package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysOrgDept;
import com.hk.pms.service.SysOrgDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author kevin
 * @date 2018-08-29 16:26
 */
@RestController
@RequestMapping("dept")
public class SysOrgDeptController extends BaseController {

    private SysOrgDeptService orgDeptService;

    @Autowired
    public SysOrgDeptController(SysOrgDeptService orgDeptService) {
        this.orgDeptService = orgDeptService;
    }

    @PostMapping(path = "list")
    public JsonResult<QueryPage<SysOrgDept>> userPage(@RequestBody QueryModel<SysOrgDept> query) {
        QueryPage<SysOrgDept> page = orgDeptService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping(path = "{id}", name = "dept-get")
    public JsonResult<SysOrgDept> get(@PathVariable String id) {
        return JsonResult.success(orgDeptService.getById(id));
    }

    @DeleteMapping(path = "{id}", name = "dept-delete")
    public JsonResult<Void> delete(@PathVariable String id) {
        orgDeptService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysOrgDept dept) {
        orgDeptService.insertOrUpdateSelective(dept);
        return JsonResult.success();
    }
}
