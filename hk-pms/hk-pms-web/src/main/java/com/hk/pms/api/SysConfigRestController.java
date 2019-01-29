package com.hk.pms.api;

import com.hk.commons.util.ListResult;
import com.hk.commons.util.TextValueItem;
import com.hk.core.data.jdbc.query.CompositeCondition;
import com.hk.core.data.jdbc.query.SimpleCondition;
import com.hk.pms.domain.SysConfig;
import com.hk.pms.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/sysconfig")
public class SysConfigRestController {

    @Autowired
    private SysConfigService configService;

    @GetMapping("{appId}")
    public List<TextValueItem> getSysConfig(@PathVariable String appId) {
        ListResult<SysConfig> sysConfigList = configService.findAll(new CompositeCondition(new SimpleCondition("app_id", appId)));
        List<TextValueItem> result = new ArrayList<>();
        for (SysConfig sysConfig : sysConfigList) {
            result.add(new TextValueItem(sysConfig.getName(), sysConfig.getValue()));
        }
        return result;
    }

}
