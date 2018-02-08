package com.wisemk.web.spg.share;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import cn.zyy.oss.share.OssFunc;

@RestControllerAdvice()
public class WRestExceptionHandler
{
    private static final Logger log = LoggerFactory.getLogger(WRestExceptionHandler.class);

    @Autowired
    private MessageSource       messageSource;

    @ExceptionHandler(value = { Exception.class, RuntimeException.class })
    public ResponseEntity<WJsonResult> handleGenericException(Exception e, WebRequest request)
    {
        String exceptionInfo = OssFunc.getExceptionInfo(e);
        log.error(exceptionInfo);

        return new ResponseEntity<>(new WJsonResult(HttpStatus.BAD_REQUEST, exceptionInfo), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { WSrvException.class })
    public WJsonResult handlerSrvException(HttpServletRequest req, WSrvException e)
    {
        String exceptionInfo = OssFunc.getExceptionInfo(e);
        log.error(exceptionInfo);

        log.error("\nand request is: \n" + req.toString());

        WJsonResult jsonResult = new WJsonResult();
        jsonResult.setCode(HttpStatus.BAD_REQUEST);
        jsonResult.setMessage(exceptionInfo);
        return jsonResult;
    }
}
