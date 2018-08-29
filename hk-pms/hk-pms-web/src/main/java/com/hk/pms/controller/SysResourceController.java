package com.hk.pms.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysResource;
import com.hk.pms.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
 * @date 2018-08-29 15:36
 */
@RestController
@RequestMapping("/resources")
public class SysResourceController extends BaseController {

    private SysResourceService resourceService;

    @Autowired
    public SysResourceController(SysResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/list")
    public JsonResult userPage(@RequestBody QueryModel<SysResource> query) {
        QueryPage<SysResource> page = resourceService.queryForPage(query);
        return JsonResult.success(page);
    }

    @GetMapping
    public JsonResult get(@RequestParam String id) {
        return JsonResult.success(resourceService.getOne(id));
    }

    @DeleteMapping
    public JsonResult delete(@RequestParam String id) {
        resourceService.deleteById(id);
        return JsonResult.success();
    }

    @PostMapping("/disabled")
    public JsonResult disabled(@RequestParam String id) {
        resourceService.disable(id);
        return JsonResult.success();
    }

    @PostMapping("/enabled")
    public JsonResult enabled(@RequestParam String id) {
        resourceService.enable(id);
        return JsonResult.success();
    }

    @PostMapping
    public JsonResult saveOrUpdate(@Validated @RequestBody SysResource resource) {
        resourceService.insertOrUpdate(resource);
        return JsonResult.success();
    }
}
