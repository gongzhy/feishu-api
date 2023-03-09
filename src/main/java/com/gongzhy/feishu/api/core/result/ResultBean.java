package com.gongzhy.feishu.api.core.result;

import com.gongzhy.feishu.api.core.exception.ServerException;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ResultBean<T> {
    /**
     * 业务错误码
     */
    private int code;
    /**
     * 信息描述
     */
    private String message;
    /**
     * 返回参数
     */
    private T data;

    private ResultBean(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    private ResultBean(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    private ResultBean(ResultCode resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    private ResultBean(ResultCode resultCode, String message) {
        this.code = resultCode.getCode();
        if (message != null && message.length() > 0) {
            this.message = message;
        } else {
            this.message = resultCode.getMessage();
        }
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static ResultBean<Void> success() {
        return new ResultBean<Void>(ResultCode.SUCCESS);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> ResultBean<T> success(T data) {
        return new ResultBean<T>(ResultCode.SUCCESS, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> ResultBean<T> success(ResultCode resultStatus, T data) {
        if (resultStatus == null) {
            return success(data);
        }
        return new ResultBean<T>(resultStatus, data);
    }

    /**
     * 业务异常返回业务代码和描述信息
     */
    public static <T> ResultBean<T> failure() {
        return new ResultBean<T>(ResultCode.INTERNAL_SERVER_ERROR);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResultBean<T> failure(ResultCode resultStatus) {
        return failure(resultStatus, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResultBean<T> failure(ServerException e) {
        return failure(e.getErrCode(), e.getMessage());
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResultBean<T> failure(int errCode, String errMsg) {
        return new ResultBean<T>(errCode, errMsg);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> ResultBean<T> failure(ResultCode resultStatus, T data) {
        if (resultStatus == null) {
            return new ResultBean<T>(ResultCode.INTERNAL_SERVER_ERROR, data);
        }
        return new ResultBean<T>(resultStatus, data);
    }


}