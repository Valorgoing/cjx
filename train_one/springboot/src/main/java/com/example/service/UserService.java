package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.User;
import com.example.exception.ServiceException;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/4/25 17:26
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

    @Resource
    UserMapper userMapper;

    @Override
    public boolean save(User entity) {
        if(StrUtil.isBlank(entity.getName())){
            entity.setName(entity.getUsername());
        }
        if(StrUtil.isBlank(entity.getPassword())){
            entity.setPassword("123");
        }
        if(StrUtil.isBlank(entity.getRole())){
            entity.setRole("用户");
        }
        return super.save(entity);
    }

    public User selectByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);  //  eq => ==   where username = #{username}
        // 根据用户名查询数据库的用户信息
        return getOne(queryWrapper); //  select * from user where username = #{username}
    }

    // 验证用户账户是否合法
    public User login(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名或密码错误");
        }
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("用户名或密码错误");
        }
        // 生成token
        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public User register(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser != null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户名已存在");
        }
        user.setName(user.getUsername());
        userMapper.insert(user);
        return user;
    }

    public void resetPassword(User user) {
        User dbUser = selectByUsername(user.getUsername());
        if (dbUser == null) {
            // 抛出一个自定义的异常
            throw new ServiceException("用户不存在");
        }
        if (!user.getPhone().equals(dbUser.getPhone())) {
            throw new ServiceException("验证错误");
        }
        dbUser.setPassword("123");   // 重置密码
        updateById(dbUser);
    }
}
//@Service
//public class UserService {
//
//    @Autowired
//    UserMapper userMapper;
//
//    public void insertUser(User user) {
//        userMapper.insert(user);
//    }
//
//    public void updateUser(User user) {
//        userMapper.updateUser(user);
//    }
//
//    public void deleteUser(Integer id) {
//        userMapper.deleteUser(id);
//    }
//
//    public void batchDeleteUser(List<Integer> ids) {
//        for (Integer id : ids) {
//            userMapper.deleteUser(id);
//        }
//    }
//
//    public List<User> selectAll() {
//        return userMapper.selectAll();
//    }
//
//    public User selectById(Integer id) {
//        return userMapper.selectById(id);
//    }
//
//    public List<User> selectByName(String name) {
//        return userMapper.selectByName(name);
//    }
//
//    public List<User> selectByMore(String username, String name) {
//        return userMapper.selectByMore(username, name);
//    }
//
//    public List<User> selectByMo(String username, String name) {
//        return userMapper.selectByMo(username, name);
//
//    }
//
//    public Page<User> selectByPage(Integer pageNum, Integer pageSize, String username, String name) {
//        Integer skipNum = (pageNum - 1) * pageSize;//计算出来
//        Page<User> page = new Page<>();
//        List<User> userList = userMapper.selectByPage(skipNum, pageSize, username, name);
//        Integer total = userMapper.selectCountByPage(username, name);
//        page.setTotal(total);
//        page.setList(userList);
//        return page;
//    }
//    验证用户账号是否合法
//    public User login(User user) {
//        //根据用户名查询数据库的用户信息
//        User dbUser=userMapper.selectByUsername(user.getUsername());
//        if(dbUser==null){
//            //抛出一个自定义异常
//            throw new ServiceException("用户名或密码错误");
//        }
//        if(!user.getPassword().equals(dbUser.getPassword())){
//            throw new ServiceException("用户名或密码错误");
//        }
//        //生成token
//        String token = TokenUtils.createToken(dbUser.getId().toString(), dbUser.getPassword());
//        dbUser.setToken(token);
//        return dbUser;
//    }
//
//    public User register(User user) {
//        User dbUser=userMapper.selectByUsername(user.getUsername());
//        if(dbUser!=null){
//            //抛出一个自定义异常
//            throw new ServiceException("用户名已存在");
//        }
//        user.setName(user.getUsername());
//        userMapper.insert(user);
//        return user;
//    }
//
//    public void resetPassword(User user) {
//        User dbUser = userMapper.selectByUsername(user.getUsername());
//        if (dbUser == null) {
//            // 抛出一个自定义的异常
//            throw new ServiceException("用户不存在");
//        }
//        if (!user.getPhone().equals(dbUser.getPhone())) {
//            throw new ServiceException("验证错误");
//        }
//        dbUser.setPassword("123");   // 重置密码
//        userMapper.updateUser(dbUser);
//    }
//}
