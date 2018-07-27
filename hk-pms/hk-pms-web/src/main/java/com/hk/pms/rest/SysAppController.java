package com.hk.pms.rest;

import com.hk.core.web.JsonResult;
import com.hk.pms.service.SysAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: kevin
 * @date 2018-07-26 10:55
 */
@RestController
@RequestMapping("app")
public class SysAppController {

    @Autowired
    private SysAppService appService;

    @GetMapping("{id}")
    public JsonResult get(@PathVariable String id) {
        return JsonResult.success(appService.findOne(id).orElse(null));
    }
}
