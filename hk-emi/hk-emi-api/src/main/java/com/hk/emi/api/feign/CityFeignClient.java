package com.hk.emi.api.feign;

import com.hk.emi.api.domain.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-07-13 15:27
 */
@FeignClient(name = "hk-emi")
@RequestMapping("/api/city")
public interface CityFeignClient {

    /**
     * 获取子级
     *
     * @param parentId parentId
     * @return all childs.
     */
    @GetMapping("child/{parentId}")
    List<City> getChildList(@PathVariable("parentId") String parentId);

    /**
     * 获取所有省
     *
     * @return all provinces.
     */
    default List<City> getProvinceList() {
        return getList(2);
    }

    /**
     * 获取所有市
     *
     * @return all cities.
     */
    default List<City> getCityList() {
        return getList(3);
    }

    /**
     * 获取所有区或县
     *
     * @return all areas.
     */
    default List<City> getAreaList() {
        return getList(4);
    }

    @GetMapping("level")
    List<City> getList(int level);

}
