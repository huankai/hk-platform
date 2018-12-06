package com.hk.solr.web.service;

import com.hk.core.query.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.query.Order;
import com.hk.core.solr.query.Condition;

import java.io.Serializable;
import java.util.List;

/**
 * @author: sjq-278
 * @date: 2018-12-03 17:37
 */
public interface BaseSolrService<T extends Serializable, ID extends Serializable> {

    T saveOrUpdate(T t);

    void deleteById(ID id);

    QueryPage<T> findByPage(QueryModel<T> queryModel);

    QueryPage<T> findByPage(List<Condition> conditions, int pageIndex, int pageSize, Order... orders);
}
