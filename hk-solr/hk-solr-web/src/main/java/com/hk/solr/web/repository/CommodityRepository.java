package com.hk.solr.web.repository;

import com.hk.solr.web.domain.Commodity;
import org.springframework.data.solr.repository.SolrCrudRepository;

/**
 * @author huangkai
 * @date 2018-12-2 21:43
 */
public interface CommodityRepository extends SolrCrudRepository<Commodity, String> {

}
