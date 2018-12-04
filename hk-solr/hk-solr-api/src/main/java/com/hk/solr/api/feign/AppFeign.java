package com.hk.solr.api.feign;

import com.hk.core.page.QueryModel;
import com.hk.core.page.SimpleQueryPage;
import com.hk.solr.api.SolrService;
import com.hk.solr.api.entity.App;
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
    SimpleQueryPage<App> findPage(@RequestBody QueryModel<App> queryModel);

}
