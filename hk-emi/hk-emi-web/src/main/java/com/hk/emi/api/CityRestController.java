package com.hk.emi.api;

import com.hk.emi.domain.City;
import com.hk.emi.service.CityService;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kevin
 * @date 2018-08-09 09:41
 */
@RestController
@RequestMapping("/api/city")
public class CityRestController extends BaseController {

    @Autowired
    private CityService cityService;

    @GetMapping(path = "/child/{parentId}")
    public List<City> findByParentId(@PathVariable("parentId") String parentId) {
        return cityService.findChildList(parentId);
    }

    @RequestMapping(path = "level")
    public List<City> findByLevel(@RequestParam byte level) {
        return cityService.findByCityType(level);
    }


}
