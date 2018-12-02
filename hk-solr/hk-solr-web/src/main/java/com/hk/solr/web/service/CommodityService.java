package com.hk.solr.web.service;

import com.hk.solr.web.domain.Commodity;

/**
 * @author huangkai
 * @date 2018-12-2 21:43
 */
public interface CommodityService {

    Iterable<Commodity> findAll();

    void deleteById(String id);

    void save(Commodity commodity);
}
