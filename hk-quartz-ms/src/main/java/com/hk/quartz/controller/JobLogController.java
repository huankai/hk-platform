package com.hk.quartz.controller;

import com.hk.commons.JsonResult;
import com.hk.core.jdbc.query.ConditionQueryModel;
import com.hk.core.page.QueryPage;
import com.hk.quartz.entity.QuartzJobLog;
import com.hk.quartz.service.JobLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @date 2019-8-16 15:17
 */
@RestController
@RequestMapping("joblog")
@RequiredArgsConstructor
public class JobLogController {

    private final JobLogService jobLogService;

    @PostMapping("list")
    public JsonResult<QueryPage<QuartzJobLog>> queryForPage(@RequestBody ConditionQueryModel query) {
        return JsonResult.success(jobLogService.queryForPage(query));
    }


}
