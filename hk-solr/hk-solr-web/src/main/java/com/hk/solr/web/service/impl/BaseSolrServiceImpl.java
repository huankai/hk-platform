package com.hk.solr.web.service.impl;

import com.hk.core.query.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.query.Order;
import com.hk.core.solr.query.Condition;
import com.hk.core.solr.respoitory.BaseSolrRepository;
import com.hk.solr.web.service.BaseSolrService;

import java.io.Serializable;
import java.util.List;

/**
 * @author: sjq-278
 * @date: 2018-12-03 17:40
 */
public abstract class BaseSolrServiceImpl<T extends Serializable, ID extends Serializable> implements BaseSolrService<T, ID> {

    protected abstract BaseSolrRepository<T, ID> getSolrRepository();

    @Override
    public T saveOrUpdate(T t) {
        return getSolrRepository().save(t);
    }

    @Override
    public void deleteById(ID id) {
        getSolrRepository().deleteById(id);
    }

    @Override
    public QueryPage<T> findByPage(QueryModel<T> queryModel) {
        return getSolrRepository().findByPage(queryModel);
    }

    @Override
    public QueryPage<T> findByPage(List<Condition> conditions, int pageIndex, int pageSize, Order... orders) {
        return getSolrRepository().findByPage(conditions, pageIndex, pageSize, orders);
    }
}
