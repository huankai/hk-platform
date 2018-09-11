package com.hk.emi.service.impl;


import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SimpleSaxReadExcel;
import com.hk.commons.util.BeanUtils;
import com.hk.commons.util.StringUtils;
import com.hk.core.cache.service.EnableCacheServiceImpl;
import com.hk.core.data.jpa.repository.BaseRepository;
import com.hk.core.exception.ServiceException;
import com.hk.emi.domain.City;
import com.hk.emi.repository.CityRepository;
import com.hk.emi.service.CityService;
import com.hk.emi.vo.CityExcelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/**
 * @author: kevin
 */
@Service
@CacheConfig(cacheNames = "City")
public class CityServiceImpl extends EnableCacheServiceImpl<City, String> implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    protected BaseRepository<City, String> getBaseRepository() {
        return cityRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withMatcher("cityType", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("fullName", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    protected City saveBefore(City entity) throws ServiceException {
        if (StringUtils.isEmpty(entity.getParentId())) {
            entity.setParentId(entity.getId());
        }
        return entity;
    }

    /**
     * 查询下级City
     *
     * @param parentId parentId
     * @return List<City>
     */
    @Override
    public List<City> findChildList(String parentId) {
        return StringUtils.isEmpty(parentId) ? Collections.emptyList() : cityRepository.findByParentId(parentId);
    }

    /**
     * 导入
     *
     * @param in excel文件
     */
    @Override
    public void importExcel(InputStream in) {
        ReadParam<CityExcelVo> readableParam = ReadParam.<CityExcelVo>builder()
                .beanClazz(CityExcelVo.class)
                .build();
        ReadableExcel<CityExcelVo> readableExcel = new SimpleSaxReadExcel<>(readableParam);
        ReadResult<CityExcelVo> result = readableExcel.read(in);
        if (!result.hasErrors()) {
            List<CityExcelVo> resultList = result.getAllSheetData();
            List<City> cityList = findAll();
            City city;
            for (CityExcelVo item : resultList) {
                city = new City();
                BeanUtils.copyProperties(item, city);
//                if (StringUtils.isNotEmpty(item.getParentName())) {
//                    Optional<City> cityOptional = cityList.stream()
//                            .filter(c -> StringUtils.equals(c.getFullName(), item.getParentName()))
//                            .findFirst();
//
//                }
                cityList.add(city);
            }
        }
    }

    @Override
    public InputStream exportExcelData(City city) {
//        List<CityExcelVo> cityList = cityRepository.findExportExcelData(city);
//        WriteParam<CityExcelVo> param = WriteParam.<CityExcelVo>builder()
//                .beanClazz(CityExcelVo.class)
//                .data(cityList)
//                .build();
//        WriteableExcel<CityExcelVo> writeableExcel = new XSSFWriteableExcel<>();
        List<CityExcelVo> list = cityRepository.findExportExcelData(city);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        writeableExcel.write(param, baos);
        return new ByteArrayInputStream(baos.toByteArray());
    }
}
