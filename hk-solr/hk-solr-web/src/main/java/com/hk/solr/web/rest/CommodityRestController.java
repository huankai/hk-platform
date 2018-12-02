package com.hk.solr.web.rest;

import com.hk.core.page.QueryModel;
import com.hk.solr.web.domain.Commodity;
import com.hk.solr.web.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangkai
 * @date 2018-12-2 21:45
 */
@RestController
@RequestMapping("/commodity")
public class CommodityRestController {

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("list")
    public Iterable<Commodity> finaAll(@RequestBody QueryModel<Commodity> queryModel) {
        return commodityService.findAll();
    }

    @PostMapping("highlight-list")
    public HighlightPage<Commodity> findHighlightPage(QueryModel<Commodity> queryModel) {
        return null;
    }

}
