package com.hk.solr.web.service.impl;

import com.hk.solr.web.domain.Commodity;
import com.hk.solr.web.repository.CommodityRepository;
import com.hk.solr.web.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author huangkai
 * @date 2018-12-2 21:44
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepository;

    @Override
    public Iterable<Commodity> findAll() {
        return commodityRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        commodityRepository.deleteById(id);
    }

    @Override
    public void save(Commodity commodity) {
        commodityRepository.save(commodity);
    }
}
