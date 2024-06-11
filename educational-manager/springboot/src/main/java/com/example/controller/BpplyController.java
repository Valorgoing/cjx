package com.example.controller;

import com.example.common.Result;
import com.example.entity.Bpply;
import com.example.service.BpplyService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bpply")
public class BpplyController {

    @Resource
    private BpplyService bpplyService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Bpply bpply) {
        bpplyService.add(bpply);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        bpplyService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        bpplyService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Bpply bpply) {
        bpplyService.updateById(bpply);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Bpply bpply = bpplyService.selectById(id);
        return Result.success(bpply);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Bpply bpply ) {
        List<Bpply> list = bpplyService.selectAll(bpply);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Bpply bpply,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Bpply> page = bpplyService.selectPage(bpply, pageNum, pageSize);
        return Result.success(page);
    }

    @GetMapping("/selectPage2")
    public Result selectPage2(Bpply bpply,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Bpply> page = bpplyService.selectPage2(bpply, pageNum, pageSize);
        return Result.success(page);
    }

}