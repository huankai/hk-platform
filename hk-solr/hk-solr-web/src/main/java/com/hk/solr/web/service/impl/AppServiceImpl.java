package com.hk.solr.web.service.impl;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.solr.respoitory.BaseSolrRepository;
import com.hk.solr.web.domain.App;
import com.hk.solr.web.repository.solr.AppRepository;
import com.hk.solr.web.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: sjq-278
 * @date: 2018-12-03 11:44
 */
@Service
public class AppServiceImpl extends BaseSolrServiceImpl<App, String> implements AppService {

    private final AppRepository appRepository;

    @Autowired
    public AppServiceImpl(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    protected BaseSolrRepository<App, String> getSolrRepository() {
        return appRepository;
    }

    @Override
    public QueryPage<App> findByPage(QueryModel<App> queryModel) {
        return appRepository.findByPage(queryModel);
    }
}
