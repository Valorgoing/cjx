package com.example.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.HoneyLogs;
import com.example.common.LogType;
import com.example.common.Result;
import com.example.entity.User;
import com.example.exception.ServiceException;
import com.example.service.UserService;
import com.example.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     */
    @HoneyLogs(operation = "用户" ,type= LogType.ADD)
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        try {
            userService.save(user);
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
     * 修改用户信息
     */
    @HoneyLogs(operation = "用户" ,type= LogType.UPDATE)
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 删除用户信息
     */
    @HoneyLogs(operation = "用户" ,type= LogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        User currentUser = TokenUtils.getCurrentUser();
        if (id.equals(currentUser.getId())) {
            throw new ServiceException("不能删除当前的用户");
        }
        userService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除用户信息
     */
    @HoneyLogs(operation = "用户" ,type= LogType.BATCH_DELETE)
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {  //  [7, 8]
        User currentUser = TokenUtils.getCurrentUser();
        if (currentUser != null && currentUser.getId() != null && ids.contains(currentUser.getId())) {
            throw new ServiceException("不能删除当前的用户");
        }
        userService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部用户信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<User> userList = userService.list(new QueryWrapper<User>().orderByDesc("id"));  // select * from user order by id desc
        return Result.success(userList);
    }

    /**
     * 根据ID查询用户信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }


    /**
     * 多条件模糊查询用户信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String username,
                               @RequestParam String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().orderByDesc("id");//默认倒叙，让最新的数据放在最上面
        queryWrapper.like(StrUtil.isNotBlank(username), "username", username);
        queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        // select * from user where username like '%#{username}%' and name like '%#{name}%'
        Page<User> page = userService.page(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(page);
    }

    /**
     * 批量导出数据
     */
    @GetMapping("/export")
    public void exportData(@RequestParam(required = false) String username,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String ids,
                           HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        List<User> list;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(ids)){
            List<Integer> idsArr1= Arrays.stream(ids.split(",")).map(Integer::valueOf).collect(Collectors.toList());
            queryWrapper.in("id",idsArr1);
        }else{
            //第一种全部导出
            queryWrapper.like(StrUtil.isNotBlank(username), "username", username);
            queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        }
        list=userService.list(queryWrapper);//查询当前User表的所有数据
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户信息表", "UTF-8") + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        writer.close();
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 批量导入
     * @param file 传入的excel文件对象
     * @return 导入结果
     * @throws IOException
     */
    @PostMapping("/import")
    public Result importData(MultipartFile file) throws IOException {
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<User> userList = reader.readAll(User.class);
        // 写入数据到数据库
        try {
            userService.saveBatch(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("数据批量导入错误");
        }
        return Result.success();
    }
}


//@CrossOrigin
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    /**
//     * 新增用户信息
//     *
//     * @return
//     */
//    @PostMapping("/add")
//    public Result add(@RequestBody User user) {
//        try {
//            userService.insertUser(user);
//        } catch (Exception e) {
//            if (e instanceof DuplicateKeyException) {
//                return Result.error("插入数据错误");
//            } else {
//                return Result.error("系统错误");
//            }
//        }
//        return Result.success();
//    }
//
//
//    /**
//     * 更改用户信息
//     *
//     * @return
//     */
//    @PutMapping("/update")
//    public Result update(@RequestBody User user) {
//        userService.updateUser(user);
//        return Result.success();
//    }
//
//    /**
//     * 删除用户信息
//     *
//     * @return
//     */
//    @DeleteMapping("/delete/{id}")
//    public Result delete(@PathVariable Integer id) {
//        userService.deleteUser(id);
//        return Result.success();
//    }
//
//    /**
//     * 批量删除用户信息
//     *
//     * @return
//     */
//    @DeleteMapping("/delete/batch")
//    public Result batchDelete(@RequestBody List<Integer> ids) {
//        userService.batchDeleteUser(ids);
//        return Result.success();
//    }
//
//    /**
//     * 查询全部用户信息
//     *
//     * @return
//     */
//    @GetMapping("/selectAll")
//    public Result selectAll(){
//        List<User> userList=userService.selectAll();
//        return Result.success(userList);
//    }
//
//    /**
//     * 根据id查询用户信息
//     *
//     * @return
//     */
//    @GetMapping("/selectById/{id}")
//    public Result selectById(@PathVariable Integer id){
//        User user=userService.selectById(id);
//        return Result.success(user);
//    }
//
//    /**
//     * 根据name查询用户信息
//     *
//     * @return
//     */
//    @GetMapping("/selectByName/{name}")
//    public Result selectByName(@PathVariable String name){
//        List<User> userList=userService.selectByName(name);
//        return Result.success(userList);
//    }
//
//    /**
//     * 多条件查询用户信息
//     *
//     * @return
//     */
//    @GetMapping("/selectByMore")
//    public Result selectByMore(@RequestParam String username,@RequestParam String name){
//        List<User> userList=userService.selectByMore(username,name);
//        return Result.success(userList);
//    }
//
//    /**
//     * 多条件模糊查询用户信息
//     *
//     * @return
//     */
//    @GetMapping("/selectByMo")
//    public Result selectByMo(@RequestParam String username,@RequestParam String name){
//        List<User> userList=userService.selectByMo(username,name);
//        return Result.success(userList);
//    }
//
//    /**
//     * 多条件分页模糊查询用户信息
//     *
//     * @param pageNum 当前的页码
//     * @param pageSize
//     * @param username
//     * @param name
//     * @return
//     */
//    @GetMapping("/selectByPage")
//    public Result selectByPage(@RequestParam Integer pageNum,
//                               @RequestParam Integer pageSize,
//                               @RequestParam String username,
//                               @RequestParam String name){
//        Page<User> page = userService.selectByPage(pageNum, pageSize, username, name);
//        return Result.success(page);
//    }
//}
