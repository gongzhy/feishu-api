package com.gongzhy.feishu.api.controller.request;

import com.gongzhy.feishu.api.core.exception.ServerException;

/**
 * @author gongzhiyuan
 * @date 2023/3/9
 */
public interface IRequest {

    void checkParams() throws ServerException;
}
