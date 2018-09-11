package com.hk.emi.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.web.JsonResult;
import com.hk.emi.domain.ChildCode;
import com.hk.emi.service.ChildCodeService;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
 * @date 2018-08-20 11:01
 */
@RestController
@RequestMapping("childCode")
public class ChildCodeController extends BaseController {

    private final ChildCodeService childCodeService;

    @Autowired
    public ChildCodeController(ChildCodeService childCodeService) {
        this.childCodeService = childCodeService;
    }

    /**
     * 列表
     *
     * @param query query
     * @return JsonResult
     */
    @PostMapping(path = "/list")
    public JsonResult list(@RequestBody QueryModel<ChildCode> query) {
        return JsonResult.success(childCodeService.queryForPage(query));
    }

    /**
     * 明细
     *
     * @param id id
     * @return JsonResult
     */
    @GetMapping(path = "{id}")
    public JsonResult get(@PathVariable String id) {
        return JsonResult.success(childCodeService.getOne(id));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return JsonResult
     */
    @DeleteMapping(path = "{id")
    @PreAuthorize("hasRole('admin')")
    public JsonResult deleteById(@PathVariable String id) {
        childCodeService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * Save Or Update
     *
     * @param childCode childCode
     * @return JsonResult
     */
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult saveOrUpdate(@Validated @RequestBody ChildCode childCode) {
        childCodeService.insertOrUpdate(childCode);
        return JsonResult.success();
    }
}
