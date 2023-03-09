package com.gongzhy.feishu.api.core.mvc;

import com.gongzhy.feishu.api.core.exception.ServerException;
import com.gongzhy.feishu.api.core.result.ResultBean;
import com.gongzhy.feishu.api.core.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * author yuzhoujie
 *
 * @date 2020/11/9 14:27
 */
@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 全局异常处理
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultBean handleException(Exception e) {
        if (e instanceof ServerException) {
            ServerException se = (ServerException) e;
            log.error("server exception ... - {} - {}", se.getErrCode(), se.getErrMsg());
            return ResultBean.failure((ServerException) e);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException se = (HttpRequestMethodNotSupportedException) e;
            String message = String.format("此接口只支持 %s, 您的请求方式为：[%s]", Arrays.toString(se.getSupportedMethods()), se.getMethod());
            log.error("HttpRequestMethodNotSupported exception ... - {}", message);
            return ResultBean.failure(ResultCode.API_METHOD_ERROR.getCode(), message);
        } else if (e instanceof HttpMessageNotReadableException) {
            log.error("param error ...", e);
            return ResultBean.failure(ResultCode.SYSTEM_ERROR.getCode(), "参数格式错误,请检查参数");
        }

        log.error("system error ...", e);
        return ResultBean.failure(ResultCode.SYSTEM_ERROR);
    }
}
