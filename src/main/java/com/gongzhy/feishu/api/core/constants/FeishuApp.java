package com.gongzhy.feishu.api.core.constants;

import com.gongzhy.feishu.api.core.exception.ServerException;
import com.gongzhy.feishu.api.core.result.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @auth Ryan
 * @date 2021/12/26 10:26
 */
@Getter
@AllArgsConstructor
public enum FeishuApp {

    MARCH("cli_a482c3e40ffbd00d", "5U3ZfeKKL9hqYeTlJDyCCdmGgVBUtkoO");

    private String appId;
    private String appSecret;

    private static Map<String, FeishuApp> appMap;

    static {
        appMap = new HashMap<>();
        FeishuApp[] feishuApps = FeishuApp.values();
        for (FeishuApp feishuApp : feishuApps) {
            appMap.put(feishuApp.appId, feishuApp);
        }
    }

    /**
     * @param appId
     * @return
     */
    public static FeishuApp get(String appId) throws ServerException {
        if (appMap.containsKey(appId)) {
            return appMap.get(appId);
        }
        throw new ServerException(ResultCode.FEISHU_FAIL, "[飞书AppID未配置]");
    }

}
