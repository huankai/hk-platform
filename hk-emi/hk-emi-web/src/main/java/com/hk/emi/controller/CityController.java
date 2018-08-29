package com.hk.emi.controller;

import com.hk.core.page.QueryModel;
import com.hk.core.web.JsonResult;
import com.hk.emi.domain.City;
import com.hk.emi.service.CityService;
import com.hk.platform.commons.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: kevin
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
     * 列表
     *
     * @param query query
     * @return JsonResult
     */
    @PostMapping
    public JsonResult list(@RequestBody QueryModel<City> query) {
        return JsonResult.success(cityService.queryForPage(query));
    }

    /**
     * 明细
     *
     * @param id id
     * @return JsonResult
     */
    @GetMapping(path = "{id}")
    public JsonResult get(@PathVariable String id) {
        return JsonResult.success(cityService.findOne(id));
    }

    /**
     * 子级
     *
     * @param parentId parentId
     * @return JsonResult
     */
    @GetMapping(path = "/child/{parentId}")
    public JsonResult childList(@PathVariable String parentId) {
        return JsonResult.success(cityService.findChildList(parentId));
    }

    /**
     * Delete By Id
     *
     * @param id id
     * @return JsonResult
     */
    @DeleteMapping(path = "{id")
    @PreAuthorize("hasRole('admin')")
    public JsonResult deleteById(@PathVariable String id) {
        cityService.deleteById(id);
        return JsonResult.success();
    }

    /**
     * Save Or Update
     *
     * @param city city
     * @return JsonResult
     */
    @PostMapping(path = "save")
    @PreAuthorize("hasRole('admin')")
    public JsonResult saveOrUpdate(@Validated @RequestBody City city) {
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
    public JsonResult excelImport(@RequestParam("file") MultipartFile multipartFile) throws IOException {
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
        InputStream inputStream = cityService.exportExcelData(city);
        return ResponseEntity.ok(new InputStreamResource(inputStream));
    }
}
