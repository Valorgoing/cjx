package com.example.controller;

import com.example.common.Result;
import com.example.entity.Work;
import com.example.service.WorkService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程资料表前端操作接口
 **/
@RestController
@RequestMapping("/work")
public class WorkController {

    @Resource
    private WorkService workService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Work work) {
        workService.add(work);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        workService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        workService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Work work) {
        workService.updateById(work);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Work work = workService.selectById(id);
        return Result.success(work);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Work work,
                            @RequestParam(required = false) Integer courseId,
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        if (courseId != null) {
            work.setCourseId(courseId);
        }
        PageInfo<Work> page = workService.selectPage(work, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Work work,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Work> page = workService.selectPage(work, pageNum, pageSize);
        return Result.success(page);
    }
}
