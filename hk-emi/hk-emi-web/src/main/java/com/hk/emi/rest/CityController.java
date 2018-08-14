package com.hk.emi.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hk.core.page.QueryModel;
import com.hk.core.page.QueryPage;
import com.hk.core.web.JsonResult;
import com.hk.emi.domain.City;
import com.hk.emi.service.CityService;

/**
 * @author: kevin
 * @date 2018-07-17 16:49
 */
@RestController
@RequestMapping(path = "citys")
public class CityController {

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
    @PostMapping(path = "list")
    public JsonResult list(@RequestBody QueryModel<City> query) {
        QueryPage<City> pageResult = cityService.queryForPage(query);
        return JsonResult.success(pageResult);
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
    public JsonResult saveOrUpdate(@RequestBody City city, Errors errors) {
        if (errors.hasErrors()) {
            return JsonResult.badRueqest(errors.getFieldError().getDefaultMessage());
        }
        cityService.insertOrUpdate(city);
        return JsonResult.success();
    }

    /**
     * Excel Import
     *
     * @param multipartFile multipartFile
     * @return JsonResult
     */
    @PostMapping("excelImport")
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
    @GetMapping("excelExport")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<byte[]> excelExport(City city) {
        byte[] excelData = cityService.exportExcelData(city);
        return ResponseEntity.ok(excelData);
    }
}
