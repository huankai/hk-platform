package com.hk.emi.mappers;

import com.hk.data.mybatis.BaseMapper;
import com.hk.emi.domain.City;
import com.hk.emi.vo.CityExcelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: kevin
 * @date 2018-09-11 21:21
 */
@Mapper
public interface CityExcelVoMapper extends BaseMapper<CityExcelVo> {

    /**
     * 查询Excel 导出的数据
     *
     * @param city city
     * @return List
     */
    List<CityExcelVo> findExportList(City city);
}
