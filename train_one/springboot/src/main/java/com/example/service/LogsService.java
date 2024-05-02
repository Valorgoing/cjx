package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Logs;
import com.example.entity.Notice;
import com.example.mapper.LogsMapper;
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
public class LogsService extends ServiceImpl<LogsMapper, Logs> {

}