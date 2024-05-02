package com.example.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.common.AuthAccess;
import com.example.common.HoneyLogs;
import com.example.common.LogType;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 基础前端接口
 */
@RestController
public class WebController {

    @Resource
    UserService userService;

    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    @HoneyLogs(operation = "用户" ,type= LogType.LOGIN)
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getUsername())||StrUtil.isBlank(user.getPassword())){
            return Result.error("数据输入不合法");
        }
        user=userService.login(user);
        return Result.success(user);
    }//

    @HoneyLogs(operation = "用户" ,type= LogType.REGISTER)
    @AuthAccess
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        if(StrUtil.isBlank(user.getUsername())||StrUtil.isBlank(user.getPassword())||StrUtil.isBlank(user.getRole())){
            return Result.error("数据输入不合法");
        }
        if(user.getUsername().length()>10||user.getPassword().length()>20){
            return Result.error("数据输入不合法");
        }
        user=userService.register(user);
        return Result.success(user);
    }

    /**
     *  Controller重置密码
     */
    @HoneyLogs(operation = "用户" ,type= LogType.UPDATE)
    @AuthAccess
    @PutMapping("/password")
    public Result password(@RequestBody User user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPhone())) {
            return Result.error("数据输入不合法");
        }
        userService.resetPassword(user);
        return Result.success();
    }
}
