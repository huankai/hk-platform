package com.hk.emi.service.impl;


import com.hk.commons.poi.excel.model.ErrorLog;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SaxReadExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.util.BeanUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.emi.domain.City;
import com.hk.emi.mappers.CityExcelVoMapper;
import com.hk.emi.repository.jpa.CityRepository;
import com.hk.emi.service.CityService;
import com.hk.emi.vo.CityExcelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @author kevin
 */
@Service
@CacheConfig(cacheNames = "City")
public class CityServiceImpl extends JpaServiceImpl<City, Long> implements CityService {

    private final CityRepository cityRepository;

    private CityExcelVoMapper cityExcelVoMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Autowired
    public void setCityExcelVoMapper(CityExcelVoMapper cityExcelVoMapper) {
        this.cityExcelVoMapper = cityExcelVoMapper;
    }

    @Override
    protected BaseJpaRepository<City, Long> getBaseRepository() {
        return cityRepository;
    }

    /**
     * 查询下级City
     *
     * @param parentId parentId
     * @return List<City>
     */
    @Override
    public List<City> findChildList(Long parentId) {
        return Objects.isNull(parentId) ? Collections.emptyList() : cityRepository.findByParentIdOrderByCodeAsc(parentId);
    }

    /**
     * 导入
     *
     * @param in excel文件
     */
    @Override
    public List<ErrorLog<CityExcelVo>> importExcel(InputStream in) {
        ReadParam<CityExcelVo> readableParam = ReadParam.<CityExcelVo>builder()
                .beanClazz(CityExcelVo.class)
                .parseAll(false)
                .sheetStartIndex(0)
                .sheetMaxIndex(1)
                .build();
        ReadableExcel<CityExcelVo> readableExcel = new SaxReadExcel<>(readableParam);
        ReadResult<CityExcelVo> result = readableExcel.read(in);
        if (result.hasErrors()) {
            return result.getErrorLogList();
        }
        List<CityExcelVo> resultList = result.getAllSheetData();
        Iterable<City> cityList = findAll();
        List<City> cityInsertList = new ArrayList<>();
        City city;
        for (CityExcelVo item : resultList) {
            city = new City();
            BeanUtils.copyProperties(item, city);
            if (StringUtils.isNotEmpty(item.getParentName())) {
                Optional<City> parentCityOptional = StreamSupport.stream(cityList.spliterator(), false)
                        .filter(c -> StringUtils.equals(c.getFullName(), item.getParentName()))
                        .findFirst();
                if (parentCityOptional.isPresent()) {
                    city.setParentId(parentCityOptional.get().getId());
                }
            }
            cityInsertList.add(city);
        }
        batchInsert(cityInsertList);
        return null;
    }

    @Override
    public byte[] exportExcelData(City city) {
        List<CityExcelVo> list = cityExcelVoMapper.findExportList(city);
        WriteParam<CityExcelVo> param = WriteParam.<CityExcelVo>builder()
                .beanClazz(CityExcelVo.class)
                .data(list)
                .build();
        WriteableExcel<CityExcelVo> writeAbleExcel = new XSSFWriteableExcel<>();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writeAbleExcel.write(param, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public List<City> findByCityType(byte cityType) {
        return cityRepository.findByCityType(cityType);
    }
}
