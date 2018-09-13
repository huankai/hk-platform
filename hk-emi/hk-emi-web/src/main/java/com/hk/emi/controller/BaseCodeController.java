package com.hk.emi.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.query.Order;
import com.hk.core.web.JsonResult;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.service.BaseCodeService;
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
@RequestMapping("baseCode")
public class BaseCodeController extends BaseController {

    private final BaseCodeService baseCodeService;

    @Autowired
    public BaseCodeController(BaseCodeService baseCodeService) {
        this.baseCodeService = baseCodeService;
    }

    /**
     * 列表
     *
     * @param query query
     * @return JsonResult
     */
    @PostMapping(path = "list")
    public JsonResult list(@RequestBody QueryModel<BaseCode> query) {
        return JsonResult.success(baseCodeService.queryForPage(query));
    }

    @GetMapping(path = "all")
    public JsonResult findAll() {
        return JsonResult.success(baseCodeService.findAll(Order.asc("baseCode")));
    }

    /**
     * 明细
     *
     * @param id id
     * @return JsonResult
     */
    @GetMapping(path = "{id}")
    public JsonResult get(@PathVariable String id) {
        BaseCode baseCode = baseCodeService.getOne(id);
        return JsonResult.success(baseCode);
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return JsonResult
     */
    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('admin')")
    public JsonResult deleteById(@PathVariable String id) {
        baseCodeService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * Save Or Update
     *
     * @param baseCode baseCode
     * @return JsonResult
     */
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult saveOrUpdate(@Validated @RequestBody BaseCode baseCode) {
        baseCodeService.insertOrUpdate(baseCode);
        return JsonResult.success();
    }
}