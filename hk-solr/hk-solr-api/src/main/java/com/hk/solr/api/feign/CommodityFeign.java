package com.hk.solr.api.feign;

import com.hk.core.page.QueryModel;
import com.hk.solr.api.entity.Commodity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangkai
 * @date 2018-12-2 23:18
 */
@FeignClient(name = "hk-solr-web")
@RequestMapping("/commodity")
public interface CommodityFeign {

    @PostMapping("list")
    Iterable<Commodity> findPage(QueryModel<Commodity> queryModel);

    @PostMapping("highlight-list")
    HighlightPage<Commodity> findHighlightPage(QueryModel<Commodity> queryModel);
}
