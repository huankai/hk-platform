package com.hk.emi.controller;

import com.hk.commons.JsonResult;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.emi.domain.BaseCode;
import com.hk.emi.domain.ChildCode;
import com.hk.emi.service.BaseCodeService;
import com.hk.emi.service.ChildCodeService;
import com.hk.platform.commons.web.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kevin
 * @date 2018-08-20 11:01
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("childCode")
public class ChildCodeController extends BaseController {

    private final ChildCodeService childCodeService;

    private final BaseCodeService baseCodeService;

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
    public JsonResult<Map<String, Object>> get(@PathVariable Long id) {
        ChildCode childCode = childCodeService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("记录不存在:" + id));
        BaseCode baseCode = baseCodeService.getOne(childCode.getBaseCodeId());
        Map<String, Object> result = new HashMap<>();
        result.put("baseCodeName", baseCode.getCodeName());
        result.put("childCode", childCode);
        return JsonResult.success(result);
    }

    /**
     * 查询子级
     *
     * @param baseCodeId baseCodeId
     * @return {@link ChildCode}
     */
    @GetMapping(path = "findClild")
    public JsonResult<List<ChildCode>> childList(@RequestParam Long baseCodeId) {
        return JsonResult.success(childCodeService.findByBaseCodeId(baseCodeId));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return {@link JsonResult}
     */
    @PostMapping(path = "{id}", name = "childcode-delete")
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
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
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody ChildCode childCode) {
        childCodeService.insertOrUpdateSelective(childCode);
        return JsonResult.success();
    }
}
