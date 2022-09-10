package com.demo.humorsource.handler;

import com.demo.humorsource.data.ResultData;
import com.demo.humorsource.enums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultData<String> handleException(Exception e) {
        log.error("Exception", e);
        return ResultData.error(StatusCode.Error.getCode(), StatusCode.Error.getMessage());
    }
}
