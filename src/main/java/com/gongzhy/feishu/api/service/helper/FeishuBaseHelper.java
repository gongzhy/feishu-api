package com.gongzhy.feishu.api.service.helper;

import com.gongzhy.feishu.api.core.constants.FeishuApp;
import com.lark.oapi.Client;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 飞书信息处理
 *
 * @author gongzhiyuan
 * @time 2020年2月16日
 */
@Slf4j
@Getter
public class FeishuBaseHelper {

    protected static Client client;

    static {
        //
        FeishuApp app = FeishuApp.MARCH;
        client = Client.newBuilder(app.getAppId(), app.getAppSecret())
                .requestTimeout(5, TimeUnit.SECONDS)
                .logReqAtDebug(true)
                .build();
    }

}
