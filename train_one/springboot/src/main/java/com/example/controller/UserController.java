package com.example.controller;

import com.example.common.Page;
import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/4/25 17:25
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 新增用户信息
     *
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return Result.error("插入数据错误");
            } else {
                return Result.error("系统错误");
            }
        }
        return Result.success();
    }


    /**
     * 更改用户信息
     *
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    /**
     * 删除用户信息
     *
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return Result.success();
    }

    /**
     * 批量删除用户信息
     *
     * @return
     */
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        userService.batchDeleteUser(ids);
        return Result.success();
    }

    /**
     * 查询全部用户信息
     *
     * @return
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        List<User> userList=userService.selectAll();
        return Result.success(userList);
    }

    /**
     * 根据id查询用户信息
     *
     * @return
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        User user=userService.selectById(id);
        return Result.success(user);
    }

    /**
     * 根据name查询用户信息
     *
     * @return
     */
    @GetMapping("/selectByName/{name}")
    public Result selectByName(@PathVariable String name){
        List<User> userList=userService.selectByName(name);
        return Result.success(userList);
    }

    /**
     * 多条件查询用户信息
     *
     * @return
     */
    @GetMapping("/selectByMore")
    public Result selectByMore(@RequestParam String username,@RequestParam String name){
        List<User> userList=userService.selectByMore(username,name);
        return Result.success(userList);
    }

    /**
     * 多条件模糊查询用户信息
     *
     * @return
     */
    @GetMapping("/selectByMo")
    public Result selectByMo(@RequestParam String username,@RequestParam String name){
        List<User> userList=userService.selectByMo(username,name);
        return Result.success(userList);
    }

    /**
     * 多条件分页模糊查询用户信息
     *
     * @param pageNum 当前的页码
     * @param pageSize
     * @param username
     * @param name
     * @return
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String name){
        Page<User> page = userService.selectByPage(pageNum, pageSize, username, name);
        return Result.success(page);
    }
}
