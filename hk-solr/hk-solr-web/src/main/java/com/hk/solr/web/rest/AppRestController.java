package com.hk.solr.web.rest;

import com.hk.core.query.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.solr.web.domain.App;
import com.hk.solr.web.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sjq-278
 * @date: 2018-12-03 11:45
 */
@RestController
@RequestMapping("/app")
public class AppRestController {

    @Autowired
    private AppService appService;

    @RequestMapping("list")
    public QueryPage<App> list(@RequestBody QueryModel<App> queryModel) {
        return appService.findByPage(queryModel);
    }
}
