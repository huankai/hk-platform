package com.hk.solr.web.service.impl;

import com.hk.core.solr.respoitory.BaseSolrRepository;
import com.hk.solr.web.domain.Commodity;
import com.hk.solr.web.repository.solr.CommodityRepository;
import com.hk.solr.web.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2018-12-2 21:44
 */
@Service
public class CommodityServiceImpl extends BaseSolrServiceImpl<Commodity, String> implements CommodityService {

    private final CommodityRepository commodityRepository;

    @Autowired
    public CommodityServiceImpl(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    @Override
    protected BaseSolrRepository<Commodity, String> getSolrRepository() {
        return commodityRepository;
    }

}
