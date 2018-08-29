package com.hk.emi.api;

import com.hk.emi.domain.ChildCode;
import com.hk.emi.service.ChildCodeService;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-08-21 09:25
 */
@RestController
@RequestMapping("/code")
public class CodeRestController extends BaseController {

    @Autowired
    private ChildCodeService childCodeService;

    @GetMapping("child")
    public List<ChildCode> findChildList(@RequestParam("parentId") String parentId, String[] ignore) {
        return childCodeService.findByBaseCodeIgnoreChildCodes(parentId, ignore);
    }


}
