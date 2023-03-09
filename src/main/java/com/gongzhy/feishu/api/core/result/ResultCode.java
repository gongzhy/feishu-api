package com.gongzhy.feishu.api.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    // 请求成功
    SUCCESS(200, "OK"),
    // 还未申请中台业务,
    SYSTEM_ERROR(300, "system error!"),
    // 参数异常
    PARAM_ERROR(301, "param error"),
    //
    SIGN_ERROR(302, "sign error"),
    // 错误的请求
    API_NOT_EXIST(303, "api not exist!"),
    // 服务器异常,
    INTERNAL_SERVER_ERROR(304, "Internal Server Error"),

    API_METHOD_ERROR(305, "api method error!"),

    FEISHU_FAIL(306, "feishu fail!");

    /**
     * 业务异常码
     */
    private int code;
    /**
     * 业务异常信息描述
     */
    private String message;
}
