package com.hk.pms.controller;

import com.hk.core.query.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.commons.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysResource;
import com.hk.pms.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author kevin
 * @date 2018-08-29 15:36
 */
@RestController
@RequestMapping("/menu")
public class SysResourceController extends BaseController {

    private SysResourceService resourceService;

    @Autowired
    public SysResourceController(SysResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/list")
    public JsonResult<QueryPage<SysResource>> userPage(@RequestBody QueryModel<SysResource> query) {
        QueryPage<SysResource> page = resourceService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult<SysResource> get(@RequestParam String id) {
        return JsonResult.success(resourceService.findById(id).orElse(null));
    }

    @DeleteMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> delete(@RequestParam String id) {
        resourceService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping("/disabled")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> disabled(@RequestParam String id) {
        resourceService.disable(id);
        return JsonResult.success();
    }

    @PostMapping("/enabled")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> enabled(@RequestParam String id) {
        resourceService.enable(id);
        return JsonResult.success();
    }

    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody SysResource resource) {
        resourceService.insertOrUpdate(resource);
        return JsonResult.success();
    }
}
