package com.hk.emi.repository.jpa;

import com.hk.core.data.jpa.repository.LongIdJpaRepository;
import com.hk.emi.domain.City;
import com.hk.emi.repository.jpa.custom.CustomCityRepository;

import java.util.List;

/**
 * @author huangkai
 * @date 2019-04-04 22:13
 */
public interface CityRepository extends LongIdJpaRepository<City>, CustomCityRepository {

    List<City> findByParentIdOrderByCodeAsc(Long parentId);

    List<City> findByCityType(byte cityType);


}
