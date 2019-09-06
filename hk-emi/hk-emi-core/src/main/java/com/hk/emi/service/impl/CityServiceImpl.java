package com.hk.emi.service.impl;


import com.hk.commons.poi.excel.model.ErrorLog;
import com.hk.commons.poi.excel.model.ReadParam;
import com.hk.commons.poi.excel.model.ReadResult;
import com.hk.commons.poi.excel.model.WriteParam;
import com.hk.commons.poi.excel.read.ReadableExcel;
import com.hk.commons.poi.excel.read.SaxReadExcel;
import com.hk.commons.poi.excel.write.WriteableExcel;
import com.hk.commons.poi.excel.write.XSSFWriteableExcel;
import com.hk.commons.util.*;
import com.hk.core.data.jpa.repository.BaseJpaRepository;
import com.hk.core.service.exception.ServiceException;
import com.hk.core.service.jpa.impl.JpaServiceImpl;
import com.hk.emi.domain.City;
import com.hk.emi.repository.jpa.CityRepository;
import com.hk.emi.service.CityService;
import com.hk.emi.vo.CityExportVo;
import com.hk.platform.commons.ui.Cascader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.*;
import java.util.stream.StreamSupport;

/**
 * @author kevin
 */
@Service
@RequiredArgsConstructor
public class CityServiceImpl extends JpaServiceImpl<City, Long> implements CityService {

    private final CityRepository cityRepository;

    @Override
    protected BaseJpaRepository<City, Long> getBaseRepository() {
        return cityRepository;
    }

    @Override
    protected ExampleMatcher ofExampleMatcher() {
        return super.ofExampleMatcher()
                .withMatcher("code", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("fullName", ExampleMatcher.GenericPropertyMatchers.contains());
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
    public List<ErrorLog<CityExportVo>> importExcel(InputStream in) {
        ReadParam<CityExportVo> readableParam = ReadParam.<CityExportVo>builder()
                .beanClazz(CityExportVo.class)
                .parseAll(false)
                .sheetStartIndex(0)
                .sheetMaxIndex(1)
                .build();
        ReadableExcel<CityExportVo> readableExcel = new SaxReadExcel<>(readableParam);
        ReadResult<CityExportVo> result = readableExcel.read(in);
        if (result.hasErrors()) {
            return result.getErrorLogList();
        }
        List<CityExportVo> resultList = result.getAllSheetData();
        Iterable<City> cityList = findAll();
        List<City> cityInsertList = new ArrayList<>();
        City city;
        for (CityExportVo item : resultList) {
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
        List<CityExportVo> list = cityRepository.findExportList(city);
        WriteParam<CityExportVo> param = WriteParam.<CityExportVo>builder()
                .beanClazz(CityExportVo.class)
                .data(list)
                .build();
        WriteableExcel<CityExportVo> writeAbleExcel = new XSSFWriteableExcel<>();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        writeAbleExcel.write(param, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] exportJsonData(City city) {
        List<CityExportVo> dataList = cityRepository.findExportList(city);
        return JsonUtils.serializeToByte(dataList, true);
    }

    @Override
    public List<City> findByCityType(byte cityType) {
        return cityRepository.findByCityType(cityType);
    }

    @Override
    public List<Cascader> findChildByCityType(byte cityType, boolean isLeaf) {
        return cityRepository.findChildByCityType(cityType, isLeaf);
    }

    @Override
    public List<Cascader> findChildByParentIdAndMaxCityType(Long parentId, Byte maxCityType) {
        return cityRepository.findChildByParentIdAndMaxCityType(parentId, maxCityType);
    }

    @Override
    public List<Cascader> findAllClildsList(Long[] parentIds) {
        List<Cascader> result = null;
        if (ArrayUtils.length(parentIds) > 0) {
            Long parentId = parentIds[0];
            City city = findById(parentId).orElseThrow(() -> new ServiceException("地址不存在"));
            result = cityRepository.findCascaderByParentId(city.getParentId(), false);
            Cascader firstCascader = new Cascader(parentId.toString(), city.getFullName());
            for (int i = 0, length = parentIds.length; i < length; i++) {
                Long itemId = parentIds[i];
                Cascader currentChildCascader = CollectionUtils.isEmpty(firstCascader.getChildren()) ? firstCascader : firstCascader.getChildren().stream()
                        .filter(item -> Long.parseLong(item.getValue()) == itemId).findFirst().orElse(null);
                if (currentChildCascader != null) {
                    List<Cascader> childList = cityRepository.findCascaderByParentId(itemId, i == length - 2);
                    currentChildCascader.setChildren(childList);
                }
            }
            for (int i = 0, size = result.size(); i < size; i++) {
                Cascader item = result.get(i);
                if (StringUtils.equals(item.getValue(), firstCascader.getValue())) {
                    result.set(i, firstCascader);
                    break;
                }
            }
        }
        return result;
    }


}
