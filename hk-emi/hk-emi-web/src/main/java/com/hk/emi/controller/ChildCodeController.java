package com.hk.emi.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.commons.JsonResult;
import com.hk.emi.domain.ChildCode;
import com.hk.emi.service.ChildCodeService;
import com.hk.platform.commons.web.BaseController;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: kevin
 * @date: 2018-08-20 11:01
 */
@Api(description = "子级字典管理")
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
    public JsonResult<QueryPage<ChildCode>> list(@RequestBody QueryModel<ChildCode> query) {
        return JsonResult.success(childCodeService.queryForPage(query));
    }

    /**
     * 明细
     *
     * @param id id
     * @return JsonResult
     */
    @GetMapping(path = "{id}")
    public JsonResult<ChildCode> get(@PathVariable String id) {
        return JsonResult.success(childCodeService.findById(id).orElse(null));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return JsonResult
     */
    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> deleteById(@PathVariable String id) {
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
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody ChildCode childCode) {
        childCodeService.insertOrUpdate(childCode);
        return JsonResult.success();
    }
}
