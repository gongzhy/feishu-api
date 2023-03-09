package com.gongzhy.feishu.api.core.exception;

import com.gongzhy.feishu.api.core.result.ResultCode;
import lombok.Getter;

@Getter
public class ServerException extends Exception {
    /**
     * 业务异常信息信息
     */
    private int errCode;
    private String errMsg;

    /**
     * @param resultCode
     */
    public ServerException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMessage());
    }

    /**
     * @param resultCode
     * @param errMsg
     */
    public ServerException(ResultCode resultCode, String errMsg) {
        this(resultCode.getCode(), errMsg);
    }

    /**
     * @param errCode
     * @param errMsg
     */
    public ServerException(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
