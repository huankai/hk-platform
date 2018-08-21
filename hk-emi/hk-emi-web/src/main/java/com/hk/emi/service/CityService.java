/**
 *
 */
package com.hk.emi.service;

import com.hk.core.service.BaseService;
import com.hk.emi.domain.City;

import java.io.InputStream;
import java.util.List;

/**
 * @author: kevin
 */
public interface CityService extends BaseService<City, String> {

    /**
     * 查询下级City
     *
     * @param parentId
     * @return
     */
    List<City> findChildList(String parentId);

    /**
     * 导入
     *
     * @param excelInput excel文件
     */
    void importExcel(InputStream excelInput);

    /**
     * 根据条件查询生成Excel Byte
     *
     * @param city
     * @return
     */
    InputStream exportExcelData(City city);
}
