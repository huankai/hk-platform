package com.hk.emi.controller;

import com.hk.commons.JsonResult;
import com.hk.core.page.QueryPage;
import com.hk.core.query.Order;
import com.hk.core.query.QueryModel;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.service.BaseCodeService;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author kevin
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
    public JsonResult<QueryPage<BaseCode>> list(@RequestBody QueryModel<BaseCode> query) {
        return JsonResult.success(baseCodeService.queryForPage(query));
    }

    @GetMapping
    public JsonResult<Iterable<BaseCode>> findAll() {
        return JsonResult.success(baseCodeService.findAll(Order.asc("baseCode")));
    }

    /**
     * 明细
     *
     * @param id id
     * @return JsonResult
     */
    @GetMapping(path = "{id}", name = "basecode-get")
    public JsonResult<BaseCode> get(@PathVariable String id) {
        return JsonResult.success(baseCodeService.getOne(id));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return JsonResult
     */
    @DeleteMapping(path = "{id}", name = "basecode-delete")
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> deleteById(@PathVariable String id) {
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
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody BaseCode baseCode) {
        baseCodeService.insertOrUpdateSelective(baseCode);
        return JsonResult.success();
    }
}
