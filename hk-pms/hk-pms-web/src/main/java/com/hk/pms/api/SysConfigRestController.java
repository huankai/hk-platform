package com.hk.pms.api;

import com.hk.commons.util.TextValueItem;
import com.hk.pms.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/sysconfig")
public class SysConfigRestController {

    @Autowired
    private SysConfigService configService;

    @GetMapping("{appId}")
    public List<TextValueItem> getSysConfig(@PathVariable Long appId) {
        return configService.findByAppId(appId)
                .stream()
                .map(item -> new TextValueItem(item.getName(), item.getValue()))
                .collect(Collectors.toList());
    }

}
