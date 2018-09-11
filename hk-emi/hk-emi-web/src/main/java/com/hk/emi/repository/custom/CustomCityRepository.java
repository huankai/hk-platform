package com.hk.emi.repository.custom;

import com.hk.emi.domain.City;
import com.hk.emi.vo.CityExcelVo;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-09-07 16:57
 */
public interface CustomCityRepository {

    default List<CityExcelVo> findExportExcelData(City city) {

        return null;
    }
}
