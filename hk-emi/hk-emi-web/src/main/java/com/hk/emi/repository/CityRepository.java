package com.hk.emi.repository;


import com.hk.core.data.jpa.repository.StringRepository;
import com.hk.emi.domain.City;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: kevin
 */
public interface CityRepository extends StringRepository<City> {

    /**
     * 查询下级
     *
     * @param parentId parentId
     * @return
     */
    @Query(value = "select id,parent_id,code,city_type,full_name,short_name,post_office,description,created_by,created_date,last_modified_by,last_modified_date" +
            " from sys_city where parent_id = ?1 and parent_id <> id order by code asc", nativeQuery = true)
    List<City> findByParentId(String parentId);
}
