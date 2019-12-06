package com.hk.emi.api;

import com.hk.emi.domain.City;
import com.hk.emi.service.CityService;
import com.hk.platform.commons.web.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kevin
 * @date 2018-08-09 09:41
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/city")
public class CityRestController extends BaseController {

    private final CityService cityService;

    @GetMapping(path = "/child/{parentId}")
    public List<City> findByParentId(@PathVariable("parentId") Long parentId) {
        return cityService.findChildList(parentId);
    }

    @RequestMapping(path = "level")
    public List<City> findByLevel(@RequestParam byte level) {
        return cityService.findByCityType(level);
    }


}
