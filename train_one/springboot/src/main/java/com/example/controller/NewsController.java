package com.example.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.HoneyLogs;
import com.example.common.LogType;
import com.example.common.Result;
import com.example.entity.News;
import com.example.entity.User;
import com.example.service.NewsService;
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
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    /**
     * 新增信息
     */
    @HoneyLogs(operation = "新闻" ,type= LogType.ADD)
    @PostMapping("/add")
    public Result add(@RequestBody News news) {
        try {
            User currentUser = TokenUtils.getCurrentUser();
            news.setAuthorid(currentUser.getId());
            news.setTime(DateUtil.now());
            newsService.save(news);
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
    @HoneyLogs(operation = "新闻" ,type= LogType.UPDATE)
    @PutMapping("/update")
    public Result update(@RequestBody News news) {
        newsService.updateById(news);
        return Result.success();
    }

    /**
     * 删除信息
     */
    @HoneyLogs(operation = "新闻" ,type= LogType.DELETE)
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        newsService.removeById(id);
        return Result.success();
    }


    /**
     * 批量删除信息
     */
    @HoneyLogs(operation = "新闻" ,type= LogType.BATCH_DELETE)
    @DeleteMapping("/delete/batch")
    public Result batchDelete(@RequestBody List<Integer> ids) {
        newsService.removeBatchByIds(ids);
        return Result.success();
    }

    /**
     * 查询全部信息
     */
    @GetMapping("/selectAll")
    public Result selectAll() {
        List<News> userList = newsService.list(new QueryWrapper<News>().orderByDesc("id"));  // select * from news order by id desc
        return Result.success(userList);
    }

    /**
     * 根据ID查询信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        News news = newsService.getById(id);
        User user=userService.getById(news.getAuthorid());
        if(user!=null){
            news.setAuthor(user.getName());
        }
        return Result.success(news);
    }


    /**
     * 多条件模糊查询信息
     * pageNum 当前的页码
     * pageSize 每页查询的个数
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String title) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<News>().orderByDesc("id");//默认倒叙，让最新的数据放在最上面
        queryWrapper.like(StrUtil.isNotBlank(title), "title", title);
        Page<News> page = newsService.page(new Page<>(pageNum, pageSize), queryWrapper);
        List<News> records = page.getRecords();
//        List<User> list = userService.list();
        for (News record : records) {
            Integer authorid = record.getAuthorid();
            User user=userService.getById(authorid);
//            String author = list.stream().filter(u -> u.getId().equals(authorid)).findFirst().map(User::getName).orElse("");
//            record.setAuthor(author);
            if(user!=null){
                record.setAuthor(user.getName());
            }
        }

        return Result.success(page);
    }
}














