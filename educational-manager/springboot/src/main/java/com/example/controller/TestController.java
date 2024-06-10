package com.example.controller;

import com.example.common.Result;
import com.example.entity.Test;
import com.example.service.TestService;
import com.github.pagehelper.PageInfo;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程资料表前端操作接口
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody  Test test) {
        testService.add(test);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        testService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        testService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Test test) {
        testService.updateById(test);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Test test = testService.selectById(id);
        return Result.success(test);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Test test ) {
        List<Test> list = testService.selectAll(test);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Test test,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Test> page = testService.selectPage(test, pageNum, pageSize);
        return Result.success(page);
    }

}