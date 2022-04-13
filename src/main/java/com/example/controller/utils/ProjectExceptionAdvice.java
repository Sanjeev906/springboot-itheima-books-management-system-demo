package com.example.controller.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// springMVC异常处理器 (AOP)
@RestControllerAdvice
@Slf4j
public class ProjectExceptionAdvice {
    // 拦截所有的异常信息
    @ExceptionHandler
    public R doException(Exception e) {
        // 记录日志
        log.error("Unexpected Error Occurred", e);
        // 返回用户可读信息
        return new R(false, "服务器故障，请稍后再试");
    }
}
