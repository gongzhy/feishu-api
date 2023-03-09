package com.gongzhy.feishu.api.controller.request.impl;

import com.gongzhy.feishu.api.controller.request.IRequest;
import com.gongzhy.feishu.api.core.exception.ServerException;
import com.gongzhy.feishu.api.core.result.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author gongzhiyuan
 * @date 2023/3/9
 */
@Data
@NoArgsConstructor
public class AuthRequest implements IRequest {
    private String appId;
    private String code;

    @Override
    public void checkParams() throws ServerException {
        if (StringUtils.isBlank(appId)) {
            throw new ServerException(ResultCode.PARAM_ERROR, "appId is null");
        }
        if (StringUtils.isBlank(code)) {
            throw new ServerException(ResultCode.PARAM_ERROR, "code is null");
        }
    }
}
