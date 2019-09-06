package com.hk.emi.repository.jpa.impl;

import com.hk.commons.util.ArrayUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.commons.query.Operator;
import com.hk.core.jdbc.JdbcDaoSupport;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.jdbc.query.CompositeCondition;
import com.hk.core.jdbc.query.SimpleCondition;
import com.hk.core.query.Order;
import com.hk.emi.domain.City;
import com.hk.emi.repository.jpa.custom.CustomCityRepository;
import com.hk.emi.vo.CityExportVo;
import com.hk.platform.commons.ui.Cascader;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author kevin
 * @date 2019-9-2 11:44
 */
public class CityRepositoryImpl extends JdbcDaoSupport implements CustomCityRepository {

    @Override
    public List<CityExportVo> findExportList(City city) {
        SelectArguments arguments = new SelectArguments();
        arguments.setFields(Arrays.asList("code", "full_name", "merger_name", "post_office", "longitude", "latitude"));
        arguments.setFrom("emi_city");
        CompositeCondition conditions = arguments.getConditions();
        if (city.getCityType() != null) {
            conditions.addConditions(new SimpleCondition("city_type", city.getCityType()));
        }
        if (StringUtils.isNotEmpty(city.getCode())) {
            conditions.addConditions(new SimpleCondition("code", Operator.LIKEANYWHERE, city.getCode()));
        }
        if (StringUtils.isNotEmpty(city.getFullName())) {
            conditions.addConditions(new SimpleCondition("full_name", Operator.LIKEANYWHERE, city.getFullName()));
        }
        return jdbcSession.queryForList(arguments, false, CityExportVo.class).getResult();
    }

    @Override
    public List<Cascader> findChildByCityType(byte cityType, boolean isLeaf) {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("emi_city");
        arguments.setFields(ArrayUtils.asArrayList("id AS value", "full_name AS label", isLeaf ? "1 AS is_leaf" : "0 AS is_leaf"));
        arguments.getConditions().addConditions(new SimpleCondition("city_type", cityType));
        arguments.setOrders(ArrayUtils.asArrayList(Order.asc("code")));
        return jdbcSession.queryForList(arguments, false, Cascader.class).getResult();
    }

    @Override
    public List<Cascader> findChildByParentIdAndMaxCityType(Long parentId, Byte maxCityType) {
        SelectArguments arguments = new SelectArguments();
        arguments.setFrom("emi_city");
        arguments.setFields(ArrayUtils.asArrayList("id AS value,city_type AS level,full_name AS label"));
        arguments.getConditions().addConditions(new SimpleCondition("parent_id", parentId));
        arguments.setOrders(ArrayUtils.asArrayList(Order.asc("code")));
        List<Cascader> cascaders = jdbcSession.queryForList(arguments, false, Cascader.class).getResult();
        if (null != maxCityType) {
            cascaders.forEach(item -> item.setIsLeaf(maxCityType.equals(item.getLevel())));
        }
        return cascaders;
    }

    @Override
    public Optional<Cascader> findCascaderById(Long parentId) {
        SelectArguments arguments = new SelectArguments();
        arguments.setFields(ArrayUtils.asArrayList("id AS value,city_type AS level,full_name AS label"));
        arguments.setFrom("emi_city");
        arguments.getConditions().addConditions(new SimpleCondition("id", parentId));
        arguments.setOrders(ArrayUtils.asArrayList(Order.asc("code")));
        return jdbcSession.queryForOne(arguments, Cascader.class);
    }

    @Override
    public List<Cascader> findCascaderByParentId(Long parentId, boolean isLeaf) {
        SelectArguments arguments = new SelectArguments();
        arguments.setFields(ArrayUtils.asArrayList("id AS value", "city_type AS level", "full_name AS label", isLeaf ? "1 AS is_leaf" : "0 AS is_leaf"));
        arguments.setFrom("emi_city");
        arguments.getConditions().addConditions(new SimpleCondition("parent_id", parentId));
        arguments.setOrders(ArrayUtils.asArrayList(Order.asc("code")));
        return jdbcSession.queryForList(arguments, false, Cascader.class).getResult();
    }
}
