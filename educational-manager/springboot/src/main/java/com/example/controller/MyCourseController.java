package com.example.controller;

import com.example.common.Result;
import com.example.entity.MyCourse;
import com.example.service.MyCourseService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 考勤信息表前端操作接口
 **/
@RestController
@RequestMapping("/myCourse")
public class MyCourseController {

    @Resource
    private MyCourseService myCourseService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody MyCourse myCourse) {
        myCourseService.add(myCourse);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        myCourseService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        myCourseService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody MyCourse myCourse) {
        myCourseService.updateById(myCourse);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        MyCourse myCourse = myCourseService.selectById(id);
        return Result.success(myCourse);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(MyCourse myCourse ) {
        List<MyCourse> list = myCourseService.selectAll(myCourse);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(MyCourse myCourse,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<MyCourse> page = myCourseService.selectPage(myCourse, pageNum, pageSize);
        return Result.success(page);
    }

//    @GetMapping("/getPie")
//    public Result getPie() {
//        Map<String, Object> resultMap = new HashMap<>();
//        List<Map<String, Object>> list = new ArrayList<>();
//        // 处理数据
//        List<MyCourse> all = myCourseService.selectAll(new MyCourse());
//        Map<String, List<MyCourse>> collect = all.stream().collect(Collectors.groupingBy(MyCourse::getStatus));
//        for (String key : collect.keySet()) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("name", key);
//            map.put("value", collect.get(key).size());
//            list.add(map);
//        }
//
//        resultMap.put("text", "考勤状态统计图（饼图）");
//        resultMap.put("subtext", "统计维度：考勤状态");
//        resultMap.put("name", "考勤状态");
//        resultMap.put("data", list);
//        return Result.success(resultMap);
//    }
}