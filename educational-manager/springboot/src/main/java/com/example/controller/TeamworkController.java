package com.example.controller;

import com.example.common.Result;
import com.example.entity.Teamwork;
import com.example.service.TeamworkService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 作业信息表前端操作接口
 **/
@RestController
@RequestMapping("/teamwork")
public class TeamworkController {

    @Resource
    private TeamworkService teamworkService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Teamwork teamwork) {
        teamworkService.add(teamwork);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        teamworkService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        teamworkService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Teamwork teamwork) {
        teamworkService.updateById(teamwork);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Teamwork teamwork = teamworkService.selectById(id);
        return Result.success(teamwork);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Teamwork teamwork ) {
        List<Teamwork> list = teamworkService.selectAll(teamwork);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Teamwork teamwork,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Teamwork> page = teamworkService.selectPage(teamwork, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 根据departmentId查询
     */
//    @GetMapping("/selectByDepartmentId/{departmentId}")
//    public Result selectByDepartmentId(@PathVariable Integer departmentId) {
//        List<Teamwork> list = teamworkService.selectByDepartmentId(departmentId);
//        return Result.success(list);
//    }

}
