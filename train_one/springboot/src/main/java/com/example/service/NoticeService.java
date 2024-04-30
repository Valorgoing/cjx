package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.News;
import com.example.entity.Notice;
import com.example.mapper.NewsMapper;
import com.example.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/4/25 17:26
 */
@Service
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {
    @Resource
    NoticeMapper noticeMapper;
}