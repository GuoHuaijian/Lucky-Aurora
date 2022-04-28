package com.aurora.common.config.handler;

import com.aurora.common.core.exception.UserNotFindException;
import com.aurora.common.core.web.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.nio.file.AccessDeniedException;

/**
 * describe: 全局异常捕获处理
 *
 * @author Guo Huaijian
 * @date 2021/8/26
 * @e-mail guohuaijian9527@gmail.com
 * @version 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 参数校验失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        log.error("出现错误：'{}'", e);
        return Result.error(e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * 参数校验失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(UserNotFindException.class)
    public Result exceptionHandler(UserNotFindException e) {
        log.error("出现错误：'{}'", e);
        return Result.error(e.getMessage());
    }

    /**
     * 参数校验失败
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result exceptionHandler(ConstraintViolationException e) {
        log.error("出现错误：'{}'", e);
        String[] split = e.getMessage().split(":");
        return Result.error(split[1]);
    }

    /**
     * 没有权限
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDeniedException.class)
    public Result exceptionHandler(AccessDeniedException e) {
        log.error("出现错误：{}", e);
        return Result.error("没有权限");
    }

    /**
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        log.error("出现错误：{}", e);
        return Result.error(e.getMessage());
    }


}
