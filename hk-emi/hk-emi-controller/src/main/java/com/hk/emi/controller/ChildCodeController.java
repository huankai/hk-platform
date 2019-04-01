package com.hk.emi.controller;

import com.hk.commons.JsonResult;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.emi.domain.ChildCode;
import com.hk.emi.service.ChildCodeService;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kevin
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
     * @return {@link ChildCode}
     */
    @PostMapping(path = "list")
    public JsonResult<QueryPage<ChildCode>> list(@RequestBody QueryModel<ChildCode> query) {
        return JsonResult.success(childCodeService.queryForPage(query));
    }

    /**
     * 明细
     *
     * @param id id
     * @return {@link ChildCode}
     */
    @GetMapping(path = "{id}", name = "childcode-get")
    public JsonResult<ChildCode> get(@PathVariable String id) {
        return JsonResult.success(childCodeService.getOne(id));
    }

    /**
     * 查询子级
     *
     * @param baseCodeId baseCodeId
     * @return {@link ChildCode}
     */
    @GetMapping(path = "{baseCodeId}", name = "childcode-child")
    public JsonResult<List<ChildCode>> childList(@PathVariable String baseCodeId) {
        return JsonResult.success(childCodeService.findByBaseCodeId(baseCodeId));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return {@link JsonResult}
     */
    @DeleteMapping(path = "{id}", name = "childcode-delete")
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> deleteById(@PathVariable String id) {
        childCodeService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * Save Or Update
     *
     * @param childCode childCode
     * @return {@link JsonResult}
     */
    @PostMapping
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody ChildCode childCode) {
        childCodeService.insertOrUpdateSelective(childCode);
        return JsonResult.success();
    }
}
