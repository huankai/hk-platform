package com.hk.emi.repository.jpa.impl;

import com.hk.commons.util.StringUtils;
import com.hk.core.data.commons.query.Operator;
import com.hk.core.jdbc.JdbcDaoSupport;
import com.hk.core.jdbc.SelectArguments;
import com.hk.core.jdbc.query.CompositeCondition;
import com.hk.core.jdbc.query.SimpleCondition;
import com.hk.emi.domain.City;
import com.hk.emi.repository.jpa.custom.CustomCityRepository;
import com.hk.emi.vo.CityExportVo;

import java.util.Arrays;
import java.util.List;

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
}
