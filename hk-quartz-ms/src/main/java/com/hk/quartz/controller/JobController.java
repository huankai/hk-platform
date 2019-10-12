package com.hk.quartz.controller;

import com.hk.commons.JsonResult;
import com.hk.commons.util.SpringContextHolder;
import com.hk.core.page.QueryPage;
import com.hk.core.query.QueryModel;
import com.hk.quartz.entity.QuartzJob;
import com.hk.quartz.service.JobService;
import com.hk.quartz.task.TaskExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2019-8-16 15:17
 */
@RestController
@RequestMapping("job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("list")
    public JsonResult<QueryPage<QuartzJob>> queryForPage(@RequestBody QueryModel<QuartzJob> query) {
        return JsonResult.success(jobService.queryForPage(query));
    }

    @GetMapping("{id}")
    public JsonResult<QuartzJob> findById(@PathVariable("id") Long id) {
        return JsonResult.success(jobService.findById(id));
    }

    @PostMapping
    public JsonResult<Boolean> saveJob(@RequestBody QuartzJob job) {
        jobService.saveJob(job);
        return JsonResult.success(true);
    }

    @GetMapping(path = "getTasks")
    public JsonResult<Set<String>> getTaskBean(Long id) {
        List<QuartzJob> list = jobService.findAll();
        List<String> usedTaskList = list.stream().map(QuartzJob::getBeanName).collect(Collectors.toList());
        Set<String> allTasks = SpringContextHolder.getBeanOfType(TaskExecutor.class).keySet();
        allTasks.removeAll(usedTaskList);
        if (null != id) {
            list.stream().filter(item -> id.equals(item.getId()))
                    .findFirst()
                    .ifPresent(item -> allTasks.add(item.getBeanName()));
        }
        return JsonResult.success(allTasks);
    }

    /**
     * 触发
     *
     * @param id id
     */
    @PostMapping("trigger")
    public JsonResult<Boolean> trigger(Long id) {
        return JsonResult.success(jobService.trigger(id));
    }

    /**
     * 暂停
     *
     * @param id id
     */
    @PostMapping("pause")
    public JsonResult<Boolean> pause(Long id) {
        return JsonResult.success(jobService.pause(id));
    }

    /**
     * 恢复
     *
     * @param id id
     */
    @PostMapping("resume")
    public JsonResult<Boolean> resume(Long id) {
        return JsonResult.success(jobService.resume(id));
    }

    /**
     * 删除
     *
     * @param id id
     */
    @PostMapping("remove")
    public JsonResult<Boolean> remove(Long id) {
        return JsonResult.success(jobService.remove(id));
    }

}
