package com.hk.pms.controller;

import com.hk.commons.JsonResult;
import com.hk.commons.util.BeanUtils;
import com.hk.core.page.QueryPage;
import com.hk.core.query.Order;
import com.hk.core.query.QueryModel;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysOrg;
import com.hk.pms.service.SysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author kevin
 * @date 2018-08-29 16:22
 */
@RestController
@RequestMapping("/org")
public class SysOrgController extends BaseController {

    private SysOrgService orgService;

    @Autowired
    public SysOrgController(SysOrgService orgService) {
        this.orgService = orgService;
    }

    @PostMapping(path = "list")
    public JsonResult<QueryPage<SysOrg>> page(@RequestBody QueryModel<SysOrg> query) {
        return JsonResult.success(orgService.queryForPage(query));
    }

    /**
     * 查询所有
     *
     * @return {@link JsonResult}
     */
    @GetMapping
    public JsonResult<Iterable<SysOrg>> findAll() {
        return JsonResult.success(orgService.findAll(Order.asc("org_code")));
    }

    @GetMapping(path = "{id}", name = "org-get")
    public JsonResult<?> get(@PathVariable Long id) {
        SysOrg org = orgService.getOne(id);
        if (org.getParentId() != 0) {
            Map<String, Object> result = BeanUtils.beanToMapIgnoreEntityProperties(org);
            result.put("parentName", orgService.getOne(org.getParentId()).getOrgName());
            return JsonResult.success(result);
        }
        return JsonResult.success(org);
    }

    @PostMapping(path = "delete", name = "org-delete")
//    @PreAuthorize("hasRole('org_admin')")
    public JsonResult<Void> delete(@RequestParam Long id) {
        orgService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * @param currentOrgId 当前机构id
     * @param statusCheck  状态检查，如果检查，会将不可以状态的机构显示 为 disabled（不可选）　状态
     */
    @GetMapping(path = "root")
    public JsonResult<List<?>> getRootList(Long currentOrgId, boolean statusCheck) {
        return JsonResult.success(statusCheck ? orgService.findRootList(currentOrgId) : orgService.findRootList());
    }

    @GetMapping(path = "child")
    public JsonResult<List<?>> getChildList(@RequestParam Long parentId, Long currentId) {
        return JsonResult.success(orgService.findChildList(parentId, currentId));
    }

    /**
     * 保存或更新
     *
     * @param org org
     * @return {@link JsonResult}
     */
    @PostMapping
//    @PreAuthorize("hasRole('org_admin')")
    public JsonResult<Void> update(@Validated @RequestBody SysOrg org) {
        orgService.insertOrUpdateSelective(org);
        return JsonResult.success();
    }
}
