package com.hk.solr.api.feign;

import com.hk.core.query.QueryModel;
import com.hk.core.page.SimpleQueryPage;
import com.hk.solr.api.request.AppRequest;
import com.hk.solr.api.response.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author huangkai
 * @date 2018-12-2 23:18
 */
@FeignClient(name = SolrService.SERVICE_NAME, path = SolrService.CONTEXT_PATH)
@RequestMapping("/app")
public interface AppFeign {

    @PostMapping("list")
    SimpleQueryPage<AppResponse> findPage(@RequestBody QueryModel<AppRequest> queryModel);

}
