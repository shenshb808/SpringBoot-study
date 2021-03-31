package com.shb.springbootstudy.config;

import com.shb.springbootstudy.domain.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice{

    /**
     * 全局异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public Result handleGlobalException(Exception e){
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return Result.failed("服务器开小差！");
    }

    /**
     * 拦截JSON参数校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleGlobalException(MethodArgumentNotValidException e){
        Map<String, String> faildMsg = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            faildMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        log.error("JSON参数校验异常",faildMsg);
        return Result.failed(faildMsg);
    }

    /**
     * 拦截表单参数校验
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result handleGlobalException(BindException e){
        Map<String, String> faildMsg = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            faildMsg.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        log.error("表单参数校验异常",faildMsg);
        return Result.failed(faildMsg);
    }

    /**
     * 处理单个参数校验失败抛出的异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> collect = constraintViolations.stream()
                .map(o -> o.getMessage())
                .collect(Collectors.toList());
        log.error("单个参数校验异常",collect);
        return Result.failed(collect);
    }



}
