package com.example.common;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/4/25 21:10
 */
import lombok.Data;

import java.util.List;
@Data
public class Page<T> {
    private Integer total;
    private List<T> list;
}
