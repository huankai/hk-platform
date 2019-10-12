package com.hk.emi.service;

import com.hk.commons.poi.excel.model.ErrorLog;
import com.hk.core.service.jpa.JpaBaseService;
import com.hk.emi.domain.City;
import com.hk.emi.vo.CityExcelVo;

import java.io.InputStream;
import java.util.List;

/**
 * @author kevin
 * @date 2018-07-17 16:49
 */
public interface CityService extends JpaBaseService<City, String> {

    /**
     * 查询下级City
     *
     * @param parentId parentId
     * @return 子级城市
     */
    List<City> findChildList(String parentId);

    /**
     * 导入
     *
     * @param excelInput excel文件
     * @return 如果解析出错，返回错误信息 {@link ErrorLog}
     */
    List<ErrorLog<CityExcelVo>> importExcel(InputStream excelInput);

    /**
     * 根据条件查询生成Excel Byte
     *
     * @param city city 查询条件
     * @return Excel byte[] 数据
     */
    byte[] exportExcelData(City city);

    /**
     * 根据类型查询城市
     *
     * @param cityType 城市等级类型
     */
    List<City> findByCityType(byte cityType);
}
