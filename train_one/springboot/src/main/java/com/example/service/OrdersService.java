package com.example.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Orders;
import com.example.mapper.OrdersMapper;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/4/25 17:26
 */
@Service
public class OrdersService extends ServiceImpl<OrdersMapper, Orders> {

}