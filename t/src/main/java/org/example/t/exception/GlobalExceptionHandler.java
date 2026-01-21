package org.example.t.exception;


import lombok.extern.slf4j.Slf4j;
import org.example.t.pojo.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e) {
        log.error("服务器发生异常：",e);
        return Result.error("服务器发生异常");
    }
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){

        log.error("数据库发生重复键异常：",e);
        String message=e.getMessage();
        int i=message.lastIndexOf("Duplicate entry");
        String msg=message.substring(i);
        String[] arr= msg.split(" ");
        return Result.error(arr[2]+"已存在");
    }
    @ExceptionHandler({BusinessException.class})
    public Map<String, Object> handleBusinessException(BusinessException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", ex.getMessage());
        return result;
    }
    /**
     * 处理数据完整性违规异常
     * 当用户输入的违纪分数总和或违纪次数超过255时触发
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("程序出错啦~", e);
        return Result.error("违纪次数最大为255！总扣分最大为255！");
    }

    /**
     * 处理数字格式异常
     * 当用户输入过大的违纪分数导致无法转化为Integer类型时触发
     */
    @ExceptionHandler(NumberFormatException.class)
    public Result handleNumberFormatException(NumberFormatException e) {
        log.error("程序出错啦~", e);
        return Result.error("总扣分最大为255！");
    }

}
