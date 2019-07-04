package com.hk.emi.test;

import com.hk.commons.util.ByteConstants;
import com.hk.core.test.BaseTest;
import com.hk.emi.EMIApplication;
import com.hk.emi.domain.City;
import com.hk.emi.service.CityService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author kevin
 * @date 2019-7-4 12:00
 */
@SpringBootTest(classes = EMIApplication.class)
public class CityServiceTest extends BaseTest {

    @Autowired
    private CityService cityService;


    @Test
    public void insert() {
        City city = new City();
        city.setParentId(city.getId());
        city.setAreaCode("");
        city.setCityType(ByteConstants.ONE);
        city.setCode("");
        city.setFullName("");
        city.setLatitude(0.0);
        city.setLongitude(0.0);
        city.setPinyin("");
        city.setPostOffice("");
        city.setShortName("");
        city.setMergerName("");
        System.out.println(cityService.insert(city));
    }
}
