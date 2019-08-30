package com.hk.emi.controller;

import com.hk.commons.JsonResult;
import com.hk.commons.poi.excel.model.ErrorLog;
import com.hk.commons.util.CollectionUtils;
import com.hk.commons.util.JsonUtils;
import com.hk.core.jdbc.query.ConditionQueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.core.web.Webs;
import com.hk.emi.domain.City;
import com.hk.emi.service.CityService;
import com.hk.emi.vo.CityExcelVo;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
        JsonResult<QueryPage<City>> result = JsonResult.success(cityService.queryForPage(query));
        System.out.println(JsonUtils.serialize(result, true));
        return result;
    }

    /**
     * 明细
     *
     * @param id id
     * @return {@link City}
     */
    @GetMapping(path = "{id}", name = "city-get")
    public JsonResult<City> get(@PathVariable Long id) {
        return JsonResult.success(cityService.getOne(id));
    }

    /**
     * 子级
     *
     * @param parentId parentId
     * @return {@link City}
     */
    @GetMapping(path = "child/{parentId}", name = "city-child")
    public JsonResult<List<City>> childList(@PathVariable Long parentId) {
        return JsonResult.success(cityService.findChildList(parentId));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return {@link JsonResult}
     */
    @DeleteMapping(path = "{id}", name = "city-delete")
    @PreAuthorize("hasRole('" + ADMIN + "')")
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
    @PreAuthorize("hasRole('" + ADMIN + "')")
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
    public JsonResult<List<ErrorLog<CityExcelVo>>> excelImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<ErrorLog<CityExcelVo>> errorLogs = cityService.importExcel(multipartFile.getInputStream());
        return CollectionUtils.isEmpty(errorLogs) ? new JsonResult<>() : JsonResult.failure(errorLogs);
    }

    /**
     * Excel export
     *
     * @param city city
     * @return {@link ResponseEntity}
     */
    @GetMapping(path = "excel/export")
    @PreAuthorize("hasRole('" + ADMIN + "')")
    public ResponseEntity<InputStreamResource> excelExport(City city) {
        return Webs.toResponseEntity("城市数据.xlsx", cityService.exportExcelData(city));
    }
}
