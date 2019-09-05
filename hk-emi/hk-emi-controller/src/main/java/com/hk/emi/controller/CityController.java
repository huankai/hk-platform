package com.hk.emi.controller;

import com.hk.commons.JsonResult;
import com.hk.commons.poi.excel.model.ErrorLog;
import com.hk.commons.util.*;
import com.hk.core.jdbc.query.ConditionQueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.core.web.Webs;
import com.hk.emi.domain.City;
import com.hk.emi.enums.CityTypeEnum;
import com.hk.emi.service.CityService;
import com.hk.emi.vo.CityExportVo;
import com.hk.platform.commons.ui.Cascader;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author kevin
 * @date 2018-07-17 16:49
 */
@RestController
@RequestMapping(path = "city")
public class CityController extends BaseController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * 测试 {@link ConditionQueryModel}
     *
     * @param queryModel
     * @return
     */
    @PostMapping(path = "list2")
    public JsonResult<QueryPage<City>> list2(@RequestBody ConditionQueryModel queryModel) {
        QueryPage<City> page = cityService.queryForPage(queryModel);
        return JsonResult.success(page);
    }

    /**
     * 列表
     *
     * @param query query
     * @return {@link City}
     */
    @PostMapping(path = "list")
    public JsonResult<QueryPage<City>> list(@RequestBody QueryModel<City> query) {
        return JsonResult.success(cityService.queryForPage(query));
    }

    /**
     * 明细
     *
     * @param id id
     * @return {@link City}
     */
    @GetMapping(path = "{id}", name = "city-get")
    public JsonResult<Map<String, Object>> get(@PathVariable Long id) {
        City city = cityService.getOne(id);
        String parentName = cityService.findById(city.getParentId()).map(City::getFullName).orElse(null);
        Map<String, Object> result = BeanUtils.beanToMapIgnoreEntityProperties(city);
        result.put("parentName", parentName);
        return JsonResult.success(result);
    }

    @GetMapping(path = "cityType")
    public JsonResult<?> getCityType() {
        return JsonResult.success(CityTypeEnum.LIST);
    }

    /**
     * 获取所有省级
     *
     * @return
     */
    @GetMapping(path = "provinces")
    public JsonResult<List<?>> getProvinceList() {
        return JsonResult.success(cityService.findChildByCityType(CityTypeEnum.PROVINCE.getValue(), false));
    }

    /**
     * 子级
     *
     * @param parentId parentId
     * @return {@link City}
     */
    @GetMapping(path = "child")
    public JsonResult<List<?>> childList(@RequestParam Long parentId, @RequestParam Byte maxCityType) {
        return JsonResult.success(cityService.findChildByParentIdAndMaxCityType(parentId, maxCityType));
    }


    @GetMapping("childs")
    public JsonResult<List<Cascader.ChildCascader>> clildsList(@RequestParam(value = "parentIds") Long[] parentIds) {
        return JsonResult.success(cityService.findAllClildsList(parentIds));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return {@link JsonResult}
     */
    @PostMapping(path = "{id}", name = "city-delete")
    //    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        cityService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * Save Or Update
     *
     * @param city city
     * @return {@link JsonResult}
     */
    @PostMapping
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody City city) {
        cityService.insertOrUpdateSelective(city);
        return JsonResult.success();
    }

    /**
     * Excel Import
     *
     * @param multipartFile multipartFile
     * @return {@link ErrorLog}
     */
    @PostMapping(path = "excel/import")
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public JsonResult<List<ErrorLog<CityExportVo>>> excelImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<ErrorLog<CityExportVo>> errorLogs = cityService.importExcel(multipartFile.getInputStream());
        return CollectionUtils.isEmpty(errorLogs) ? new JsonResult<>() : JsonResult.failure(errorLogs);
    }

    /**
     * Excel export
     *
     * @param city city
     * @return {@link ResponseEntity}
     */
    @GetMapping(path = "export")
//    @PreAuthorize("hasRole('" + ADMIN + "')")
    public ResponseEntity<InputStreamResource> excelExport(City city, String exportType) {
        if (StringUtils.equals(exportType, "excel")) {
            return Webs.toResponseEntity("城市数据.xlsx", cityService.exportExcelData(city));
        } else {
            return Webs.toResponseEntity("城市数据.json", cityService.exportJsonData(city));
        }
    }
}
