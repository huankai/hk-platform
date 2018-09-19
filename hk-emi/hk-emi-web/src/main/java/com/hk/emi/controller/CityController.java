package com.hk.emi.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.core.web.Webs;
import com.hk.emi.domain.City;
import com.hk.emi.service.CityService;
import com.hk.platform.commons.web.BaseController;
import io.swagger.annotations.Api;
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
 * @author: kevin
 * @date: 2018-07-17 16:49
 */
@Api(description = "城市管理")
@RestController
@RequestMapping(path = "city")
public class CityController extends BaseController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * 列表
     *
     * @param query query
     * @return JsonResult
     */
    @PostMapping(path = "/list")
    public JsonResult<QueryPage<City>> list(@RequestBody QueryModel<City> query) {
        return JsonResult.success(cityService.queryForPage(query));
    }

    /**
     * 明细
     *
     * @param id id
     * @return JsonResult
     */
    @GetMapping(path = "{id}")
    public JsonResult<City> get(@PathVariable String id) {
        return JsonResult.success(cityService.getOne(id));
    }

    /**
     * 子级
     *
     * @param parentId parentId
     * @return JsonResult
     */
    @GetMapping(path = "/child/{parentId}")
    public JsonResult<List<City>> childList(@PathVariable String parentId) {
        return JsonResult.success(cityService.findChildList(parentId));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return JsonResult
     */
    @DeleteMapping(path = "{id}")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> deleteById(@PathVariable String id) {
        cityService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * Save Or Update
     *
     * @param city city
     * @return JsonResult
     */
    @PostMapping
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> saveOrUpdate(@Validated @RequestBody City city) {
        cityService.insertOrUpdate(city);
        return JsonResult.success();
    }

    /**
     * Excel Import
     *
     * @param multipartFile multipartFile
     * @return JsonResult
     */
    @PostMapping("excel/import")
    @PreAuthorize("hasRole('admin')")
    public JsonResult<Void> excelImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        cityService.importExcel(multipartFile.getInputStream());
        return JsonResult.success();
    }

    /**
     * Excel export
     *
     * @param city city
     * @return ResponseEntity
     */
    @GetMapping("excel/export")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<InputStreamResource> excelExport(City city) {
        return Webs.toDownloadResponseEntity("城市数据.xlsx", cityService.exportExcelData(city));
    }
}
