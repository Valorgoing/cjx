package com.example.controller;

import com.example.common.Result;
import com.example.entity.Data;
import com.example.service.DataService;
import com.github.pagehelper.PageInfo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程资料表前端操作接口
 **/
@RestController
@RequestMapping("/data")
public class DataController {

    @Resource
    private DataService dataService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Data data) {
        dataService.add(data);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        dataService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        dataService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Data data) {
        dataService.updateById(data);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Data data = dataService.selectById(id);
        return Result.success(data);
    }

    /**
     * 查询所有
     */
//    @GetMapping("/selectAll")
//    public Result selectAll(Data data ) {
//        List<Data> list = dataService.selectAll(data);
//        return Result.success(list);
//    }
    @GetMapping("/selectAll")
    public Result selectAll(Data data,
                            @RequestParam(required = false) Integer courseId, // 添加课程ID作为参数
                            @RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize) {
        if (courseId != null) {
            data.setCourseId(courseId); // 设置课程ID
        }
        PageInfo<Data> page = dataService.selectPage(data, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Data data,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Data> page = dataService.selectPage(data, pageNum, pageSize);
        return Result.success(page);
    }

}