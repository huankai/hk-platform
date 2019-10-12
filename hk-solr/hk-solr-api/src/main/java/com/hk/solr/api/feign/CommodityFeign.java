package com.hk.solr.api.feign;

import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.solr.api.request.CommodityRequest;
import com.hk.solr.api.response.CommodityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangkai
 * @date 2018-12-2 23:18
 */
@FeignClient(name = SolrService.SERVICE_NAME, path = SolrService.CONTEXT_PATH)
@RequestMapping("/commodity")
public interface CommodityFeign {

    @PostMapping("list")
    QueryPage<CommodityResponse> findPage(QueryModel<CommodityRequest> queryModel);

    @PostMapping("highlight-list")
    HighlightPage<CommodityResponse> findHighlightPage(QueryModel<CommodityRequest> queryModel);
}
