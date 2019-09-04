package com.hk.emi.repository.jpa.custom;

import com.hk.emi.domain.City;
import com.hk.emi.vo.CityExportVo;
import com.hk.platform.commons.ui.Cascader;

import java.util.List;

/**
 * @author kevin
 * @date 2019-9-2 11:43
 */
public interface CustomCityRepository {

    List<CityExportVo> findExportList(City city);

    List<Cascader> findChildByCityType(byte cityType);

    List<Cascader> findChildByParentIdAndMaxCityType(Long parentId, Byte maxCityType);
}
