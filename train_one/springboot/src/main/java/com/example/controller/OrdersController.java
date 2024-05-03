package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.HoneyLogs;
import com.example.common.LogType;
import com.example.common.Result;
import com.example.entity.Orders;
import com.example.entity.User;
import com.example.service.OrdersService;
import com.example.service.UserService;
import com.example.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/4/25 17:25
 */

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @Autowired
    UserService userService;

    /**
     * 新增信息
     */
    @HoneyLogs(operation = "订单", type = LogType.ADD)
    @PostMapping("/add")
    public Result add(@RequestBody Orders orders) {
        try {
            User currentUser = TokenUtils.getCurrentUser();
            orders.setUserid(currentUser.getId());
            orders.setDate(DateUtil.today());
            orders.setNo(IdUtil.fastSimpleUUID());
            ordersService.save(orders);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("插入数据库错误");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }

    /**
     * 修改信息
     */
    @HoneyLogs(operation = "订单", type = LogType.UPDATE)
    @PutMapping("/update")
    public Result update(@RequestBody Orders orders) {
        ordersService.updateById(orders);
        return Result.success();
    }

    /**
     * 删除信息
     */
    @HoneyLogs(operation = "订单", type = LogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        ordersService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除信息
     */
    @HoneyLogs(operation = "订单", type = LogType.BATCH_DELETE)
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        ordersService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<Orders> ordersList= ordersService.list(new QueryWrapper<Orders>().orderByDesc("id"));  // select * from orders order by id desc
        return Result.success(ordersList);
    }


    /**
     * 根据ID查询信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Orders orders = ordersService.getById(id);
        User user = userService.getById(orders.getUserid());
        if (user != null) {
            orders.setUser(user.getName());
        }
        return Result.success(orders);
    }


    /**
     * 多条件模糊查询信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<Orders>().orderByDesc("id");//默认倒叙，让最新的数据放在最上面
        queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        Page<Orders> page = ordersService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<Orders> records = page.getRecords();
        for (Orders record : records) {
            Integer authorid = record.getUserid();
            User user = userService.getById(authorid);
            if (user != null) {
                record.setUser(user.getName());
            }
        }

        return Result.success(page);
    }
}














