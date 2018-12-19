package com.hk.pms.controller;

import com.hk.commons.util.JsonUtils;
import com.hk.core.authentication.api.SecurityContextUtils;
import com.hk.core.authentication.api.UserPrincipal;
import com.hk.commons.JsonResult;
import com.hk.platform.commons.web.BaseController;
import com.hk.pms.domain.SysOrg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2018-08-14 16:19
 */
@RestController
public class IndexController extends BaseController {

    @GetMapping({"/", "/index"})
    public JsonResult<UserPrincipal> index() {
        return JsonResult.success(SecurityContextUtils.getPrincipal());
    }

    public static void main(String[] args) {
        SysOrg org = new SysOrg();
        org.setAddress("a");
        org.setCreatedBy("ad");
        System.out.println(JsonUtils.serialize(org, true));
    }
}
