package com.hk.emi.repository.jdbc;


import com.hk.core.data.jdbc.repository.StringIdJdbcRepository;
import com.hk.emi.domain.City;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author kevin
 */
public interface CityRepository extends StringIdJdbcRepository<City> {

    /**
     * 查询下级
     *
     * @param parentId parentId
     * @return City
     */
    @Query(value = "select * from sys_city where parent_id = :parentId and parent_id <> id order by code asc")
    List<City> findByParentId(@Param("parentId") String parentId);

}
