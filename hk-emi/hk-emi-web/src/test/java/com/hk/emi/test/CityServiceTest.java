package com.hk.emi.test;

import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.read.DomReadExcel;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.util.ByteConstants;
import com.hk.commons.util.JsonUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.test.BaseTest;
import com.hk.emi.EMIApplication;
import com.hk.emi.domain.City;
import com.hk.emi.service.CityService;
import com.hk.emi.vo.CityExcelVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

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
        city.setAreaCode("000000");
        city.setCityType(ByteConstants.ZERO);
        city.setCode("000000");
        city.setFullName("中国");
        city.setLatitude(0.0);
        city.setLongitude(0.0);
        city.setPinyin("China");
        city.setPostOffice("000000");
        city.setShortName("中国");
        city.setMergerName("中国");
        System.out.println(cityService.insert(city));
    }

    @Test
    public void importProvinceTest() {
        ReadParam<CityExcelVo> readParam = ReadParam.<CityExcelVo>builder()
                .beanClazz(CityExcelVo.class)
                .sheetMaxIndex(1)
                .build();
        ReadableExcel<CityExcelVo> readableExcel = new DomReadExcel<>(readParam);
        ReadResult<CityExcelVo> result = readableExcel.read(new File("C:/Users/kevin/Desktop/全国地区.xlsx"));
        if (result.hasErrors()) {
            System.out.println(JsonUtils.serialize(result.getErrorLogList(), true));
        }
        List<City> countryList = cityService.findByCityType(ByteConstants.ZERO);
        City city;
        for (CityExcelVo item : result.getAllSheetData()) {
            city = new City();
            city.setParentId(countryList.stream()
                    .filter(country -> StringUtils.equals(country.getFullName(), item.getParentName())).findFirst().get().getId());
            city.setCode(item.getCode());
            city.setAreaCode(item.getAreaCode());
            city.setCityType(item.getCityType());
            city.setFullName(item.getFullName());
            city.setShortName(item.getShortName());
            city.setMergerName(item.getFullName());
            city.setPinyin(item.getPinyin());
            city.setPostOffice(item.getPostOffice());
            city.setLongitude(item.getLongitude());
            city.setLatitude(item.getLatitude());
            cityService.insert(city);
        }

    }
}
