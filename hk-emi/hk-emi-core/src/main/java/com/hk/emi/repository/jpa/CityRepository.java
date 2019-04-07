package com.hk.emi.repository.jpa;

import com.hk.core.data.jpa.repository.StringIdJpaRepository;
import com.hk.emi.domain.City;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-04-04 22:13
 */
public interface CityRepository extends StringIdJpaRepository<City> {

    List<City> findByParentIdOrderByCodeAsc(String parentId);
}
