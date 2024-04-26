package com.example.exception;

import lombok.Getter;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : Valor
 * @create 2024/4/26 13:23
 */
@Getter
public class ServiceException extends RuntimeException{
    private final String code;

    public ServiceException(String msg) {
        super(msg);
        this.code = "500";
    }

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
//    public ServiceException(String msg){
//        super(msg);
//    }
}
