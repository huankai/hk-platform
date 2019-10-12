package com.hk.emi.api.feign;

import com.hk.emi.api.feign.fallback.CityFeignClientFallback;
import com.hk.emi.api.response.CityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kevin
 * @date 2018-07-13 15:27
 */
@FeignClient(name = EmiService.SERVICE_NAME, path = EmiService.CONTEXT_PATH,
        fallback = CityFeignClientFallback.class)
@RequestMapping("/api/city")
public interface CityFeignClient {

    /**
     * 获取子级
     *
     * @param parentId parentId
     * @return all childs.
     */
    @GetMapping("child/{parentId}")
    List<CityResponse> getChildList(@PathVariable("parentId") Long parentId);

    /**
     * 获取所有省
     *
     * @return all provinces.
     */
    default List<CityResponse> getProvinceList() {
        return getList(2);
    }

    /**
     * 获取所有市
     *
     * @return all cities.
     */
    default List<CityResponse> getCityList() {
        return getList(3);
    }

    /**
     * 获取所有区或县
     *
     * @return all areas.
     */
    default List<CityResponse> getAreaList() {
        return getList(4);
    }

    @RequestMapping(path = {"level"},method = RequestMethod.GET)
    List<CityResponse> getList(@RequestParam(value = "level") int level);

}
